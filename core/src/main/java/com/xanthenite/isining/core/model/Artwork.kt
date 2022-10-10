package com.xanthenite.isining.core.model

data class Artwork(
        val id : Int?,
        val title : String?,
        val description : String?,
        val length : String?,
        val width : String?,
        val type : String?,
        val price : String?,
        val pictures : List<String?>,
        val user_name : String?
)
