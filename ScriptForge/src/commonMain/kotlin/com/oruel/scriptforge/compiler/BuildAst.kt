package com.oruel.scriptforge.compiler

import com.oruel.scriptforge.token.Token

fun buildAstTree(postfix: List<Token>): AstNode {
    val stack = mutableListOf<AstNode>()

    for (token in postfix) {
        if (token.type.argumentsSize == 0) {
            stack.add(ValueNode(token))
        } else {
            val right = stack.takeIf { it.isNotEmpty() && token.type.argumentsSize >= 1 }?.removeLast()
            val left = stack.takeIf { it.isNotEmpty() && token.type.argumentsSize >= 2 }?.removeLast()
            stack.add(CommandNode(token, left, right))
        }
    }

    return stack.removeLast()
}