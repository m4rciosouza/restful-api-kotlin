package com.kazale.pontointeligente.api.pontointeligenteapi.services

import com.kazale.pontointeligente.api.pontointeligenteapi.documents.Funcionario

interface FuncionarioService {

    fun persistir(funcionario: Funcionario): Funcionario

    fun buscarPorCpf(cpf: String): Funcionario?

    fun buscarPorEmail(email: String): Funcionario?

    fun buscarPorId(id: String): Funcionario?

}
