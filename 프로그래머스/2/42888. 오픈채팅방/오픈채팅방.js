function solution(record) {
  var answer = [];
  const arr = new Array();
  const map = {};

  record.forEach((info) => {
    let infos = info.split(" ");
    let verb = infos[0];
    switch (verb) {
      case "Enter":
        arr.push([infos[1], true]);
        map[infos[1]] = infos[2];
        break;
      case "Leave":
        arr.push([infos[1], false]);
        break;
      case "Change":
        map[infos[1]] = infos[2];
    }
  });

  return arr.map(infos => infos[1] ? `${map[infos[0]]}님이 들어왔습니다.` : `${map[infos[0]]}님이 나갔습니다.` );
}
