package de.bixilon.kotlinglm.vec2.operators

import de.bixilon.kotlinglm.d
import de.bixilon.kotlinglm.vec2.Vec2d
import de.bixilon.kotlinglm.vec2.Vec2d.Companion.div
import de.bixilon.kotlinglm.vec2.Vec2d.Companion.minus
import de.bixilon.kotlinglm.vec2.Vec2d.Companion.plus
import de.bixilon.kotlinglm.vec2.Vec2d.Companion.rem
import de.bixilon.kotlinglm.vec2.Vec2d.Companion.times

/**
 * Created by GBarbieri on 08.11.2016.
 */
interface op_Vec2d {

    fun plus(res: Vec2d, a: Vec2d, bX: Double, bY: Double): Vec2d {
        res.x = a.x + bX
        res.y = a.y + bY
        return res
    }

    fun minus(res: Vec2d, a: Vec2d, bX: Double, bY: Double): Vec2d {
        res.x = a.x - bX
        res.y = a.y - bY
        return res
    }

    fun minus(res: Vec2d, aX: Double, aY: Double, b: Vec2d): Vec2d {
        res.x = aX - b.x
        res.y = aY - b.y
        return res
    }

    fun times(res: Vec2d, a: Vec2d, bX: Double, bY: Double): Vec2d {
        res.x = a.x * bX
        res.y = a.y * bY
        return res
    }

    fun div(res: Vec2d, a: Vec2d, bX: Double, bY: Double): Vec2d {
        res.x = a.x / bX
        res.y = a.y / bY
        return res
    }

    fun div(res: Vec2d, aX: Double, aY: Double, b: Vec2d): Vec2d {
        res.x = aX / b.x
        res.y = aY / b.y
        return res
    }

    fun rem(res: Vec2d, a: Vec2d, bX: Double, bY: Double): Vec2d {
        res.x = a.x % bX
        res.y = a.y % bY
        return res
    }

    fun rem(res: Vec2d, aX: Double, aY: Double, b: Vec2d): Vec2d {
        res.x = aX % b.x
        res.y = aY % b.y
        return res
    }
}


// -- Specific binary arithmetic operators --

infix operator fun Double.plus(b: Vec2d) = plus(Vec2d(), b, this, this)
fun Double.plus(b: Vec2d, res: Vec2d) = plus(res, b, this, this)
infix fun Double.plusAssign(b: Vec2d) = plus(b, b, this, this)

infix operator fun Double.minus(b: Vec2d) = minus(Vec2d(), this, this, b)
fun Double.minus(b: Vec2d, res: Vec2d) = minus(res, b, this, this)
infix fun Double.minusAssign(b: Vec2d) = minus(b, this, this, b)

infix operator fun Double.times(b: Vec2d) = times(Vec2d(), b, this, this)
fun Double.times(b: Vec2d, res: Vec2d) = times(res, b, this, this)
infix fun Double.timesAssign(b: Vec2d) = times(b, b, this, this)

infix operator fun Double.div(b: Vec2d) = div(Vec2d(), this, this, b)
fun Double.div(b: Vec2d, res: Vec2d) = div(res, b, this, this)
infix fun Double.divAssign(b: Vec2d) = div(b, this, this, b)

infix operator fun Double.rem(b: Vec2d) = rem(Vec2d(), this, this, b)
fun Double.rem(b: Vec2d, res: Vec2d) = rem(res, b, this, this)
infix fun Double.remAssign(b: Vec2d) = rem(b, this, this, b)


// -- Generic binary arithmetic operators --

infix operator fun Number.plus(b: Vec2d) = plus(Vec2d(), b, this.d, this.d)
fun Number.plus(b: Vec2d, res: Vec2d) = plus(res, b, this.d, this.d)
infix fun Number.plusAssign(b: Vec2d) = plus(b, b, this.d, this.d)

infix operator fun Number.minus(b: Vec2d) = minus(Vec2d(), this.d, this.d, b)
fun Number.minus(b: Vec2d, res: Vec2d) = minus(res, b, this.d, this.d)
infix fun Number.minusAssign(b: Vec2d) = minus(b, this.d, this.d, b)

infix operator fun Number.times(b: Vec2d) = times(Vec2d(), b, this.d, this.d)
fun Number.times(b: Vec2d, res: Vec2d) = times(res, b, this.d, this.d)
infix fun Number.timesAssign(b: Vec2d) = times(b, b, this.d, this.d)

infix operator fun Number.div(b: Vec2d) = div(Vec2d(), this.d, this.d, b)
fun Number.div(b: Vec2d, res: Vec2d) = div(res, b, this.d, this.d)
infix fun Number.divAssign(b: Vec2d) = div(b, this.d, this.d, b)

infix operator fun Number.rem(b: Vec2d) = rem(Vec2d(), this.d, this.d, b)
fun Number.rem(b: Vec2d, res: Vec2d) = rem(res, b, this.d, this.d)
infix fun Number.remAssign(b: Vec2d) = rem(b, this.d, this.d, b)
