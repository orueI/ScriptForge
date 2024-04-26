package com.oruel.scriptforge.compiler

import com.oruel.scriptforge.token.ScriptForgeTokenTypesProvider
import com.oruel.scriptforge.token.TokenTypesProvider

class Compiler(tokenTypesProvider: TokenTypesProvider = ScriptForgeTokenTypesProvider()) {

    private val tokenizer = Tokenizer(tokenTypesProvider)
    private val reversePolishNotationSorter = ReversePolishNotationSorter(tokenTypesProvider)

    fun compile(code: String) = code
        .let(tokenizer::tokenize)
        .toList()
        .let(reversePolishNotationSorter::shuntingYardSort)
        .let(::buildAstTree)

}