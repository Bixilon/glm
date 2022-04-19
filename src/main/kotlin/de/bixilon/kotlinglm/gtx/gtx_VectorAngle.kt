package de.bixilon.kotlinglm.gtx

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec4.Vec4
import kotlin.math.acos

interface gtx_VectorAngle {

    /** Returns the absolute angle between two vectors.
     *  Parameters need to be normalized.
     *  @see gtx_vector_angle extension.    */
    fun angle(x: Vec2, y: Vec2) = acos(GLM.clamp(x dot y, -1f, 1f))

    /** Returns the absolute angle between two vectors.
     *  Parameters need to be normalized.
     *  @see gtx_vector_angle extension.    */
    fun angle(x: Vec3, y: Vec3) = acos(GLM.clamp(x dot y, -1f, 1f))

    /** Returns the absolute angle between two vectors.
     *  Parameters need to be normalized.
     *  @see gtx_vector_angle extension.    */
    fun angle(x: Vec4, y: Vec4) = acos(GLM.clamp(x dot y, -1f, 1f))

    /** Returns the oriented angle between two 2d vectors.
     *  Parameters need to be normalized.
     *  @see gtx_vector_angle extension.    */
    fun orientedAngle(x: Vec2, y: Vec2): Float {

        val angle = acos(GLM.clamp(x dot y, -1f, 1f))

        return when {
            y.allEqual(x rotate angle, 0.0001f) -> angle
            else -> -angle
        }
    }

    /** Returns the oriented angle between two 3d vectors based from a reference axis.
     *  Parameters need to be normalized.
     *  @see gtx_vector_angle extension.    */
    fun orientedAngle(x: Vec3, y: Vec3, ref: Vec3): Float {
        val angle = acos(GLM.clamp(x dot y, -1f, 1f))
        return GLM.mix(angle, -angle, (ref dot (x cross y)) < 0f)
    }
}
