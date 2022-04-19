package glm.gtx

import de.bixilon.kotlinglm.glm
import de.bixilon.kotlinglm.vec2.Vec2i
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testGtxTexture : StringSpec() {

    init {
        "levels" {

            run {
                val levels = glm.levels(Vec2i(3, 2))
                levels shouldBe 2
            }

            run { glm.levels(32) shouldBe 6 }
        }
    }
}
