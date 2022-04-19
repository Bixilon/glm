package de.bixilon.kotlinglm.gtx

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec4.Vec4
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testGtxSpline : StringSpec() {

    init {

        "catmullRom" {

            val result2 = GLM.catmullRom(
                    Vec2(0f, 0f),
                    Vec2(1f, 0f),
                    Vec2(1f, 1f),
                    Vec2(0f, 1f), 0.5f)

            result2 shouldBe Vec2(1.125f, 0.5f)

            val result3 = GLM.catmullRom(
                    Vec3(0f, 0f, 0f),
                    Vec3(1f, 0f, 0f),
                    Vec3(1f, 1f, 0f),
                    Vec3(0f, 1f, 0f), 0.5f)

            result3 shouldBe Vec3(1.125f, 0.5f, 0f)

            val result4 = GLM.catmullRom(
                    Vec4(0f, 0f, 0f, 1f),
                    Vec4(1f, 0f, 0f, 1f),
                    Vec4(1f, 1f, 0f, 1f),
                    Vec4(0f, 1f, 0f, 1f), 0.5f)

            result4 shouldBe Vec4(1.125f, 0.5f, 0f, 1f)
        }

        "hermite" {

            val result2 = GLM.hermite(
                    Vec2(0f, 0f),
                    Vec2(1f, 0f),
                    Vec2(1f, 1f),
                    Vec2(0f, 1f), 0.5f)

            result2 shouldBe Vec2(0.625f, 0.375f)

            val result3 = GLM.hermite(
                    Vec3(0f, 0f, 0f),
                    Vec3(1f, 0f, 0f),
                    Vec3(1f, 1f, 0f),
                    Vec3(0f, 1f, 0f), 0.5f)

            result3 shouldBe Vec3(0.625f, 0.375f, 0f)

            val result4 = GLM.hermite(
                    Vec4(0f, 0f, 0f, 1f),
                    Vec4(1f, 0f, 0f, 1f),
                    Vec4(1f, 1f, 0f, 1f),
                    Vec4(0f, 1f, 0f, 1f), 0.5f)

            result4 shouldBe Vec4(0.625f, 0.375f, 0f, 1f)
        }

        "cubic" {

            val result2 = GLM.cubic(
                    Vec2(0f, 0f),
                    Vec2(1f, 0f),
                    Vec2(1f, 1f),
                    Vec2(0f, 1f), 0.5f)

            result2 shouldBe Vec2(0.75f, 1.5f)

            val result3 = GLM.cubic(
                    Vec3(0f, 0f, 0f),
                    Vec3(1f, 0f, 0f),
                    Vec3(1f, 1f, 0f),
                    Vec3(0f, 1f, 0f), 0.5f)

            result3 shouldBe Vec3(0.75f, 1.5f, 0f)

            val result4 = GLM.cubic(
                    Vec4(0f, 0f, 0f, 1f),
                    Vec4(1f, 0f, 0f, 1f),
                    Vec4(1f, 1f, 0f, 1f),
                    Vec4(0f, 1f, 0f, 1f), 0.5f)

            result4 shouldBe Vec4(0.75f, 1.5f, 0f, 1.875f)
        }
    }
}
