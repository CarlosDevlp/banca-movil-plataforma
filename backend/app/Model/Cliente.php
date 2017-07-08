<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class Cliente extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'Cliente';
	public $primaryKey='idCliente';
}