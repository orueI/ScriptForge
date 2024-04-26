package com.oruel.conditionscript.libs

import com.oruel.conditionscript.Message
import com.oruel.scriptforge.FunctionalType1

val messageStdLib = listOf(
    "message\\w*.allCorrect" to FunctionalType1 { message: Message ->
        message.options?.all { it.isCorrected == it.isSelected } ?: false
    },

    "message\\w*.anyCorrect" to FunctionalType1 { message: Message ->
        message.options?.any { it.isCorrected == it.isSelected } ?: false
    },

    "message\\w*.allIncorrect" to FunctionalType1 { message: Message ->
        message.options?.all { it.isCorrected == it.isSelected }?.not() ?: false
    },

    "message\\w*.anyIncorrect" to FunctionalType1 { message: Message ->
        message.options?.any { it.isCorrected == it.isSelected }?.not() ?: false
    },
    "message\\w*.selected" to FunctionalType1 { message: Message ->
        val indexes: List<Int> = message.options
            ?.withIndex()
            ?.filter { it.value.isSelected }
            ?.map { it.index + 1 }
            ?: listOf()

        indexes
    },
)