package com.oruel.conditionscript.script

import com.oruel.conditionscript.libs.messageStdLib
import com.oruel.scriptforge.FType3
import com.oruel.scriptforge.compiler.CommandNode
import com.oruel.scriptforge.engine.ScriptForgeScriptEngine

class ConditionScriptScriptEngine : ScriptForgeScriptEngine(messageStdLib) {

    override fun processingCommandNode(node: CommandNode): Any = when (node.type) {
        ConditionScriptTokenTypesProvider.Where -> processingWhereOperator(node)
        else -> super.processingCommandNode(node)
    }

    private fun processingWhereOperator(node: CommandNode) = run {
        val variable = node.left.processing()
        val fieldName =
            ConditionScriptTokenTypesProvider.Where.regex?.toRegex()?.find(node.token.value)?.groupValues?.get(1)
                .orEmpty()

        findFunction<FType3>(node.type.valuePath)
            .invokeWithEngine(
                this@ConditionScriptScriptEngine,
                variable,
                fieldName,
                node.right.processing().returnValue(),
            )
    }
}