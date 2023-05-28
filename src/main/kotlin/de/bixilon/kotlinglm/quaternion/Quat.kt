package de.bixilon.kotlinglm.quaternion

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.GLM.cos
import de.bixilon.kotlinglm.GLM.sin
import de.bixilon.kotlinglm.f
import de.bixilon.kotlinglm.float
import de.bixilon.kotlinglm.gtc.gtc_Quaternion
import de.bixilon.kotlinglm.mat3x3.Mat3
import de.bixilon.kotlinglm.mat4x4.Mat4
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec4.Vec4
import de.bixilon.kotlinglm.vec4.Vec4t
import de.bixilon.kotlinkool.BYTES
import de.bixilon.kotlinkool.FloatPtr
import de.bixilon.kotlinkool.Ptr
import org.lwjgl.system.MemoryUtil.memGetFloat
import org.lwjgl.system.MemoryUtil.memPutFloat
import java.io.InputStream
import java.io.PrintStream
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Created by GBarbieri on 15.11.2016. TODO array
 */
class Quat(w: Float, x: Float, y: Float, z: Float) : QuatT<Float>(w, x, y, z) {


    // -- Implicit basic constructors --

    constructor() : this(1f, 0f, 0f, 0f)
    constructor(f: Float) : this(f, f, f, f)
    constructor(q: Quat) : this(q.w, q.x, q.y, q.z)
    constructor(q: QuatT<out Number>) : this(q.w, q.x, q.y, q.z)
    constructor(s: Float, v: Vec3) : this(s, v.x, v.y, v.z)
    constructor(u: Vec3, v: Vec3) : this() {
        val normUnormV = sqrt((u dot u) * (v dot v))
        var realPart = normUnormV + (u dot v)
        val w = when {
            realPart < 1e-6f * normUnormV -> {
                /*  If u and v are exactly opposite, rotate 180 degrees around an arbitrary orthogonal axis.
                    Axis normalisation can happen later, when we normalise the quaternion. */
                realPart = 0f
                if (abs(u.x) > abs(u.z)) Vec3(-u.y, u.x, 0f) else Vec3(0f, -u.z, u.y)
            }
            // Otherwise, build quaternion the standard way.
            else -> u cross v
        }
        put(realPart, w.x, w.y, w.z).normalizeAssign()
    }

    constructor(block: (Int) -> Float) : this(block(0), block(1), block(2), block(3))

    constructor(eulerAngle: Vec3) : this() {
        val eX = eulerAngle.x * .5f
        val eY = eulerAngle.y * .5f
        val eZ = eulerAngle.z * .5f
        val cX = cos(eX)
        val cY = cos(eY)
        val cZ = cos(eZ)
        val sX = sin(eX)
        val sY = sin(eY)
        val sZ = sin(eZ)
        w = cX * cY * cZ + sX * sY * sZ
        x = sX * cY * cZ - cX * sY * sZ
        y = cX * sY * cZ + sX * cY * sZ
        z = cX * cY * sZ - sX * sY * cZ
    }

    constructor(vec4: Vec4) : this(vec4.w, vec4.x, vec4.y, vec4.z)

//    constructor(m: Mat3x3) : this() {
//        quat_cast(m, this)
//    }
//    constructor(m: Mat4x4) : this() {
//        quat_cast(m, this)
//    }

    constructor(inputStream: InputStream, bigEndian: Boolean = true) :
            this(inputStream.float(bigEndian), inputStream.float(bigEndian), inputStream.float(bigEndian), inputStream.float(bigEndian))

    constructor(ptr: FloatPtr) : this(block = { i -> ptr[i] })

    infix fun to(res: Mat3) = GLM.mat3_cast(this, res)
    fun toMat3() = GLM.mat3_cast(this, Mat3())

    infix fun to(res: Mat4) = GLM.mat4_cast(this, res)
    fun toMat4() = GLM.mat4_cast(this, Mat4())

    // -- Explicit basic constructors --

    constructor(q: QuatD) : this(q.w.f, q.x.f, q.y.f, q.z.f)
    constructor(w: Number, x: Number, y: Number, z: Number) : this(w.f, x.f, y.f, z.f)
    constructor(vec4: Vec4t<*>) : this(vec4._w.f, vec4._x.f, vec4._y.f, vec4._z.f)


    fun put(w: Float, x: Float, y: Float, z: Float): Quat {
        this.w = w
        this.x = x
        this.y = y
        this.z = z
        return this
    }

    infix fun put(quat: Quat) = put(quat.w, quat.x, quat.y, quat.z)

    infix fun to(ptr: Ptr) {
        memPutFloat(ptr, w)
        memPutFloat(ptr + Float.BYTES, x)
        memPutFloat(ptr + Float.BYTES * 2, y)
        memPutFloat(ptr + Float.BYTES * 3, z)
    }

    // -- Component accesses --

