package com.oruel.scriptforge.token

class ScriptForgeTokenTypesProvider : TokenTypesProvider() {

    override val sequenceTokenTypeFind: List<TokenType> = listOf(
        TokenType.Equal,
        TokenType.More,
        TokenType.Less,
        TokenType.And,
        TokenType.Or,
        TokenType.Access,
        TokenType.Invoke,
        TokenType.ConstString,
        TokenType.ConstList,
        TokenType.Value
    )
}