package com.kazale.pontointeligente.api.pontointeligenteapi.dtos

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty
import org.hibernate.validator.constraints.br.CNPJ
import org.hibernate.validator.constraints.br.CPF

data class CadastroPJDto (

        @get:NotEmpty(message = "Nome não pode ser vazio.")
        @get:Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
        val nome: String = "",

        @get:NotEmpty(message = "Email não pode ser vazio.")
        @get:Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
        @get:Email(message="Email inválido.")
        val email: String = "",

        @get:NotEmpty(message = "Senha não pode ser vazia.")
        val senha: String = "",

        @get:NotEmpty(message = "CPF não pode ser vazio.")
        @get:CPF(message="CPF inválido")
        val cpf: String = "",

        @get:NotEmpty(message = "CNPJ não pode ser vazio.")
        @get:CNPJ(message="CNPJ inválido.")
        val cnpj: String = "",

        @get:NotEmpty(message = "Razão social não pode ser vazio.")
        @get:Length(min = 5, max = 200, message = "Razão social deve conter entre 5 e 200 caracteres.")
        val razaoSocial: String = "",

        val id: String? = null
)
