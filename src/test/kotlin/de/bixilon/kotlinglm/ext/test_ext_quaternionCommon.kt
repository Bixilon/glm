package de.bixilon.kotlinglm.ext

import de.bixilon.kotlinglm.func.deg
import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.quaternion.Quat
import de.bixilon.kotlinglm.shouldEqual
import de.bixilon.kotlinglm.vec3.Vec3
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class test_ext_quaternionCommon : StringSpec() {

    init {

        "conjugate" {

            val A = Quat(Vec3(1, 0, 0), Vec3(0, 1, 0))
            val C = A.conjugate()
            A.anyNotEqual(C) shouldBe true

            val B = C.conjugate()
            A shouldEqual B
        }

        "mix" {

            val Q1 = Quat(Vec3(1, 0, 0), Vec3(1, 0, 0))
            val Q2 = Quat(Vec3(1, 0, 0), Vec3(0, 1, 0))

            run {
                val Q3 = GLM.mix(Q1, Q2, 0.5f)
                val F3 = GLM.angle(Q3).deg
                F3.shouldEqual(45f, 0.001f)

                val Q4 = GLM.mix(Q2, Q1, 0.5f)
                val F4 = GLM.angle(Q4).deg
                F4.shouldEqual(45f, 0.001f)
            }

            run {
                val Q3 = GLM.slerp(Q1, Q2, 0.5f)
                val F3 = GLM.angle(Q3).deg
                F3.shouldEqual(45f, 0.001f)

                val Q4 = GLM.slerp(Q2, Q1, 0.5f)
                val F4 = GLM.angle(Q4).deg
                F4.shouldEqual(45f, 0.001f)
            }
        }
    }
}
