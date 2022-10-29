package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Transaction

data class TransactionResponse(
        val data : List<Transaction> = emptyList(),
        override val state : State ,
        override val message : String?
): BaseResponse
