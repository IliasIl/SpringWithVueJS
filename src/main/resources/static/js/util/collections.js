export function getIndex(list, el) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === el) {
            return i
        }
    }
    return -1
}