package de.bixilon.kotlinglm.ext

import de.bixilon.kotlinglm.GLM
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.shouldBeGreaterThan
import io.kotest.matchers.doubles.shouldBeLessThan
import io.kotest.matchers.floats.shouldBeGreaterThan
import io.kotest.matchers.floats.shouldBeLessThan

class ext_scalarConstants : StringSpec() {

    init {

        "epsilon" {

            run {
                val test = GLM.εf
                test shouldBeGreaterThan 0f
            }
            run {
                val test = GLM.ε
                test shouldBeGreaterThan 0.0
            }
        }

        "pi" {
            run {
                val test = GLM.πf
                test shouldBeGreaterThan 3.14f
                test shouldBeLessThan 3.15f
            }
            run {
                val test = GLM.π
                test shouldBeGreaterThan 3.14
                test shouldBeLessThan 3.15
            }
        }
    }
}
