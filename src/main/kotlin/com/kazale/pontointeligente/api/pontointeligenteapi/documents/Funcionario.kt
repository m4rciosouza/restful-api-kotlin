package com.kazale.pontointeligente.api.pontointeligenteapi.documents

import com.kazale.pontointeligente.api.pontointeligenteapi.enums.PerfilEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Funcionario (
        val nome: String,
        val email: String,
        val senha: String,
        val cpf: String,
        val perfil: PerfilEnum,
        val empresaId: String,
        val valorHora: Double? = 0.0,
        val qtdHorasTrabalhoDia: Float? = 0.0f,
        val qtdHorasAlmoco: Float? = 0.0f,
        @Id val id: String? = null
)
