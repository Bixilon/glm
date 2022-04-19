package glm.ext

import glm.func.deg
import glm.glm
import glm.quat.Quat
import glm.shouldEqual
import glm.vec3.Vec3
import io.kotest.core.spec.style.StringSpec

class test_ext_quaternionGeometric : StringSpec() {

    init {

        "length" {

            run {
                val A = Quat(1, 0, 0, 0).length()
                A shouldEqual 1f
            }

            run {
                val A = Quat(1f, Vec3(0)).length()
                A shouldEqual 1f
            }

            run {
                val A = Quat(Vec3(1, 0, 0), Vec3(0, 1, 0)).length()
                A.shouldEqual(1f, 0.0001f)
            }
        }

        "normalize"        {

            run {
                val A = Quat(1, 0, 0, 0)
                val N = A.normalize()
                A shouldEqual N
            }

            run {
                val A = Quat(1f, Vec3(0))
                val N = A.normalize()
                A shouldEqual N
            }
        }

        "dot" {
            val A = Quat(1, 0, 0, 0)
            val B = Quat(1, 0, 0, 0)
            val C = A dot B
            C.shouldEqual(1f)
        }

        "cross" {}
    }
}
