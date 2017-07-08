<?php 
namespace App\Http\Controllers;
use Illuminate\Support\Facades\View;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Response;
use Illuminate\Http\Request;
use App\Model\Usuario;
use App\Model\Cliente;
use App\Model\Tarjeta;
use App\Model\CuentaBancaria;
use App\Model\PuntoAtencion;
use App\Model\TipoPuntoAtencion;
use App\Model\Operacion;
use App\Model\TipoOperacion;
use App\Model\Empresa;
use App\Model\TipoServicio;
use App\Model\Servicio;
use App\Model\TipoCuenta;

class WebServiceController extends Controller
{
    /**
     * Create a new controller instance.
     * Implementado integramente con Eloquent
     * por carlos chavez laguna
     * @return void
     */
    

    public function __construct()
    {
        
    }


    //---------------------------------------------
    //cliente

    /**
    *
    * Registrar el cliente
    * @param $request objeto request con todos los parámetros de registro
    * @return status del registro (exitoso o no)
    */
    public function registerCliente(Request $request){
        $cliente = new Cliente;
        $cliente->nombre=$request->input('nombre');
        $cliente->apellidos=$request->input('apellidos');
        $cliente->dni=$request->input('dni');
        if($request->has('telefono'))
        $cliente->telefono=$request->input('telefono');
        if($request->has('correo'))
        $cliente->correo=$request->input('correo');
        $cliente->save();
        return response()->json(['status'=>'ok']);
    }


    /**
    *
    * Obtener lista de cliente 
    * @return  clientes en json
    */
    public function getClientes(){            
        //return response()->json(Cliente::join('Usuario','Cliente_idCliente','=','idCliente')->get()->toArray()); join
        return response()->json(Cliente::all()->toArray()); 
    }


    /**
    *
    * Obtener el usuario con cliente buscando por condiciones
    * @param $request objeto request con todos los parámetros de búsqueda
    * @return usuario con cliente en json
    */
    public function getClienteUsuario(Request $request){
        
            if($request->has('dni') && $request->has('password')){
                $dni=$request->input('dni');
                $password=$request->input('password');
                return response()->json(Usuario::join('Cliente','Cliente_idCliente','=','idCliente')
                                        ->where('dni',$dni)
                                        ->where('password',$password)
                                        ->first()
                                        ->toArray());
            }

            
            return response()->json(['status'=>'error','message'=>'el usuario no existe'],500);

    }


    /**
    *
    * Obtener el cliente con sus cuentas bancarias buscando por condiciones
    * @param $request objeto request con todos los parámetros de búsqueda
    * @return cliente con sus cuentas bancarias en json
    */
    public function getClienteCuentaBancaria(Request $request){
        
            if($request->has('dni')){
                $dni=$request->input('dni');
                $password=$request->input('password');
                return response()->json(CuentaBancaria::join('Cliente','Cliente_idCliente','=','idCliente')
                                        ->join('Tarjeta','Tarjeta_idTarjeta','=','idTarjeta')
                                        ->where('dni',$dni)
                                        ->get()
                                        ->toArray());
            }

            
            return response()->json(['status'=>'error','message'=>'las cuentas bancarias del supuesto cliente no existe'],500);

    }


    //---------------------------------------------
    //usuarios    
    /**
    *
    * registrar usuario si el cliente existe
    * @param $request parámetros con los valores a registrar
    * @return status del registro (exitoso o no)
    */
    public function registerUser(Request $request){

        $cliente=Cliente::where('dni',$request->input('dni'))->first();
        if($cliente!=null){
            $usuario=new Usuario;
            $usuario->Cliente_idCliente=$cliente->idCliente;
            $usuario->password=$request->input('password');
            $usuario->save();            
            return response()->json(['status'=>'ok']);
        }

        return response()->json(['status'=>'error','message'=>'el cliente no existe'],500);
    }


    /**
    *
    * Obtener todos los usuarios
    * @return lista de usuarios en json
    */
    public function getUsers(){
        return response()->json(Usuario::all()->toArray());
    }



    /**
    *
    * Obtener el usuario buscado por condiciones
    * @param $request objeto request con todos los parámetros de búsqueda
    * @return usuario en json
    */
    public function getUser(Request $request){
        try{
            if($request->has('dni') && $request->has('password')){
                $dni=$request->input('dni');
                $password=$request->input('password');
                return response()->json(Usuario::where('dni',$dni)
                                        ->where('password',$password)
                                        ->first()
                                        ->toArray());
            }
            
            return response()->json(['status'=>'error','message'=>'el usuario no existe'],500);
            
        }catch(Exception $e){

        }
    }