    override operator fun set(index: Int, value: Number) = when (index) {
        0 -> x = value.f
        1 -> y = value.f
        2 -> z = value.f
        3 -> w = value.f
        else -> throw ArrayIndexOutOfBoundsException()
    }


    // -- Unary arithmetic operators --

    operator fun unaryPlus() = this

    operator fun unaryMinus() = Quat(-w, -x, -y, -z)


    // -- Specific binary arithmetic operators --

    infix operator fun plus(b: Quat) = plus(Quat(), this, b)
    fun plus(b: Quat, res: Quat) = plus(res, this, b)
    infix operator fun plusAssign(b: Quat) {
        plus(this, this, b)
    }

    infix operator fun minus(b: Quat) = minus(Quat(), this, b)
    fun minus(b: Quat, res: Quat) = minus(res, this, b)
    infix operator fun minusAssign(b: Quat) {
        minus(this, this, b)
    }


    infix operator fun times(b: Quat) = times(Quat(), this, b)
    infix operator fun times(b: Float) = times(Quat(), this, b)
    infix operator fun times(b: Vec3) = times(Vec3(), this, b)
    infix operator fun times(b: Vec4) = times(Quat(), this, b)
    fun times(b: Quat, res: Quat) = times(res, this, b)
    fun times(b: Float, res: Quat) = times(res, this, b)
    fun times(b: Vec3, res: Vec3) = times(res, this, b)
    fun times(b: Vec4, res: Quat) = times(res, this, b)
    infix operator fun timesAssign(b: Quat) {
        times(this, this, b)
    }

    infix operator fun timesAssign(b: Float) {
        times(this, this, b)
    }

    infix operator fun timesAssign(b: Vec3) {
        times(b, this, b)
    }

    infix operator fun timesAssign(b: Vec4) {
        times(this, this, b)
    }


    infix operator fun div(b: Float) = div(Quat(), this, b)
    fun div(b: Float, res: Quat) = div(res, this, b)
    infix operator fun divAssign(b: Float) {
        div(this, this, b)
    }


    // -- Quat func --

    fun length() = GLM.length(this)

    @JvmOverloads
    fun normalize(res: Quat = Quat()) = GLM.normalize(this, res)

    fun normalizeAssign() = GLM.normalize(this, this)

    infix fun dot(b: Quat) = GLM.dot(this, b)

    @JvmOverloads
    fun angleAxis(angle: Float, axis: Vec3, res: Quat = Quat()) = GLM.angleAxis(angle, axis, res)

    fun angleAxisAssign(angle: Float, axis: Vec3) = GLM.angleAxis(angle, axis, this)

    @JvmOverloads
    fun conjugate(res: Quat = Quat()) = GLM.conjugate(this, res)

    fun conjugateAssign() = GLM.conjugate(this, this)

    @JvmOverloads
    fun inverse(res: Quat = Quat()) = GLM.inverse(this, res)

    fun inverseAssign() = GLM.inverse(this, this)

    fun angle() = GLM.angle(this)

    @JvmOverloads
    fun eulerAngles(res: Vec3 = Vec3()) = GLM.eulerAngles(this, res)

    @JvmOverloads
    fun slerp(b: Quat, interp: Float, res: Quat = Quat()) = GLM.slerp(this, b, interp, res)

    fun slerpAssign(b: Quat, interp: Float) = GLM.slerp(this, b, interp, this)


    @JvmOverloads
    fun vectorize(res: Vec4 = Vec4()) = res.put(x, y, z, w)


    companion object : op_Quat, gtc_Quaternion {
        @JvmField
        val length = 4

        @JvmField
        val size = length * Float.BYTES

        @JvmStatic
        fun fromPointer(ptr: Ptr) = Quat(memGetFloat(ptr), memGetFloat(ptr + Float.BYTES), memGetFloat(ptr + Float.BYTES * 2), memGetFloat(ptr + Float.BYTES * 3))

        val identity: Quat
            get() = Quat(1f, 0f, 0f, 0f)
    }

    override fun equals(other: Any?) = other is Quat && this[0] == other[0] && this[1] == other[1] && this[2] == other[2] && this[3] == other[3]

    override fun hashCode() = 31 * (31 * (31 * w.hashCode() + x.hashCode()) + y.hashCode()) + z.hashCode()

    @JvmOverloads
    fun print(name: String = "", stream: PrintStream = System.out) = stream.print("$name$this")

    @JvmOverloads
    fun println(name: String = "", stream: PrintStream = System.out) = stream.println("$name$this")

    override fun toString(): String = "($w, {$x, $y, $z})"

    fun allEqual(q: Quat, epsilon: Float = GLM.εf): Boolean =
            x - q.x < epsilon && y - q.y < epsilon && z - q.z < epsilon && w - q.w < epsilon

    fun anyNotEqual(q: Quat, epsilon: Float = GLM.εf): Boolean =
            x - q.x >= epsilon || y - q.y >= epsilon || z - q.z >= epsilon || w - q.w >= epsilon
}
