var named="";
var senha = "";
function bthover (){
    document.getElementById('entrar').style.backgroundColor='#D05700';
}

function cadastro(){
    document.getElementById('containerLogin').style.display="none";
    document.getElementById('containerCadastro').style.display="flex";
    document.getElementById('containerCadastro').style.flexDirection="column";
    document.getElementById('containerCadastro').style.alignItems="center";
    
}


function alerta(){
     document.getElementById('alert').style.display="none";
}