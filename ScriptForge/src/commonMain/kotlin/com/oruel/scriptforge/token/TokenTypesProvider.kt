package com.oruel.scriptforge.token

abstract class TokenTypesProvider {

    abstract val sequenceTokenTypeFind: List<TokenType>

    internal fun entriesToRegex() =
        sequenceTokenTypeFind.joinToString(separator = "|") { it.regex ?: it.expression.orEmpty() }

    internal fun getTokenType(token: String) = sequenceTokenTypeFind
        .firstOrNull { it.expression == token || it.regex?.toRegex()?.matches(token) ?: false }
        ?: throw Exception("Not supported \"$token\"")
}