    //---------------------------------------------
    //tarjeta
    /**
    *
    * Registrar la tarjeta del cliente
    * @param $request objeto request con todos los parámetros de registro
    * @return status del registro (exitoso o no)
    */
    public function registerTarjeta(Request $request){
        $tarjeta=new Tarjeta;
        $tarjeta->numeroTarjeta=$request->input('numeroTarjeta');
        $tarjeta->descripcion=$request->input('descripcion');
        $tarjeta->save();
        return response()->json(['status'=>'ok']);
    }

    /**
    *
    * Obtener todas las tarjetas
    * @return lista de tarjetas en json
    */
    public function getTarjetas(){
        return response()->json(Tarjeta::all()->toArray());
    } 


    /**
    *
    * Obtener todas las tarjetas por condiciones
    * @param $request objeto request con todos los parámetros de búsqueda
    * @return lista de tarjetas en json
    */
    public function getTarjeta(Request $request){

            if($request->has('idTarjeta')){
                $idTarjeta=$request->input('idTarjeta');
                return response()->json(Tarjeta::where('idTarjeta',$idTarjeta)
                                        ->get()
                                        ->toArray());
            }

            
            return response()->json(['status'=>'error','message'=>'las cuentas bancarias del supuesto cliente no existe'],500);
    } 


    /**
    *
    * Obtener tarjetas y cuentas bancarias buscando por condiciones
    * @param $request objeto request con todos los parámetros de búsqueda
    * @return tarjetas y cuentas bancarias en json
    */
    public function getTarjetaCuentaBancaria(Request $request){

            if($request->has('idCliente')){
                $idCliente=$request->input('idCliente');
                return response()->json(CuentaBancaria::where('Cliente_idCliente',$idCliente)
                                        ->join('Tarjeta','Tarjeta_idTarjeta','=','idTarjeta')
                                        ->get()
                                        ->toArray());
            }

            
            return response()->json(['status'=>'error','message'=>'las cuentas bancarias del supuesto cliente no existe'],500);
    }


    //---------------------------------------------
    //cuenta bancaria
    /**
    *
    * Registrar la cuenta bancaria del cliente
    * @param $request objeto request con todos los parámetros de registro
    * @return status del registro (exitoso o no)
    */
    public function registerCuentaBancaria(Request $request){
        $cuentaBancaria=new CuentaBancaria;
    

        $cliente=Cliente::where('dni',$request->input('dni'))->first();
        if($cliente!=null){
            $cuentaBancaria->numeroCuenta=$request->input('numeroCuenta');
            $cuentaBancaria->saldo=$request->input('saldo');
            $cuentaBancaria->Cliente_idCliente=$cliente->idCliente;
            $cuentaBancaria->TipoCuenta_idTipoCuenta=$request->input('TipoCuenta_idTipoCuenta');
            if($request->has('Tarjeta_idTarjeta'))
                $cuentaBancaria->Tarjeta_idTarjeta=$request->input('Tarjeta_idTarjeta');
            $cuentaBancaria->save();
            return response()->json(['status'=>'ok']);  
        } 
        return response()->json(['status'=>'error','message'=>'el cliente no existe'],500);
    }

    
    /**
    *
    * Obtener cuentas bancarias buscando por condiciones
    * @param $request objeto request con todos los parámetros de búsqueda
    * @return cuentas bancarias en json
    */
    public function getCuentaBancaria(Request $request){
        
            if($request->has('idCliente')){
                $idCliente=$request->input('idCliente');
                return response()->json(CuentaBancaria::where('Cliente_idCliente',$idCliente)
                                        ->get()
                                        ->toArray());
            }

            
            return response()->json(['status'=>'error','message'=>'las cuentas bancarias del supuesto cliente no existe'],500);

    }


    /**
    *
    * Actualizar el saldo de la cuenta bancaria
    * @param $request objeto request con todos los parámetros de actualización
    * @return status de la actualización (exitoso o no)
    */
    public function updateCuentaBancariaSaldo(Request $request){
        $id=(int)$request->input('idCuentaBancaria');
        $cuentaBancaria=CuentaBancaria::find($id);
        $cuentaBancaria->saldo=$request->input('saldo');
        $cuentaBancaria->save();
        return response()->json(['status'=>'ok']);
    }





    //---------------------------------------------
    //tipo de cuenta
    /**
    *
    * Obtener lista de tipo de cuenta 
    * @return  tipo de cuenta en json
    */
    public function getTipoCuenta(){        
        return response()->json(TipoCuenta::all()->toArray()); 
    }



    //---------------------------------------------
    //Operacion

