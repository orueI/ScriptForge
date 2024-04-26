package com.oruel.scriptforge

import com.oruel.scriptforge.compiler.Compiler
import com.oruel.scriptforge.engine.AstEngine
import com.oruel.scriptforge.engine.ScriptForgeScriptEngine
import com.oruel.scriptforge.token.ScriptForgeTokenTypesProvider
import com.oruel.scriptforge.token.TokenTypesProvider

fun ScriptForgeRunner(
    engine: ScriptForgeScriptEngine = ScriptForgeScriptEngine(),
    tokenTypesProvider: ScriptForgeTokenTypesProvider = ScriptForgeTokenTypesProvider(),
) = Runner(engine, tokenTypesProvider)

class Runner(
    private val engine: AstEngine,
    tokenTypesProvider: TokenTypesProvider,
) {

    private val compiler = Compiler(tokenTypesProvider)

    fun loadLib(library: List<Pair<String, Any>>) = engine.loadLib(library)

    fun resetLibs() = engine.reset()

    fun compile(code: String) = compiler.compile(code)

    fun runCode(code: String) = code
        .let(::compile)
        .let(engine::execute)

    fun runPredicate(code: String): Boolean = runCode(code) as Boolean
}