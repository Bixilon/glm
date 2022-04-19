package glm

import glm.vec2.Vec2
import glm.vec2.Vec2ub
import glm.vec2.Vec2us
import io.kotest.core.spec.style.StringSpec


class testMix : StringSpec() {

    init {

        "test" {

            val a = Vec2(3, 4)
            val b = Vec2(1)
            val c = Vec2(2)
            a plusAssign b * c
            println(a)
        }

        run {
            val a = Vec2us(3.s)
        }
    }
}
