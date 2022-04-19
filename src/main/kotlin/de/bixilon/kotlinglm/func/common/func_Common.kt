package de.bixilon.kotlinglm.func.common

import de.bixilon.kotlinglm.*
import de.bixilon.kotlinglm.GLM.ceil
import de.bixilon.kotlinglm.GLM.floor
import de.bixilon.kotlinglm.GLM.fma
import de.bixilon.kotlinglm.GLM.fract
import de.bixilon.kotlinglm.GLM.isInf
import de.bixilon.kotlinglm.GLM.isNan
import de.bixilon.kotlinglm.GLM.max
import de.bixilon.kotlinglm.GLM.min
import de.bixilon.kotlinglm.GLM.mix
import de.bixilon.kotlinglm.GLM.sign
import de.bixilon.kotlinglm.GLM.smoothStep
import de.bixilon.kotlinglm.GLM.step
import de.bixilon.kotlinglm.GLM.trunc
import unsigned.Uint
import unsigned.Ulong
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sign
import kotlin.reflect.KMutableProperty0
import kotlin.math.ceil as _ceil
import kotlin.math.floor as _floor
import kotlin.math.max as _max
import kotlin.math.min as _min
import kotlin.math.round as _round


/**
 * Created by elect on 11/11/16.
 */
interface func_common {

    fun abs(a: Float) = a.absoluteValue
    fun abs(a: Double) = a.absoluteValue
    fun abs(a: Byte) = a.i.absoluteValue.b
    fun abs(a: Int) = a.absoluteValue
    fun abs(a: Long) = a.absoluteValue
    fun abs(a: Short) = a.i.absoluteValue.s


    fun sign(a: Float) = a.sign
    fun sign(a: Double) = a.sign
    fun sign(a: Byte) = a.i.sign.b
    fun sign(a: Int) = a.sign
    fun sign(a: Long) = a.sign.L
    fun sign(a: Short) = a.i.sign.s


    fun floor(a: Float) = _floor(a)
    fun floor(a: Double) = _floor(a)


    fun trunc(a: Float) = if (a < 0) -floor(-a) else floor(a)
    fun trunc(a: Double) = if (a < 0) -floor(-a) else floor(a)


    fun round(a: Float) = when {
        a >= 0 -> Math.round(a).f
        else -> {
            val i = floor(a) // integer portion
            val f = a - i // fractional portion
            if (f <= 0.5f) i else i + 1f // round integer portion based on fractional portion
        }
    }

    fun round(a: Double) = when {
        a >= 0 -> Math.round(a).d
        else -> {
            val i = floor(a) // integer portion
            val f = a - i // fractional portion
            if (f <= 0.5) i else i + 1.0 // round integer portion based on fractional portion
        }
    }


    fun roundEven(a: Float) = _round(a)
    fun roundEven(a: Double) = _round(a)


    fun ceil(a: Float) = _ceil(a)
    fun ceil(a: Double) = _ceil(a)


    fun fract(a: Float) = a - floor(a)
    fun fract(a: Double) = a - floor(a)


    fun mod(a: Float, b: Float) = a - b * floor(a / b)
    fun mod(a: Double, b: Double) = a - b * floor(a / b)

    fun modf(a: Float, b: KMutableProperty0<Float>): Float {
        val res = a % 1f
        b.set(a - res)
        return res
    }

    fun modf(a: Double, b: KMutableProperty0<Double>): Double {
        val res = a % 1.0
        b.set(a - res)
        return res
    }


    fun min(a: Float, b: Float): Float = _min(a, b)
    fun min(a: Double, b: Double): Double = _min(a, b)
    fun min(a: Byte, b: Byte): Byte = _min(a.i, b.i).b
    fun min(a: Int, b: Int): Int = _min(a, b)
    fun min(a: Long, b: Long): Long = _min(a, b)
    fun min(a: Short, b: Short): Short = _min(a.i, b.i).s
    fun min(a: Char, b: Char): Char = if (a < b) a else b

