package com.xanthenite.isining.view.state.form

import com.xanthenite.isining.view.state.State

data class HireArtistState(
        val isLoading : Boolean = false,
        val isSuccess : String? = null,
        val error : String? = null,
        val artist_user_id : Int = 0,
        val price : String = "",
        val address : String = "",
        val description : String = ""
) : State
