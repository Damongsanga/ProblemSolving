function solution(k, dungeons) {
  var answer = -1;

  const visited = Array(dungeons.length).fill(false);

  function dfs(currentHp, depth) {
    answer = Math.max(answer, depth);

    for (let i = 0; i < dungeons.length; i++) {
      const [minHp, useHp] = dungeons[i];
      if (visited[i]) continue;
      if (currentHp >= minHp) {
        visited[i] = true;
        dfs(currentHp - useHp, depth + 1);
        visited[i] = false;
      }
    }
  }

  dfs(k, 0);

  return answer;
}
