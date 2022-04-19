package de.bixilon.kotlinglm.ext

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.shouldEqual
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class test_ext_scalarCommon : StringSpec() {

    init {

        "min" {
            run {
                val N = 0f
                val B = 1f
                GLM.min(N, B) shouldEqual N
                GLM.min(B, N) shouldEqual N

                val C = 2f
                GLM.min(N, B, C) shouldEqual N
                GLM.min(B, N, C) shouldEqual N
                GLM.min(C, N, B) shouldEqual N
                GLM.min(C, B, N) shouldEqual N
                GLM.min(B, C, N) shouldEqual N
                GLM.min(N, C, B) shouldEqual N

                val D = 3f
                GLM.min(D, N, B, C) shouldEqual N
                GLM.min(B, D, N, C) shouldEqual N
                GLM.min(C, N, D, B) shouldEqual N
                GLM.min(C, B, D, N) shouldEqual N
                GLM.min(B, C, N, D) shouldEqual N
                GLM.min(N, C, B, D) shouldEqual N
            }
            run {
                val N = 0.0
                val B = 1.0
                GLM.min(N, B) shouldEqual N
                GLM.min(B, N) shouldEqual N

                val C = 2.0
                GLM.min(N, B, C) shouldEqual N
                GLM.min(B, N, C) shouldEqual N
                GLM.min(C, N, B) shouldEqual N
                GLM.min(C, B, N) shouldEqual N
                GLM.min(B, C, N) shouldEqual N
                GLM.min(N, C, B) shouldEqual N

                val D = 3.0
                GLM.min(D, N, B, C) shouldEqual N
                GLM.min(B, D, N, C) shouldEqual N
                GLM.min(C, N, D, B) shouldEqual N
                GLM.min(C, B, D, N) shouldEqual N
                GLM.min(B, C, N, D) shouldEqual N
                GLM.min(N, C, B, D) shouldEqual N
            }
        }

        "min nan" {
            run {
                val A = 0f
                val B = 1f
                val N = Float.NaN
                GLM.isNan(GLM.min(N, B)) shouldBe true
                GLM.isNan(GLM.min(B, N)) shouldBe true // jvm returns always NaN if any input is NaN

                val C = 2f
                GLM.isNan(GLM.min(N, B, C)) shouldBe true
                GLM.isNan(GLM.min(B, N, C)) shouldBe true
                GLM.isNan(GLM.min(C, N, B)) shouldBe true
                GLM.isNan(GLM.min(C, B, N)) shouldBe true
                GLM.isNan(GLM.min(B, C, N)) shouldBe true
                GLM.isNan(GLM.min(N, C, B)) shouldBe true

                val D = 3f
                GLM.isNan(GLM.min(D, N, B, C)) shouldBe true
                GLM.isNan(GLM.min(B, D, N, C)) shouldBe true
                GLM.isNan(GLM.min(C, N, D, B)) shouldBe true
                GLM.isNan(GLM.min(C, B, D, N)) shouldBe true
                GLM.isNan(GLM.min(B, C, N, D)) shouldBe true
                GLM.isNan(GLM.min(N, C, B, D)) shouldBe true
            }
            run {
                val A = 0.0
                val B = 1.0
                val N = Double.NaN
                GLM.isNan(GLM.min(N, B)) shouldBe true
                GLM.isNan(GLM.min(B, N)) shouldBe true // jvm returns always NaN if any input is NaN

                val C = 2.0
                GLM.isNan(GLM.min(N, B, C)) shouldBe true
                GLM.isNan(GLM.min(B, N, C)) shouldBe true
                GLM.isNan(GLM.min(C, N, B)) shouldBe true
                GLM.isNan(GLM.min(C, B, N)) shouldBe true
                GLM.isNan(GLM.min(B, C, N)) shouldBe true
                GLM.isNan(GLM.min(N, C, B)) shouldBe true

                val D = 3.0
                GLM.isNan(GLM.min(D, N, B, C)) shouldBe true
                GLM.isNan(GLM.min(B, D, N, C)) shouldBe true
                GLM.isNan(GLM.min(C, N, D, B)) shouldBe true
                GLM.isNan(GLM.min(C, B, D, N)) shouldBe true
                GLM.isNan(GLM.min(B, C, N, D)) shouldBe true
                GLM.isNan(GLM.min(N, C, B, D)) shouldBe true
            }
        }

        "max" {
            run {
                val N = 0f
                val B = 1f
                GLM.max(N, B) shouldEqual B
                GLM.max(B, N) shouldEqual B

                val C = 2f
                GLM.max(N, B, C) shouldEqual C
                GLM.max(B, N, C) shouldEqual C
                GLM.max(C, N, B) shouldEqual C
                GLM.max(C, B, N) shouldEqual C
                GLM.max(B, C, N) shouldEqual C
                GLM.max(N, C, B) shouldEqual C

                val D = 3f
                GLM.max(D, N, B, C) shouldEqual D
                GLM.max(B, D, N, C) shouldEqual D
                GLM.max(C, N, D, B) shouldEqual D
                GLM.max(C, B, D, N) shouldEqual D
                GLM.max(B, C, N, D) shouldEqual D
                GLM.max(N, C, B, D) shouldEqual D
            }
            run {
                val N = 0.0
                val B = 1.0
                GLM.max(N, B) shouldEqual B
                GLM.max(B, N) shouldEqual B

                val C = 2.0
                GLM.max(N, B, C) shouldEqual C
                GLM.max(B, N, C) shouldEqual C
                GLM.max(C, N, B) shouldEqual C
                GLM.max(C, B, N) shouldEqual C
                GLM.max(B, C, N) shouldEqual C
                GLM.max(N, C, B) shouldEqual C

                val D = 3.0
                GLM.max(D, N, B, C) shouldEqual D
                GLM.max(B, D, N, C) shouldEqual D
                GLM.max(C, N, D, B) shouldEqual D
                GLM.max(C, B, D, N) shouldEqual D
                GLM.max(B, C, N, D) shouldEqual D
                GLM.max(N, C, B, D) shouldEqual D
            }
        }

        "max nan" {
            run {
                val A = 0f
                val B = 1f
                val N = Float.NaN
                GLM.isNan(GLM.max(N, B)) shouldBe true
                GLM.isNan(GLM.max(B, N)) shouldBe true

                val C = 2f
                GLM.isNan(GLM.max(N, B, C)) shouldBe true
                GLM.isNan(GLM.max(B, N, C)) shouldBe true
                GLM.isNan(GLM.max(C, N, B)) shouldBe true
                GLM.isNan(GLM.max(C, B, N)) shouldBe true
                GLM.isNan(GLM.max(B, C, N)) shouldBe true
                GLM.isNan(GLM.max(N, C, B)) shouldBe true

                val D = 3f
                GLM.isNan(GLM.max(D, N, B, C)) shouldBe true
                GLM.isNan(GLM.max(B, D, N, C)) shouldBe true
                GLM.isNan(GLM.max(C, N, D, B)) shouldBe true
                GLM.isNan(GLM.max(C, B, D, N)) shouldBe true
                GLM.isNan(GLM.max(B, C, N, D)) shouldBe true
                GLM.isNan(GLM.max(N, C, B, D)) shouldBe true
            }
            run {
                val A = 0.0
                val B = 1.0
                val N = Double.NaN
                GLM.isNan(GLM.max(N, B)) shouldBe true
                GLM.isNan(GLM.max(B, N)) shouldBe true

                val C = 2.0
                GLM.isNan(GLM.max(N, B, C)) shouldBe true
                GLM.isNan(GLM.max(B, N, C)) shouldBe true
                GLM.isNan(GLM.max(C, N, B)) shouldBe true
                GLM.isNan(GLM.max(C, B, N)) shouldBe true
                GLM.isNan(GLM.max(B, C, N)) shouldBe true
                GLM.isNan(GLM.max(N, C, B)) shouldBe true

                val D = 3.0
                GLM.isNan(GLM.max(D, N, B, C)) shouldBe true
                GLM.isNan(GLM.max(B, D, N, C)) shouldBe true
                GLM.isNan(GLM.max(C, N, D, B)) shouldBe true
                GLM.isNan(GLM.max(C, B, D, N)) shouldBe true
                GLM.isNan(GLM.max(B, C, N, D)) shouldBe true
                GLM.isNan(GLM.max(N, C, B, D)) shouldBe true
            }
        }

        "fmin" {
            run {
                val A = 0f
                val B = 1f
                val N = Float.NaN
                GLM.fmin(N, B) shouldEqual B
                GLM.fmin(B, N) shouldEqual B

                val C = 2f
                GLM.fmin(N, B, C) shouldEqual B
                GLM.fmin(B, N, C) shouldEqual B
                GLM.fmin(C, N, B) shouldEqual B
                GLM.fmin(C, B, N) shouldEqual B
                GLM.fmin(B, C, N) shouldEqual B
                GLM.fmin(N, C, B) shouldEqual B

                val D = 3f
                GLM.fmin(D, N, B, C) shouldEqual B
                GLM.fmin(B, D, N, C) shouldEqual B
                GLM.fmin(C, N, D, B) shouldEqual B
                GLM.fmin(C, B, D, N) shouldEqual B
                GLM.fmin(B, C, N, D) shouldEqual B
                GLM.fmin(N, C, B, D) shouldEqual B
            }
            run {
                val A = 0.0
                val B = 1.0
                val N = Double.NaN
                GLM.fmin(N, B) shouldEqual B
                GLM.fmin(B, N) shouldEqual B

                val C = 2.0
                GLM.fmin(N, B, C) shouldEqual B
                GLM.fmin(B, N, C) shouldEqual B
                GLM.fmin(C, N, B) shouldEqual B
                GLM.fmin(C, B, N) shouldEqual B
                GLM.fmin(B, C, N) shouldEqual B
                GLM.fmin(N, C, B) shouldEqual B

                val D = 3.0
                GLM.fmin(D, N, B, C) shouldEqual B
                GLM.fmin(B, D, N, C) shouldEqual B
                GLM.fmin(C, N, D, B) shouldEqual B
                GLM.fmin(C, B, D, N) shouldEqual B
                GLM.fmin(B, C, N, D) shouldEqual B
                GLM.fmin(N, C, B, D) shouldEqual B
            }
        }

        "fmax" {
            run {
                val A = 0f
                val B = 1f
                val N = Float.NaN
                GLM.fmax(N, B) shouldEqual B
                GLM.fmax(B, N) shouldEqual B

                val C = 2f
                GLM.fmax(N, B, C) shouldEqual C
                GLM.fmax(B, N, C) shouldEqual C
                GLM.fmax(C, N, B) shouldEqual C
                GLM.fmax(C, B, N) shouldEqual C
                GLM.fmax(B, C, N) shouldEqual C
                GLM.fmax(N, C, B) shouldEqual C

                val D = 3f
                GLM.fmax(D, N, B, C) shouldEqual D
                GLM.fmax(B, D, N, C) shouldEqual D
                GLM.fmax(C, N, D, B) shouldEqual D
                GLM.fmax(C, B, D, N) shouldEqual D
                GLM.fmax(B, C, N, D) shouldEqual D
                GLM.fmax(N, C, B, D) shouldEqual D
            }
            run {
                val A = 0.0
                val B = 1.0
                val N = Double.NaN
                GLM.fmax(N, B) shouldEqual B
                GLM.fmax(B, N) shouldEqual B

                val C = 2.0
                GLM.fmax(N, B, C) shouldEqual C
                GLM.fmax(B, N, C) shouldEqual C
                GLM.fmax(C, N, B) shouldEqual C
                GLM.fmax(C, B, N) shouldEqual C
                GLM.fmax(B, C, N) shouldEqual C
                GLM.fmax(N, C, B) shouldEqual C

                val D = 3.0
                GLM.fmax(D, N, B, C) shouldEqual D
                GLM.fmax(B, D, N, C) shouldEqual D
                GLM.fmax(C, N, D, B) shouldEqual D
                GLM.fmax(C, B, D, N) shouldEqual D
                GLM.fmax(B, C, N, D) shouldEqual D
                GLM.fmax(N, C, B, D) shouldEqual D
            }
        }
    }
}
