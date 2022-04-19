package de.bixilon.kotlinglm

import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec2.Vec2d

interface Gauss {

    fun gauss(x: Float, expectedValue: Float, standardDeviation: Float) =
            GLM.exp(-((x - expectedValue) * (x - expectedValue)) / (2f * standardDeviation * standardDeviation)) /
                    (standardDeviation * GLM.sqrt(6.28318530717958647692528676655900576f))

    fun gauss(x: Double, expectedValue: Double, standardDeviation: Double) =
            GLM.exp(-((x - expectedValue) * (x - expectedValue)) / (2.0 * standardDeviation * standardDeviation)) /
                    (standardDeviation * GLM.sqrt(6.28318530717958647692528676655900576))

    fun gauss(coord: Vec2, expectedValue: Vec2, standardDeviation: Vec2, res: Vec2 = Vec2()): Float {
        val squaredX = ((coord.x - expectedValue.x) * (coord.x - expectedValue.x)) / (2f * standardDeviation.x * standardDeviation.x)
        val squaredY = ((coord.y - expectedValue.y) * (coord.y - expectedValue.y)) / (2f * standardDeviation.y * standardDeviation.y)
        return GLM.exp(-(squaredX + squaredY))
    }

    fun gauss(coord: Vec2d, expectedValue: Vec2d, standardDeviation: Vec2d, res: Vec2d = Vec2d()): Double {
        val squaredX = ((coord.x - expectedValue.x) * (coord.x - expectedValue.x)) / (2f * standardDeviation.x * standardDeviation.x)
        val squaredY = ((coord.y - expectedValue.y) * (coord.y - expectedValue.y)) / (2.0 * standardDeviation.y * standardDeviation.y)
        return GLM.exp(-(squaredX + squaredY))
    }
}
