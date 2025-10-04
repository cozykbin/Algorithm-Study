class Point:
    def __init__(self,x,y):
        self.x = x
        self.y = y

    # py에서는 lt(less than!!!)를 compare처럼 사용한다. 
    def __lt__(self, other):
        if self.x != other.x:
            return self.x < other.x
        return self.y < other.y

def main():
    tc = int(input())
    points = []
    for i in range(tc):
        x, y = map(int, input().split())
        points.append(Point(x, y))

    points.sort()

    for p in points:
        print(p.x, p.y)

main()