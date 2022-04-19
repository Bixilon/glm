package de.bixilon.kotlinglm.detail

import de.bixilon.kotlinglm.GLM.floor
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec2.Vec2d
import de.bixilon.kotlinglm.vec2.Vec2t
import de.bixilon.kotlinglm.vec2.operators.minus
import de.bixilon.kotlinglm.vec2.operators.times
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec3.Vec3d
import de.bixilon.kotlinglm.vec3.Vec3t
import de.bixilon.kotlinglm.vec3.operators.minus
import de.bixilon.kotlinglm.vec3.operators.times
import de.bixilon.kotlinglm.vec4.Vec4
import de.bixilon.kotlinglm.vec4.Vec4d
import de.bixilon.kotlinglm.vec4.Vec4t
import de.bixilon.kotlinglm.vec4.operators.minus
import de.bixilon.kotlinglm.vec4.operators.times

/**
 * Created by GBarbieri on 13.12.2016.
 */


interface Noise {

    fun mod289(a: Number): Number = when (a) {
        is Float -> a - floor(a * 1f / 289f) * 289f
        is Double -> a - floor(a * 1.0 / 289.0) * 289.0
        else -> throw ArithmeticException("mod289 only accepts floating-point inputs")
    }

    fun mod289(a: Vec2t<out Number>): Vec2t<out Number> = when (a) {
        is Vec2 -> a - floor(a * 1f / 289f) * 289f
        is Vec2d -> a - floor(a * 1.0 / 289.0) * 289.0
        else -> throw ArithmeticException("mod289 only accepts floating-point inputs")
    }

    fun mod289(a: Vec3t<out Number>): Vec3t<out Number> = when (a) {
        is Vec3 -> a - floor(a * 1f / 289f) * 289f
        is Vec3d -> a - floor(a * 1.0 / 289.0) * 289.0
        else -> throw ArithmeticException("mod289 only accepts floating-point inputs")
    }

    fun mod289(a: Vec4t<out Number>): Vec4t<out Number> = when (a) {
        is Vec4 -> a - floor(a * 1f / 289f) * 289f
        is Vec4d -> a - floor(a * 1.0 / 289.0) * 289.0
        else -> throw ArithmeticException("mod289 only accepts floating-point inputs")
    }


    fun permute(a: Number): Number = when (a) {
        is Float -> mod289(((a * 34f) + 1f) * a)
        is Double -> mod289(((a * 34.0) + 1.0) * a)
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }

    fun permute(a: Vec2t<out Number>): Vec2t<out Number> = when (a) {
        is Vec2 -> mod289(((a * 34f) + 1f) * a)
        is Vec2d -> mod289(((a * 34.0) + 1.0) * a)
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }

    fun permute(a: Vec3t<out Number>): Vec3t<out Number> = when (a) {
        is Vec3 -> mod289(((a * 34f) + 1f) * a)
        is Vec3d -> mod289(((a * 34.0) + 1.0) * a)
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }

    fun permute(a: Vec4t<out Number>): Vec4t<out Number> = when (a) {
        is Vec4 -> mod289(((a * 34f) + 1f) * a)
        is Vec4d -> mod289(((a * 34.0) + 1.0) * a)
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }


    fun taylorInvSqrt(a: Number): Number = when (a) {
        is Float -> 1.79284291400159f - 0.85373472095314f * a
        is Double -> 1.79284291400159 - 0.85373472095314 * a
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }

    fun taylorInvSqrt(a: Vec2t<out Number>): Vec2t<out Number> = when (a) {
        is Vec2 -> 1.79284291400159f - 0.85373472095314f * a
        is Vec2d -> 1.79284291400159 - 0.85373472095314 * a
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }

    fun taylorInvSqrt(a: Vec3t<out Number>): Vec3t<out Number> = when (a) {
        is Vec3 -> 1.79284291400159f - 0.85373472095314f * a
        is Vec3d -> 1.79284291400159 - 0.85373472095314 * a
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }

    fun taylorInvSqrt(a: Vec4t<out Number>): Vec4t<out Number> = when (a) {
        is Vec4 -> 1.79284291400159f - 0.85373472095314f * a
        is Vec4d -> 1.79284291400159 - 0.85373472095314 * a
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }


    fun fade(a: Vec2t<out Number>): Vec2t<out Number> = when (a) {
        is Vec2 -> (a * a * a) * (a * (a * 6f - 15f) + 10f)
        is Vec2d -> (a * a * a) * (a * (a * 6.0 - 15.0) + 10.0)
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }

    fun fade(a: Vec3t<out Number>): Vec3t<out Number> = when (a) {
        is Vec3 -> (a * a * a) * (a * (a * 6f - 15f) + 10f)
        is Vec3d -> (a * a * a) * (a * (a * 6.0 - 15.0) + 10.0)
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }

    fun fade(a: Vec4t<out Number>): Vec4t<out Number> = when (a) {
        is Vec4 -> (a * a * a) * (a * (a * 6f - 15f) + 10f)
        is Vec4d -> (a * a * a) * (a * (a * 6.0 - 15.0) + 10.0)
        else -> throw ArithmeticException("permute only accepts floating-point inputs")
    }
}
