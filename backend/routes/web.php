<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$app->get('/', function () use ($app) {
    return $app->version();
});


/*
	--GRUPO DE PESTAÑAS DE LA WEB--
*/

$app->group(['prefix'=>'web'],function () use ($app) {
	$app->get('/home', 'WebsiteController@indexPage');
	$app->get('/registro', 'WebsiteController@registerPage');
	$app->get('/cliente-nuevo/registro', 'WebsiteController@registerClientPage');
});


/*
	--WEB SERVICE--
*/

$app->group(['prefix'=>'api'],function () use ($app) {	
	//cliente
	$app->get('/cliente/listar', 'WebServiceController@getClientes');
	$app->post('/cliente', 'WebServiceController@registerCliente');
	$app->get('/cliente/usuario', 'WebServiceController@getClienteUsuario');
	$app->get('/cliente/cuenta-bancaria', 'WebServiceController@getClienteCuentaBancaria');

	//usuario
	$app->get('/usuario', 'WebServiceController@getUser');
	$app->get('/usuario/listar', 'WebServiceController@getUsers');
	$app->post('/usuario', 'WebServiceController@registerUser');

	//tarjeta
	$app->get('/tarjeta/listar', 'WebServiceController@getTarjetas');
	$app->get('/tarjeta', 'WebServiceController@getTarjeta');
	$app->get('/tarjeta/cuenta-bancaria', 'WebServiceController@getTarjetaCuentaBancaria');


	//cuenta bancaria
	$app->get('/cuenta-bancaria', 'WebServiceController@getCuentaBancaria');
	$app->put('/cuenta-bancaria/saldo', 'WebServiceController@updateCuentaBancariaSaldo');
	
	//tipo de cuenta bancaria
	$app->get('/tipo-cuenta-bancaria/listar', 'WebServiceController@getTipoCuenta');

	//operacion
	$app->get('/operacion/listar', 'WebServiceController@getOperaciones');
	$app->get('/operacion', 'WebServiceController@getOperacion');
	$app->post('/operacion', 'WebServiceController@registerOperacion');
	
	//tipo de operación
	$app->get('/tipo-operacion/listar', 'WebServiceController@getTiposOperacion');

	//empresa
	$app->get('/empresa/listar', 'WebServiceController@getEmpresas');

	//punto de atención
	$app->get('/punto-atencion/listar', 'WebServiceController@getPuntosAtencion');

	//tipo de punto de atención
	$app->get('/tipo-punto-atencion/listar', 'WebServiceController@getTipoPuntoAtencion');


	//servicio
	$app->get('/servicio/listar', 'WebServiceController@getServicios');	
	$app->get('/servicio', 'WebServiceController@getServicio');

	//tipo de servicio
	$app->get('/tipo-servicio/listar', 'WebServiceController@getTiposServicio');


});