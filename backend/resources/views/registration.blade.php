@extends('index')

@section('content')
	<!-- contact -->
	<div class="contact" id="contact">
		<div class="container">
			<div class="w3-welcome-heading">
				<h3>Registrate</h3>
			</div>
			<div class="agile-contact-grids">
				<div class="col-md-12 contact-form">
					
					<form>
						
						<div class="row" >
							<h5 class="col-md-12 text-left">Número de tarjeta</h5><br>
							@for($i=0;$i<4;$i++)								
								<div class="col-md-2">
									<input type="text" name="card-digit-group-{{$i+1}}" placeholder="0000" required="" />
								</div>
							@endfor
						</div>


						<input type="text" class="dni" name="DNI" placeholder="Documento de identidad" id="username" required="">


						<h5 class="text-left">Contraseña Web</h5><br>
						
						<div class="row">
							<div class="col-md-10">
								<input class="col-md-12 web-password" 
								type="password" id="password"  name="password" placeholder="" required="" disabled="">
							</div>
							<div class="col-md-2">
								<button id="virtual-keyboard" class="btn1">
									Teclado
									<span class="glyphicon glyphicon-modal-window" aria-hidden="true"></span>
								</button>
							</div>
						</div>



						<input type="submit" id="btn-register" class="btn1 col-md-offset-4" value="Registrarme">
					</form>
				</div>


				<div class="clearfix"> </div>	
			</div>
				<div class="col-md-12 agileits-w3layouts-address">
					<div class="agileits-w3layouts-address-top">
						<h5>Atención al cliente</h5>
						<ul>
							<li>+51 999 999 9999</li>
						</ul>
					</div>
				</div>
		</div>
	</div>
	<!-- //contact -->



	<script type="text/javascript">

        $('.web-password').keyboard({
            trigger: '#buttom1'
        });

		var keyboard = $('.web-password').data('pluginKeyboard');
		var keyboardHidden=false;
		$('#virtual-keyboard').on('click',function(e){
			e.preventDefault();
			keyboardHidden=!keyboardHidden;
			keyboard.setHidden(keyboardHidden);
		});

		var showAlertDialog=function  (title,content){
			$('#alert-dialog #modal-title').text(title);
			$('#alert-dialog #modal-content').text(content);
			$('#alert-dialog').modal('show');
		};

		var isFormValid=function(){
			return  $('#username').val().trim()!='' && $('#password').val().trim()!='';
		};


		//realizando un ajax al backend upn
		$('#btn-register').on('click',function(e){			
			e.preventDefault();
			if(isFormValid())
				$.ajax({
					url:'/api/usuario',
					method:'post',
					data:{
						dni:$('#username').val(),
						password:$('#password').val()
					},
					success:function(resp){
						if(resp.status='ok'){
						 showAlertDialog('Registrado exitoso','El cliente se ha registrado con éxito');		
						 setTimeout(function(){
						 	location.href='/web/home';
						 },1000);

						}
					}
				});
			else
				showAlertDialog('Aviso','No debe haber campos vacíos en el formulario');
		});

		$('#home a').removeClass('active');
		$('#register a').addClass('active');
	</script>

@endsection


@include('modals')