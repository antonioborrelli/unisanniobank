//Qui vanno tutte le funzioni javascript che 
//implementiam ed usiamo noi!!!!
var LOG = undefined;
$(function() {
	
	recuperaDati();
	//GESTIONE EVENTI
	$(".btn-createAccount").click(showModalNewAccount);
	$(".btn-modalnewaccount").click(creaConto);
	$(".btn-modalnewaccount-close").click(hideModalNewAccount);
	$('.table_accounts tr').click(clickTr);
	$('.btn-modaloperations').click(chooseOperation);
	
	
	//FUNZIONI
	function deposit(){
		//LUIGI!!!
		alert('hai premuto depositoCheck');
	};
	function witdraw(){
		//BENEDETTA
		alert('hai premuto prelievoCheck');
	};
	function transfer(){
		//LUCIANO
		alert('hai premuto trasferimentoCheck');
	};
	
	function chooseOperation(){
		if($("#depositoCheck").is(':checked'))
			deposit();
		else if($("#prelievoCheck").is(':checked'))
			 witdraw();
		else if($("#trasferimentoCheck").is(':checked'))
			transfer();
	};
	function clickTr(){
		var tr = this;
		var td = tr.children[1];
		var numeroConto = $(td).html();
		var td = tr.children[2];
		var saldoConto = $(td).html();
		LOG = td;
		$('.idAccountOperations').val(numeroConto);
		$('.balanceOperations').val(saldoConto);
		$(".modaloperationsError").hide();
		$(".modaloperationsSuccess").hide();
		$(".modaloperationsError").val();
		$(".modaloperationsSuccess").val();
		$('#operations-modal').modal('show');
		
	};
	function hideModalNewAccount(){
		$('#newaccount-modal').modal('hide');
		$(".modalNewAccountError").hide();
		$(".modalNewAccountSuccess").hide();
		$('.ballanceNewAccount').val('');
		$(".btn-modalnewaccount").show();
		$(".btn-modalnewaccount-close").hide();
	};
	function showModalNewAccount(){
		$('#newaccount-modal').modal('show');
		$(".modalNewAccountError").hide();
		$(".modalNewAccountSuccess").hide();
		$('.ballanceNewAccount').val('');
	};
	
	function creaConto(){
		var amount = $('.ballanceNewAccount').val();
		if(amount >= 0){
			$(".modalNewAccountError").hide();
			$(".modalNewAccountError").empty();
			var url = "NuovoConto";
			var paramJSON = {'amount':amount,'api_key' : $.cookie("api_key")};
			var dataType="application/json";
			var headerJSON = {};
			function risposta(data){

				var result = JSON.parse(data.responseText);
				if(result.error == "true"){
					$(".modalNewAccountError").show();
					$(".modalNewAccountError").empty();
					$(".modalNewAccountError").html('ERRORE: ' + result.message);
				}else{
					$(".modalNewAccountSuccess").show();
					$(".modalNewAccountSuccess").empty();
					$(".modalNewAccountSuccess").html(result.message);
					var account = result.account;
					var totAccounts = parseInt($('.totAccounts').val())+1;
					$('.totAccounts').val(totAccounts);
					var balance = new Number(account.balance);
					$('.table_accounts').append('<tr><th scope="row">'+totAccounts+'</th><td>'+account.id+'</td><td>'+balance+'</td></tr>');
					$(".btn-modalnewaccount").hide();
					$(".btn-modalnewaccount-close").show();
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
		}else{
			$(".modalNewAccountError").show();
			$(".modalNewAccountError").empty();
			$(".modalNewAccountError").html('ERRORE: Inserire un saldo positivo!');
		}
	}
	
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
					alert('Si è verificato un errore');
				}else{
					var user = result.user;
					$('.nameSurnameDashboard').html(user.nome + ' ' + user.cognome);
					$('.codFiscEmailDashboard').html(user.codicefiscale + ' - ' + user.email);
					$('.table_accounts').empty();
					var accounts = result.accounts;

					if(accounts != null && accounts.length > 0){
						for(var i = 0 ; i< accounts.length; i++){
							var balance = new Number(accounts[i].balance);
							$('.table_accounts').append('<tr><th scope="row">'+(i+1)+'</th><td>'+accounts[i].id+'</td><td>'+balance+'</td></tr>');
						}
					$('.totAccounts').val(accounts.length);
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