function checkout() {
	if ( isLoggedIn() ) {
		generateOrder()
	} else {
		window.alert('Favor fazer o login antes!');
  		window.open('login-cadastro.html');
	}
};

function isLoggedIn() {
	// checar se o cliente está logado
	return true;
};

function generateOrder() {
	// salvar pedido no banco
	window.alert('Pedido enviado para produção!');
  	window.open('index.html');
};