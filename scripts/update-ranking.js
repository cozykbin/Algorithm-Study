// scripts/update-ranking.js
const { Client } = require('@notionhq/client');
const { execSync } = require('child_process');

const notion = new Client({ auth: process.env.NOTION_TOKEN });
const memberDbId = process.env.MEMBER_DB_ID;

async function updateRanking() {
  try {
    console.log('🔄 주간 커밋 랭킹 업데이트 시작...');
    
    // 지난 7일 기준
    const lastWeek = new Date();
    lastWeek.setDate(lastWeek.getDate() - 7);
    const since = lastWeek.toISOString().split('T')[0]; // YYYY-MM-DD
    
    console.log(`📅 집계 기간: ${since} ~ 오늘`);
    
    // Git 로그에서 커밋 수 집계 (머지 커밋 제외)
    let gitLog;
    try {
      gitLog = execSync(
        `git log --since="${since}" --pretty=format:"%an" --no-merges`,
        { encoding: 'utf-8' }
      );
    } catch (error) {
      console.log(
        '⚠️ Git 로그를 가져올 수 없습니다. Git 저장소에서 실행 중인지 확인하세요.'
      );
      return;
    }
    
    const commits = gitLog.split('\n').filter(Boolean);
    const countByAuthor = {};
    
    commits.forEach((author) => {
      countByAuthor[author] = (countByAuthor[author] || 0) + 1;
    });
    
    console.log('📊 커밋 통계:', countByAuthor);
    
    // 순위 정렬
    const ranking = Object.entries(countByAuthor)
      .sort(([, a], [, b]) => b - a)
      .map(([author, count], index) => ({
        rank: index + 1,
        author,
        count
      }));
    
    if (ranking.length === 0) {
      console.log('ℹ️ 이번 주 커밋이 없습니다.');
      return;
    }
    
    console.log('🏆 이번 주 랭킹:');
    ranking.forEach((entry) => {
      console.log(`  ${entry.rank}위: ${entry.author} (${entry.count}개)`);
    });
    
    // Member DB의 모든 멤버 가져오기 (페이지네이션 포함)
    let hasMore = true;
    let startCursor = undefined;
    const members = [];
    
    while (hasMore) {
      const response = await notion.databases.query({
        database_id: memberDbId,
        start_cursor: startCursor
      });
      
      members.push(...response.results);
      hasMore = response.has_more;
      startCursor = response.next_cursor;
    }
    
    // 각 멤버의 GitHub ID/이름과 Git author 매칭
    for (const member of members) {
      const nameProp = member.properties['이름'];
      const githubIdProp = member.properties['GitHub ID'];
      
      const memberName =
        nameProp?.title?.[0]?.plain_text?.trim() ?? '';
      const githubId =
        githubIdProp?.rich_text?.[0]?.plain_text?.trim() ?? '';
      
      if (!memberName && !githubId) continue;
      
      const commitEntry = ranking.find((r) => {
        const author = r.author;
        if (!author) return false;
        
        // Git author == GitHub ID (대소문자 무시)
        if (githubId && author.toLowerCase() === githubId.toLowerCase()) {
          return true;
        }
        // 또는 Git author == 이름 (정확히 일치)
        if (memberName && author === memberName) {
          return true;
        }
        return false;
      });
      
      const commitCount = commitEntry ? commitEntry.count : 0;
      
      await notion.pages.update({
        page_id: member.id,
        properties: {
          '이번 주 커밋': {
            number: commitCount
          },
          '오늘 풀이': {
            checkbox: false  // 주간 리셋
          }
        }
      });
      
      console.log(
        `✅ ${memberName || githubId}: ${commitCount}개 커밋`
      );
    }
    
    console.log('\n🎉 주간 커밋 랭킹 업데이트 완료!');
  } catch (error) {
    console.error('❌ 오류 발생:', error.message);
    if (error.body) {
      console.error('상세:', JSON.stringify(error.body, null, 2));
    }
    process.exit(1);
  }
}

updateRanking();
