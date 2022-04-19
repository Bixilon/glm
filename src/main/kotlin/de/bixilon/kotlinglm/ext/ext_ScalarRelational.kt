package de.bixilon.kotlinglm.ext

import de.bixilon.kotlinglm.GLM

interface ext_ScalarRelational {

    fun equal(x: Float, y: Float, epsilon: Float) = GLM.abs(x - y) < epsilon
    fun equal(x: Byte, y: Byte, epsilon: Byte) = GLM.abs(x - y) < epsilon
    fun equal(x: Double, y: Double, epsilon: Double) = GLM.abs(x - y) < epsilon
    fun equal(x: Int, y: Int, epsilon: Int) = GLM.abs(x - y) < epsilon
    fun equal(x: Long, y: Long, epsilon: Long) = GLM.abs(x - y) < epsilon
    fun equal(x: Short, y: Short, epsilon: Short) = GLM.abs(x - y) < epsilon

    fun notEqual(x: Float, y: Float, epsilon: Float) = GLM.abs(x - y) >= epsilon
    fun notEqual(x: Byte, y: Byte, epsilon: Byte) = GLM.abs(x - y) >= epsilon
    fun notEqual(x: Double, y: Double, epsilon: Double) = GLM.abs(x - y) >= epsilon
    fun notEqual(x: Int, y: Int, epsilon: Int) = GLM.abs(x - y) >= epsilon
    fun notEqual(x: Long, y: Long, epsilon: Long) = GLM.abs(x - y) >= epsilon
    fun notEqual(x: Short, y: Short, epsilon: Short) = GLM.abs(x - y) >= epsilon
}

fun Float.equal(y: Float, epsilon: Float = Float.MIN_VALUE) = GLM.abs(minus(y)) < epsilon
fun Byte.equal(y: Byte, epsilon: Byte = 0) = GLM.abs(minus(y)) < epsilon
fun Double.equal(y: Double, epsilon: Double = Double.MIN_VALUE) = GLM.abs(minus(y)) < epsilon
fun Int.equal(y: Int, epsilon: Int = 0) = GLM.abs(minus(y)) < epsilon
fun Long.equal(y: Long, epsilon: Long = 0) = GLM.abs(minus(y)) < epsilon
fun Short.equal(y: Short, epsilon: Short = 0) = GLM.abs(minus(y)) < epsilon

fun Float.notEqual(y: Float, epsilon: Float = Float.MIN_VALUE) = GLM.abs(minus(y)) >= epsilon
fun Byte.notEqual(y: Byte, epsilon: Byte = 0) = GLM.abs(minus(y)) >= epsilon
fun Double.notEqual(y: Double, epsilon: Double = Double.MIN_VALUE) = GLM.abs(minus(y)) >= epsilon
fun Int.notEqual(y: Int, epsilon: Int = 0) = GLM.abs(minus(y)) >= epsilon
fun Long.notEqual(y: Long, epsilon: Long = 0) = GLM.abs(minus(y)) >= epsilon
fun Short.notEqual(y: Short, epsilon: Short = 0) = GLM.abs(minus(y)) >= epsilon
