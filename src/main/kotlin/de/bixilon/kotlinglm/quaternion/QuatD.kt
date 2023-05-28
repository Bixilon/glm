package de.bixilon.kotlinglm.quaternion

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.d
import de.bixilon.kotlinglm.gtc.gtc_Quaternion
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec3.Vec3d
import de.bixilon.kotlinglm.vec4.Vec4d
import de.bixilon.kotlinglm.vec4.Vec4t
import de.bixilon.kotlinkool.BYTES
import de.bixilon.kotlinkool.DoublePtr
import de.bixilon.kotlinkool.Ptr
import org.lwjgl.system.MemoryUtil.memGetDouble
import org.lwjgl.system.MemoryUtil.memPutDouble
import java.io.PrintStream
import kotlin.math.abs
import kotlin.math.sqrt


/**
 * Created by GBarbieri on 15.11.2016. TODO array
 */
class QuatD(w: Double, x: Double, y: Double, z: Double) : QuatT<Double>(w, x, y, z) {

    // -- Implicit basic constructors --

    constructor() : this(1.0, 0.0, 0.0, 0.0)
    constructor(d: Double) : this(d, d, d, d)
    constructor(q: Quat) : this(q.w, q.x, q.y, q.z)
    constructor(s: Double, v: Vec3d) : this(s, v.x, v.y, v.z)
    constructor(u: Vec3d, v: Vec3d) : this() {
        val normUnormV = sqrt((u dot u) * (v dot v))
        var realPart = normUnormV + (u dot v)
        val w = when {
            realPart < 1e-6f * normUnormV -> {
                /*  If u and v are exactly opposite, rotate 180 degrees around an arbitrary orthogonal axis.
                    Axis normalisation can happen later, when we normalise the quaternion. */
                realPart = 0.0
                if (abs(u.x) > abs(u.z)) Vec3d(-u.y, u.x, 0.0) else Vec3d(0.0, -u.z, u.y)
            }
            // Otherwise, build quaternion the standard way.
            else -> u cross v
        }
        put(realPart, w.x, w.y, w.z).normalizeAssign()
    }

    constructor(block: (Int) -> Double) : this(block(0), block(1), block(2), block(3))

    constructor(eulerAngle: Vec3) : this() {
        val eX = eulerAngle.x * .5
        val eY = eulerAngle.y * .5
        val eZ = eulerAngle.z * .5
        val cX = GLM.cos(eX)
        val cY = GLM.cos(eY)
        val cZ = GLM.cos(eZ)
        val sX = GLM.sin(eX)
        val sY = GLM.sin(eY)
        val sZ = GLM.sin(eZ)
        w = cX * cY * cZ + sX * sY * sZ
        x = sX * cY * cZ - cX * sY * sZ
        y = cX * sY * cZ + sX * cY * sZ
        z = cX * cY * sZ - sX * sY * cZ
    }

    constructor(vec4: Vec4d) : this(vec4.w, vec4.x, vec4.y, vec4.z)
//    constructor(m: Mat3x3) : this() {
//        quat_cast(m, this)
//    }
//    constructor(m: Mat4x4) : this() {
//        quat_cast(m, this)
//    }

    // TODO
//    infix fun to(res: Mat3) = GLM.mat3_cast(res, this)
//    fun toMat3() = GLM.mat3_cast(Mat3(), this)
//
//    infix fun to(res: Mat4) = GLM.mat4_cast(res, this)
//    fun toMat4() = GLM.mat4_cast(Mat4(), this)

    // -- Explicit basic constructors --

    constructor(q: QuatD) : this(q.w.d, q.x.d, q.y.d, q.z.d)
    constructor(w: Number, x: Number, y: Number, z: Number) : this(w.d, x.d, y.d, z.d)
    constructor(vec4: Vec4t<*>) : this(vec4._w.d, vec4._x.d, vec4._y.d, vec4._z.d)

    constructor(ptr: DoublePtr) : this(block = { i -> ptr[i] })


    fun put(w: Double, x: Double, y: Double, z: Double): QuatD {
        this.w = w
        this.x = x
        this.y = y
        this.z = z
        return this
    }

    infix fun put(quat: QuatD) = put(quat.w, quat.x, quat.y, quat.z)

    infix fun to(ptr: Ptr) {
        memPutDouble(ptr, w)
        memPutDouble(ptr + Double.BYTES, x)
        memPutDouble(ptr + Double.BYTES * 2, y)
        memPutDouble(ptr + Double.BYTES * 3, z)
    }
    
    // -- Component accesses --

    override operator fun set(index: Int, value: Number) = when (index) {
        0 -> x = value.d
        1 -> y = value.d
        2 -> z = value.d
        3 -> w = value.d
        else -> throw ArrayIndexOutOfBoundsException()
    }


    // -- Unary arithmetic operators --

