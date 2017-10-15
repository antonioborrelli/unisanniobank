<div class="modal fade bd-example-modal-lg" id="operations-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    <div class="modal-header">
        <h4 class="modal-title" id="myLargeModalLabel">Operazioni</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">x</span>
        </button>
      </div>
      
      <div class="container">
      	<div class="row">
		    <div class="col-12">
		    	<hr>
		    </div>
		  </div>
      	<div class="row">
		    <div class="col-12">
		      	<div class="alert alert-danger modaloperationsError" role="alert" style="display: none;"></div>
				<div class="alert alert-success modaloperationsSuccess" role="alert" style="display: none;"></div>
		    </div>
		  </div>
		  <div class="row">
		    <div class="col">
		    	<div class="input-group">
				  <span class="input-group-addon" id="basic-addon1">Conto</span>
				  <input type="text" class="form-control idAccountOperations" placeholder="Numero conto" aria-describedby="basic-addon1" disabled>
				</div>
				<br>
				<div class="input-group">
				  <span class="input-group-addon" id="basic-addon1">Saldo</span>
				  <input type="text" class="form-control balanceOperations" placeholder="Saldo" aria-describedby="basic-addon1" disabled>
				</div>
				<br>
				<div class="input-group">
				  <span class="input-group-addon" id="basic-addon1">Cifra</span>
				  <input type="text" class="form-control amountOperations" placeholder="Cifra" aria-describedby="basic-addon1" >
				</div>
				<br>
				<div class="input-group inputDestinatario">
				  <span class="input-group-addon" id="basic-addon1">Destinatario</span>
				  <input type="text" class="form-control idAccountDestinationOperations" placeholder="Numero conto" aria-describedby="basic-addon1" >
				</div>
				<br>
		      	
		    </div>
		    <div class="col">
		      <div class="form-check">
				  <label class="form-check-label">
				    <input class="form-check-input" type="radio" name="exampleRadios" id="depositoCheck" value="option1" checked>
				    Deposito
				  </label>
				</div>
				<div class="form-check">
				  <label class="form-check-label">
				    <input class="form-check-input" type="radio" name="exampleRadios" id="prelievoCheck" value="option1" >
				    Prelievo
				  </label>
				</div>
				<div class="form-check">
				  <label class="form-check-label">
				    <input class="form-check-input" type="radio" name="exampleRadios" id="trasferimentoCheck" value="option1" >
				    Trasferimento
				  </label>
				</div>
		    </div>
		  </div>
		  
		  
		  <div class="row">
		    <div class="col-12">
		    	<hr>
		    </div>
		  </div>
		
		<div class="row">
		    <div class="col-10">
		    </div>
		    <div class="col-2">
		     <input type="submit" class="btn btn-primary btn-modaloperations" value="Esegui">
		    </div>
		  </div>
		  
		  <div class="row">
		    <div class="col-12">
		    	<br>
		    </div>
		  </div>
		  
		</div>
      
		
			
			
			
			
			
			
			
			


    </div>
  </div>
</div>
