package com.kazale.pontointeligente.api.pontointeligenteapi.utils

import org.junit.Assert
import org.junit.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder



/**
 * Created by marciosouza on 9/16/17.
 */
class SenhaUtilsTest {

    private val SENHA = "123456"
    private val bCryptEncoder = BCryptPasswordEncoder()

    @Test
    fun testGerarHashSenha() {
        val hash = SenhaUtils().gerarBCrypt(SENHA)
        Assert.assertTrue(bCryptEncoder.matches(SENHA, hash))
    }

}
