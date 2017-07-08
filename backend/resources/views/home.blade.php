@extends('index')

@section('banner')
			<div class="w3layouts-banner-info">
			<div class="container">
				<div class="w3layouts-banner-slider">
					<div class="slider">
						<div class="callbacks_container">
							<ul class="rslides callbacks callbacks1" id="slider4">
								@foreach($content['banner'] as $slide)
								<li>
									<div class="agileits-banner-info">
										{!!$slide!!}
									</div>
								</li>
								@endforeach								
							</ul>
						</div>
						<script src="{{ $ASSET_PATH }}js/responsiveslides.min.js"></script>
						<script>
							// You can also use "$(window).load(function() {"
							$(function () {
							  // Slideshow 4
							  $("#slider4").responsiveSlides({
								auto: true,
								pager:true,
								nav:false,
								speed: 500,
								namespace: "callbacks",
								before: function () {
								  $('.events').append("<li>before event fired.</li>");
								},
								after: function () {
								  $('.events').append("<li>after event fired.</li>");
								}
							  });
						
							});
						 </script>
						<!--banner Slider starts Here-->
					</div>
				</div>
			</div>
		</div>
		<div class="bounce animated">
			<a href="#welcome" class="scroll">
				<div class="mouse"></div>
			</a>
		</div>
@endsection

