package com.kazale.pontointeligente.api.pontointeligenteapi.services

import com.kazale.pontointeligente.api.pontointeligenteapi.documents.Empresa

interface EmpresaService {

    fun buscarPorCnpj(cnpj: String): Empresa?

    fun persistir(empresa: Empresa): Empresa

}
