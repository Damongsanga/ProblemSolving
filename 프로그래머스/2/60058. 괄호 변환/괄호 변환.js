function solution(p) {
  function check(str){
    let cnt = 0;
    for (let i = 0; i < str.length; i++) {
      if (str[i] === "(") cnt++;
      else cnt--;
      
      if (cnt < 0) return false;
    }
    return true;
  }

  function dfs(p){
    if (check(p)) return p;
    let u = "";
    let v = "";
    let cnt = 0;
    for (let i = 0; i < p.length; i++) {
      if (p[i] === "(") cnt++;
      else if (p[i] === ")") cnt--;

      if (cnt == 0){
        u = p.substring(0, i+1);
        v = p.substring(i+1);
        break;
      }
    }

    if (check(u)) return u + dfs(v);
    return "(" + dfs(v) + ")" + Array.from(u).slice(1, u.length-1).map(a => a === "(" ? ")" : "(").join("");
  }

  if (p === "" || check(p)) return p;
  return dfs(p);
}
