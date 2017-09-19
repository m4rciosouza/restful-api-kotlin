package com.kazale.pontointeligente.api.pontointeligenteapi.services.impl

import com.kazale.pontointeligente.api.pontointeligenteapi.documents.Empresa
import com.kazale.pontointeligente.api.pontointeligenteapi.repositories.EmpresaRepository
import com.kazale.pontointeligente.api.pontointeligenteapi.services.EmpresaService
import org.springframework.stereotype.Service

@Service
class EmpresaServiceImpl (val empresaRepository: EmpresaRepository) : EmpresaService {

    override fun buscarPorCnpj(cnpj: String) = empresaRepository.findByCnpj(cnpj)

    override fun persistir(empresa: Empresa) = empresaRepository.save(empresa)

}
