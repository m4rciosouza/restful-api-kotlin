package com.kazale.pontointeligente.api.pontointeligenteapi.services.impl

import com.kazale.pontointeligente.api.pontointeligenteapi.documents.Lancamento
import com.kazale.pontointeligente.api.pontointeligenteapi.repositories.LancamentoRepository
import com.kazale.pontointeligente.api.pontointeligenteapi.services.LancamentoService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LancamentoServiceImpl (val lancamentoRepository: LancamentoRepository) : LancamentoService {

    override fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest) =
            lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest)

    override fun buscarPorId(id: String) = lancamentoRepository.findOne(id)

    override fun persistir(lancamento: Lancamento) = lancamentoRepository.save(lancamento)

    override fun remover(id: String) = lancamentoRepository.delete(id)

}
