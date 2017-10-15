<jsp:include page="component/header.jsp" />
<jsp:include page="component/modalOperazioni.jsp" />
<jsp:include page="component/modalNewAccount.jsp" />
<div class="container containerDashboard" style="display: none;">

	<!-- INIZIO PRIMA RIGA -->
	<div class="row">
		<div class="col-md-6 col-md-offset-1 text-center">
			<img src="resource/img/user.png" alt="website template image"
				class="img_profilo">
			<h1 class="nameSurnameDashboard"></h1>
			<p class="codFiscEmailDashboard"></p>
		</div>
		<div class="col-6">
			<div class="card">
				<div class="card-header" role="tab" id="headingOne">
					<h5 class="mb-0">
						<a data-toggle="collapse" href="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne"> INFO </a>
					</h5>
				</div>

				<div id="collapseOne" class="collapse show" role="tabpanel"
					aria-labelledby="headingOne" data-parent="#accordion">
					<div class="card-body">
					
						<div class="row">
						  	<div class="col-6"><h5>Capitale  Unisannio  Bank:</h5></div>
							<div class="col-6"><input type="text" readonly class="totalAmount" > &euro;</div>
						</div>
						<div class="row">
						  	<div class="col-12"><hr></div>
						</div>
						<div class="row">
						  	<div class="col-6"><h5>Saldo Totale Conti utente:</h5></div>
							<div class="col-6"><input type="text" readonly class="totalAmountUser" > &euro;</div>
						</div>

					<div class="row">
							<div class="col-12"><br></div>
						</div>
						
						<div class="row">
							<div class="col-8"></div>
						  	<div class="col-4"><button type="button" class="btn btn-info btn-createAccount">Apertura conto</button></div>
						</div>
						
						

							
							
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- 	    <div class="col-4"></div> -->
<!-- 			<div class="col-2"> -->
<!-- 				<button type="button" class="btn btn-info btn-createAccount">Apertura conto</button><br> -->
<!-- 				<div class="card"> -->
<!-- 					<div class="card-header" role="tab" id="headingOne"> -->
<!-- 						<h5 class="mb-0"> -->
<!-- 							<a data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne"> INFO </a> -->
<!-- 						</h5> -->
<!-- 					</div> -->

<!-- 					<div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion"> -->
<!-- 						<div class="card-body"> -->
<!-- 							<button type="button" class="btn btn-primary btn-createAccount">Apertura conto</button><br> -->

<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->

<!-- 			</div> -->
		
	<!-- FINE PRIMA RIGA -->

	<hr>

	<!-- INIZIO SECONDA RIGA -->
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header" role="tab" id="heading2">
					<h5 class="mb-0">
						<a data-toggle="collapse" href="#collapse2" aria-expanded="true"
							aria-controls="collapse2"> CONTI CORRENTE </a>
					</h5>
				</div>

				<div id="collapse2" class="collapse" role="tabpanel"
					aria-labelledby="heading2" data-parent="#accordion">
					<div class="card-body card-accounts">
						<table class="table table-hover tabella_conti">
							<thead>
								<tr>
									<th>#</th>
									<th>Numero</th>
									<th>Saldo &euro;</th>
								</tr>
							</thead>
							<tbody class="table_accounts"></tbody>
						</table>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- FINE SECONDA RIGA -->


</div>



<script src="resource/bootstrap-4.0.0-beta-dist/js/dashboard.js"></script>
<jsp:include page="component/footer.jsp" />