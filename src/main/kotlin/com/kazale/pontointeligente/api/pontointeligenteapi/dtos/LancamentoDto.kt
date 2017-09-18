package com.kazale.pontointeligente.api.pontointeligenteapi.dtos

import org.hibernate.validator.constraints.NotEmpty

data class LancamentoDto (

        @get:NotEmpty(message = "Data não pode ser vazia.")
        val data: String? = null,

        @get:NotEmpty(message = "Tipo não pode ser vazio.")
        val tipo: String? = null,

        val descricao: String? = null,
        val localizacao: String? = null,
        val funcionarioId: String? = null,
        var id: String? = null
)
