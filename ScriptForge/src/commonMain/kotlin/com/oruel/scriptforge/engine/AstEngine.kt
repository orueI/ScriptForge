package com.oruel.scriptforge.engine

import co.touchlab.kermit.Logger
import com.oruel.scriptforge.compiler.AstNode
import com.oruel.scriptforge.compiler.CommandNode
import com.oruel.scriptforge.compiler.ValueNode
import com.oruel.scriptforge.token.TokenType
import com.oruel.scriptforge.utils.toCode

abstract class AstEngine(initValues: List<Pair<String, Any>>) : Engine(initValues) {

    fun execute(astNode: AstNode): Any = astNode.processing().returnValue()

    protected open fun AstNode?.processing(): ValuePath {
        require(this != null)
        val cachePath = getCachePath(this)

        if (hasCacheValue(this).not()) {
            val value = when (this) {
                is ValueNode -> processingValueNode(this)
                is CommandNode -> processingCommandNode(this)
            }

            if (value is ValuePath) {
                return value
            }

            saveValue(cachePath, value)
        }
        Logger.i("Processed ast node: ${this.toCode()}")
        return cachePath
    }

    protected abstract fun processingCommandNode(node: CommandNode): Any
    protected abstract fun processingValueNode(node: ValueNode): Any

    private fun hasCacheValue(node: AstNode) = _values.containsKey(getCachePath(node).path)

    private fun getCachePath(node: AstNode) = ValuePath(node.toString())
    fun AstNode.newValuePath(newValuePath: String) = ValuePath(this.toString() + newValuePath)

    val TokenType.valuePath get() = ValuePath(this.expression.toString())
}