    fun max(a: Float, b: Float): Float = _max(a, b)
    fun max(a: Double, b: Double): Double = _max(a, b)
    fun max(a: Byte, b: Byte): Byte = _max(a.i, b.i).b
    fun max(a: Int, b: Int): Int = _max(a, b)
    fun max(a: Long, b: Long): Long = _max(a, b)
    fun max(a: Short, b: Short): Short = _max(a.i, b.i).s
    fun max(a: Char, b: Char): Char = if (a < b) b else a


    fun clamp(a: Float, min: Float, max: Float) = min(max(a, min), max)
    fun clamp(a: Double, min: Double, max: Double) = min(max(a, min), max)
    fun clamp(a: Byte, min: Byte, max: Byte) = min(max(a, min), max)
    fun clamp(a: Int, min: Int, max: Int) = min(max(a, min), max)
    fun clamp(a: Long, min: Long, max: Long) = min(max(a, min), max)
    fun clamp(a: Short, min: Short, max: Short) = min(max(a, min), max)
    fun clamp(a: Char, min: Char, max: Char) = min(max(a, min), max)


    fun mix(a: Float, b: Float, interp: Float) = a + interp * (b - a)
    fun mix(a: Double, b: Double, interp: Double) = a + interp * (b - a)

    fun mix(a: Float, b: Float, interp: Boolean) = if (interp) b else a
    fun mix(a: Double, b: Double, interp: Boolean) = if (interp) b else a


    fun step(edge: Float, a: Float) = mix(1f, 0f, a < edge)

    fun step(edge: Double, a: Double) = mix(1.0, 0.0, a < edge)

    fun smoothStep(edge0: Float, edge1: Float, a: Float): Float {
        val tmp = clamp((a - edge0) / (edge1 - edge0), 0f, 1f)
        return tmp * tmp * (3 - 2 * tmp)
    }

    fun smoothStep(edge0: Double, edge1: Double, a: Double): Double {
        val tmp = clamp((a - edge0) / (edge1 - edge0), 0.0, 1.0)
        return tmp * tmp * (3 - 2 * tmp)
    }


    fun isNan(a: Float) = a.isNaN()
    fun isNan(a: Double) = a.isNaN()

    fun isInf(a: Float) = a.isInfinite()
    fun isInf(a: Double) = a.isInfinite()


    fun floatBitsToInt(a: Float) = a.toRawBits()
    fun floatBitsToUint(a: Float) = Uint(a.toRawBits())
    fun intBitsToFloat(a: Int) = Float.fromBits(a)
    fun uintBitsToFloat(a: Uint) = Float.fromBits(a.v)


    fun doubleBitsToLong(a: Double) = a.toRawBits()
    fun doubleBitsToUlong(a: Double) = Ulong(a.toRawBits())
    fun intBitsToDouble(a: Long) = Double.fromBits(a)
    fun ulongBitsToFloat(a: Ulong) = Double.fromBits(a.v)


    fun fma(a: Float, b: Float, c: Float) = a * b + c
    fun fma(a: Double, b: Double, c: Double) = a * b + c


    fun frexp(a: Float, exp: KMutableProperty0<Int>): Float {

        val bits = a.asIntBits
        var realMant: Float

        // Test for NaN, infinity, and zero.
        return when {
            a.isNaN || a + a == a || a.isInfinite -> {
                exp.set(0)
                a
            }
            else -> {
                val neg = bits < 0
                var exponent = (bits ushr 23) and 0xff
                var mantissa = bits and 0xffffff

                if (exponent == 0)
                    exponent++
                else
                    mantissa = mantissa or (1 shl 23)

                /*  bias the exponent - actually biased by 127.
                    we are treating the mantissa as m.0 instead of 0.m so subtract another 23.  */
                exponent -= 150
                realMant = mantissa.f

                // normalize
                while (realMant > 1f) {
                    mantissa = mantissa ushr 1
                    realMant /= 2f
                    exponent++
                }

                if (neg)
                    realMant *= -1

                exp.set(exponent)
                realMant
            }
        }
    }

