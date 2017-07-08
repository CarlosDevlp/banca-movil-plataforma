<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;


class TipoOperacion extends Model
{
     /**
     * Indicates if the model should be timestamped.
     *
     * @var bool
     */
    public $timestamps = false;
    public $table= 'TipoOperacion';
	public $primaryKey='idTipoOperacion';
}