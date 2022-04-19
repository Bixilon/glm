package de.bixilon.kotlinglm

import de.bixilon.kotlinglm.GLM.Ef
import de.bixilon.kotlinglm.vec1.Vec1
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec4.Vec4
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testCoreFuncExponential : StringSpec() {

    init {
        "pow" {

            val a = GLM.pow(2f, 2f)
            a.shouldEqual(4f, 0.01f)

            val b = GLM.pow(Vec1(2f), Vec1(2f))
            b.shouldEqual(Vec1(4f), 0.01f)

            val c = GLM.pow(Vec2(2f), Vec2(2f))
            c.shouldEqual(Vec2(4f), 0.01f)

            val d = GLM.pow(Vec3(2f), Vec3(2f))
            d.shouldEqual(Vec3(4f), 0.01f)

            val e = GLM.pow(Vec4(2f), Vec4(2f))
            e.shouldEqual(Vec4(4f), 0.01f)
        }

        "sqrt" {

            val a = GLM.sqrt(4f)
            a.shouldEqual(2f, 0.01f)

            val b = GLM.sqrt(Vec1(4f))
            b.shouldEqual(Vec1(2f), 0.01f)

            val c = GLM.sqrt(Vec2(4f))
            c.shouldEqual(Vec2(2f), 0.01f)

            val d = GLM.sqrt(Vec3(4f))
            d.shouldEqual(Vec3(2f), 0.01f)

            val e = GLM.sqrt(Vec4(4f))
            e.shouldEqual(Vec4(2f), 0.01f)
        }

        "exp" {

            val a = GLM.exp(1f)

            GLM.equal(a, Ef, 0.01f) shouldBe true

            val b = GLM.exp(Vec1(1f))
            b.shouldEqual(Vec1(Ef), 0.01f)

            val c = GLM.exp(Vec2(1f))
            c.shouldEqual(Vec2(Ef), 0.01f)

            val d = GLM.exp(Vec3(1f))
            d.shouldEqual(Vec3(Ef), 0.01f)

            val e = GLM.exp(Vec4(1f))
            e.shouldEqual(Vec4(Ef), 0.01f)
        }

        "log" {

            val a = GLM.log(Ef)
            a.shouldEqual(1f, 0.01f)

            val b = GLM.log(Vec1(Ef))
            b.shouldEqual(Vec1(1f), 0.01f)

            val c = GLM.log(Vec2(Ef))
            c.shouldEqual(Vec2(1f), 0.01f)

            val d = GLM.log(Vec3(Ef))
            d.shouldEqual(Vec3(1f), 0.01f)

            val e = GLM.log(Vec4(Ef))
            e.shouldEqual(Vec4(1f), 0.01f)
        }

        "exp2" {

            val a = GLM.exp2(4f)
            a.shouldEqual(16f, 0.01f)

            val b = GLM.exp2(Vec1(4f))
            b.shouldEqual(Vec1(16f), 0.01f)

            val c = GLM.exp2(Vec2(4f, 3f))
            c.shouldEqual(Vec2(16f, 8f), 0.01f)

            val d = GLM.exp2(Vec3(4f, 3f, 2f))
            d.shouldEqual(Vec3(16f, 8f, 4f), 0.01f)

            val e = GLM.exp2(Vec4(4f, 3f, 2f, 1f))
            e.shouldEqual(Vec4(16f, 8f, 4f, 2f), 0.01f)
        }

        "log2" {

            val a = GLM.log2(16f)
            a.shouldEqual(4f, 0.01f)

            val b = GLM.log2(Vec1(16f))
            b.shouldEqual(Vec1(4f), 0.01f)

            val c = GLM.log2(Vec2(16f, 8f))
            c.shouldEqual(Vec2(4f, 3f), 0.01f)

            val d = GLM.log2(Vec3(16f, 8f, 4f))
            d.shouldEqual(Vec3(4f, 3f, 2f), 0.01f)

            val e = GLM.log2(Vec4(16f, 8f, 4f, 2f))
            e.shouldEqual(Vec4(4f, 3f, 2f, 1f), 0.01f)
        }

        "inverse sqrt" {

            val a = GLM.inverseSqrt(16f) * GLM.sqrt(16f)
            a.shouldEqual(1f, 0.01f)

            val b = GLM.inverseSqrt(Vec1(16f)) * GLM.sqrt(16f)
            b.shouldEqual(Vec1(1.f), 0.01f)

            val c = GLM.inverseSqrt(Vec2(16f)) * GLM.sqrt(16f)
            c.shouldEqual(Vec2(1f), 0.01f)

            val d = GLM.inverseSqrt(Vec3(16f)) * GLM.sqrt(16f)
            d.shouldEqual(Vec3(1.f), 0.01f)

            val e = GLM.inverseSqrt(Vec4(16f)) * GLM.sqrt(16f)
            e.shouldEqual(Vec4(1f), 0.01f)
        }
    }
}
