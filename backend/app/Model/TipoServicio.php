<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class TipoServicio extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'TipoServicio';
	public $primaryKey='idTipoServicio';
}