@section('content')
<!-- welcome -->
	<div class="welcome" id="welcome">
		<div class="container">
			<div class="w3-welcome-heading">
				<h2>Bienvenido</h2>
			</div>
			<div class="w3l-welcome-info">
				
				<div class="col-sm-6 welcome-grids col-sm-offset-3" >
					<div class="welcome-img">
						<img src="{{ $ASSET_PATH }}images/6.jpg" class="img-responsive zoom-img" alt="">
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="w3l-welcome-text">
				<p>Este es el sistema bancario de confianza que ahora todo el mundo está hablando. UpnBank es la mejor opción para planear tu futuro estado financiero.
				<br>
				 ¿qué esperas para registrarte? </p>
			</div>
		</div>
	</div>
	<!-- //welcome -->
	<!-- about -->
	<div class="about" id="about">
		<div class="container">
			<div class="w3-welcome-heading">
				<h3>Sobre Nosotros</h3>
			</div>
			<div class="w3ls-about-grids">
				<div class="col-md-6 about-right">
					<img src="{{ $ASSET_PATH }}images/9.jpg" alt="">
				</div>
				<div class="col-md-6 about-left"> 
					<h4>Características que nos definen </h4>
					
					<ul> 
						<li><span class="glyphicon glyphicon-share-alt"></span> Empresa joven e innovadora</li>
						<li><span class="glyphicon glyphicon-share-alt"></span> Lo último en tecnología para brindar el mejor servicio</li>
						<li><span class="glyphicon glyphicon-share-alt"></span> Programas de ahorro asegurado </li>
						<li><span class="glyphicon glyphicon-share-alt"></span> Servicios de consulta las 24 horas</li>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!-- //about -->
	<!-- services -->
	<div class="services" id="services">
		<div class="container">
			<div class="w3-welcome-heading">
				<h3>Nuestros servicios</h3>
			</div>
			<div class="agileits-services-grids">
				<div class="col-md-8 agileits-services-left">
					<h4>Servicios</h4>
					<div class="agileits-services-text">
						<p>Servicios de préstamo con 0.01% de interés bisemestral que es totalmente atractivo para los pequeños negocios que tienen el potencial de convertirse en grandes empresas.</p>
					</div>
					<div class="credit-grids">
						<h5>Tarjetas de crédito y débito:</h5>
						<div class="credit-grid-left">
							<div class="credit-grid">
								<img src="{{ $ASSET_PATH }}images/c2.jpg" alt="" />
								<h6>Visa</h6>
							</div>
							<div class="credit-grid">
								<img src="{{ $ASSET_PATH }}images/c3.jpg" alt="" />
								<h6>MasterCard</h6>
							</div>
							<div class="credit-grid">
								<img src="{{ $ASSET_PATH }}images/c4.jpg" alt="" />
								<h6>MasterCard</h6>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>

				</div>
				<div class="col-md-4 agileits-services-right">
					<h4>Pago de servicios</h4>
					<div class="services-two-grids">
						<div class="agileinfo-services-right">
							<img src="{{ $ASSET_PATH }}images/10.jpg" alt="" />
							<h6>Paga los siguientes servicios desde la aplicación</h6>
						</div>
						<!-- date -->
						<div id="design" class="date">
										<div id="cycler">   
											@foreach($content['services'] as $service)
												<div class="date-text">
													<a href="#" data-toggle="modal" data-target="#myModal"><i class="fa fa-arrow-right" aria-hidden="true"></i> {{$service}}</a>
												</div>
										    @endforeach
										</div>
										<script>
										function blinker() {
											$('.blinking').fadeOut(500);
											$('.blinking').fadeIn(500);
										}
										setInterval(blinker, 1000);
										</script>
										<script>
											function cycle($item, $cycler){
												setTimeout(cycle, 2000, $item.next(), $cycler);
												
												$item.slideUp(1000,function(){
													$item.appendTo($cycler).show();        
												});
												}
											cycle($('#cycler div:first'),  $('#cycler'));
										</script>
						</div>
						<!-- //date -->
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!-- //services -->
	
	
	<!-- feedback -->
	<div class="jarallax feedback">
		<div class="container">
			<div class="w3-welcome-heading">
				<h3>Comentarios de clientes</h3>
			</div>
			<div class="agileits-feedback-grids">
				<div id="owl-demo" class="owl-carousel owl-theme">
					<div class="item">
						<div class="feedback-info">
							<div class="feedback-top">
								<p> Todo lo que necesito para cumplir mi sueño de negocio</p>
							</div>
							<div class="feedback-grids">
								<div class="feedback-img">
									<img src="{{ $ASSET_PATH }}images/f1.jpg" alt="" />
								</div>
								<div class="feedback-img-info">
									<h5>Jorge Sanchez</h5>
									<p>Perú</p>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>	
					</div>
					<div class="item">
						<div class="feedback-info">
							<div class="feedback-top">
								<p>It promises even more, for sure! </p>
							</div>
							<div class="feedback-grids">
								<div class="feedback-img">
									<img src="{{ $ASSET_PATH }}images/f2.jpg" alt="" />
								</div>
								<div class="feedback-img-info">
									<h5>Pedro Pablo</h5>
									<p>EE.UU</p>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>	
					</div>
					<div class="item">
						<div class="feedback-info">
							<div class="feedback-top">
								<p> No solía ahorrar, pero es que estos programas son tan atractivos!!!</p>
							</div>
							<div class="feedback-grids">
								<div class="feedback-img">
									<img src="{{ $ASSET_PATH }}images/f3.jpg" alt="" />
								</div>
								<div class="feedback-img-info">
									<h5>Fulano Mengano</h5>
									<p>Argentina</p>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>	
					</div>
					<div class="item">
						<div class="feedback-info">
							<div class="feedback-top">
								<p> Sed semper leo metus, a lacinia eros semper at. Etiam sodales orci sit amet vehicula pellentesque. </p>
							</div>
							<div class="feedback-grids">
								<div class="feedback-img">
									<img src="{{ $ASSET_PATH }}images/f1.jpg" alt="" />
								</div>
								<div class="feedback-img-info">
									<h5>Mary Jane</h5>
									<p>Vestibulum</p>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>	
					</div>
					<div class="item">
						<div class="feedback-info">
							<div class="feedback-top">
								<p> Sed semper leo metus, a lacinia eros semper at. Etiam sodales orci sit amet vehicula pellentesque. </p>
							</div>
							<div class="feedback-grids">
								<div class="feedback-img">
									<img src="{{ $ASSET_PATH }}images/f2.jpg" alt="" />
								</div>
								<div class="feedback-img-info">
									<h5>Peter guptill</h5>
									<p>Vestibulum</p>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>	
					</div>
					<div class="item">
						<div class="feedback-info">
							<div class="feedback-top">
								<p> Sed semper leo metus, a lacinia eros semper at. Etiam sodales orci sit amet vehicula pellentesque. </p>
							</div>
							<div class="feedback-grids">
								<div class="feedback-img">
									<img src="{{ $ASSET_PATH }}images/f3.jpg" alt="" />
								</div>
								<div class="feedback-img-info">
									<h5>Steven Wilson</h5>
									<p>Vestibulum</p>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>	
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //feedback -->
	<!-- subscribe -->
	<div class="wthree-subscribe">
		<div class="container">
			<div class="w3-welcome-heading">
				<h3>Puntos de atención</h3>
			</div>
			<!--<div class="w3-agileits-subscribe-form">
				<form action="#" method="post">
					<input type="text" placeholder="Subscribe" name="Subscribe" required="">
					<button class="btn1">Subscribe</button>
				</form>
			</div>-->
		</div>
	</div>
	<!-- //subscribe -->
	<!-- map -->
	<div class="map-grid">
		<!--<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1859251.8642025779!2d-76.08274894689792!3d40.06224332601239!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c0fb959e00409f%3A0x2cd27b07f83f6d8d!2sNew+Jersey%2C+USA!5e0!3m2!1sen!2sin!4v1474436926209" style="border:0" allowfullscreen></iframe>-->
	</div>
	<!-- //map -->


	<!-- contact -->
	<div class="contact" id="contact">
		<div class="container">
			<div class="w3-welcome-heading">
				<h3>Contáctanos</h3>
			</div>
			<div class="agile-contact-grids">
				<div class="col-md-7 contact-form">
					<form action="#" method="post">
						<input type="text" name="First Name" placeholder="Nombre" required="">
						<input type="text" class="email" name="Last Name" placeholder="Apellido" required="">
						<input type="email" class="email" name="Email" placeholder="Correo" required="">
						<textarea name="Message" placeholder="Message" required=""></textarea>
						<input type="submit" value="Enviar consulta">
					</form>
				</div>
				<div class="col-md-5 agileits-w3layouts-address">
					<div class="agileits-w3layouts-address-top">
						<h5>Llámanos</h5>
						<ul>
							<li>+51 999 999 999</li>
							<li>+51 555 555 555</li>
						</ul>
					</div>
					<div class="agileits-w3layouts-address-top">
						<h5>Dirección</h5>
						<ul>
							<li>Lima ,Perú</li>
							<li>Universitaria, por ahí</li>							
						</ul>
					</div>
					<div class="agileits-w3layouts-address-top">
						<h5>Correo</h5>
						<ul>
							<li><a href="mailto:info@example.com"> contactenos@upnbank.com</a></li>
						</ul>
					</div>
				</div>
				<div class="clearfix"> </div>	
			</div>
		</div>
	</div>
	<!-- //contact -->
@endsection