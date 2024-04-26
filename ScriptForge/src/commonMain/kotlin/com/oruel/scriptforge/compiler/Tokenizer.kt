package com.oruel.scriptforge.compiler

import com.oruel.scriptforge.token.Token
import com.oruel.scriptforge.token.TokenTypesProvider

internal class Tokenizer(private val tokenTypesProvider: TokenTypesProvider) {
    fun tokenize(code: String) = tokenTypesProvider.entriesToRegex().toRegex()
        .findAll(code.lowercase())
        .map { it.value }
        .map { Token(it, tokenTypesProvider.getTokenType(it)) }
}
