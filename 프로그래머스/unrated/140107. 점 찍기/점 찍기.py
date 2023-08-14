def solution(k, d):
    answer = 0
    for a in range(d//k+1):
            answer += int(((d**2 - (a*k)**2)**0.5)/k)+1
    return answer