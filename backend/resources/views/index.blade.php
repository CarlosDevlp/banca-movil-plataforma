
<!DOCTYPE html>
<html lang="en">
<head>
<title>{{ $WEB_DETAILS['TITLE'] }}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="{{ $WEB_DETAILS['META'] }}" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link href="{{ $ASSET_PATH }}css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="{{ $ASSET_PATH }}css/style.css" type="text/css" media="all" />
<!--// css -->
<link rel="stylesheet" href="{{ $ASSET_PATH }}css/owl.carousel.css" type="text/css" media="all">
<link href="{{ $ASSET_PATH }}css/owl.theme.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="{{ $ASSET_PATH }}css/cm-overlay.css" />
<!-- virtual-keyborad -->
<link href="{{ $ASSET_PATH }}assets/plugins/jquery.virtual_keyboard/jquery.virtual_keyboard.css" rel="stylesheet" type="text/css"/>
<!-- font-awesome icons -->
<link href="{{ $ASSET_PATH }}css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!-- font -->
<link href="//fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<!-- //font -->
<script src="{{ $ASSET_PATH }}js/jquery-1.11.1.min.js"></script>
<script src="{{ $ASSET_PATH }}js/bootstrap.js"></script>
<!-- virtual-keyborad -->
<script src="{{ $ASSET_PATH }}assets/plugins/jquery.virtual_keyboard/jquery.virtual_keyboard.js" type="text/javascript"></script>
@if($PREVENT_DEFAULT)
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
				$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
			});


		});
	</script>
@endif
<!-- animation -->
<link href="{{ $ASSET_PATH }}css/animate.css" rel="stylesheet" type="text/css" media="all">
<script src="{{ $ASSET_PATH }}js/wow.min.js"></script>
	<script>
		 new WOW().init();
	</script>
<!-- //animation --> 
<script>
$(document).ready(function() { 
	$("#owl-demo").owlCarousel({
 
		autoPlay: 3000, //Set AutoPlay to 3 seconds
		autoPlay:true,
		items : 3,
		itemsDesktop : [640,5],
		itemsDesktopSmall : [414,4]
 
	});
	
}); 
</script>

<style type="text/css">
	.banner{
	    padding-top: 0;
	}
	.header{
		background:#249c91;
	}

</style>
</head>
<body>
	<!-- banner -->
	<div class="banner">
		<!--header-->
		<div class="header">
			<div class="container">		
				<nav class="navbar navbar-default">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<h1><a  href="index.html">{{ $WEB_DETAILS['APP_NAME'] }}</a></h1>
					</div>
					<!--navbar-header-->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">
							<li id="home"><a  href="/web/home" class="active">{{ $WEB_DETAILS['MENU']['HOME'] }}</a></li>							
							<li><a href="/web/home#about" class="scroll">{{ $WEB_DETAILS['MENU']['ABOUT_US'] }}</a></li>
							<li><a href="/web/home#services" class="scroll">{{ $WEB_DETAILS['MENU']['SERVICES'] }}</a></li>
							<li id="register"><a  href="#" class="scroll">{{ $WEB_DETAILS['MENU']['REGISTRATION'] }}</a></li>							
							<li><a href="/web/home#contact" class="scroll">{{ $WEB_DETAILS['MENU']['CONTACT_US'] }}</a></li>
						</ul>	
						<div class="clearfix"> </div>	
					</div>
				</nav>
			</div>
		</div>	
		<!--//header-->

		@yield('banner')


	</div>
	<!-- //banner -->

	@yield('content')
	
	<!-- footer -->
	<div class="jarallax footer">
		<div class="container">
			<div class="footer-logo">
				<h3><a href="index.html">{{ $WEB_DETAILS['APP_NAME'] }}</a></h3>
			</div>
			<div class="agileinfo-social-grids">
				<h4>Búscanos en las redes</h4>
				<div class="border"></div>
				<ul>
					<li><a href="#"><i class="fa fa-facebook"></i></a></li>
					<li><a href="#"><i class="fa fa-twitter"></i></a></li>					
				</ul>
			</div>
			<div class="copyright">
				<p>{{ $WEB_DETAILS['FOOTER'] }}</p>
			</div>
		</div>
	</div>

	
	<!-- //copyright -->
	<script src="{{ $ASSET_PATH }}js/jarallax.js"></script>
	<script src="{{ $ASSET_PATH }}js/SmoothScroll.min.js"></script>
	<script type="text/javascript">
		/* init Jarallax */
		$('.jarallax').jarallax({
			speed: 0.5,
			imgWidth: 1366,
			imgHeight: 768
		})
	</script>
<script type="text/javascript" src="{{ $ASSET_PATH }}js/move-top.js"></script>
<script type="text/javascript" src="{{ $ASSET_PATH }}js/easing.js"></script>
	<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
			*/
								
			//$().UItoTop({ easingType: 'easeOutQuart' });


				$('#register').on('click',function(){
					location.href='/web/registro';
				});
								
			});
	</script>
<!-- //here ends scrolling icon -->
<script src="{{ $ASSET_PATH }}js/owl.carousel.js"></script>  
</body>	
</html>