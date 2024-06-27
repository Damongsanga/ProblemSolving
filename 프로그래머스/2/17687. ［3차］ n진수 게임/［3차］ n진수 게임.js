function solution(n, t, m, p) {
  let count = 0;
  let decimal = 0;
  let currentIdx = 0;
  let prefixSumIdex = 0;
  let myIdx = p;
  let answer = "";

  while (count < t) {
    let baseNString = decimal.toString(n);
    while (currentIdx < myIdx) {
      baseNString = decimal.toString(n);
      prefixSumIdex = currentIdx;
      currentIdx += baseNString.length;
      decimal++;
    }

    while (myIdx <= currentIdx && count < t) {
      answer += baseNString
        .charAt(myIdx - prefixSumIdex - 1)
        .toUpperCase()
        .toString();
      myIdx += m;
      count++;
    }
  }

  return answer;
}
