package com.oruel.conditionscript.script

import com.oruel.scriptforge.token.TokenType
import com.oruel.scriptforge.token.TokenTypesProvider

class ConditionScriptTokenTypesProvider : TokenTypesProvider() {

    data object Where : TokenType("where", "where(\\w*)", argumentsSize = 2, precedence = 4)

    override val sequenceTokenTypeFind: List<TokenType> = listOf(
        TokenType.Equal,
        TokenType.More,
        TokenType.Less,
        TokenType.And,
        TokenType.Or,
        TokenType.Access,
        TokenType.Invoke,
        Where,
        TokenType.ConstString,
        TokenType.ConstList,
        TokenType.Value
    )
}