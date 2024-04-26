package com.oruel.conditionscript.mock

import com.oruel.conditionscript.Option

val mockCorrectSingleTrueOptions =
    listOf(CorrectTrueOption("1"), CorrectFalseOption("2"), CorrectFalseOption("3"), CorrectFalseOption("4"))
val mockCorrectMultiTrueOptions =
    listOf(CorrectTrueOption("1"), CorrectFalseOption("2"), CorrectTrueOption("3"), CorrectTrueOption("4"))

val mockAllIncorrectSingleTrueOptions =
    listOf(IncorrectTrueOption("1"), CorrectFalseOption("2"), IncorrectFalseOption("3"), CorrectFalseOption("4"))

fun CorrectTrueOption(text: String) = Option(
    text = text,
    isCorrected = true,
    isSelected = true,
)

fun CorrectFalseOption(text: String) = Option(
    text = text,
    isCorrected = false,
    isSelected = false,
)

fun IncorrectTrueOption(text: String) = Option(
    text = text,
    isCorrected = true,
    isSelected = false,
)

fun IncorrectFalseOption(text: String) = Option(
    text = text,
    isCorrected = false,
    isSelected = true,
)