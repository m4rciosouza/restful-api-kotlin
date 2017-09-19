package com.kazale.pontointeligente.api.pontointeligenteapi.services.impl

import com.kazale.pontointeligente.api.pontointeligenteapi.documents.Funcionario
import com.kazale.pontointeligente.api.pontointeligenteapi.repositories.FuncionarioRepository
import com.kazale.pontointeligente.api.pontointeligenteapi.services.FuncionarioService
import org.springframework.stereotype.Service

@Service
class FuncionarioServiceImpl (val funcionarioRepository: FuncionarioRepository) : FuncionarioService {

    override fun persistir(funcionario: Funcionario) = funcionarioRepository.save(funcionario)

    override fun buscarPorCpf(cpf: String) = funcionarioRepository.findByCpf(cpf)

    override fun buscarPorEmail(email: String) = funcionarioRepository.findByEmail(email)

    override fun buscarPorId(id: String) = funcionarioRepository.findOne(id)

}
