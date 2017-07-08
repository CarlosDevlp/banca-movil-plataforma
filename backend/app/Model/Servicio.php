<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class Servicio extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'Servicio';
	public $primaryKey='idServicio';
}