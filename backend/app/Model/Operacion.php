<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class Operacion extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'Operacion';
	public $primaryKey='idOperacion';
}