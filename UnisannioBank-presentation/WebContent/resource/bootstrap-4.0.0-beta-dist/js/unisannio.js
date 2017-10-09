//Qui vanno tutte le funzioni javascript che 
//implementiam ed usiamo noi!!!!

var LOG = undefined;

$(function() {
	//Verifico che l'utente è loggato
	isLogged();
	
	//GESTIONE EVENTI
	$(".btn-login").click(showlogin);
	$(".btn-logout").click(logout);
	$(".btn-modalLogin").click(login);
	$(".showRegister").click(showregister);
	$(".btn-modalRegister").click(register);

	
	//FUNZIONI
	function login(){
		var username = $('.userLogin').val();
		var password = $('.pwsLogin').val();
		
		var url = "Login";
		var paramJSON = {'username' : username, 'password' : password};
		var dataType="application/json";
		var headerJSON = {};
		function risposta(data){
			var result = JSON.parse(data.responseText);
			if(result.error == "true"){
				$('.modalLoginError').show();
				$('.modalLoginError').html("ERRORE: " + result.message);
			}else{
				$('.modalLoginError').hide();
				$.cookie("api_key", result.api_key, { expires: 7 });
				$( ".btn-logout" ).show();
				$( ".btn-login" ).hide();
				$('#login-modal').modal('hide');
				window.location.href = 'dashboard.jsp';
			}
		}
		//Richiamo la funzione ajax che invia i dati alla servlet
		$.ajax({
			  type: "POST",
			  url: url,
			  headers: headerJSON,
			  dataType: dataType,
			  data: paramJSON,
			  success: risposta,
			  error: risposta,
			  async: false
			});
		
	}
	function register(){
		
		var nome = $('.nameRegister').val();
		var cognome = $('.surnameRegister').val();
		var codicefiscale = $('.cfRegister').val();
		var email = $('.emailRegister').val();
		var password = $('.passwordRegister').val();
		var password2 = $('.replypasswordRegister').val();
		
		
		if( password == password2 ){
			
			var url = "CreateUser";
			var paramJSON = {'nome' : nome, 'cognome' : cognome, 'codicefiscale':codicefiscale, 'email':email, 'psw':password };
			var dataType="application/json";
			var headerJSON = {};
			
			function risposta(data){
				
				var result = JSON.parse(data.responseText);
				if(result.error == "true"){
					$('.modalRegisterError').show();
					$('.modalRegisterSuccess').hide();
					$('.modalRegisterError').html("ERRORE: " + result.message);
				}else{
					$.cookie("api_key", result.api_key, { expires: 7 });
					$('.modalRegisterSuccess').show();
					$('.modalRegisterError').hide();
					$('.modalRegisterSuccess').html(result.message);
					console.log(result);
					window.location.href = 'dashboard.jsp';
				}
			}
			
			
			//Richiamo la funzione ajax che invia i dati alla servlet
			$.ajax({
				  type: "POST",
				  url: url,
				  headers: headerJSON,
				  dataType: dataType,
				  data: paramJSON,
				  success: risposta,
				  error: risposta,
				  async: false
				  
				  
				});

		}else{
			$('.modalRegisterError').show();
			$('.modalRegisterSuccess').hide();
			$('.modalRegisterError').html("Le password non combaciano");
		}
		
		
		
	};
	function showregister(){
		$('#login-modal').modal('hide');
		$('#register-modal').modal('show');
	};
	
	function showlogin(){
		$('#login-modal').modal('show');
	};
	
	function logout(){
		$.cookie("api_key", null, { expires: -1 });
		$( ".btn-logout" ).hide();
		$( ".btn-login" ).show();
		window.location.href = 'index.jsp';
	};

	function isLogged(){
		if($.cookie("api_key")!= null && $.cookie("api_key")!=""){
			$( ".btn-logout" ).show();
			$( ".btn-login" ).hide();
			
		}else{
			$( ".btn-logout" ).hide();
			$( ".btn-login" ).show();
		}
	};
	
	
});