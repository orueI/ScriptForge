package com.oruel.scriptforge.compiler

import com.oruel.scriptforge.token.Token

sealed interface AstNode {
    val token: Token
    val type get() = token.type

    fun asCommand() = this as? CommandNode
    fun asValue() = this as? ValueNode
}

class CommandNode(override val token: Token, val left: AstNode? = null, val right: AstNode? = null) : AstNode
class ValueNode(override val token: Token) : AstNode