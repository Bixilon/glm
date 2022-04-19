package de.bixilon.kotlinglm.gtx

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec4.Vec4
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testGtxVectorAngle : StringSpec() {

    init {

        "angle" {

            val angleA = GLM.angle(Vec2(1, 0), Vec2(1, 1).normalize())
            GLM.equal(angleA, GLM.PIf * 0.25f, 0.01f) shouldBe true
            val angleB = GLM.angle(Vec3(1, 0, 0), Vec3(1, 1, 0).normalize())
            GLM.equal(angleB, GLM.PIf * 0.25f, 0.01f) shouldBe true
            val angleC = GLM.angle(Vec4(1, 0, 0, 0), Vec4(1, 1, 0, 0).normalize())
            GLM.equal(angleC, GLM.PIf * 0.25f, 0.01f) shouldBe true
        }

        "oriented angle Vec2 "{

            val angleA = GLM.orientedAngle(Vec2(1, 0), Vec2(1, 1).normalize())
            GLM.equal(angleA, GLM.PIf * 0.25f, 0.01f) shouldBe true
            val angleB = GLM.orientedAngle(Vec2(0, 1), Vec2(1, 1).normalize())
            GLM.equal(angleB, -GLM.PIf * 0.25f, 0.01f) shouldBe true
            val angleC = GLM.orientedAngle(Vec2(1, 1).normalize(), Vec2(0, 1))
            GLM.equal(angleC, GLM.PIf * 0.25f, 0.01f) shouldBe true
        }

        "oriented angle Vec3"{

            val angleA = GLM.orientedAngle(Vec3(1, 0, 0), Vec3(1, 1, 0).normalize(), Vec3(0, 0, 1))
            GLM.equal(angleA, GLM.PIf * 0.25f, 0.01f) shouldBe true
            val angleB = GLM.orientedAngle(Vec3(0, 1, 0), Vec3(1, 1, 0).normalize(), Vec3(0, 0, 1))
            GLM.equal(angleB, -GLM.PIf * 0.25f, 0.01f) shouldBe true
            val angleC = GLM.orientedAngle(Vec3(1, 1, 0).normalize(), Vec3(0, 1, 0), Vec3(0, 0, 1))
            GLM.equal(angleC, GLM.PIf * 0.25f, 0.01f) shouldBe true
        }
    }
}
