package com.oruel.scriptforge.compiler

import com.oruel.scriptforge.token.Token
import com.oruel.scriptforge.token.TokenType
import com.oruel.scriptforge.token.TokenTypesProvider
import com.oruel.scriptforge.utils.peekLast

internal class ReversePolishNotationSorter(tokenTypesProvider: TokenTypesProvider) {
    private val precedence =
        tokenTypesProvider.sequenceTokenTypeFind.map { it to it.precedence }.filter { it.second > 0 }.toMap()

    fun shuntingYardSort(input: List<Token>): List<Token> {
        val output = mutableListOf<Token>()
        val operators = ArrayDeque<Token>()

        for (token in input) {
            when {
                token.type.argumentsSize == 0 -> output.add(token)
                token.type is TokenType.OpeningBrackets -> operators.addLast(token)
                token.type is TokenType.ClosingBrackets -> {
                    while (operators.isNotEmpty() && operators.peekLast()?.type is TokenType.OpeningBrackets) {
                        output.add(operators.removeLast())
                    }
                    operators.removeLast()
                }

                precedence.containsKey(token.type) -> {
                    while (operators.isNotEmpty() && precedence.getOrElse(token.type) { 0 } <= precedence.getOrElse(
                            operators.peekLast()!!.type
                        ) { 0 }) {
                        output.add(operators.removeLast())
                    }
                    operators.addLast(token)
                }
            }
        }

        while (operators.isNotEmpty()) {
            output.add(operators.removeLast())
        }

        return output.toList()
    }

//    fun shuntingYard(tokens: List<TokenType>): List<String> {
//        val outputQueue = mutableListOf<String>()
//        val operatorStack = mutableListOf<TokenType>()
//
//        tokens.forEach { token ->
//            when (token) {
//                is TokenType.Value, is TokenType.ConstString, is TokenType.ConstList -> outputQueue.add(token.expression!!)
//                is TokenType.OpeningBrackets -> operatorStack.add(token)
//                is TokenType.ClosingBrackets -> {
//                    while (operatorStack.isNotEmpty() && operatorStack.last() != TokenType.OpeningBrackets) {
//                        outputQueue.add(operatorStack.removeLast().expression!!)
//                    }
//                    operatorStack.removeLast() // Remove the opening bracket
//                }
//                else -> { // Operator
//                    while (operatorStack.isNotEmpty() &&
//                        operatorStack.last() != TokenType.OpeningBrackets &&
//                        token.precedence <= operatorStack.last().precedence) {
//                        outputQueue.add(operatorStack.removeLast().expression!!)
//                    }
//                    operatorStack.add(token)
//                }
//            }
//        }
//
//        // Add remaining operators from the stack
//        while (operatorStack.isNotEmpty()) {
//            outputQueue.add(operatorStack.removeLast().expression!!)
//        }
//
//        return outputQueue
//    }


//    fun shuntingYardSort(input: List<Token>): List<Token> {
//        val output = mutableListOf<Token>()
//        val operators = ArrayDeque<Token>()
//
//        for (token in input) {
//            when {
//                token.type.argumentsSize == 0 -> output.add(token)
//                token.type is TokenType.OpeningBrackets -> operators.addLast(token)
//                token.type is TokenType.ClosingBrackets -> {
//                    while (operators.isNotEmpty() && operators.lastOrNull()?.type is TokenType.OpeningBrackets) {
//                        output.add(operators.removeLast())
//                    }
//                    operators.removeLast()
//                }
//
//                precedence.containsKey(token.type) -> {
//                    while (operators.isNotEmpty() && precedence.getOrElse(token.type) { 0 } <= precedence.getOrElse(
//                            operators.last().type
//                        ) { 0 }) {
//                        output.add(operators.removeLast())
//                    }
//                    operators.addLast(token)
//                }
//            }
//        }
//
//        while (operators.isNotEmpty()) {
//            output.add(operators.removeLast())
//        }
//
//        return output.toList()
//    }
}