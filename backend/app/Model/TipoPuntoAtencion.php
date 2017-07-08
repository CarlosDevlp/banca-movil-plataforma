<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class TipoPuntoAtencion extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'TipoPuntoAtencion';
	public $primaryKey='idTipoPuntoAtencion';
}
