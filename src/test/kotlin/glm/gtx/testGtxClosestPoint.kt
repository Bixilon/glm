package glm.gtx

import de.bixilon.kotlinglm.glm
import de.bixilon.kotlinglm.shouldEqual
import de.bixilon.kotlinglm.vec3.Vec3
import io.kotest.core.spec.style.StringSpec

class testGtxClosestPoint : StringSpec() {

    init {

        "gtx Closest Point" {

            val origin = Vec3()

            val a = Vec3(0, 1, 0)
            val b = Vec3(1, 0, 0)

            val point = glm.closestPointOnLine(origin, a, b)
            point.shouldEqual(Vec3(0.5f, 0.5f, 0f), 0.00001f)
        }
    }
}
