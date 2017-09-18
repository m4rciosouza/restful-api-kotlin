package com.kazale.pontointeligente.api.pontointeligenteapi.response

data class Response<T> (
        val erros: ArrayList<String> = arrayListOf(),
        var data: T? = null
)
