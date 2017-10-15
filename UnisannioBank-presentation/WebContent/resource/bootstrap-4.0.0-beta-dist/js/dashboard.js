//Qui vanno tutte le funzioni javascript che 
//implementiam ed usiamo noi!!!!

$(function() {
	
	recuperaDati();
	//GESTIONE EVENTI
	$(".btn-createAccount").click(creaConto);
//	$(".btn-modalnewaccount").click(creaConto); // rimuovere
//	$(".btn-modalnewaccount-close").click(hideModalNewAccount); //rimuovere
	$('.table_accounts tr').click(clickTr);
	$('.btn-modaloperations').click(chooseOperation);
	$("#depositoCheck").click(hideDestination);
	$("#prelievoCheck").click(hideDestination);
	$("#trasferimentoCheck").click(showDestination);

	function showDestination(){
		$('.inputDestinatario').show();
	};
	
	function hideDestination(){
		$('.inputDestinatario').hide();
	};
	
	//FUNZIONI
	function deposit(){
		
		$('.inputDestinatario').hide();
		$('.modaloperationsError').hide();
		$('.modaloperationsSuccess').hide();
		// LUIGI!!!
		var amount = parseFloat($('.amountOperations').val());
		var idaccount = $('.idAccountOperations').val();
		var balance = parseFloat($('.balanceOperations').val());
		if (balance != null && amount != "" && amount > 0) {

			var url = "Deposito";
			var paramJSON = {'idaccount':idaccount,'balance':balance,'amount':amount,'api_key' : $.cookie("api_key")};
			var dataType="application/json";
			var headerJSON = {};
			function risposta(data){
				console.log(data);
				var result = JSON.parse(data.responseText);
				if(result.error == "true"){
					$('.modaloperationsError').show();
					$('.modaloperationsError').html(result.message);
					$('.modaloperationsSuccess').hide();
					
				}else{
					$('.balanceOperations').val(result.account.balance);
					$('.accountId_'+result.account.id).html(result.account.balance);
					$('.modaloperationsSuccess').show();
					$('.modaloperationsError').hide();
					$('.modaloperationsSuccess').html(result.message);
					totalAmount();
				}
				
			};
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
			$('.modaloperationsError').show();
			$('.modaloperationsError').html('Errore: Riempire correttamente tutti i campi!');
			$('.modaloperationsSuccess').hide();
		}
	};
	
	function witdraw(){
		//BENEDETTA
		$('.inputDestinatario').hide();
		$('.modaloperationsError').hide();
		$('.modaloperationsSuccess').hide();
		$('.modaloperationsError').html("");
		$('.modaloperationsSuccess').html("");
		
		var id = $('.idAccountOperations').val();
		var amount = parseFloat($('.balanceOperations').val());
		var cifra = parseFloat($('.amountOperations').val());
		
		
		if(amount>=0 && amount >= cifra){
			var url = "Prelievo";
			var paramJSON = {'cifra':cifra,'idAccount':id,'api_key' : $.cookie("api_key")};
			var dataType="application/json";
			var headerJSON = {};
			function risposta(data){

				var result = JSON.parse(data.responseText);
				if(result.error == "true"){
					$(".modaloperationsError").show();
					$(".modaloperationsError").empty();
					$(".modaloperationsError").html('ERRORE: ' + result.message);
				}else{
					$(".modaloperationsSuccess").show();
					$(".modaloperationsSuccess").empty();
					$(".modaloperationsSuccess").html(result.message);
					var account = result.account;
					$('.balanceOperations').val(account.balance);
					$('.accountId_' + account.id).html(account.balance);
					totalAmount();
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
			$('.modaloperationsError').show();
			$('.modaloperationsError').html("Errore: transazione non possibile saldo non sufficiente");
			$('.modaloperationsSuccess').hide();

		}

	}
	;
	function transfer() {
		$('.modaloperationsError').hide();
		$('.modaloperationsSuccess').hide();
		// LUCIANO

		var amount = parseFloat($('.amountOperations').val());
		var balance = parseFloat($('.balanceOperations').val());
		var idSource = $('.idAccountOperations').val();
		var idDest = $('.idAccountDestinationOperations').val();

		if (balance != null && balance >= amount && idDest != null
				&& amount > 0) {

			var url = "Trasferimento";
			var paramJSON = {'idSource':idSource,'idDest':idDest,'balance':balance,'amount':amount,'api_key' : $.cookie("api_key")};
			var dataType="application/json";
			var headerJSON = {};
			function risposta(data)
			{
				console.log(data);
				var result = JSON.parse(data.responseText);
				if(result.error == "true")
				{
					$('.modaloperationsError').show();
					$('.modaloperationsError').html(result.message);
					$('.modaloperationsSuccess').hide();
					
				}
				else{
//					$('.balanceOperations').val(result.account.balance);
					//AGGIORNARE TABELLA CONTI
					console.log(result);
					$('.modaloperationsSuccess').show();
					$('.modaloperationsError').hide();
					$('.modaloperationsSuccess').html(result.message);
					$('.amountOperations').val('');
					$('.idAccountDestinationOperations').val('');
					var list = result.accounts;
					console.log(list);
					for(var i in list){
						var account = list[i];
						if(account.id==parseFloat(idSource)){
							console.log("sono qui 01");
							$('.balanceOperations').val(account.balance);
						
						}
						$('.accountId_'+account.id).html(account.balance);
						
					}
					totalAmount();
				}
			};
		
		
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
			$('.modaloperationsError').show();
			$('.modaloperationsError').html('ERRORE: il Saldo disponibile non &egrave sufficiente!!!');
			$('.modaloperationsSuccess').hide();
		}
	}
	;

	function chooseOperation() {
		if ($("#depositoCheck").is(':checked'))
			deposit();
		else if ($("#prelievoCheck").is(':checked'))
			witdraw();
		else if ($("#trasferimentoCheck").is(':checked'))
			transfer();
		
		$('.amountOperations').val('');
		$('.idAccountDestinationOperations').val('');
	}
	;
	function clickTr() {
		var tr = this;
		var td = tr.children[1];
		var numeroConto = $(td).html();
		if(numeroConto != 'NON SONO PRESENTI CONTI'){
			var td = tr.children[2];
			var saldoConto = $(td).html();
	
			$('.idAccountOperations').val(numeroConto);
			$('.balanceOperations').val(saldoConto);
			$(".modaloperationsError").hide();
			$(".modaloperationsSuccess").hide();
			$(".modaloperationsError").val('');
			$(".modaloperationsSuccess").val('');
			$('.amountOperations').val('');
			$('.idAccountDestinationOperations').val('');
			$('#operations-modal').modal('show');
		}
		
	};

	
	

	function creaConto() {

		$(".modaloperationsSuccess").hide();
		$(".modaloperationsSuccess").empty();
		$(".modaloperationsError").hide();
		$(".modaloperationsError").empty();

		var url = "NuovoConto";
		var paramJSON = {
			'api_key' : $.cookie("api_key")
		};
		var dataType = "application/json";
		var headerJSON = {};
		function risposta(data) {

			var result = JSON.parse(data.responseText);
			if (result.error == "true") {
				$(".modaloperationsError").show();
				$(".modaloperationsError").empty();
				$(".modaloperationsError").html('ERRORE: ' + result.message);
			} else {

				var account = result.account;
				var totAccounts = parseInt($('.totAccounts').val()) + 1;
				$('.totAccounts').val(totAccounts);
				var balance = new Number(account.balance);
				var tr = $('.table_accounts tr')[0];
				var td = tr.children[1];
				var numeroConto = $(td).html();
				if (numeroConto == 'NON SONO PRESENTI CONTI')
					$('.table_accounts').empty();
				$('.table_accounts').append(
						'<tr><th scope="row">' + totAccounts
								+ '</th><td>' + account.id + '</td><td class="accountId_' + account.id
								+ '">' + balance
								+ '</td></tr>');
				$('.table_accounts tr').click(clickTr);
				$('.accountId_' + account.id).click();
				$(".modaloperationsSuccess").show();
				$(".modaloperationsSuccess").empty();
				$(".modaloperationsSuccess").html(result.message);
			}
		}
		;
		$.ajax({
			type : "POST",
			url : url,
			headers : headerJSON,
			dataType : dataType,
			data : paramJSON,
			success : risposta,
			error : risposta,
			async : false

		});

	}
	;

	function recuperaDati() {
		$('.inputDestinatario').hide();
		if ($.cookie("api_key") != null && $.cookie("api_key") != "") {

			var url = "RecuperoDati";
			var paramJSON = {
				'api_key' : $.cookie("api_key")
			};
			var dataType = "application/json";
			var headerJSON = {};

			function risposta(data) {

				var result = JSON.parse(data.responseText);
				if (result.error == "true") {
					alert('Si è verificato un errore');
				} else {
					var user = result.user;
					$('.nameSurnameDashboard').html(
							user.nome + ' ' + user.cognome);
					$('.codFiscEmailDashboard').html(
							user.codicefiscale + ' - ' + user.email);
					$('.table_accounts').empty();
					var accounts = result.accounts;

					if (accounts != null && accounts.length > 0) {
						for (var i = 0; i < accounts.length; i++) {
							var balance = new Number(accounts[i].balance);
							$('.table_accounts').append(
									'<tr><th scope="row">' + (i + 1)
											+ '</th><td >' + accounts[i].id
											+ '</td><td class="accountId_'
											+ accounts[i].id + '">' + balance
											+ '</td></tr>');
						}
						$('.totAccounts').val(accounts.length);
					} else {
						$('.table_accounts')
								.append(
										'<tr><th scope="row"></th><td>NON SONO PRESENTI CONTI</td><td></td></tr>');
					}
				}
			}

			$.ajax({
				type : "POST",
				url : url,
				headers : headerJSON,
				dataType : dataType,
				data : paramJSON,
				success : risposta,
				error : risposta,
				async : false

			});

			$('.containerDashboard').show();
			totalAmount();
		} else {
			window.location.href = 'index.jsp';
		}
	}
	;

	function totalAmount() {
		console.log('totalAmount');
		var url = "CapitaleBanca";
		var dataType = "application/json";

		function risposta(data) {
			console.log(data);
			console.log('risposta');
			var capBank = parseFloat(data.responseText);
			$('.totalAmount').val(capBank);
		}

		$.ajax({
			type : "POST",
			url : url,
			dataType : dataType,
			success : risposta,
			error : risposta,
			async : false

		});
		
		totalAmountUser();

	}// end total amount

	function totalAmountUser() {
		console.log('inizio funzione');
		var url = "SaldoTotaleContiUtente";
		var dataType = "application/json";
		var paramJSON = {
				'api_key' : $.cookie("api_key")
			};

		function risposta(data) {

			console.log(data);
			console.log('funzione risposta');
			var capBank = parseFloat(data.responseText);
			$('.totalAmountUser').val(capBank);

			console.log(capBank);
		}

		$.ajax({
			type : "POST",
			url : url,
			dataType : dataType,
			data : paramJSON,
			success : risposta,
			error : risposta,
			async : false

		});

	}// end total amount user
});