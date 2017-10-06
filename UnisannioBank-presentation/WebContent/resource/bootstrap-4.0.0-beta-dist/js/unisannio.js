//Qui vanno tutte le funzioni javascript che 
//implementiam ed usiamo noi!!!!
$(function() {
	//Verifico che l'utente è loggato
	isLogged();
	
	//GESTIONE EVENTI
	$(".btn-login").click(showlogin);
	$(".btn-logout").click(logout);
//	$(".btn-modalLogin").click(login);
	
	
	
	//FUNZIONI
	function showlogin(){
		$('#login-modal').modal('show');
	};
	
	function logout(){
		$.cookie("api_key", null, { expires: -1 });
		$.cookie("name", null, { expires: -1 });
		$.cookie("email", null, { expires: -1 });
		$( ".btn-logout" ).hide();
		$( ".btn-login" ).show();
		window.location.href = 'https://www.google.it';
	};

	function isLogged(){
		if($.cookie("api_key")!= null && $.cookie("api_key")!="")
		{	$( ".btn-logout" ).show();
			$( ".btn-login" ).hide();
		}else{
			$( ".btn-logout" ).hide();
			$( ".btn-login" ).show();
		}
	};
	
	
});