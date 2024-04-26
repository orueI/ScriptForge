package com.oruel.conditionscript

import com.oruel.conditionscript.libs.MessageManagerLib
import com.oruel.conditionscript.mock.mockMessage
import com.oruel.conditionscript.script.ConditionScriptRunner
import kotlin.test.Test
import kotlin.test.assertTrue

class LoadTest {
    private val runner = ConditionScriptRunner()

    @Test
    fun compile() {
        val testCode = listOf(
            "message whereId \"3\"",
            "message whereId \"3\" and message.anyCorrect()",
            "message1 whereId \"3\" and message1.selected() == [1] or message1.selected() == [2 ,3]",
        )

        runner.loadLib(MessageManagerLib { mockMessage }.lib)
        testCode.forEach(runner::compile)
    }

    @Test
    fun runCodeWithBrackets() {
        val newPredicate = "message1 whereId \"3\" and message1.selected() == [1] or message1.selected() == [2 ,3]"

        runner.loadLib(MessageManagerLib { mockMessage }.lib)
        val result = runner.runPredicate(newPredicate)

        assertTrue(result)
    }
}