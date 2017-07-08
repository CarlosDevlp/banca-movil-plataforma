<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class CuentaBancaria extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'CuentaBancaria';
	public $primaryKey='idCuentaBancaria';
}