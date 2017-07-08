@extends('index')

@section('content')
	<!-- contact -->
	<div class="contact" id="contact">
		<div class="container">
			<div class="w3-welcome-heading">
				<h3>Registro de cliente nuevo</h3>
			</div>
			<div class="agile-contact-grids">
				<div class="col-md-12 contact-form">
					
					<form>
						


						@if(false)
						<div class="row" >
							<h5 class="col-md-12 text-left">Número de tarjeta</h5><br>
							@for($i=0;$i<4;$i++)								
								<div class="col-md-2">
									<input type="text" name="card-digit-group-{{$i+1}}" placeholder="0000" required="" />
								</div>
							@endfor
						</div>
						@endif

						<h5 class="text-left">Datos Personales</h5>

						<input type="text" class="name" name="name" placeholder="Nombre" id="name" required="">
						<input type="text" class="last-name" name="last-name" placeholder="Apellidos" id="last-name" required="">						
						<input type="text" class="dni" name="dni" placeholder="Documento de identidad" id="dni" required="">
						<input type="text" class="telefono" name="telefono" placeholder="Telefono" id="telefono" required="">
						<input type="email" class="correo" name="correo" placeholder="Correo" id="correo" required="">




						<input type="submit" id="btn-register" class="btn1 col-md-offset-4" value="Registrar nuevo cliente">
					</form>
				</div>


				<div class="clearfix"> </div>	
			</div>
		</div>
	</div>
	<!-- //contact -->



	<script type="text/javascript">

        
		var showAlertDialog=function  (title,content){
			$('#alert-dialog #modal-title').text(title);
			$('#alert-dialog #modal-content').text(content);
			$('#alert-dialog').modal('show');
		};

		var isFormValid=function(){			
		 return $('#name').val().trim()!='' &&
				$('#last-name').val().trim()!='' &&
				$('#dni').val().trim()!='' &&
				$('#telefono').val().trim()!='' &&
				$('#correo').val().trim()!=''
				;
		};


		//realizando un ajax al backend upn
		$('#btn-register').on('click',function(e){			
			e.preventDefault();
			if(isFormValid())
				$.ajax({
					url:'/api/cliente',
					method:'post',
					data:{						
						nombre:$('#name').val(),
						apellidos:$('#last-name').val(),
						dni:$('#dni').val(),
						telefono:$('#telefono').val(),
						correo:$('#correo').val()
					},
					success:function(resp){
						if(resp.status='ok') {
							showAlertDialog('Registrado exitoso','El usuario del cliente se ha registrado con éxito');	
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