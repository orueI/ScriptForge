package com.oruel.scriptforge.token

open class TokenType(
    val expression: String? = null,
    val regex: String? = null,
    val argumentsSize: Int = 2,
    val precedence: Int
) {
    data object Equal : TokenType("==", precedence = 4)
    data object More : TokenType(">", precedence = 4)
    data object Less : TokenType("<", precedence = 4)

    data object And : TokenType("and", precedence = 2)
    data object Or : TokenType("or", precedence = 1)

    data object Access : TokenType(".", "\\.", precedence = 5)
    data object Invoke : TokenType("()", "\\(\\)", 1, precedence = 5)

    data object ConstString : TokenType(null, "\".*?\"", 0, precedence = 0)
    data object ConstList : TokenType(null, "\\[.*?\\]", 0, precedence = 0)
    data object Value : TokenType(null, "\\w+", 0, precedence = 0)

    data object OpeningBrackets : TokenType("(", null, 0, precedence = 0)
    data object ClosingBrackets : TokenType(")", null, 0, precedence = 0)
}