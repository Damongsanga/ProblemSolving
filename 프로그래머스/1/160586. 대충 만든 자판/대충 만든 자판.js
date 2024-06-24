function solution(keymap, targets) {
  var answer = [];
  const map = new Map();

  for (let i = 0; i < keymap.length; i++) {
    for (let j = 0; j < keymap[i].length; j++) {
      let key = keymap[i][j];
      if (map.has(key)) {
        map.set(key, Math.min(map.get(key), j + 1));
      } else {
        map.set(key, j + 1);
      }
    }
  }

  for (let i = 0; i < targets.length; i++) {
    let count = 0;
    for (let j = 0; j < targets[i].length; j++) {
        const key = targets[i][j];
        if (map.has(key)) {
          count += map.get(key);
        } else {
          count = -1;
          break;
        }
      }
    answer.push(count);
  }

  return answer;
}
