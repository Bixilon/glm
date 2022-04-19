package de.bixilon.kotlinglm.gtc

import de.bixilon.kotlinglm.func.rad
import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.mat4x4.Mat4
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec4.Vec4
import de.bixilon.kotlinglm.vec4.Vec4i
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testGtcMatrixTransform : StringSpec() {

    init {

        "translate" {

            val v = Vec3(1.0)
            val m = Mat4(0)
            val t = m translate v

            t shouldBe Mat4(0f)

            v.put(1f, 2f, 3f)
            m put 4f
            val u = m translate v

            u shouldBe Mat4(
                    4f, 0f, 0f, 0f,
                    0f, 4f, 0f, 0f,
                    0f, 0f, 4f, 0f,
                    4f, 8f, 12f, 4f)
        }

        "tweakedInfinitePerspective" {

            val projectionA = GLM.tweakedInfinitePerspective(45f, 640f / 480f, 1f)
            val projectionB = GLM.tweakedInfinitePerspective(45f, 640f / 480f, 1f, 0.001f)

            projectionA.allEqual(Mat4(
                    1.3444432f, 0f, 0f, 0f,
                    0f, 1.792591f, 0f, 0f,
                    0f, 0f, -0.9999999f, -1f,
                    0f, 0f, -1.9999999f, 0f), 0.001f) shouldBe true

            projectionB.allEqual(Mat4(
                    1.3444432f, 0f, 0f, 0f,
                    0f, 1.792591f, 0f, 0f,
                    0f, 0f, -0.999f, -1f,
                    0f, 0f, -1.999f, 0f), 0.001f) shouldBe true
        }

        "pick" {

            val pick = GLM.pickMatrix(Vec2(1, 2), Vec2(3, 4), Vec4i(0, 0, 320, 240))

            pick shouldBe Mat4(
                    106.666664f, 0f, 0f, 0f,
                    0f, 60f, 0f, 0f,
                    0f, 0f, 1f, 0f,
                    106f, 59f, 0f, 1f)
        }

        "perspective" {

            val projection = GLM.perspective(GLM.PIf * 0.25f, 4f / 3f, 0.1f, 100f)

            projection shouldBe Mat4(
                    1.8106601f, 0f, 0f, 0f,
                    0f, 2.4142134f, 0f, 0f,
                    0f, 0f, -1.002002f, -1f,
                    0f, 0f, -0.2002002f, 0f)
        }

        "frustum" {

            val frustum = GLM.frustum(0f, 1f, 2f, 3f, 4f, 5f)

            frustum shouldBe Mat4(
                    8f, 0f, 0f, 0f,
                    0f, 8f, 0f, 0f,
                    1f, 5f, -9f, -1f,
                    0f, 0f, -40f, 0f)
        }

        "ortho" {

            val ortho = GLM.ortho(0f, 1f, 2f, 3f, 4f, 5f)

            ortho shouldBe Mat4(
                    2f, 0f, 0f, 0f,
                    0f, 2f, 0f, 0f,
                    0f, 0f, -2f, 0f,
                    -1f, -5f, -9f, 1f)
        }

        "project" {

            val obj = Vec3(0f, 1f, 2f)
            val model = Mat4(
                    3f, 4f, 5f, 6f,
                    7f, 8f, 9f, 10f,
                    11f, 12f, 13f, 14f,
                    15f, 16f, 17f, 18f)
            val proj = Mat4(
                    19f, 20f, 21f, 22f,
                    23f, 24f, 25f, 26f,
                    27f, 28f, 29f, 30f,
                    31f, 32f, 33f, 34f)
            val viewport = Vec4i(35, 36, 37, 38)
            val project = GLM.project(obj, model, proj, viewport)

            project shouldBe Vec3(70.04578f, 72.66197f, 0.98239434f)
        }

        "rotate XYZ" {

            val mat = Mat4(
                    0f, 1f, 2f, 3f,
                    4f, 5f, 6f, 7f,
                    8f, 9f, 10f, 11f,
                    12f, 13f, 14f, 15f)

            val rotate = GLM.rotateXYZ(mat, 16f.rad, (17f + 90f).rad, (18f + 180f).rad)

            rotate shouldBe Mat4(
                    4.121773f, 4.6411877f, 5.1606007f, 5.680016f,
                    -7.7007446f, -9.170065f, -10.639385f, -12.108706f,
                    -1.9260118f, -1.1701641f, -0.41431677f, 0.3415308f,
                    12f, 13f, 14f, 15f)
        }

        "scale" {

            val mat = Mat4(
                    0f, 1f, 2f, 3f,
                    4f, 5f, 6f, 7f,
                    8f, 9f, 10f, 11f,
                    12f, 13f, 14f, 15f)

            val scale = Vec3(16f, 17f, 18f)

            val res = GLM.scale(mat, scale)

            res shouldBe Mat4(
                    0f, 16f, 32f, 48f,
                    68f, 85f, 102f, 119f,
                    144f, 162f, 180f, 198f,
                    12f, 13f, 14f, 15f)
        }

        "lookAt" {

            val eye = Vec3(0f, 1f, 2f)
            val center = Vec3(3f, 4f, 5f)
            val up = Vec3(6f, 7f, 8f)

            val lookAt = GLM.lookAt(eye, center, up)

            lookAt shouldBe Mat4(
                    0.4082482f, -0.7071068f, -0.57735026f, 0f,
                    -0.81649655f, 1.0430813E-7f, -0.57735026f, 0f,
                    0.40824836f, 0.7071067f, -0.57735026f, 0f,
                    -1.7881393E-7f, -1.4142135f, 1.7320508f, 1f)
        }

        "clean translation" {

            val m = Mat4 { i, j -> i * 4 + j }

            m[3] shouldBe Vec4(12f, 13f, 14f, 15f)

            m.cleanTranslationAssign()

            m[3] shouldBe Vec4(0f, 0f, 0f, 1f)
        }
    }
}
