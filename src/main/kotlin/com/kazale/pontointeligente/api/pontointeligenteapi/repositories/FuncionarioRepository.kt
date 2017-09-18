package com.kazale.pontointeligente.api.pontointeligenteapi.repositories

import com.kazale.pontointeligente.api.pontointeligenteapi.documents.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository

interface FuncionarioRepository: MongoRepository<Funcionario, String> {

    fun findByCpf(cpf: String): Funcionario

    fun findByEmail(email: String): Funcionario

}
