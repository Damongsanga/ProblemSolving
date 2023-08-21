from collections import deque
def solution(k, dungeons):
    L, n, dp = len(dungeons), 0, deque()
    dp.append([k,[]])
    while dp :
        p,v = dp.popleft()
        for i in range(L):
            [a,b] = dungeons[i]
            if i not in v and p>=a and p-b >= 1 : dp.append([p-b,v+[i]])
            else : n = max(n, len(v))
    return n
   