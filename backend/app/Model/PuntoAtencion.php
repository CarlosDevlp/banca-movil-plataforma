<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class PuntoAtencion extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'PuntoAtencion';
	public $primaryKey='idPuntoAtencion';
}