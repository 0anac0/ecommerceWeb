document.addEventListener("DOMContentLoaded", function() {
    var elements = document.getElementsByTagName("INPUT");
    for (var i = 0; i < elements.length; i++) {
        elements[i].oninvalid = function(e) {
            e.target.setCustomValidity("");
            if (!e.target.validity.valid) {
                e.target.setCustomValidity("Por favor preecha o campo corretamente!");
            }
        };
        elements[i].oninput = function(e) {
            e.target.setCustomValidity("");
        };
    }
})

const loginForm = document.getElementById('containerLogin');
loginForm.addEventListener('submit', login);

function login() {
  const userValid = true;
  if (userValid) {
  	window.open('user.html');
  }
};

const signUpForm = document.getElementById('containerCadastro');
loginForm.addEventListener('submit', login);

function signUp() {
  	window.open('login-cadastro.html');
};