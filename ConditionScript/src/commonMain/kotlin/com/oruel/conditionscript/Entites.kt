package com.oruel.conditionscript

enum class MessageType {
    Text, SingleChoiceTask, MultiChoiceTask, SingleChoiceAnswer, Images, Videos, EnterText
}

data class Option(
    val text: String,
    val isCorrected: Boolean,
    val isSelected: Boolean,
)