    fun frexp(a: Double, exp: KMutableProperty0<Int>): Double {

        val bits = a.asLongBits
        var realMant: Double

        // Test for NaN, infinity, and zero.
        return when {
            a.isNaN || a + a == a || a.isInfinite -> {
                exp.set(0)
                a
            }
            else -> {
                val neg = bits < 0
                var exponent = ((bits ushr 52) and 0x7ffL).i
                var mantissa = bits and 0xfffffffffffffL

                if (exponent == 0)
                    exponent++
                else
                    mantissa = mantissa or (1L shl 52)

                /*  bias the exponent - actually biased by 1023.
                    we are treating the mantissa as m.0 instead of 0.m so subtract another 52.  */
                exponent -= 1075
                realMant = mantissa.d

                // normalize
                while (realMant > 1.0) {
                    mantissa = mantissa ushr 1
                    realMant /= 2.0
                    exponent++
                }

                if (neg)
                    realMant *= -1

                exp.set(exponent)
                realMant
            }
        }
    }


    fun ldexp(a: Float, exp: Int) = a * 2f.pow(exp)
    fun ldexp(a: Double, exp: Int) = a * 2.0.pow(exp)
}


val Float.abs get() = this.absoluteValue
val Double.abs get() = this.absoluteValue
val Byte.abs get() = i.absoluteValue.b
val Int.abs get() = this.absoluteValue
val Long.abs get() = this.absoluteValue
val Short.abs get() = i.absoluteValue.s


val Byte.sign get() = sign(this)
val Short.sign get() = sign(this)


val Float.floor get() = floor(this)
val Double.floor get() = floor(this)


val Float.trunc get() = trunc(this)
val Double.trunc get() = trunc(this)


val Float.ceil get() = ceil(this)
val Double.ceil get() = ceil(this)


val Float.fract get() = fract(this)
val Double.fract get() = fract(this)


infix fun Float.min(b: Float) = min(this, b)
infix fun Double.min(b: Double) = min(this, b)
infix fun Byte.min(b: Byte) = min(this, b)
infix fun Int.min(b: Int) = min(this, b)
infix fun Long.min(b: Long) = min(this, b)
infix fun Short.min(b: Short) = min(this, b)

infix fun Float.max(b: Float) = max(this, b)
infix fun Double.max(b: Double) = max(this, b)
infix fun Byte.max(b: Byte) = max(this, b)
infix fun Int.max(b: Int) = max(this, b)
infix fun Long.max(b: Long) = max(this, b)
infix fun Short.max(b: Short) = max(this, b)


fun Byte.clamp(min: Byte = this, max: Byte = this) = GLM.clamp(this, min, max)
fun Short.clamp(min: Short = this, max: Short = this) = GLM.clamp(this, min, max)
fun Int.clamp(min: Int = this, max: Int = this) = GLM.clamp(this, min, max)
fun Long.clamp(min: Long = this, max: Long = this) = GLM.clamp(this, min, max)
fun Float.clamp(min: Float = this, max: Float = this) = GLM.clamp(this, min, max)
fun Double.clamp(min: Double = this, max: Double = this) = GLM.clamp(this, min, max)
fun Char.clamp(min: Char = this, max: Char = this) = GLM.clamp(this, min, max)


fun Float.mix(b: Float, interp: Float) = mix(this, b, interp)
fun Double.mix(b: Double, interp: Double) = mix(this, b, interp)

fun Float.mix(b: Float, interp: Boolean) = mix(this, b, interp)
fun Double.mix(b: Double, interp: Boolean) = mix(this, b, interp)


infix fun Float.step(edge: Float) = step(edge, this)
infix fun Double.step(edge: Double) = step(edge, this)

fun Float.smoothstep(edge0: Float, edge1: Float) = smoothStep(edge0, edge1, this)
fun Double.smoothstep(edge0: Double, edge1: Double) = smoothStep(edge0, edge1, this)


val Float.isNan get() = isNan(this)
val Double.isNan get() = isNan(this)

val Float.isInf get() = isInf(this)
val Double.isInf get() = isInf(this)


//fun Float.floatBitsToInt() = floatBitsToInt(this) TODO?
//
//fun Float.floatBitsToUint() = floatBitsToUint(this)
//
//fun Int.intBitsToFloat() = intBitsToFloat(this)
//
//fun Uint.uintBitsToFloat() = uintBitsToFloat(this)


fun Float.fma(b: Float, c: Float) = fma(this, b, c)
fun Double.fma(b: Double, c: Double) = fma(this, b, c)
