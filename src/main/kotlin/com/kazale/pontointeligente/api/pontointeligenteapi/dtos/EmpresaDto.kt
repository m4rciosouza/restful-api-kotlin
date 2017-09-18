package com.kazale.pontointeligente.api.pontointeligenteapi.dtos

data class EmpresaDto (
        val razaoSocial: String,
        val cnpj: String,
        val id: String? = null
)
