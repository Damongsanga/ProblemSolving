import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public static class Homework {
        int starttime;
        int time;
        String name;

        public Homework(String name, int strattime, int time) {
            this.name = name;
            this.starttime = strattime;
            this.time = time;
        }
    }

    public static String[] solution(String[][] plans) {

        // 우선순위 큐로 정렬
        PriorityQueue<Homework> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.starttime, o2.starttime));
        PriorityQueue<Homework> pq2 = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.starttime, o1.starttime));
        String[] answer = new String[plans.length];
        Homework tmp;

        // 초기화 및 입력
        for (int i = 0; i < plans.length; i++) {
            pq.add(new Homework(plans[i][0], changetime(plans[i][1]), Integer.parseInt(plans[i][2])));
        }

        // 현재 시간
        int nowtime = pq.peek().starttime;
        int count = 0;

        // 반복문으로 돌아올 때 늘 1. 현재시간을 설정해주고 2. answer 배열에 추가할 수 있는지 or 스택으로 돌릴지 판단 3. 이를 반영하여 갱신
        while (!pq.isEmpty() || !pq2.isEmpty()) {
            // 우선순위 큐가 남아있으면 꺼냄
            if (!pq.isEmpty()) {
                Homework hw = pq.poll();

                // 꺼내고도 더 새로 시작할 과제 있으면 시간내 끝낼 수 있는지 판단
                if (!pq.isEmpty()) {
                    // 시간내 끝낼 수 있으면 남은 시간에 안끝난 과제 해결
                    if (pq.peek().starttime >= nowtime + hw.time) {
                        nowtime += hw.time;
                        answer[count++] = hw.name;

                        while (!pq2.isEmpty()) {
                            Homework pq2hw = pq2.poll();
                            // pq2에 있는 과제가 다 끝낼 수 있으면 현재 시간 갱신 & 카운트
                            if (pq.peek().starttime >= nowtime + pq2hw.time) {
                                answer[count++] = pq2hw.name;
                                nowtime += pq2hw.time;
                            }
                            // 다 끝내지 못하면 현재 시간 갱신 & 작업시간 & 시작시간 갱신 후 다시 pq에 넣음
                            else {
                                pq2.add(new Homework(pq2hw.name, pq.peek().starttime, pq2hw.time - (pq.peek().starttime - nowtime)));
                                nowtime = pq.peek().starttime;
                                break;
                            }
                        }
                        nowtime = pq.peek().starttime;

                    }
                    // 시간 내 끝낼 수 없으면 하는데 까지 하고 pq2에 넣음
                    else {
                        pq2.add(new Homework(hw.name, pq.peek().starttime, hw.time - (pq.peek().starttime - nowtime)));
                        nowtime = pq.peek().starttime;
                        continue;
                    }
                }
                // 꺼내도 더 새로 시작해야 될 과제가 없으면 (우선순위 큐가 비어있으면) 지금 하는 과제 끝내고 pq2만 순서대로 처리하면 끝
                else {
                    answer[count++] = hw.name;
                    nowtime += hw.time;
                    continue;
                }
                // 우선순위 큐가 비어있다면 pq2의 데이터를 처리
            } else {
                tmp = pq2.poll();
                answer[count++] = tmp.name;
                nowtime += tmp.time;
            }
        }

        return answer;


    }

    public static int changetime(String st) {
        return Integer.parseInt(st.split(":")[0]) * 60 + Integer.parseInt(st.split(":")[1]);
    }



}