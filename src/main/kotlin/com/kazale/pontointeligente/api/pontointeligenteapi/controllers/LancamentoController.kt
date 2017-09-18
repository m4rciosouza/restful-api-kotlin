package com.kazale.pontointeligente.api.pontointeligenteapi.controllers

import com.kazale.pontointeligente.api.pontointeligenteapi.documents.Funcionario
import com.kazale.pontointeligente.api.pontointeligenteapi.documents.Lancamento
import com.kazale.pontointeligente.api.pontointeligenteapi.dtos.LancamentoDto
import com.kazale.pontointeligente.api.pontointeligenteapi.enums.TipoEnum
import com.kazale.pontointeligente.api.pontointeligenteapi.response.Response
import com.kazale.pontointeligente.api.pontointeligenteapi.services.FuncionarioService
import com.kazale.pontointeligente.api.pontointeligenteapi.services.LancamentoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import java.text.SimpleDateFormat

@RestController
@RequestMapping("/api/lancamentos")
class LancamentoController(val lancamentoService: LancamentoService,
                           val funcionarioService: FuncionarioService) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Value("\${paginacao.qtd_por_pagina}")
    val qtdPorPagina: Int = 15

    @GetMapping(value = "/funcionario/{funcionarioId}")
    fun listarPorFuncionarioId(@PathVariable("funcionarioId") funcionarioId: String,
                               @RequestParam(value = "pag", defaultValue = "0") pag: Int,
                               @RequestParam(value = "ord", defaultValue = "id") ord: String,
                               @RequestParam(value = "dir", defaultValue = "DESC") dir: String):
            ResponseEntity<Response<Page<LancamentoDto>>> {

        val response: Response<Page<LancamentoDto>> = Response<Page<LancamentoDto>>()

        val pageRequest: PageRequest = PageRequest(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord)
        val lancamentos: Page<Lancamento> =
                lancamentoService.buscarPorFuncionarioId(funcionarioId, pageRequest)

        val lancamentosDto: Page<LancamentoDto> =
                lancamentos.map { lancamento -> converterLancamentoDto(lancamento) }

        response.data = lancamentosDto
        return ResponseEntity.ok(response)
    }

    @GetMapping(value = "/{id}")
    fun listarPorId(@PathVariable("id") id: String): ResponseEntity<Response<LancamentoDto>> {
        val response: Response<LancamentoDto> = Response<LancamentoDto>()
        val lancamento: Lancamento? = lancamentoService.buscarPorId(id)

        if (lancamento == null) {
            response.erros.add("Lançamento não encontrado para o id $id")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = converterLancamentoDto(lancamento)
        return ResponseEntity.ok(response)
    }

	@PostMapping
	fun adicionar(@Valid @RequestBody lancamentoDto: LancamentoDto,
                  result: BindingResult): ResponseEntity<Response<LancamentoDto>> {
		val response: Response<LancamentoDto> = Response<LancamentoDto>()
		validarFuncionario(lancamentoDto, result)

		if (result.hasErrors()) {
            for (erro in result.allErrors) response.erros.add(erro.defaultMessage)
			return ResponseEntity.badRequest().body(response)
		}

		val lancamento: Lancamento = converterDtoParaLancamento(lancamentoDto, result)
		lancamentoService.persistir(lancamento)
        response.data = converterLancamentoDto(lancamento)
		return ResponseEntity.ok(response)
	}

	@PutMapping(value = "/{id}")
	fun atualizar(@PathVariable("id") id: String, @Valid @RequestBody lancamentoDto: LancamentoDto,
                  result: BindingResult): ResponseEntity<Response<LancamentoDto>> {

		val response: Response<LancamentoDto> = Response<LancamentoDto>()
		validarFuncionario(lancamentoDto, result)
		lancamentoDto.id = id
		val lancamento: Lancamento = converterDtoParaLancamento(lancamentoDto, result)

		if (result.hasErrors()) {
            for (erro in result.allErrors) response.erros.add(erro.defaultMessage)
			return ResponseEntity.badRequest().body(response)
		}

		lancamentoService.persistir(lancamento)
		response.data = converterLancamentoDto(lancamento)
		return ResponseEntity.ok(response)
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	fun remover(@PathVariable("id") id: String): ResponseEntity<Response<String>> {

		val response: Response<String> = Response<String>()
		val lancamento: Lancamento? = lancamentoService.buscarPorId(id)

		if (lancamento == null) {
			response.erros.add("Erro ao remover lançamento. Registro não encontrado para o id $id")
			return ResponseEntity.badRequest().body(response)
		}

		lancamentoService.remover(id)
		return ResponseEntity.ok(Response<String>())
	}

	private fun validarFuncionario(lancamentoDto: LancamentoDto, result: BindingResult) {
		if (lancamentoDto.funcionarioId == null) {
			result.addError(ObjectError("funcionario", "Funcionário não informado."))
			return
		}

		val funcionario: Funcionario? = funcionarioService.buscarPorId(lancamentoDto.funcionarioId)
		if (funcionario == null) {
			result.addError(ObjectError("funcionario", "Funcionário não encontrado. ID inexistente."));
		}
	}

	private fun converterLancamentoDto(lancamento: Lancamento): LancamentoDto =
            LancamentoDto(dateFormat.format(lancamento.data), lancamento.tipo.toString(),
                    lancamento.descricao, lancamento.localizacao,
                    lancamento.funcionarioId, lancamento.id)

    private fun converterDtoParaLancamento(lancamentoDto: LancamentoDto,
                                           result: BindingResult): Lancamento {
        if (lancamentoDto.id != null) {
            val lanc: Lancamento? = lancamentoService.buscarPorId(lancamentoDto.id!!)
            if (lanc == null) result.addError(ObjectError("lancamento", "Lançamento não encontrado."))
        }

        return Lancamento(dateFormat.parse(lancamentoDto.data), TipoEnum.valueOf(lancamentoDto.tipo!!),
                lancamentoDto.funcionarioId!!, lancamentoDto.descricao,
                lancamentoDto.localizacao, lancamentoDto.id)
	}

}
