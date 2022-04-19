package de.bixilon.kotlinglm.gtx

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.mat4x4.Mat4
import de.bixilon.kotlinglm.quaternion.Quat
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec4.Vec4
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

            GLM.decompose(matrix, scale, orientation, translation, skew, perspective)
        }
    }
}
