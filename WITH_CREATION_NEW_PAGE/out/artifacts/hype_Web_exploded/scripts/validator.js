
let x, y, r = '';

function pressX(id){
    x = document.getElementById(id).value;
}

function pressR(id){
    r = document.getElementById(id).value;
    for(let i=1 ;i<=5; i++) if (id !== i+'c') document.getElementById(i.toString()+'c').checked = false;
    console.log(r);
}

function reset(){
    for (let i=-3 ;i<=5; i++) document.getElementById(i.toString()).checked = false;
    document.getElementById('R').value = '';
    document.getElementById('Y').value = '';
}

function check(){

    let fail = false;
    let choose = false;

    for(let i=0 ;i<=8; i++) {
        if (document.getElementById(i.toString()).checked) {
            choose = true;
            break;
        }
    }
    if (!choose) fail = 'Выберите X \n';

    y = document.getElementById('Y').value.trim();
    if (y === ''){
        if(!fail) fail = 'Введите Y \n'; else fail += 'Введите Y \n';
        choose = false;
    }else {
        if (!/^(-?\d+)([,.]\d+)?$/.test(y)) {
            if(!fail) fail = 'Некорректный ввод Y \n'; else fail += 'Некорректный ввод Y \n';
            choose = false;
        } else if (y <= -3 || y >= 5) {
            if(!fail) fail = 'Y вне диапозона \n'; else fail +='Y вне диапозона \n';
            choose = false;
        }
    }

    for(let i=1 ;i<=5; i++) {
        if (document.getElementById(i.toString()+'c').checked) {
            choose = true;
            break;
        }
    }
    if (!choose) fail = 'Выберите R \n';

    if (fail){
        alert(fail);
        return false;
    } return choose;
}

function checkR() {
    let choose = '';
    for(let i=1 ;i<=5; i++) {
        if (document.getElementById(i.toString()+'c').checked) {
            choose = true;
            break;
        }
    }
    if (!choose) { alert('Выберите R \n');}
    return choose;
}

function show_coords(event) {
    if (checkR()) {
        let rect = event.currentTarget.getBoundingClientRect();
        let x = event.clientX - rect.left;
        let y = event.clientY - rect.top;
        let basis = 100 / r;
        let cx = ((x - 150) / basis);
        let cy = ((150 - y) / basis);
        // console.log("X coords: " + x + ", Y coords: " + y + ",R: " + basis);
        // console.log("X coords: " + cx + ", Y coords: " + cy);
        sendRequest(cx, cy, r, "canvas");

    }
}

function point_draw(x_value, y_value, r, flag) {
    let canvas = document.getElementById('area');
    let context = canvas.getContext('2d');
    let x = x_value * 100 / r + 150;
    let y = 150 - y_value * 100 / r;
    if (flag) context.fillStyle = "green";
    if (!flag) context.fillStyle = "red";
    context.beginPath();
    context.arc(x, y, 2, 0, Math.PI * 2, false);
    context.fill();
    context.stroke();
}

function sendRequest(x, y, r, flag){
    location.href = "/hype_Web_exploded/controller?coordinateX=" + x + "&coordinateY=" + y + "&radius=" + r + "&flag=" + flag;
}

function trySendRequest() {
    if(check() && checkR()){
        sendRequest(x,y,r, "button");
    }
}