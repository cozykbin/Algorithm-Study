const { Client } = require('@notionhq/client');

const notion = new Client({ auth: process.env.NOTION_TOKEN });
const studyScheduleDbId = process.env.STUDY_SCHEDULE_DB_ID;
const memberDbId = process.env.MEMBER_DB_ID;

async function addToNotion() {
  try {
    const prTitle = process.env.PR_TITLE;
    const author = process.env.PR_AUTHOR;
    const prUrl = process.env.PR_URL;
    const mergedAt = process.env.MERGED_AT;
    
    console.log(`🔍 PR 정보: ${prTitle} by ${author}`);
    
    // PR 제목 파싱: [cozykbin] 11월 18일 문제풀이
    const match = prTitle.match(/\[(.+?)\]\s*(\d+)월\s*(\d+)일\s*문제풀이/);
    
    if (!match) {
      console.log('⚠️ PR 제목 형식이 맞지 않습니다. [아이디] O월 O일 문제풀이 형식을 사용해주세요.');
      return;
    }
    
    const [, memberId, month, day] = match;
    const date = new Date(mergedAt);
    const dateStr = date.toISOString().split('T')[0];
    
    console.log(`📅 파싱된 정보: ${month}월 ${day}일, 멤버: ${memberId}`);
    
    // 1. Member DB에서 GitHub ID로 멤버 찾기
    const memberResponse = await notion.databases.query({
      database_id: memberDbId,
      filter: {
        property: 'GitHub ID',
        rich_text: {
          equals: author
        }
      }
    });
    
    let memberPageId = null;
    let memberName = memberId;
    
    if (memberResponse.results.length > 0) {
      memberPageId = memberResponse.results[0].id;
      memberName = memberResponse.results[0].properties['이름'].title[0]?.plain_text || memberId;
      console.log(`✅ 멤버 찾음: ${memberName}`);
      
      // 멤버의 "이번 주 커밋" 업데이트
      const currentCommits = memberResponse.results[0].properties['이번 주 커밋']?.number || 0;
      await notion.pages.update({
        page_id: memberPageId,
        properties: {
          '이번 주 커밋': {
            number: currentCommits + 1
          },
          '오늘 풀이': {
            checkbox: true
          }
        }
      });
      console.log(`📊 ${memberName}의 이번 주 커밋: ${currentCommits} → ${currentCommits + 1}`);
    } else {
      console.log(`⚠️ Member DB에서 GitHub ID "${author}"를 찾을 수 없습니다.`);
    }
    
    // 2. Study Schedule DB에 오늘 날짜의 문제 찾기
    const scheduleResponse = await notion.databases.query({
      database_id: studyScheduleDbId,
      filter: {
        property: '날짜',
        date: {
          equals: dateStr
        }
      }
    });
    
    console.log(`📚 오늘(${dateStr}) 문제: ${scheduleResponse.results.length}개 발견`);
    
    // 3. 오늘 문제에 멤버 추가
    for (const problem of scheduleResponse.results) {
      const problemTitle = problem.properties['문제'].title[0]?.plain_text || '제목 없음';
      
      if (memberPageId) {
        // 기존 해결한 멤버 목록에 추가
        const existingMembers = problem.properties['해결한 멤버']?.relation || [];
        const memberIds = existingMembers.map(m => m.id);
        
        if (!memberIds.includes(memberPageId)) {
          await notion.pages.update({
            page_id: problem.id,
            properties: {
              '해결한 멤버': {
                relation: [...existingMembers, { id: memberPageId }]
              }
            }
          });
          console.log(`✅ "${problemTitle}"에 ${memberName} 추가됨`);
        } else {
          console.log(`ℹ️ "${problemTitle}"에 ${memberName}이(가) 이미 등록되어 있습니다.`);
        }
      }
    }
    
    console.log('');
    console.log('🎉 Notion 동기화 완료!');
    console.log(`📌 PR: ${prUrl}`);
    
  } catch (error) {
    console.error('❌ 오류 발생:', error.message);
    if (error.body) {
      console.error('상세:', JSON.stringify(error.body, null, 2));
    }
    process.exit(1);
  }
}

addToNotion();
