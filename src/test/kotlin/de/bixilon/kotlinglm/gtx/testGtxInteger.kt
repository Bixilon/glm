package de.bixilon.kotlinglm.gtx

import de.bixilon.kotlinglm.d
import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.i
import de.bixilon.kotlinglm.msb
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class testGtxInteger : StringSpec() {

    init {
        "nlz" {
            for (i in 1..32)
                GLM.nlz(i) shouldBe 31 - i.msb
        }

//        int test_floor_log2() TODO
//        {
//            int Error = 0;
//
//            for(std::size_t i = 1; i < 1000000; ++i)
//            {
//                glm::uint A = glm::floor_log2(GLM::uint(i));
//                glm::uint B = glm::uint(GLM::floor(GLM::log2(double(i)))); // Will fail with float, lack of accuracy
//
//                Error += A == B ? 0 : 1;
//                assert(!Error);
//            }
//
//            return Error;
//        }

        "log2" {

            for (i in 1..23) {
                val a = GLM.log2(1 shl i)
                val b = GLM.log2((1 shl i).d).i

                //Error += glm::equalEpsilon(double(A), B, 1.0) ? 0 : 1;
                (GLM.abs(a.d - b) <= 24) shouldBe true

                println("Log2(${1 shl 1}) error A=$a, B=$b")
            }
        }

        "pow uint" {

            val p0 = GLM.powU(2, 0)
            p0 shouldBe 1

            val p1 = GLM.powU(2, 1)
            p1 shouldBe 2

            val p2 = GLM.powU(2, 2)
            p2 shouldBe 4
        }

        "pow int" {

            val p0 = GLM.pow(2, 0)
            p0 shouldBe 1

            val p1 = GLM.pow(2, 1)
            p1 shouldBe 2

            val p2 = GLM.pow(2, 2)
            p2 shouldBe 4

            val p0n = GLM.pow(-2, 0)
            p0n shouldBe -1

            val p1n = GLM.pow(-2, 1)
            p1n shouldBe -2

            val p2n = GLM.pow(-2, 2)
            p2n shouldBe 4
        }
    }
}
