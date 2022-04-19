@file:Suppress("DIVISION_BY_ZERO")
package de.bixilon.kotlinglm

import de.bixilon.kotlinglm.ext.equal
import de.bixilon.kotlinglm.GLM.epsilonF
import de.bixilon.kotlinglm.vec1.Vec1
import de.bixilon.kotlinglm.vec1.Vec1d
import de.bixilon.kotlinglm.vec1.Vec1i
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec2.Vec2bool
import de.bixilon.kotlinglm.vec2.Vec2d
import de.bixilon.kotlinglm.vec2.Vec2i
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec3.Vec3bool
import de.bixilon.kotlinglm.vec3.Vec3d
import de.bixilon.kotlinglm.vec3.Vec3i
import de.bixilon.kotlinglm.vec4.Vec4
import de.bixilon.kotlinglm.vec4.Vec4bool
import de.bixilon.kotlinglm.vec4.Vec4d
import de.bixilon.kotlinglm.vec4.Vec4i
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

/**
 * Created by elect on 11/11/16.
 */

class testCoreFunCommon : StringSpec() {

    init {

        "floor" {

            run {
                val a = 1.1f
                val b = GLM.floor(a)
                b.shouldEqual(1f, 0.0001f)
            }

            run {
                val a = 1.1
                val b = GLM.floor(a)
                b.shouldEqual(1.0, 0.0001)
            }

            run {
                val a = Vec1(1.1f)
                val b = GLM.floor(a)

                b.shouldEqual(Vec1(1.0), 0.0001f)
            }

            run {
                val a = Vec1d(1.1)
                val b = GLM.floor(a)

                b.shouldEqual(Vec1d(1.0), 0.0001)
            }

            run {
                val a = Vec2(1.1f)
                val b = GLM.floor(a)

                b.shouldEqual(Vec2(1.0), 0.0001f)
            }

            run {
                val a = Vec2d(1.1)
                val b = GLM.floor(a)

                b.shouldEqual(Vec2d(1.0), 0.0001)
            }

            run {
                val a = Vec3(1.1f)
                val b = GLM.floor(a)

                b.shouldEqual(Vec3(1.0), 0.0001f)
            }

            run {
                val a = Vec3d(1.1)
                val b = GLM.floor(a)

                b.shouldEqual(Vec3d(1.0), 0.0001)
            }

            run {
                val a = Vec4(1.1f)
                val b = GLM.floor(a)

                b.shouldEqual(Vec4(1.0), 0.0001f)
            }

            run {
                val a = Vec4d(1.1)
                val b = GLM.floor(a)

                b.shouldEqual(Vec4d(1.0), 0.0001)
            }
        }

        "modf" {

            run {
                val x = 1.5f
                _bF = 0f
                val a = GLM.modf(x, ::_bF)

                _bF.equal(1f, 0.0001f) shouldBe true
                a.equal(0.5f, 0.0001f) shouldBe true
            }

            run {
                val x = Vec4(1.1f, 1.2f, 1.5f, 1.7f)
                val i = Vec4(0f)
                val a = GLM.modf(x, i)

                Vec4i(i) shouldBe Vec4i(1)
                a.shouldEqual(Vec4(0.1f, 0.2f, 0.5f, 0.7f), 0.00001f)
            }

            run {
                val x = Vec4d(1.1, 1.2, 1.5, 1.7)
                val i = Vec4d(0.0)
                val a = GLM.modf(x, i)

                Vec4i(i) shouldBe Vec4i(1)
                a.shouldEqual(Vec4d(0.1, 0.2, 0.5, 0.7), 0.000000001)
            }

            run {
                val x = 1.5
                bD = 0.0
                val a = GLM.modf(x, ::bD)

                bD.shouldEqual(1.0, 0.0001)
                a.shouldEqual(0.5, 0.0001)
            }
        }

        "mod" {

            run {
                val a = 1.5f
                val b = 1f
                val c = GLM.mod(a, b)

                c.shouldEqual(0.5f, 0.00001f)
            }

            run {
                val a = -0.2f
                val b = 1f
                val c = GLM.mod(a, b)

                c.shouldEqual(0.8f, 0.00001f)
            }

            run {
                val a = 3f
                val b = 2f
                val c = GLM.mod(a, b)

                c.shouldEqual(1f, 0.00001f)
            }

            run {
                val a = Vec4(3.0)
                val b = 2f
                val c = GLM.mod(a, b)

                c.shouldEqual(Vec4(1f), 0.00001f)
            }

            run {
                val a = Vec4(3.0)
                val b = Vec4(2f)
                val c = GLM.mod(a, b)

                c.shouldEqual(Vec4(1f), 0.00001f)
            }
        }

        "floatBitsToInt" {

            run {
                val a = 1f
                val b = GLM.floatBitsToInt(a)
                val c = GLM.intBitsToFloat(b)
                a.shouldEqual(c, 0.0001f)
            }

            run {
                val a = Vec2(1f, 2f)
                val b = GLM.floatBitsToInt(a)
                val c = GLM.intBitsToFloat(b)
                a.shouldEqual(c, 0.0001f)
            }

            run {
                val a = Vec3(1f, 2f, 3f)
                val b = GLM.floatBitsToInt(a)
                val c = GLM.intBitsToFloat(b)
                a.shouldEqual(c, 0.0001f)
            }

            run {
                val a = Vec4(1f, 2f, 3f, 4f)
                val b = GLM.floatBitsToInt(a)
                val c = GLM.intBitsToFloat(b)
                a.shouldEqual(c, 0.0001f)
            }
        }

        "floatBitsToUint" {

            run {
                val a = 1f
                val b = GLM.floatBitsToUint(a)
                val c = GLM.uintBitsToFloat(b)
                a.shouldEqual(c, 0.0001f)
            }

            run {
                val a = Vec2(1f, 2f)
                val b = GLM.floatBitsToUint(a)
                val c = GLM.uintBitsToFloat(b)
                a.shouldEqual(c, 0.0001f)
            }

            run {
                val a = Vec3(1f, 2f, 3f)
                val b = GLM.floatBitsToUint(a)
                val c = GLM.uintBitsToFloat(b)
                a.shouldEqual(c, 0.0001f)
            }

            run {
                val a = Vec4(1f, 2f, 3f, 4f)
                val b = GLM.floatBitsToUint(a)
                val c = GLM.uintBitsToFloat(b)
                a.shouldEqual(c, 0.0001f)
            }
        }

        "min" {

            val a0 = GLM.min(Vec1(1), Vec1(1))

            val b0 = GLM.min(Vec2(1), Vec2(1))
            val b1 = GLM.min(Vec2(1), 1f)
            val b2 = b0.allEqual(b1, Float.MIN_VALUE)
            b2 shouldBe true

            val c0 = GLM.min(Vec3(1), Vec3(1))
            val c1 = GLM.min(Vec3(1), 1f)
            val c2 = c0.allEqual(c1, Float.MIN_VALUE)
            c2 shouldBe true

            val d0 = GLM.min(Vec4(1), Vec4(1))
            val d1 = GLM.min(Vec4(1), 1f)
            val d2 = d0.allEqual(d1, Float.MIN_VALUE)
            d2 shouldBe true
        }

        "max" {

            val a0 = GLM.max(Vec1(1), Vec1(1))

            val b0 = GLM.max(Vec2(1), Vec2(1))
            val b1 = GLM.max(Vec2(1), 1f)
            val b2 = b0.allEqual(b1, Float.MIN_VALUE)
            b2 shouldBe true

            val c0 = GLM.max(Vec3(1), Vec3(1))
            val c1 = GLM.max(Vec3(1), 1f)
            val c2 = c0.allEqual(c1, Float.MIN_VALUE)
            c2 shouldBe true

            val d0 = GLM.max(Vec4(1), Vec4(1))
            val d1 = GLM.max(Vec4(1), 1f)
            val d2 = d0.allEqual(d1, Float.MIN_VALUE)
            d2 shouldBe true
        }

        "mix" {

            class Entry<T, B>(val x: T, val y: T, val a: B, val result: T)

            val testBool = arrayOf(
                    Entry(0f, 1f, false, 0f),
                    Entry(0f, 1f, true, 1f),
                    Entry(-1f, 1f, false, -1f),
                    Entry(-1f, 1f, true, 1f))

            val testFloat = arrayOf(
                    Entry(0f, 1f, 0f, 0f),
                    Entry(0f, 1f, 1f, 1f),
                    Entry(-1f, 1f, 0f, -1f),
                    Entry(-1f, 1f, 1f, 1f))

            val testVec2Bool = arrayOf(
                    Entry(Vec2(0f), Vec2(1f), false, Vec2(0f)),
                    Entry(Vec2(0f), Vec2(1f), true, Vec2(1f)),
                    Entry(Vec2(-1f), Vec2(1f), false, Vec2(-1f)),
                    Entry(Vec2(-1f), Vec2(1f), true, Vec2(1f)))

            val testBVec2 = arrayOf(
                    Entry(Vec2(0f), Vec2(1f), Vec2bool(false), Vec2(0f)),
                    Entry(Vec2(0f), Vec2(1f), Vec2bool(true), Vec2(1f)),
                    Entry(Vec2(-1f), Vec2(1f), Vec2bool(false), Vec2(-1f)),
                    Entry(Vec2(-1f), Vec2(1f), Vec2bool(true), Vec2(1f)),
                    Entry(Vec2(-1f), Vec2(1f), Vec2bool(true, false), Vec2(1f, -1f)))

            val testVec3Bool = arrayOf(
                    Entry(Vec3(0f), Vec3(1f), false, Vec3(0f)),
                    Entry(Vec3(0f), Vec3(1f), true, Vec3(1f)),
                    Entry(Vec3(-1f), Vec3(1f), false, Vec3(-1f)),
                    Entry(Vec3(-1f), Vec3(1f), true, Vec3(1f)))

            val testBVec3 = arrayOf(
                    Entry(Vec3(0f), Vec3(1f), Vec3bool(false), Vec3(0f)),
                    Entry(Vec3(0f), Vec3(1f), Vec3bool(true), Vec3(1f)),
                    Entry(Vec3(-1f), Vec3(1f), Vec3bool(false), Vec3(-1f)),
                    Entry(Vec3(-1f), Vec3(1f), Vec3bool(true), Vec3(1f)),
                    Entry(Vec3(1f, 2f, 3f), Vec3(4f, 5f, 6f), Vec3bool(true, false, true), Vec3(4f, 2f, 6f)))

            val testVec4Bool = arrayOf(
                    Entry(Vec4(0f), Vec4(1f), false, Vec4(0f)),
                    Entry(Vec4(0f), Vec4(1f), true, Vec4(1f)),
                    Entry(Vec4(-1f), Vec4(1f), false, Vec4(-1f)),
                    Entry(Vec4(-1f), Vec4(1f), true, Vec4(1f)))

            val testBVec4 = arrayOf(
                    Entry(Vec4(0f, 0f, 1f, 1f), Vec4(2f, 2f, 3f, 3f), Vec4bool(false, true, false, true), Vec4(0f, 2f, 1f, 3f)),
                    Entry(Vec4(0f), Vec4(1f), Vec4bool(true), Vec4(1f)),
                    Entry(Vec4(-1f), Vec4(1f), Vec4bool(false), Vec4(-1f)),
                    Entry(Vec4(-1f), Vec4(1f), Vec4bool(true), Vec4(1f)),
                    Entry(Vec4(1f, 2f, 3f, 4f), Vec4(5f, 6f, 7f, 8f), Vec4bool(true, false, true, false), Vec4(5f, 2f, 7f, 4f))
            )

            // Float with bool
            for (i in 0..3) {
                val result = GLM.mix(testBool[i].x, testBool[i].y, testBool[i].a)
                result.shouldEqual(testBool[i].result)
            }

            // Float with float
            for (i in 0..3) {
                val result = GLM.mix(testFloat[i].x, testFloat[i].y, testFloat[i].a)
                result shouldEqual testFloat[i].result
            }

            // vec2 with bool
            for (i in 0..3) {
                val result = GLM.mix(testVec2Bool[i].x, testVec2Bool[i].y, testVec2Bool[i].a)
                result shouldEqual testVec2Bool[i].result
            }

            // vec2 with bvec2
            for (i in 0..3) {
                val result = GLM.mix(testBVec2[i].x, testBVec2[i].y, testBVec2[i].a)
                GLM.equal(result.x, testBVec2[i].result.x, epsilonF) shouldBe true
                GLM.equal(result.y, testBVec2[i].result.y, epsilonF) shouldBe true
            }

            // vec3 with bool
            for (i in 0..3) {
                val result = GLM.mix(testVec3Bool[i].x, testVec3Bool[i].y, testVec3Bool[i].a)
                GLM.equal(result.x, testVec3Bool[i].result.x, epsilonF) shouldBe true
                GLM.equal(result.y, testVec3Bool[i].result.y, epsilonF) shouldBe true
                GLM.equal(result.z, testVec3Bool[i].result.z, epsilonF) shouldBe true
            }

            // vec3 with bvec3
            for (i in 0..3) {
                val result = GLM.mix(testBVec3[i].x, testBVec3[i].y, testBVec3[i].a)
                GLM.equal(result.x, testBVec3[i].result.x, epsilonF) shouldBe true
                GLM.equal(result.y, testBVec3[i].result.y, epsilonF) shouldBe true
                GLM.equal(result.z, testBVec3[i].result.z, epsilonF) shouldBe true
            }

            // vec4 with bool
            for (i in 0..3) {
                val result = GLM.mix(testVec4Bool[i].x, testVec4Bool[i].y, testVec4Bool[i].a)
                GLM.equal(result.x, testVec4Bool[i].result.x, epsilonF) shouldBe true
                GLM.equal(result.y, testVec4Bool[i].result.y, epsilonF) shouldBe true
                GLM.equal(result.z, testVec4Bool[i].result.z, epsilonF) shouldBe true
                GLM.equal(result.w, testVec4Bool[i].result.w, epsilonF) shouldBe true
            }

            // vec4 with bvec4
            for (i in 0..3) {
                val result = GLM.mix(testBVec4[i].x, testBVec4[i].y, testBVec4[i].a)
                GLM.equal(result.x, testBVec4[i].result.x, epsilonF) shouldBe true
                GLM.equal(result.y, testBVec4[i].result.y, epsilonF) shouldBe true
                GLM.equal(result.z, testBVec4[i].result.z, epsilonF) shouldBe true
                GLM.equal(result.w, testBVec4[i].result.w, epsilonF) shouldBe true
            }
        }

        "step" {

            class Entry<Edge, Vec>(val edge: Edge, val x: Vec, val result: Vec)

            val testVec4Scalar = arrayOf(
                    Entry(1f, Vec4(1f, 2f, 3f, 4f), Vec4(1f)),
                    Entry(0f, Vec4(1f, 2f, 3f, 4f), Vec4(1f)),
                    Entry(0f, Vec4(-1f, -2f, -3f, -4f), Vec4(0f)))

            val testVec4Vector = arrayOf(
                    Entry(Vec4(-1f, -2f, -3f, -4f), Vec4(-2f, -3f, -4f, -5f), Vec4(0f)),
                    Entry(Vec4(0f, 1f, 2f, 3f), Vec4(1f, 2f, 3f, 4f), Vec4(1f)),
                    Entry(Vec4(2f, 3f, 4f, 5f), Vec4(1f, 2f, 3f, 4f), Vec4(0f)),
                    Entry(Vec4(0f, 1f, 2f, 3f), Vec4(-1f, -2f, -3f, -4f), Vec4(0f)))

            // scalar
            run {
                val edge = 2f

                val a = GLM.step(edge, 1f)
                a shouldEqual 0f

                val b = GLM.step(edge, 3f)
                b shouldEqual 1f

                val c = GLM.step(edge, 2f)
                c shouldEqual 1f
            }

            // vec4 and float
            for (i in 0..2) {
                val result = GLM.step(testVec4Scalar[i].edge, testVec4Scalar[i].x)
                result shouldEqual testVec4Scalar[i].result
            }

            // vec4 and vec4
            for (i in 0..3) {
                val result = GLM.step(testVec4Vector[i].edge, testVec4Vector[i].x)
                result shouldEqual testVec4Vector[i].result
            }
        }

        "round" {

            run {
                val a = GLM.round(0f)
                a shouldEqual 0f
                val b = GLM.round(0.5f)
                b shouldEqual 1f
                val c = GLM.round(1f)
                c shouldEqual 1f
                val d = GLM.round(0.1f)
                d shouldEqual 0f
                val e = GLM.round(0.9f)
                e shouldEqual 1f
                val f = GLM.round(1.5f)
                f shouldEqual 2f
                val g = GLM.round(1.9f)
                g shouldEqual 2f
            }

            run {
                val a = GLM.round(-0f)
                a shouldEqual 0f
                val b = GLM.round(-0.5f)
                b shouldEqual -1f
                val c = GLM.round(-1f)
                c shouldEqual -1f
                val d = GLM.round(-0.1f)
                d shouldEqual 0f
                val e = GLM.round(-0.9f)
                e shouldEqual -1f
                val f = GLM.round(-1.5f)
                f shouldEqual -2f
                val g = GLM.round(-1.9f)
                g shouldEqual -2f
            }
        }

        "round even" {

            run {
                val a1 = GLM.roundEven(-1.5f)
                a1.shouldEqual(-2f, 0.0001f)

                val a2 = GLM.roundEven(1.5f)
                a2.shouldEqual(2f, 0.0001f)

                val a5 = GLM.roundEven(-2.5f)
                a5.shouldEqual(-2f, 0.0001f)

                val a6 = GLM.roundEven(2.5f)
                a6.shouldEqual(2f, 0.0001f)

                val a3 = GLM.roundEven(-3.5f)
                a3.shouldEqual(-4f, 0.0001f)

                val a4 = GLM.roundEven(3.5f)
                a4.shouldEqual(4f, 0.0001f)

                val c7 = GLM.roundEven(-4.5f)
                c7.shouldEqual(-4f, 0.0001f)

                val c8 = GLM.roundEven(4.5f)
                c8.shouldEqual(4f, 0.0001f)

                val c1 = GLM.roundEven(-5.5f)
                c1.shouldEqual(-6f, 0.0001f)

                val c2 = GLM.roundEven(5.5f)
                c2.shouldEqual(6f, 0.0001f)

                val c3 = GLM.roundEven(-6.5f)
                c3.shouldEqual(-6f, 0.0001f)

                val c4 = GLM.roundEven(6.5f)
                c4.shouldEqual(6f, 0.0001f)

                val c5 = GLM.roundEven(-7.5f)
                c5.shouldEqual(-8f, 0.0001f)

                val c6 = GLM.roundEven(7.5f)
                c6.shouldEqual(8f, 0.0001f)
            }

            run {
                val a7 = GLM.roundEven(-2.4f)
                a7.shouldEqual( -2f, 0.0001f)

                val a8 = GLM.roundEven(2.4f)
                a8.shouldEqual( 2f, 0.0001f)

                val b1 = GLM.roundEven(-2.6f)
                b1.shouldEqual( -3f, 0.0001f)

                val b2 = GLM.roundEven(2.6f)
                b2.shouldEqual( 3f, 0.0001f)

                val b3 = GLM.roundEven(-2f)
                b3.shouldEqual( -2f, 0.0001f)

                val b4 = GLM.roundEven(2f)
                b4.shouldEqual( 2f, 0.0001f)
            }

            run {
                val a = GLM.roundEven(0f)
                a shouldEqual 0f
                val b = GLM.roundEven(0.5f)
                b shouldEqual 0f
                val c = GLM.roundEven(1f)
                c shouldEqual 1f
                val d = GLM.roundEven(0.1f)
                d shouldEqual 0f
                val e = GLM.roundEven(0.9f)
                e shouldEqual 1f
                val f = GLM.roundEven(1.5f)
                f shouldEqual 2f
                val g = GLM.roundEven(1.9f)
                g shouldEqual 2f
            }

            run {
                val a = GLM.roundEven(-0f)
                a shouldEqual  0f
                val b = GLM.roundEven(-0.5f)
                b shouldEqual  -0f
                val c = GLM.roundEven(-1f)
                c shouldEqual  -1f
                val d = GLM.roundEven(-0.1f)
                d shouldEqual  0f
                val e = GLM.roundEven(-0.9f)
                e shouldEqual  -1f
                val f = GLM.roundEven(-1.5f)
                f shouldEqual  -2f
                val g = GLM.roundEven(-1.9f)
                g shouldEqual  -2f
            }

            run {
                val a = GLM.roundEven(1.5f)
                a shouldEqual  2f
                val b = GLM.roundEven(2.5f)
                b shouldEqual  2f
                val c = GLM.roundEven(3.5f)
                c shouldEqual  4f
                val d = GLM.roundEven(4.5f)
                d shouldEqual  4f
                val e = GLM.roundEven(5.5f)
                e shouldEqual  6f
                val f = GLM.roundEven(6.5f)
                f shouldEqual  6f
                val g = GLM.roundEven(7.5f)
                g shouldEqual  8f
            }

            run {
                val a = GLM.roundEven(-1.5f)
                a shouldEqual  -2f
                val b = GLM.roundEven(-2.5f)
                b shouldEqual  -2f
                val c = GLM.roundEven(-3.5f)
                c shouldEqual  -4f
                val d = GLM.roundEven(-4.5f)
                d shouldEqual  -4f
                val e = GLM.roundEven(-5.5f)
                e shouldEqual  -6f
                val f = GLM.roundEven(-6.5f)
                f shouldEqual  -6f
                val g = GLM.roundEven(-7.5f)
                g shouldEqual  -8f
            }
        }

        "is NaN" {

            val zeroF = 0f
            val zeroD = 0.0

            run {
                GLM.isNan(0.0 / zeroD) shouldBe true
                GLM.any(GLM.isNan(Vec2d(0.0 / zeroD))) shouldBe true
                GLM.any(GLM.isNan(Vec3d(0.0 / zeroD))) shouldBe true
                GLM.any(GLM.isNan(Vec4d(0.0 / zeroD))) shouldBe true
            }

            run {
                GLM.isNan(0f / zeroF) shouldBe true
                GLM.any(GLM.isNan(Vec2(0f / zeroF))) shouldBe true
                GLM.any(GLM.isNan(Vec3(0f / zeroF))) shouldBe true
                GLM.any(GLM.isNan(Vec4(0f / zeroF))) shouldBe true
            }
        }

        "is Inf" {

            val zeroF = 0f
            val zeroD = 0.0

            run {
                GLM.isInf(+1.0 / zeroD) shouldBe true
                GLM.isInf(-1.0 / zeroD) shouldBe true
                GLM.any(GLM.isInf(Vec2d(+1.0 / zeroD))) shouldBe true
                GLM.any(GLM.isInf(Vec2d(-1.0 / zeroD))) shouldBe true
                GLM.any(GLM.isInf(Vec3d(+1.0 / zeroD))) shouldBe true
                GLM.any(GLM.isInf(Vec3d(-1.0 / zeroD))) shouldBe true
                GLM.any(GLM.isInf(Vec4d(+1.0 / zeroD))) shouldBe true
                GLM.any(GLM.isInf(Vec4d(-1.0 / zeroD))) shouldBe true
            }

            run {
                GLM.isInf(+1f / zeroF) shouldBe true
                GLM.isInf(-1f / zeroF) shouldBe true
                GLM.any(GLM.isInf(Vec2(+1f / zeroF))) shouldBe true
                GLM.any(GLM.isInf(Vec2(-1f / zeroF))) shouldBe true
                GLM.any(GLM.isInf(Vec3(+1f / zeroF))) shouldBe true
                GLM.any(GLM.isInf(Vec3(-1f / zeroF))) shouldBe true
                GLM.any(GLM.isInf(Vec4(+1f / zeroF))) shouldBe true
                GLM.any(GLM.isInf(Vec4(-1f / zeroF))) shouldBe true
            }
        }

        "sign" {

            class Type<T>(val value: T, val return_: T)

            // Int
            arrayOf(
                    Type(Int.MAX_VALUE, 1),
                    Type(Int.MIN_VALUE, -1),
                    Type(0, 0),
                    Type(1, 1),
                    Type(2, 1),
                    Type(3, 1),
                    Type(-1, -1),
                    Type(-2, -1),
                    Type(-3, -1))
                    .forEach {
                        val result = GLM.sign(it.value)
                        it.return_ shouldBe result
                    }

            // vec4
            arrayOf(
                    Type(Vec4(1), Vec4(1)),
                    Type(Vec4(0), Vec4(0)),
                    Type(Vec4(2), Vec4(1)),
                    Type(Vec4(3), Vec4(1)),
                    Type(Vec4(-1), Vec4(-1)),
                    Type(Vec4(-2), Vec4(-1)),
                    Type(Vec4(-3), Vec4(-1)))
                    .forEach {
                        val result = GLM.sign(it.value)
                        it.return_ shouldEqual result
                    }
        }

        "frexp" {

            run {
                val x = Vec1(1024)
                val exp = Vec1i()
                val a = GLM.frexp(x, exp)
                a shouldEqual Vec1(1)
                exp shouldBe Vec1i(10)
            }

            run {
                val x = Vec2(1024, 0.24)
                val exp = Vec2i()
                val a = GLM.frexp(x, exp)
                a shouldEqual Vec2(1, 0.96)
                exp shouldBe Vec2i(10, -2)
            }

            run {
                val x = Vec3(1024, 0.24, 0)
                val exp = Vec3i()
                val a = GLM.frexp(x, exp)
                a shouldEqual Vec3(1, 0.96, 0.0)
                exp shouldBe Vec3i(10, -2, 0)
            }

            run {
                val x = Vec4(1024, 0.24, 0, -1.33)
                val exp = Vec4i()
                val a = GLM.frexp(x, exp)
                a shouldEqual Vec4(1, 0.96, 0.0, -0.665)
                exp shouldBe Vec4i(10, -2, 0, 1)
            }
        }

        "ldexp" {

            run {
                val a = Vec1(1)
                val exp = Vec1i(10)
                val x = GLM.ldexp(a, exp)
                x.shouldEqual(Vec1(1024), 0.00001f)
            }

            run {
                val a = Vec2(1, 0.96)
                val exp = Vec2i(10, -2)
                val x = GLM.ldexp(a, exp)
                x.shouldEqual(Vec2(1024, .24), 0.00001f)
            }

            run {
                val a = Vec3(1, 0.96, 0.0)
                val exp = Vec3i(10, -2, 0)
                val x = GLM.ldexp(a, exp)
                x.shouldEqual(Vec3(1024, .24, 0), 0.00001f)
            }

            run {
                val a = Vec4(1, 0.96, 0.0, -0.665)
                val exp = Vec4i(10, -2, 0, 1)
                val x = GLM.ldexp(a, exp)
                x.shouldEqual(Vec4(1024, .24, 0, -1.33), 0.00001f)
            }
        }
    }

    companion object {
        var _bF = 0f
        var bD = 0.0
    }
}
