<jsp:include page="component/header.jsp" />
<jsp:include page="component/modalLogin.jsp" />
<jsp:include page="component/modalRegister.jsp" />

<!-- 	inizio carosel -->

<div id="myCarousel" class="carousel slide" data-ride="carousel">
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class=""></li>
		<li data-target="#myCarousel" data-slide-to="1" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner">
		<div class="carousel-item active carousel-item-left">
			<img class="first-slide"
				src="resource/img/img02.jpg"
				alt="First slide">
			<div class="container">
				<div class="carousel-caption d-none d-md-block text-left">
					<h1>Il conto per i giovani a portata di click!</h1>
					<p>Con zero spese ti permette di studiare ed affrontare le spese universitarie a piccole rate</p>
					<p>
						<a class="btn btn-lg btn-primary" href="dashboard.jsp" role="button">Scopri come</a>
					</p>
				</div>
			</div>
		</div>
		<div class="carousel-item carousel-item-next carousel-item-left">
			<img class="second-slide"
				src="resource/img/img01.jpg"
				alt="Second slide">
			<div class="container">
				<div class="carousel-caption d-none d-md-block">
					<h1>Benvenuto Apple Pay</h1>
					<p>Provalo subito! Apri il conto Unisannio Bank</p>
					<p>
						<a class="btn btn-lg btn-primary" href="dashboard.jsp" role="button">Scopri come</a>
					</p>
				</div>
			</div>
		</div>
		<div class="carousel-item">
			<img class="third-slide"
				src="resource/img/img05.jpg"
				alt="Third slide">
			<div class="container">
				<div class="carousel-caption d-none d-md-block text-right">
					<h1>Mutuo Giovani</h1>
					<p>I sogni possono scegliere il posto in cui vivere</p>
					<p>
						<a class="btn btn-lg btn-primary" href="dashboard.jsp" role="button">Scopri come</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<a class="carousel-control-prev" href="#myCarousel" role="button"
		data-slide="prev"> <span class="carousel-control-prev-icon"
		aria-hidden="true"></span> <span class="sr-only">Previous</span>
	</a> <a class="carousel-control-next" href="#myCarousel" role="button"
		data-slide="next"> <span class="carousel-control-next-icon"
		aria-hidden="true"></span> <span class="sr-only">Next</span>
	</a>
</div>

<!-- fine carosel -->



<!-- Container fine-->
<div class="container slogan">
	<div class="mt-3">
		<h1>La banca pi&ugrave; vicina ai giovani</h1>

	</div>
<!-- 	<p class="lead"> -->
<!-- 		Pin a fixed-height footer to the bottom of the viewport in desktop -->
<!-- 		browsers with this custom HTML and CSS. A fixed navbar has been added -->
<!-- 		with -->
<!-- 		<code>padding-top: 60px;</code> -->
<!-- 		on the -->
<!-- 		<code>body &gt; .container</code> -->
<!-- 		. -->
<!-- 	</p> -->
<!-- 	<p> -->
<!-- 		Back to <a href="../sticky-footer">the default sticky footer</a> minus -->
<!-- 		the navbar. -->
<!-- 	</p> -->




</div>
<!-- Container fine-->



<jsp:include page="component/footer.jsp" />
