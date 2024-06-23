function solution(name, yearning, photo) {
    return photo.map((v) => v.reduce((a,c) => a += yearning[name.indexOf(c)] ?? 0, 0))
}