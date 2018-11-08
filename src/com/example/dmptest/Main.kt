package com.example.dmptest

import java.io.BufferedReader
import java.io.FileReader
import java.util.*

fun main(args:Array<String>) {
    val dmp = diff_match_patch()
    val origin = readFile("data/base.bundle")
    val expected = readFile("data/expected.bundle")
    val patch = readFile("data/patch.txt").replace("\\n", "\n")
    val parsedPatches = LinkedList(dmp.patch_fromText(patch))
    val dmpResult = dmp.patch_apply(parsedPatches, origin)
    if (dmpResult == null) {
        println("Error - can't apply patch - dmpResult is null")
        return
    }
    val resultString = checkResult(dmpResult)
    if (resultString == "") {
        println("Error - can't apply patch - error during apply")
        return
    }

    println(if (resultString == expected) "SUCCESS" else "FAIL")
}

fun checkResult(dmpResult:Array<Any>):String {
    if (dmpResult.size != 2) {
        return ""
    }
    if (dmpResult[0] !is String) {
        return ""
    }
    if (dmpResult[1] !is BooleanArray) {
        return ""
    }
    val res = dmpResult[1] as BooleanArray
    if (!res.fold(true) { acc, it -> acc && it }) {
        return ""
    }

    return dmpResult[0] as String
}

fun readFile(path:String):String {
    val input = BufferedReader(FileReader(path))
    val sb = StringBuilder()
    var line = input.readLine()
    while (line != null) {
        sb.append(line)
        line = input.readLine()
    }
    return sb.toString()
}