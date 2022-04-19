package de.bixilon.kotlinglm

import de.bixilon.kotlinglm.vec1.Vec1
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec2.Vec2d
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec4.Vec4
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testCoreFuncGeometric : StringSpec() {

    init {
        "length" {

            val length1 = GLM.length(Vec1(1))
            val length2 = GLM.length(Vec2(1, 0))
            val length3 = GLM.length(Vec3(1, 0, 0))
            val length4 = GLM.length(Vec4(1, 0, 0, 0))

            (GLM.abs(length1 - 1f) < Float.MIN_VALUE) shouldBe true
            (GLM.abs(length2 - 1f) < Float.MIN_VALUE) shouldBe true
            (GLM.abs(length3 - 1f) < Float.MIN_VALUE) shouldBe true
            (GLM.abs(length4 - 1f) < Float.MIN_VALUE) shouldBe true
        }

        "distance" {

            val distance1 = GLM.distance(Vec1(1), Vec1(1))
            val distance2 = GLM.distance(Vec2(1, 0), Vec2(1, 0))
            val distance3 = GLM.distance(Vec3(1, 0, 0), Vec3(1, 0, 0))
            val distance4 = GLM.distance(Vec4(1, 0, 0, 0), Vec4(1, 0, 0, 0))

            (GLM.abs(distance1) < Float.MIN_VALUE) shouldBe true
            (GLM.abs(distance2) < Float.MIN_VALUE) shouldBe true
            (GLM.abs(distance3) < Float.MIN_VALUE) shouldBe true
            (GLM.abs(distance4) < Float.MIN_VALUE) shouldBe true
        }

        "distance point line" {

            val point = Vec3(0, 1, 0)
            val lineA = Vec3()
            val lineB = Vec3(0, 0, 1)
            val distance = GLM.distance(point, lineA, lineB)

            distance shouldBe 1f
        }

        "dot" {

            val dot1 = GLM.dot(Vec1(1), Vec1(1))
            val dot2 = GLM.dot(Vec2(1), Vec2(1))
            val dot3 = GLM.dot(Vec3(1), Vec3(1))
            val dot4 = GLM.dot(Vec4(1), Vec4(1))

            (GLM.abs(dot1 - 1f) < Float.MIN_VALUE) shouldBe true
            (GLM.abs(dot2 - 2f) < Float.MIN_VALUE) shouldBe true
            (GLM.abs(dot3 - 3f) < Float.MIN_VALUE) shouldBe true
            (GLM.abs(dot4 - 4f) < Float.MIN_VALUE) shouldBe true
        }

        "cross" {
            val cross1 = GLM.cross(Vec3(1, 0, 0), Vec3(0, 1, 0))
            val cross2 = GLM.cross(Vec3(0, 1, 0), Vec3(1, 0, 0))

            GLM.all(GLM.lessThan(GLM.abs(cross1 - Vec3(0, 0, 1)), Vec3(Float.MIN_VALUE))) shouldBe true
            GLM.all(GLM.lessThan(GLM.abs(cross2 - Vec3(0, 0, -1)), Vec3(Float.MIN_VALUE))) shouldBe true
        }

        "normalize" {

            val normalize1 = GLM.normalize(Vec3(1, 0, 0))
            val normalize2 = GLM.normalize(Vec3(2, 0, 0))

            val normalize3 = GLM.normalize(Vec3(-0.6, 0.7, -0.5))

            val ro = Vec3(GLM.cos(5f) * 3f, 2f, GLM.sin(5f) * 3f)
            val w = GLM.normalize(Vec3(0, -0.2f, 0) - ro)
            val u = GLM.normalize(GLM.cross(w, Vec3(0, 1, 0)))
            val v = GLM.cross(u, w)

            GLM.all(GLM.lessThan(GLM.abs(normalize1 - Vec3(1, 0, 0)), Vec3(Float.MIN_VALUE))) shouldBe true
            GLM.all(GLM.lessThan(GLM.abs(normalize2 - Vec3(1, 0, 0)), Vec3(Float.MIN_VALUE))) shouldBe true
        }

        "face forward" {

            val n = Vec3(0f, 0f, 1f)
            val i = Vec3(1f, 0f, 1f)
            val nRef = Vec3(0f, 0f, 1f)
            val f = GLM.faceForward(n, i, nRef)

            f shouldBe Vec3(0f, 0f, -1f)
        }

        "reflect" {

            run {
                val a = Vec2(1f, -1f)
                val b = Vec2(0f, 1f)
                val c = GLM.reflect(a, b)
                c.shouldEqual(Vec2(1f), 0.0001f)
            }

            run {
                val a = Vec2d(1.0, -1.0)
                val b = Vec2d(0.0, 1.0)
                val c = GLM.reflect(a, b)
                c.shouldEqual(Vec2d(1.0), 0.0001)
            }
        }

        "refract" {

            run {
                val a = -1f
                val b = 1f
                val c = GLM.refract(a, b, 0.5f)
                c.shouldEqual(-1f, 0.0001f)
            }

            run {
                val a = Vec2(0f, -1f)
                val b = Vec2(0f, 1f)
                val c = GLM.refract(a, b, 0.5f)
                c.shouldEqual(Vec2(0, -1), 0.0001f)
            }

            run {
                val a = Vec2d(0.0, -1.0)
                val b = Vec2d(0.0, 1.0)
                val c = GLM.refract(a, b, 0.5)
                c shouldEqual Vec2d(0.0, -1.0)
            }
        }
    }
}