    operator fun unaryPlus() = this

    operator fun unaryMinus() = QuatD(-w, -x, -y, -z)


    // -- Specific binary arithmetic operators --

    infix operator fun plus(b: QuatD) = plus(QuatD(), this, b)
    fun plus(b: QuatD, res: QuatD) = plus(res, this, b)
    infix operator fun plusAssign(b: QuatD) {
        plus(this, this, b)
    }


    operator fun minus(b: QuatD) = minus(QuatD(), this, b)
    fun minus(b: QuatD, res: QuatD) = minus(res, this, b)
    infix operator fun minusAssign(b: QuatD) {
        minus(this, this, b)
    }


    infix operator fun times(b: QuatD) = times(QuatD(), this, b)
    infix operator fun times(b: Double) = times(QuatD(), this, b)
    infix operator fun times(b: Vec3d) = times(Vec3d(), this, b)
    infix operator fun times(b: Vec4d) = times(QuatD(), this, b)
    fun times(b: QuatD, res: QuatD) = times(res, this, b)
    fun times(b: Double, res: QuatD) = times(res, this, b)
    fun times(b: Vec3d, res: Vec3d) = times(res, this, b)
    fun times(b: Vec4d, res: QuatD) = times(res, this, b)
    infix fun timesAssign(b: QuatD) = times(this, this, b)
    infix fun timesAssign(b: Double) = times(this, this, b)
    infix fun timesAssign(b: Vec3d) = times(b, this, b)
    infix fun timesAssign(b: Vec4d) = times(this, this, b)


    infix operator fun div(b: Double) = div(QuatD(), this, b)
    fun div(b: Double, res: QuatD) = div(res, this, b)
    infix operator fun divAssign(b: Double) {
        div(this, this, b)
    }


    // -- Quat func --

    fun length() = GLM.length(this)

    @JvmOverloads
    fun normalize(res: QuatD = QuatD()) = GLM.normalize(this, res)

    fun normalizeAssign() = GLM.normalize(this, this)

    infix fun dot(b: QuatD) = GLM.dot(this, b)

    @JvmOverloads
    fun angleAxis(angle: Double, axis: Vec3d, res: QuatD = QuatD()) = GLM.angleAxis(angle, axis, res)

    fun angleAxisAssign(angle: Double, axis: Vec3d) = GLM.angleAxis(angle, axis, this)

    @JvmOverloads
    fun conjugate(res: QuatD = QuatD()) = GLM.conjugate(this, res)

    fun conjugateAssign() = GLM.conjugate(this, this)

    @JvmOverloads
    fun inverse(res: QuatD = QuatD()) = GLM.inverse(this, res)

    fun inverseAssign() = GLM.inverse(this, this)

    fun angle() = GLM.angle(this)

    @JvmOverloads
    fun eulerAngles(res: Vec3d = Vec3d()) = GLM.eulerAngles(this, res)

    @JvmOverloads
    fun slerp(b: QuatD, interp: Double, res: QuatD = QuatD()) = GLM.slerp(this, b, interp, res)

    fun slerpAssign(b: QuatD, interp: Double) = GLM.slerp(this, b, interp, this)


    companion object : op_QuatD, gtc_Quaternion {

        @JvmField
        val length = 4

        @JvmField
        val size = length * Double.BYTES

        @JvmStatic
        fun fromPointer(ptr: Ptr) = Quat(memGetDouble(ptr), memGetDouble(ptr + Double.BYTES), memGetDouble(ptr + Double.BYTES * 2), memGetDouble(ptr + Double.BYTES * 3))

        val identity: QuatD
            get() = QuatD(1.0, 0.0, 0.0, 0.0)
    }

    override fun equals(other: Any?) = other is QuatD && this[0] == other[0] && this[1] == other[1] && this[2] == other[2] && this[3] == other[3]
    override fun hashCode() = 31 * (31 * (31 * w.hashCode() + x.hashCode()) + y.hashCode()) + z.hashCode()

    @JvmOverloads
    fun print(name: String = "", stream: PrintStream = System.out) = stream.print("$name$this")

    @JvmOverloads
    fun println(name: String = "", stream: PrintStream = System.out) = stream.println("$name$this")

    override fun toString(): String = "($w, {$x, $y, $z})"

    @JvmOverloads
    fun vectorize(res: Vec4d = Vec4d()) = res.put(x, y, z, w)

    fun allEqual(q: QuatD, epsilon: Double = GLM.ε): Boolean =
            x - q.x < epsilon && y - q.y < epsilon && z - q.z < epsilon && w - q.w < epsilon

    fun anyNotEqual(q: QuatD, epsilon: Double = GLM.ε): Boolean =
            x - q.x >= epsilon || y - q.y >= epsilon || z - q.z >= epsilon || w - q.w >= epsilon
}
