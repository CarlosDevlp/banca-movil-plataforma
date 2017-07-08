<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class Tarjeta extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'Tarjeta';
	public $primaryKey='idTarjeta';
}