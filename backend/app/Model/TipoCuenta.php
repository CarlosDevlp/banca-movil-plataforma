<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class TipoCuenta extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'TipoCuenta';
	public $primaryKey='TipoidCuenta';
}