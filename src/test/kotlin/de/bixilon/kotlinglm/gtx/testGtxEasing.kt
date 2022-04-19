package de.bixilon.kotlinglm.gtx

import de.bixilon.kotlinglm.GLM
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testGtxEasing : StringSpec() {

    init {

        "easing" {

            val a = 0.5f

            GLM.linearInterpolation(a) shouldBe 0.5f

            GLM.quadraticEaseIn(a) shouldBe 0.25f
            GLM.quadraticEaseOut(a) shouldBe 0.75f
            GLM.quadraticEaseInOut(a) shouldBe 0.5f

            GLM.cubicEaseIn(a) shouldBe 0.125f
            GLM.cubicEaseOut(a) shouldBe 0.875f
            GLM.cubicEaseInOut(a) shouldBe 0.5f

            GLM.quarticEaseIn(a) shouldBe 0.0625f
            GLM.quarticEaseOut(a) shouldBe 0.9375f
            GLM.quinticEaseInOut(a) shouldBe 0.5f

            GLM.sineEaseIn(a) shouldBe 0.292893231f
            GLM.sineEaseOut(a) shouldBe 0.707106769f
            GLM.sineEaseInOut(a) shouldBe 0.5f

            GLM.circularEaseIn(a) shouldBe 0.133974612f
            GLM.circularEaseOut(a) shouldBe 0.866025388f
            GLM.circularEaseInOut(a) shouldBe 0.5f

            GLM.exponentialEaseIn(a) shouldBe 0.03125f
            GLM.exponentialEaseOut(a) shouldBe 0.96875f
            GLM.exponentialEaseInOut(a) shouldBe 0.5f

            GLM.elasticEaseIn(a) shouldBe -0.0220970940f
            GLM.elasticEaseOut(a) shouldBe 1.02209711f
            GLM.elasticEaseInOut(a) shouldBe 0.5f

            GLM.backEaseIn(a) shouldBe -0.0876975060f
            GLM.backEaseOut(a) shouldBe 1.08769751f
            GLM.backEaseInOut(a) shouldBe 0.5f

            GLM.bounceEaseIn(a) shouldBe 0.281249762f
            GLM.bounceEaseOut(a) shouldBe 0.718750238f
            GLM.bounceEaseInOut(a) shouldBe 0.5f
        }
    }
}
