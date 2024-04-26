package com.oruel.scriptforge.libs

import com.oruel.scriptforge.FunctionalType1

val stdLib = listOf(
    ".*\\.not" to FunctionalType1 { arg0: Boolean -> arg0.not() },
)
