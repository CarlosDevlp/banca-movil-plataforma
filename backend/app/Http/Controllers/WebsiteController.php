<?php 
namespace App\Http\Controllers;
use Illuminate\Support\Facades\View;

class WebsiteController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */

    private $ASSET_PATH='../../assets/web/';
    private $WEB_DETAILS=['APP_NAME'=>'UpnBank',
    					  'TITLE'=>'UpnBank | mejores soluciones bancarias',
    					  'META'=>'upn es una empresa que se destaca por la garantía que le da al cliente de un ahorro y éxito asegurado.',
    					  'MENU'=>[
    					  			'HOME'=>'home',
    					  			'ABOUT_US'=>'Sobre Nosotros',
    					  			'SERVICES'=>'Servicios',
    					  			'REGISTRATION'=>'Registrate',		
    					  			'CONTACT_US'=>'Contáctanos'
    					  			],
    					  'FOOTER'=>'Desarrollado por Carlos Chavez Laguna'];

    public function __construct()
    {
        
    }

    
    public function indexPage(){
    	

    	return view('home')
    				->with('ASSET_PATH',$this->ASSET_PATH)
    				->with('PREVENT_DEFAULT',true)
    				->with('WEB_DETAILS',$this->WEB_DETAILS)
    				->with('content',['banner'=>
	    								['<h3>Banco de confianza</h3>'.
	    								 '<p>La mejor tecnología y la mejor seguridad que respaldan tu economía. </p>',

	    								 '<h3>Ahorro que se demuestra</h3>'.
	    								 '<p>Registrate ya y empieza tu nuevo camino al éxito</p>',

	    											],
                                     'services'=>['claro','bitel','movistar','entel','upc','upn','enel','sedapal']
                                     ]
	    				);

    }

    public function registerPage(){
        return view('registration')
        			->with('ASSET_PATH',$this->ASSET_PATH)
        			->with('PREVENT_DEFAULT',false)
    				->with('WEB_DETAILS',$this->WEB_DETAILS)
    				->with('content','');
    }

    public function registerClientPage(){
        return view('registration_client')
        			->with('ASSET_PATH',$this->ASSET_PATH)
        			->with('PREVENT_DEFAULT',false)
    				->with('WEB_DETAILS',$this->WEB_DETAILS)
    				->with('content','');
    }
}