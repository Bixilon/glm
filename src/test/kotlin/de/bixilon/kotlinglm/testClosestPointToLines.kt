package de.bixilon.kotlinglm

import de.bixilon.kotlinglm.vec3.Vec3
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testClosestPointToLines : StringSpec() {

    init {
        "closest point to lines" {

            val lines = arrayOf(
                    floatArrayOf(0f, 0f, 0f, 1f, 1f, 1f),
                    floatArrayOf(1f, 0f, 0f, -1f, 1f, 1f))

            val closestPoint = GLM.closestPointToLines(lines)

            closestPoint shouldBe Vec3(0.5f)
        }
    }
}
