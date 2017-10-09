//Qui vanno tutte le funzioni javascript che 
//implementiam ed usiamo noi!!!!
var LOG = undefined;
$(function() {
	
	recuperaDati();
	
	
	function recuperaDati(){
		if($.cookie("api_key")!= null && $.cookie("api_key")!=""){
			
			var url = "RecuperoDati";
			var paramJSON = {'api_key' : $.cookie("api_key")};
			var dataType="application/json";
			var headerJSON = {};
			
			function risposta(data){
				console.log(data.responseText);
				var result = JSON.parse(data.responseText);
				if(result.error == "true"){
//					$('.modalRegisterError').show();
//					$('.modalRegisterSuccess').hide();
//					$('.modalRegisterError').html("ERRORE: " + result.message);
					alert('Si è verificato un errore');
					console.log(result);
				}else{
//					$.cookie("api_key", result.api_key, { expires: 7 });
//					$('.modalRegisterSuccess').show();
//					$('.modalRegisterError').hide();
//					$('.modalRegisterSuccess').html(result.message);
//					console.log(result);
//					window.location.href = 'dashboard.jsp';
					console.log(result);
					var user = result.user;
					$('.nameSurnameDashboard').html(user.nome + ' ' + user.cognome);
					$('.codFiscEmailDashboard').html(user.codicefiscale + ' - ' + user.email);
					$('.table_accounts').empty();
					var accounts = result.accounts;
					LOG=result;
					if(accounts != null && accounts.length > 0){
						
						for(var i = 0 ; i< accounts.length; i++)
							$('.table_accounts').append('<tr><th scope="row">'+(i+1)+'</th><td>'+accounts[i].id+'</td><td>'+accounts[i].balance+'</td></tr>');
					
					}else{
						$('.table_accounts').append('<tr><th scope="row"></th><td>NON SONO PRESENTI CONTI</td><td></td></tr>');
					}
				}
			}
			
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
			
			$('.containerDashboard').show();
		}else
			window.location.href = 'index.jsp';
	}
});