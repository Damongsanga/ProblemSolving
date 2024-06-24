function solution(n) {
    const MOD = 1_000_000_007;
    let init = [1,3,10];
    if (n <= 3) return init[n-1];

    let pure = [1,2,5];
    let pureRepeat = [4,2,2]; // dp6부터 3의배수마다 4, 나머지는 2
    let cache = [8,0,2]; // dp4에서 0, dp5에서 2, dp6에서 8임

    for (let i = 4; i <= n; i++) {
        let r = i % 3;
        let [prev3, prev2, prev1] = init;
        let now = prev3 * pure[2] % MOD + prev2 * pure[1] % MOD + prev1 * pure[0] % MOD + pureRepeat[r];
        
        now += cache[r];
        cache[r] += prev3 * pureRepeat[0] + prev2 * pureRepeat[1] + prev1 * pureRepeat[2];
        cache[r] %= MOD;
        init = [prev2 , prev1 , now % MOD];
    }

    return init[2];
}