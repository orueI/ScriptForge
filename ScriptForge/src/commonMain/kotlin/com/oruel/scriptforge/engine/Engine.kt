package com.oruel.scriptforge.engine

import com.oruel.scriptforge.*
import com.oruel.scriptforge.utils.first

abstract class Engine(private val initValues: List<Pair<String, Any>>) {

    protected val _values: MutableMap<String, Any> = mutableMapOf(*initValues.toTypedArray())
    val values: Map<String, Any> = _values

    fun reset() {
        _values.apply {
            clear()
            putAll(initValues)
        }
    }

    fun loadLib(library: List<Pair<String, Any>>) {
        _values.putAll(library)
    }

    inline fun <reified T : FunctionalType> findFunction(path: ValuePath): FunctionalType = values
        .first { it.key.lowercase().toRegex().matches(path.path) && it.value is T }
        ?.let { it as? T }
        ?: throw FunctionNotExistException(path.path)

    fun findValue(path: ValuePath): Any = _values
        .first { it.key.lowercase() == path.path && it.value !is FunctionalType1<*> }
        ?: throw ValueNotExistException(path.path)

    fun saveValue(path: ValuePath, value: Any): ValuePath {
        _values[path.path] = value
        return path
    }

    fun ValuePath.returnValue(): Any = findValue(this)
}

inline fun <reified T : FunctionalType> Engine.executeFunction(functionPath: ValuePath, argumentPaths: List<Any>) =
    with(findFunction<T>(functionPath)) {
        invoke(*argumentPaths.map { if (it is ValuePath) findValue(it) else it }.toTypedArray())
    }

fun Engine.executeFunction(functionPath: ValuePath, argumentPath: ValuePath) =
    with(findFunction<FunctionalType1<*>>(functionPath)) {
        invoke(findValue(argumentPath))
    }

fun Engine.executeFunction(
    functionPath: ValuePath,
    argument1Path: ValuePath,
    argument2Path: ValuePath
) = with(findFunction<FunctionalType2<*, *>>(functionPath)) {
    invoke(findValue(argument1Path), findValue(argument2Path))
}

fun Engine.executeFunction(
    functionPath: ValuePath,
    argument1Path: ValuePath,
    argument2Path: ValuePath,
    argument3Path: ValuePath,
) = with(findFunction<FunctionalType3<*, *, *>>(functionPath)) {
    invoke(
        findValue(argument1Path),
        findValue(argument2Path),
        findValue(argument3Path),
    )
}