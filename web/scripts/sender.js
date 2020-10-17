function sendRequest(x,y,r,flag) {
    $.ajax({
        method: "GET",
        url: "/try_war_exploded/controller?coordinateX=" + x + "&coordinateY="
            + y + "&radius=" + r + "&flag=" + flag,
    }).done(function (cell) {
        console.log(toTable(cell));
        $(".table-data tbody").append(toTable(cell));
    });
}

function toTable(cell) {
    let x =  toDouble(cell.x);
    let y = toDouble(cell.y);
    let r =  toDouble(cell.r);
    let date = new Date(cell.date);
    let time = toTime(date.getHours()) + ":" + toTime(date.getMinutes()) + ":" + toTime(date.getSeconds());
    let res = cell.res;
    toDouble(x);
    point_draw(x,y,r,res);
    return "<tr><td>" + x + "</td><td>" + y + "</td><td>" + r + "</td><td>" + time + "</td><td>" + res + "</td></tr>";
}

function toDouble(num) {
    return (num - Math.trunc(num)) === 0? (num + ".0") : num;
}
function toTime(time) {
    return (time <= 9)? ("0" + time) : time;
}