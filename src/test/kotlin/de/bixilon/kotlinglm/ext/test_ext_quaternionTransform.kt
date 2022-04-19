package de.bixilon.kotlinglm.ext

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.vec3.Vec3
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.floats.shouldNotBeGreaterThan

class test_ext_quaternionTransform : StringSpec() {

    init {
        "lookAt" {

            val eye = Vec3(0f)
            val center = Vec3(1.1f, -2f, 3.1416f)
            val up = Vec3(-0.17f, 7.23f, -1.744f)

            val testQuat = GLM.quatLookAt(GLM.normalize(center - eye), up)
            val testMat = GLM.conjugate(GLM.quat_cast(GLM.lookAt(eye, center, up)))

            GLM.abs(GLM.length(testQuat) - 1f) shouldNotBeGreaterThan GLM.εf
            GLM.min(GLM.length(testQuat + (-testMat)), GLM.length(testQuat + testMat)) shouldNotBeGreaterThan GLM.εf

             // Test left-handed implementation
            val testQuatLH = GLM.quatLookAtLH(GLM.normalize(center - eye), up)
            val testMatLH = GLM.conjugate(GLM.quat_cast(GLM.lookAtLH(eye, center, up)))
            GLM.abs(GLM.length(testQuatLH) - 1f) shouldNotBeGreaterThan GLM.εf
            GLM.min(GLM.length(testQuatLH - testMatLH), GLM.length(testQuatLH + testMatLH)) shouldNotBeGreaterThan GLM.εf

             // Test right-handed implementation
            val testQuatRH = GLM.quatLookAtRH(GLM.normalize(center - eye), up)
            val testMatRH = GLM.conjugate(GLM.quat_cast(GLM.lookAtRH(eye, center, up)))
            GLM.abs(GLM.length(testQuatRH) - 1f) shouldNotBeGreaterThan GLM.εf
            GLM.min(GLM.length(testQuatRH - testMatRH), GLM.length(testQuatRH + testMatRH)) shouldNotBeGreaterThan GLM.εf
        }
    }
}
