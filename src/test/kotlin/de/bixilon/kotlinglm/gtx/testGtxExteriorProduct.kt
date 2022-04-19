package de.bixilon.kotlinglm.gtx

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.vec2.Vec2
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testGtxExteriorProduct : StringSpec() {

    init {
        "cross" {
            val f = Vec2(1f) cross Vec2(1f)
            GLM.equal(f, 0f, 0.001f) shouldBe true
        }
    }
}
