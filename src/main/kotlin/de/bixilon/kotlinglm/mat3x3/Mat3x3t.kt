package  de.bixilon.kotlinglm.mat3x3

import de.bixilon.kotlinglm.ToBuffer
import de.bixilon.kotlinglm.vec3.Vec3t
import java.io.PrintStream

abstract class Mat3x3t<T : Number> : ToBuffer {

    abstract var a0: T
    abstract var a1: T
    abstract var a2: T

    abstract var b0: T
    abstract var b1: T
    abstract var b2: T

    abstract var c0: T
    abstract var c1: T
    abstract var c2: T


    operator fun component1() = a0
    operator fun component2() = a1
    operator fun component3() = a2

    operator fun component4() = b0
    operator fun component5() = b1
    operator fun component6() = b2

    operator fun component7() = c0
    operator fun component8() = c1
    operator fun component9() = c2


    // -- Accesses --

    abstract operator fun get(index: Int): Vec3t<out Number>
    abstract operator fun get(column: Int, row: Int): T

    abstract operator fun set(index: Int, value: Vec3t<out Number>)
    abstract operator fun set(column: Int, row: Int, value: T)


    // component alias

    var v00
        @JvmName("v00") get() = a0
        @JvmName("v00") set(value) {
            a0 = value
        }
    var v01
        @JvmName("v01") get() = a1
        @JvmName("v01") set(value) {
            a1 = value
        }
    var v02
        @JvmName("v02") get() = a2
        @JvmName("v02") set(value) {
            a2 = value
        }

    var v10
        @JvmName("v10") get() = b0
        @JvmName("v10") set(value) {
            b0 = value
        }
    var v11
        @JvmName("v11") get() = b1
        @JvmName("v11") set(value) {
            b1 = value
        }
    var v12
        @JvmName("v12") get() = b2
        @JvmName("v12") set(value) {
            b2 = value
        }

    var v20
        @JvmName("v20") get() = c0
        @JvmName("v20") set(value) {
            c0 = value
        }
    var v21
        @JvmName("v21") get() = c1
        @JvmName("v21") set(value) {
            c1 = value
        }
    var v22
        @JvmName("v22") get() = c2
        @JvmName("v22") set(value) {
            c2 = value
        }

    abstract val isIdentity: Boolean


    companion object {
        const val length = 3 * 3
    }

    @JvmOverloads
    fun print(name: String = "", stream: PrintStream = System.out) = stream.print("""$name:
        $this""")

    @JvmOverloads
    fun println(name: String = "", stream: PrintStream = System.out) = stream.println("""$name:
        $this""")

    override fun toString() = """
        $v00 $v10 $v20
        $v01 $v11 $v21
        $v02 $v12 $v22"""

//    override fun toString() =
//            "| ${this[0][ 0]}][ ${this[1][ 0]}][ ${this[2][ 0]} |\n" +
//                    "| ${this[0][ 1]}][ ${this[1][ 1]}][ ${this[2][ 1]} |\n" +
//                    "| ${this[0][ 2]}][ ${this[1][ 2]}][ ${this[2][ 2]} |"
}
