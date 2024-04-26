package com.oruel.scriptforge

class ValueNotExistException(valuePath: String) : Exception("Value path: $valuePath")
class FunctionNotExistException(valuePath: String) : Exception("Function path: $valuePath")

class NodeNotExistException : Exception()