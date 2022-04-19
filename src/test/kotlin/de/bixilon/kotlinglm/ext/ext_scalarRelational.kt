package de.bixilon.kotlinglm.ext

import de.bixilon.kotlinglm.GLM
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class ext_scalarRelational: StringSpec() {

    init {
        "test equal"        {

            GLM.equal(1.01f, 1.02f, 0.1f) shouldBe true
            GLM.equal(1.01f, 1.02f, 0.001f) shouldBe false
        }

        "test notEqual"        {

            GLM.notEqual(1.01f, 1.02f, 0.001f) shouldBe true
            GLM.notEqual(1.01f, 1.02f, 0.1f) shouldBe false
        }
    }
}
