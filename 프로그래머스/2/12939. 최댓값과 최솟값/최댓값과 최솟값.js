function solution(s) {
  const numbers = s.split(" ");
  let min = parseInt(numbers[0]);
  let max = parseInt(numbers[0]);

  for (let i = 1; i < numbers.length; i++) {
    min = Math.min(min, parseInt(numbers[i]));
    max = Math.max(max, parseInt(numbers[i]));
  }

  return min + " " + max;
}
