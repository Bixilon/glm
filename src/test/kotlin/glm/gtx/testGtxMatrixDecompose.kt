package glm.gtx

import glm.glm
import glm.mat4x4.Mat4
import glm.quat.Quat
import glm.vec3.Vec3
import glm.vec4.Vec4
import io.kotest.core.spec.style.StringSpec

class testGtxMatrixDecompose : StringSpec() {

    init {

        "gtx matrix decompose" {

            val matrix = Mat4(1)

            val scale = Vec3()
            val orientation = Quat()
            val translation = Vec3()
            val skew = Vec3(1)
            val perspective = Vec4(1)

            glm.decompose(matrix, scale, orientation, translation, skew, perspective)
        }
    }
}
