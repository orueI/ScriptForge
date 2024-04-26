package com.oruel.scriptforge.utils

import com.oruel.scriptforge.compiler.AstNode
import com.oruel.scriptforge.compiler.CommandNode
import com.oruel.scriptforge.compiler.ValueNode

//fun printTree(root: AstNode) {
//    val queue: Queue<AstNode> = LinkedList()
//    queue.add(root)
//
//    while (queue.isNotEmpty()) {
//        val size = queue.size
//        repeat(size) {
//            val node = queue.poll()
//            print("${node.token.value} ")
//
//            (node as? CommandNode)?.left?.let { queue.add(it) }
//            (node as? CommandNode)?.right?.let { queue.add(it) }
//        }
//        println()
//    }
//}
//
//fun printTreeDetails(root: AstNode) {
//    val queue: Queue<AstNode> = LinkedList()
//    queue.add(root)
//
//    while (queue.isNotEmpty()) {
//        val size = queue.size
//        repeat(size) {
//            val node = queue.poll()
//            print("${node.toCode()} ")
//
//            (node as? CommandNode)?.left?.let { queue.add(it) }
//            (node as? CommandNode)?.right?.let { queue.add(it) }
//        }
//        println()
//    }
//}

fun AstNode.toCode(): String = when (this) {
    is ValueNode -> token.value
    is CommandNode -> "${token.value}: ${left?.toCode().orEmpty()}${right?.toCode().orEmpty()}"
}