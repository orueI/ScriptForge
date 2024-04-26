package com.oruel.conditionscript.libs

import com.oruel.conditionscript.Message
import com.oruel.conditionscript.NotImplementedException
import com.oruel.conditionscript.script.ConditionScriptTokenTypesProvider
import com.oruel.scriptforge.FunctionalType3
import com.oruel.scriptforge.engine.ValuePath

class MessageManagerLib(
    messageProvider: (Long) -> Message?,
) {

    val lib = listOf(
        ConditionScriptTokenTypesProvider.Where.expression!! to FunctionalType3 { variable: ValuePath, fieldName: String, value: Any ->
            val message = when (fieldName) {
                "id" -> messageProvider(value.toString().toLong())
                else -> throw NotImplementedException("Find by $fieldName not implemented")
            } ?: return@FunctionalType3 false

            saveValue(variable, message)
            true
        },
    )
}