    /**
    * 
    * Registrar la operación bancaria
    * @param $request objeto request con todos los parámetros de registro
    * @return status del registro (exitoso o no)
    */
    public function registerOperacion(Request $request){
        $operacion=new Operacion;    

        $operacion->fechaOperacion=$request->input('fechaOperacion');
        $operacion->monto=$request->input('monto');

        if($request->has('numeroCuentaDestino'))
            $operacion->numeroCuentaDestino=$request->input('numeroCuentaDestino');
          //campo obligatorio - id de cuenta bancaria que realizó la operación
        $operacion->CuentaBancaria_idCuentaBancaria=$request->input('idCuentaBancaria');          
        $operacion->TipoOperacion_idTipoOperacion=$request->input('idTipoOperacion');

        //si la operación está relacionada a un servicio
        if($request->has('idServicio'))
          $operacion->Servicio_idServicio=$request->input('idServicio');
    
        $operacion->save();
        return response()->json(['status'=>'ok']);  
    }

    /**
    *
    * Obtener lista de operaciones
    * @return  operaciones en json
    */
    public function getOperaciones(){
        return response()->json(Operacion::all()->toArray()); 
    }

    /**
    *
    * Obtener operaciones buscando por condiciones
    * @param $request objeto request con todos los parámetros de búsqueda
    * @return operaciones en json
    */
    public function getOperacion(Request $request){
        
                
            if($request->has('idCuentaBancaria')){                   
                $idCuentaBancaria=$request->input('idCuentaBancaria');
                return response()->json(Operacion::join('TipoOperacion',
                                        'TipoOperacion_idTipoOperacion','=','idTipoOperacion')
                                        ->where('CuentaBancaria_idCuentaBancaria',$idCuentaBancaria)
                                        ->get()
                                        ->toArray());
            }

            
            return response()->json(['status'=>'error','message'=>'las operaciones no existe'],500);

    }

    //---------------------------------------------
    //tipo de operación
    /**
    *
    * Obtener lista de tipo de operación 
    * @return  tipo de opearción en json
    */
    public function getTiposOperacion(){        
        return response()->json(TipoOperacion::all()->toArray()); 
    }




    //---------------------------------------------
    //empresa
    /**
    *
    * Obtener lista empresas
    * @return  empresas en json
    */
    public function getEmpresas(){
        return response()->json(Empresa::all()->toArray());
    }

    //---------------------------------------------
    //servicios
    /**
    *
    * Obtener lista de servicios
    * @return  servicios en json
    */
    public function getServicios(){
        return response()->json(Servicio::all()->toArray());
    }


    /**
    *
    * Obtener lista de servicio por condiciones
    * @param $request objeto con los parámetros de búsqueda
    * @return  servicios en json
    */
    public function getServicio(Request $request){

        if($request->has('idEmpresa')){
            $idEmpresa=$request->input('idEmpresa');
            return response()->json(Servicio::where('Empresa_idEmpresa',$idEmpresa)
                                    ->get()
                                    ->toArray());

        }
        else if($request->has('idTipoServicio')){
            $idTipoServicio=$request->input('idTipoServicio');
            return response()->json(Servicio::where('TipoServicio_idTipoServicio',$idTipoServicio)
                                    ->get()
                                    ->toArray());
        } 

        return response()->json(Servicio::all()->toArray());
        
    }


    //---------------------------------------------
    //tipo de servicios
    /**
    *
    * Obtener lista de tipos de servicio
    * @return  tipos de servicio en json
    */
    public function getTiposServicio(){
        return response()->json(TipoServicio::all()->toArray());
    }




    //---------------------------------------------
    //Punto de atención
    /**
    *
    * Registrar la punto de atención de upnbank
    * @param $request objeto request con todos los parámetros de registro
    * @return status del registro (exitoso o no)
    */
    public function registerPuntoAtencion(Request $request){
        $puntoAtencion= new PuntoAtencion;
                    
        $puntoAtencion->descripcion=$request->input('descripcion');
        $puntoAtencion->coordenadaLat=$request->input('coordenadaLat');
        $puntoAtencion->coordenadaLng=$request->input('coordenadaLng');
        $puntoAtencion->TipoPuntoAtencion_idTipoPuntoAtencion=$request->input('TipoPuntoAtencion_idTipoPuntoAtencion');

        $puntoAtencion->save();
        return response()->json(['status'=>'ok']);  
    }


    /**
    *
    * Obtener lista de puntos de atención por condiciones 
    * @return  puntos de atencion en json
    */
    public function getPuntosAtencion(Request $request){
        
        if($request->has('idTipoPuntoAtencion')){
            $idTipoPuntoAtencion=$request->input('idTipoPuntoAtencion');
            return response()->json(PuntoAtencion::where('TipoPuntoAtencion_idTipoPuntoAtencion',
                                                          $idTipoPuntoAtencion)->get()->toArray()); 
        }

        return response()->json(PuntoAtencion::all()->toArray()); 
    }


    //---------------------------------------------
    //Tipos de punto de atención
    /**
    *
    * Obtener lista de tipos de puntos de atención por condiciones 
    * @return  tipos de puntos de atencion en json
    */
    public function getTipoPuntoAtencion(){                
        return response()->json(TipoPuntoAtencion::all()->toArray()); 
    }


}

