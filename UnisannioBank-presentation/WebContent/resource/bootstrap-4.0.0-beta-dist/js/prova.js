/**
 * 
 */

function totalAmount (){
	
	
	var url = "CapitaleBanca";
	
	
	
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
	
}// end total amount