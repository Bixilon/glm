package de.bixilon.kotlinglm.ext

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.quaternion.Quat
import de.bixilon.kotlinglm.quaternion.QuatD
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec3.Vec3d

interface ext_QuaternionTrigonometric {

    /** Returns euler angles, pitch as x, yaw as y, roll as z.
     * The result is expressed in radians.     */
    fun eulerAngles(a: Quat, res: Vec3): Vec3 {
        res.x = pitch(a)
        res.y = yaw(a)
        res.z = roll(a)
        return res
    }

    infix fun eulerAngles(a: Quat): Vec3 =
            eulerAngles(a, Vec3())

    /** Returns euler angles, pitch as x, yaw as y, roll as z.
     * The result is expressed in radians.     */
    fun eulerAngles(a: QuatD, res: Vec3d): Vec3d {
        res.x = pitch(a)
        res.y = yaw(a)
        res.z = roll(a)
        return res
    }

    infix fun eulerAngles(a: QuatD): Vec3d =
            eulerAngles(a, Vec3d())

    /** Returns roll value of euler angles expressed in radians.    */
    infix fun roll(q: Quat): Float =
            GLM.atan(2f * (q.x * q.y + q.w * q.z), q.w * q.w + q.x * q.x - q.y * q.y - q.z * q.z)

    /** Returns roll value of euler angles expressed in radians.    */
    infix fun roll(q: QuatD): Double =
            GLM.atan(2.0 * (q.x * q.y + q.w * q.z), q.w * q.w + q.x * q.x - q.y * q.y - q.z * q.z)


    /** Returns pitch value of euler angles expressed in radians.   */
    infix fun pitch(q: Quat): Float {
        //atan(2f * (q.y * q.z + q.w * q.x), q.w * q.w - q.x * q.x - q.y * q.y + q.z * q.z)
        val y = 2f * (q.y * q.z + q.w * q.x)
        val x = q.w * q.w - q.x * q.x - q.y * q.y + q.z * q.z
        return when {
            //avoid atan2(0,0) - handle singularity - Matiis
            y.equal(0f) && x.equal(0f) -> 2f * GLM.atan(q.x, q.w)
            else -> GLM.atan(y, x)
        }
    }

    /** Returns pitch value of euler angles expressed in radians.   */
    infix fun pitch(q: QuatD): Double =
            GLM.atan(2.0 * (q.y * q.z + q.w * q.x), q.w * q.w - q.x * q.x - q.y * q.y + q.z * q.z)


    /** Returns yaw value of euler angles expressed in radians. */
    infix fun yaw(q: Quat): Float =
            GLM.asin(GLM.clamp(-2f * (q.x * q.z - q.w * q.y), -1f, 1f))

    /** Returns yaw value of euler angles expressed in radians. */
    infix fun yaw(q: QuatD): Double =
            GLM.asin(GLM.clamp(-2.0 * (q.x * q.z - q.w * q.y), -1.0, 1.0))


    // TODO remove everything above https://github.com/g-truc/glm/commit/fceca6cf19eb583b97f6850eeef3b50bc5c8ef06

    /** Returns the quaternion rotation angle.  */
    fun angle(q: Quat): Float = GLM.acos(q.w) * 2f

    /** Returns the quaternion rotation angle.  */
    fun angle(q: QuatD): Double = GLM.acos(q.w) * 2.0


    /** Returns the q rotation axis.    */
    fun axis(q: Quat, res: Vec3): Vec3 {

        val tmp1 = 1f - q.w * q.w
        if (tmp1 <= 0f) {
            res.x = 0f
            res.y = 0f
            res.z = 1f
            return res
        }
        val tmp2 = 1f / GLM.sqrt(tmp1)
        res.x = q.x * tmp2
        res.y = q.y * tmp2
        res.z = q.z * tmp2
        return res
    }

    fun axis(q: Quat): Vec3 = axis(q, Vec3())

    /** Returns the q rotation axis.    */
    fun axis(q: QuatD, res: Vec3d): Vec3d {

        val tmp1 = 1.0 - q.w * q.w
        if (tmp1 <= 0.0) {
            res.x = 0.0
            res.y = 0.0
            res.z = 1.0
            return res
        }
        val tmp2 = 1.0 / GLM.sqrt(tmp1)
        res.x = q.x * tmp2
        res.y = q.y * tmp2
        res.z = q.z * tmp2
        return res
    }

    fun axis(q: QuatD): Vec3d = axis(q, Vec3d())


    /** Build a quaternion from an angle main.and a normalized axis. */
    fun angleAxis(angle: Float, axisX: Float, axisY: Float, axisZ: Float, res: Quat): Quat {

        val a = angle * 0.5f
        val s = GLM.sin(a)

        res.w = GLM.cos(a)
        res.x = axisX * s
        res.y = axisY * s
        res.z = axisZ * s

        return res
    }

    fun angleAxis(angle: Float, axisX: Float, axisY: Float, axisZ: Float): Quat = angleAxis(angle, axisX, axisY, axisZ, Quat())
    fun angleAxis(angle: Float, axis: Vec3, res: Quat): Quat = angleAxis(angle, axis.x, axis.y, axis.z, res)
    fun angleAxis(angle: Float, axis: Vec3): Quat = angleAxis(angle, axis.x, axis.y, axis.z, Quat())


    /** Build a quaternion from an angle main.and a normalized axis. */
    fun angleAxis(angle: Double, axisX: Double, axisY: Double, axisZ: Double, res: QuatD): QuatD {

        val a = angle * 0.5
        val s = GLM.sin(a)

        res.w = GLM.cos(a)
        res.x = axisX * s
        res.y = axisY * s
        res.z = axisZ * s

        return res
    }

    fun angleAxis(angle: Double, axisX: Double, axisY: Double, axisZ: Double): QuatD = angleAxis(angle, axisX, axisY, axisZ, QuatD())
    fun angleAxis(angle: Double, axis: Vec3d, res: QuatD): QuatD = angleAxis(angle, axis.x, axis.y, axis.z, res)
    fun angleAxis(angle: Double, axis: Vec3d): QuatD = angleAxis(angle, axis.x, axis.y, axis.z, QuatD())
}
