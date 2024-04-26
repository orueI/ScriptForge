package com.oruel.scriptforge.utils

fun String.isPrimitive() = (
        null
            ?: toIntOrNull()
            ?: toBooleanStrictOrNull()
        ) != null

fun String.toPrimitive() = null
    ?: toIntOrNull()
    ?: toBooleanStrict()