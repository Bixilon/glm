package de.bixilon.kotlinglm.ext

import de.bixilon.kotlinglm.func.deg
import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.quaternion.Quat
import de.bixilon.kotlinglm.shouldEqual
import de.bixilon.kotlinglm.vec3.Vec3
import io.kotest.core.spec.style.StringSpec

class test_ext_quaternionTrigonometric : StringSpec() {

    init {

        "angle" {

            run {
                val Q = Quat(Vec3(1, 0, 0), Vec3(0, 1, 0))
                val A = GLM.angle(Q).deg
                A shouldEqual 90f
            }

            run {
                val Q = Quat(Vec3(0, 1, 0), Vec3(1, 0, 0))
                val A = GLM.angle(Q).deg
                A shouldEqual 90f
            }
        }
    }
}
