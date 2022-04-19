package glm

import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec4.Vec4
import io.kotest.core.spec.style.StringSpec

class testColorSpace : StringSpec() {

    init {

        "test" {

            val colorSourceRGB = Vec3(1.0f, 0.5f, 0.0f)

            run {
                val colorSRGB = glm.convertLinearToSRGB(colorSourceRGB)
                val colorRGB = glm.convertSRGBToLinear(colorSRGB)
                colorSourceRGB.shouldEqual(colorRGB, 0.00001f)
            }

            run {
                val colorSRGB = glm.convertLinearToSRGB(colorSourceRGB, 2.8f)
                val colorRGB = glm.convertSRGBToLinear(colorSRGB, 2.8f)
                colorSourceRGB.shouldEqual(colorRGB, 0.00001f)
            }

            val colorSourceRGBA = Vec4(1.0f, 0.5f, 0.0f, 1.0f)

            run {
                val colorSRGB = glm.convertLinearToSRGB(colorSourceRGBA)
                val colorRGB = glm.convertSRGBToLinear(colorSRGB)
                colorSourceRGBA.shouldEqual(colorRGB, 0.00001f)
            }

            run {
                val colorSRGB = glm.convertLinearToSRGB(colorSourceRGBA, 2.8f)
                val colorRGB = glm.convertSRGBToLinear(colorSRGB, 2.8f)
                colorSourceRGBA.shouldEqual(colorRGB, 0.00001f)
            }

            val a = Vec2()
            a put listOf("516", "139")
            println(a)
        }
    }
}
