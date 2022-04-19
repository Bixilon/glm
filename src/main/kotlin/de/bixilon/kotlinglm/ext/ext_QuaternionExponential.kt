package de.bixilon.kotlinglm.ext

import de.bixilon.kotlinglm.func.cos
import de.bixilon.kotlinglm.func.sin
import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.quaternion.Quat
import de.bixilon.kotlinglm.quaternion.QuatD
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec3.Vec3d
import de.bixilon.kotlinglm.vec3.operators.times
import kotlin.math.*

interface ext_QuaternionExponential {

    /** Returns a exp of a quaternion.
     *  @see gtx_quaternion */
    fun exp(q: Quat): Quat {

        val u = Vec3(q.x, q.y, q.z)
        val angle = u.length()
        if (angle < GLM.εf)
            return Quat()

        val v = u / angle
        return Quat(angle.cos, angle.sin * v)
    }

    /** Returns a log of a quaternion.
     *  @see gtx_quaternion */
    fun log(q: Quat): Quat {

        val u = Vec3(q.x, q.y, q.z)
        val vec3Len = u.length()

        return when {
            vec3Len < GLM.εf -> when {
                q.w > 0f -> Quat(GLM.log(q.w), 0f, 0f, 0f)
                q.w < 0f -> Quat(GLM.log(-q.w), GLM.πf, 0f, 0f)
                else -> Quat(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
            }
            else -> {
                val t = GLM.atan(vec3Len, q.w) / vec3Len
                val quatLen2 = vec3Len * vec3Len + q.w * q.w
                Quat(0.5f * GLM.log(quatLen2), t * q.x, t * q.y, t * q.z)
            }
        }
    }

    /** Returns x raised to the y power.
     *  @see gtx_quaternion */
    fun pow(x: Quat, y: Float): Quat {
        // Raising to the power of 0 should yield 1
        // Needed to prevent a division by 0 error later on
        if (y > -GLM.εf && y < GLM.εf) return Quat(1f, 0f, 0f, 0f)

        // To deal with non-unit quaternions
        val magnitude = sqrt(x.x * x.x + x.y * x.y + x.z * x.z + x.w * x.w)

        // Equivalent to raising a real number to a power
        // Needed to prevent a division by 0 error later on
        if (abs(x.w / magnitude) > 1f - GLM.εf && abs(x.w / magnitude) < 1 + GLM.εf)
            return Quat(GLM.pow(x.w, y), 0f, 0f, 0f)

        val angle = acos(x.w / magnitude)
        val newAngle = angle * y
        val div = sin(newAngle) / sin(angle)
        val mag = GLM.pow(magnitude, y - 1)

        return Quat(cos(newAngle) * magnitude * mag, x.x * div * mag, x.y * div * mag, x.z * div * mag)
    }

    /** Returns quarternion square root.
     *  @see gtx_quaternion */
    fun sqrt(q: Quat): Quat =
            pow(q, 0.5f)

    // ------------------------------ QuatD ------------------------------

    /** Returns a exp of a quaternion.
     *  @see gtx_quaternion */
    fun exp(q: QuatD): QuatD {

        val u = Vec3d(q.x, q.y, q.z)
        val angle = u.length()
        if (angle < GLM.ε)
            return QuatD()

        val v = u / angle
        return QuatD(angle.cos, angle.sin * v)
    }

    /** Returns a log of a quaternion.
     *  @see gtx_quaternion */
    fun log(q: QuatD): QuatD {

        val u = Vec3d(q.x, q.y, q.z)
        val vec3Len = u.length()

        return when {
            vec3Len < GLM.ε -> when {
                q.w > 0.0 -> QuatD(GLM.log(q.w), 0.0, 0.0, 0.0)
                q.w < 0.0 -> QuatD(GLM.log(-q.w), GLM.π, 0.0, 0.0)
                else -> QuatD(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
            }
            else -> {
                val t = GLM.atan(vec3Len, q.w) / vec3Len
                val quatLen2 = vec3Len * vec3Len + q.w * q.w
                QuatD(0.5 * GLM.log(quatLen2), t * q.x, t * q.y, t * q.z)
            }
        }
    }

    /** Returns x raised to the y power.
     *  @see gtx_quaternion */
    fun pow(x: QuatD, y: Double): QuatD {
        // Raising to the power of 0 should yield 1
        // Needed to prevent a division by 0 error later on
        if (y > -GLM.ε && y < GLM.ε) return QuatD(1.0, 0.0, 0.0, 0.0)

        // To deal with non-unit quaternions
        val magnitude = sqrt(x.x * x.x + x.y * x.y + x.z * x.z + x.w * x.w)

        // Equivalent to raising a real number to a power
        // Needed to prevent a division by 0 error later on
        if (abs(x.w / magnitude) > 1f - GLM.ε && abs(x.w / magnitude) < 1 + GLM.ε)
            return QuatD(GLM.pow(x.w, y), 0.0, 0.0, 0.0)

        val angle = acos(x.w / magnitude)
        val newAngle = angle * y
        val div = sin(newAngle) / sin(angle)
        val mag = GLM.pow(magnitude, y - 1)

        return QuatD(cos(newAngle) * magnitude * mag, x.x * div * mag, x.y * div * mag, x.z * div * mag)
    }

    /** Returns quarternion square root.
     *  @see gtx_quaternion */
    fun sqrt(q: QuatD): QuatD =
            pow(q, 0.5)
}
