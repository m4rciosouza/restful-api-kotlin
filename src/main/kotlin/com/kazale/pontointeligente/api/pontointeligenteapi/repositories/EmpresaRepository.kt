package com.kazale.pontointeligente.api.pontointeligenteapi.repositories

import com.kazale.pontointeligente.api.pontointeligenteapi.documents.Empresa
import org.springframework.data.mongodb.repository.MongoRepository

interface EmpresaRepository: MongoRepository<Empresa, String> {

    fun findByCnpj(cnpj: String): Empresa

}
