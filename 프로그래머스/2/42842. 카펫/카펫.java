class Solution {
    public int[] solution(int brown, int yellow) {
        
        for (int i = 1; i <= (int) Math.sqrt(yellow); i++){
            if (yellow % i != 0) continue;
            int a = yellow / i;
            int b = i;
            
            if (brown == 2 * (a + b) + 4) {
                return new int[] {a + 2, b + 2};
            }
        }
        
        return new int[] {};
        
    }
}