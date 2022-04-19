package glm.gtx

import glm.glm
import glm.vec3.Vec3
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testGtxNormal : StringSpec() {

    init {
        "gtx normal" {

            run {
                val p1 = Vec3()
                val p2 = Vec3(1, 0, 0)
                val p3 = Vec3(0, 1, 0)

                val normal = glm.triangleNormal(p1, p2, p3)

                normal shouldBe Vec3(0, 0, 1)
            }

            run {
                val p1 = Vec3()
                val p2 = Vec3(1, 0, 0)
                val p3 = Vec3(0, 0, 1)

                val normal = glm.triangleNormal(p1, p2, p3)

                normal shouldBe Vec3(0, -1, 0)
            }

            run {
                val p1 = Vec3()
                val p2 = Vec3(0, 0, 1)
                val p3 = Vec3(0, 1, 0)

                val normal = glm.triangleNormal(p1, p2, p3)

                normal shouldBe Vec3(-1, 0, 0)
            }
        }
    }
}
