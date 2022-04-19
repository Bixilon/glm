package de.bixilon.kotlinglm.gtx

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.vec2.Vec2i
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testGtxTexture : StringSpec() {

    init {
        "levels" {

            run {
                val levels = GLM.levels(Vec2i(3, 2))
                levels shouldBe 2
            }

            run { GLM.levels(32) shouldBe 6 }
        }
    }
}
