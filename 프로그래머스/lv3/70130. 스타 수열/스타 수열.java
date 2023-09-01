import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = -1;
        int N = a.length;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
        	if(map.containsKey(a[i]))
        		map.get(a[i]).add(i);
        	else {
        		ArrayList<Integer> list = new ArrayList<>();
        		list.add(i);
        		map.put(a[i], list);
        	}
        }
        
        int max = 0;
        for (int i : map.keySet()) {
        	if (map.get(i).size() < 1 || map.get(i).size() < max) continue;
        	ArrayList<Integer> list = map.get(i);
        	int selected = -1;
        	int count = 0;
        	for (int j = 0; j < list.size(); j++) {
        		// 교집합수 전 값이 이미 골라졌거나 역시 교집합수라면 전 수가 아니라 다음 수를 골라야 한다
        		if (list.get(j)-1 == selected || (j > 0 && list.get(j) -1 == list.get(j-1))) { // || (j > 0 && list.get(j) -1 == list.get(j-1))
        			
        			// 마지막 원소면 해당 원소가 배열의 마지막 값인지 확인, 아니면 가능
        			if (j == list.size()-1) {
        				if (list.get(j) == N-1) {
        					continue;
        				} else {
        					selected = list.get(j)+1; // 할 필요는 없긴 함
        				}
        			
        			// 교집합 수 다음 수도 교집합 수라면 고를 수가 없다
        			} else if (list.get(j)+1 == list.get(j+1)) {
        				continue;
        			} else {
        				selected = list.get(j)+1; 
        			}
        		}
        		else {
        			selected = list.get(j) - 1;
        		}
        		count++;
//        		System.out.println(j + " selected : " + selected);
        	}
        	
        	if (max < count) max = count;
        }
        
        return max * 2;
        
        
        
    }
}

