package com.oruel.scriptforge.engine

import kotlin.jvm.JvmInline

@JvmInline
value class ValuePath(private val _path: String) {
    val path get() = _path.lowercase()
}