let c = document.getElementById('name');
let p = document.getElementById('produto');
let s = document.getElementById('sabor');
let v = document.getElementById('valor');

let form = document.querySelector("form");

let result = document.getElementById('result');

alert()
form.onsubmit = (event) => {
  event.preventDefault();
 Chamada.envia(c.value, p.value,s.value, v.value)
 // location.href = './mostrar.html'
};

function consulta(){
 result.value =  Chamada.getResult()
}