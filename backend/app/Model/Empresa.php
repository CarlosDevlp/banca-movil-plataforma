<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class Empresa extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'Empresa';
	public $primaryKey='idEmpresa';
}