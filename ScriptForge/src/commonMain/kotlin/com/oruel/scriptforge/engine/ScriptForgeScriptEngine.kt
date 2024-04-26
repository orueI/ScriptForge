package com.oruel.scriptforge.engine

import com.oruel.scriptforge.NodeNotExistException
import com.oruel.scriptforge.compiler.CommandNode
import com.oruel.scriptforge.compiler.ValueNode
import com.oruel.scriptforge.libs.stdLib
import com.oruel.scriptforge.libs.tokenLib
import com.oruel.scriptforge.token.TokenType
import com.oruel.scriptforge.utils.isPrimitive
import com.oruel.scriptforge.utils.toPrimitive

open class ScriptForgeScriptEngine(
    initValues: List<Pair<String, Any>> = listOf()
) : AstEngine(tokenLib + stdLib + initValues) {

    override fun processingCommandNode(node: CommandNode): Any = when (node.type) {
        TokenType.Access -> ValuePath(node.left?.processing()?.path + "." + node.right?.processing()?.path)

        TokenType.Invoke -> node.right?.asCommand()
            ?.let { executeFunction(it.processing(), it.left.processing()) }
            ?: throw NodeNotExistException()

        TokenType.Equal,
        TokenType.More,
        TokenType.Less,

        TokenType.And,
        TokenType.Or,
        -> executeFunction(node.type.valuePath, node.left.processing(), node.right.processing())


        else -> node.token.value
    }

    override fun processingValueNode(node: ValueNode): Any = when {
        node.type == TokenType.ConstString -> node.token.value.trim('"')
        node.type == TokenType.ConstList -> node.token.value
            .trim('[', ']')
            .split(",")
            .map(::convertCodeToPrimitive)

        node.token.value.isPrimitive() -> node.token.value.toPrimitive()
        else -> ValuePath(node.token.value)
    }

    private fun convertCodeToPrimitive(code: String): Any = code.trim().let {
        when {
            TokenType.ConstString.regex?.toRegex()?.matches(it) == true -> it.trim('"')
            else -> it.toPrimitive()
        }
    }
}