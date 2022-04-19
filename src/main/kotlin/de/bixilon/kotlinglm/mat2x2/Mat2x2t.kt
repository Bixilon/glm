package de.bixilon.kotlinglm.mat2x2

import de.bixilon.kotlinglm.ToBuffer
import de.bixilon.kotlinglm.vec2.Vec2t
import java.io.PrintStream


abstract class Mat2x2t<T : Number> : ToBuffer {

    abstract var a0: T
    abstract var a1: T

    abstract var b0: T
    abstract var b1: T


    operator fun component1(): T = a0
    operator fun component2(): T = a1

    operator fun component3(): T = b0
    operator fun component4(): T = b1


    // -- Accesses --

    abstract operator fun get(index: Int): Vec2t<out Number>
    abstract operator fun get(column: Int, row: Int): T

    abstract operator fun set(index: Int, value: Vec2t<out Number>)
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

    var v10
        @JvmName("v10") get() = b0
        @JvmName("v02") set(value) {
            b0 = value
        }
    var v11
        @JvmName("v11") get() = b1
        @JvmName("v11") set(value) {
            b1 = value
        }


    abstract val isIdentity: Boolean

    companion object {
        const val length = 2 * 2 // TODO parametrize using Vec2t.length
    }

    @JvmOverloads
    fun print(name: String = "", stream: PrintStream = System.out) = stream.print("""$name:
        $this""")

    @JvmOverloads
    fun println(name: String = "", stream: PrintStream = System.out) = stream.println("""$name:
        $this""")

    override fun toString() = """
        |$v00 $v10
        |$v01 $v11""".trimMargin()


//    override fun toString() =
//            "| ${this[0][ 0]}][ ${this[1][ 0]}][ ${this[2][ 0]} |\n" +
//                    "| ${this[0][ 1]}][ ${this[1][ 1]}][ ${this[2][ 1]} |\n" +
//                    "| ${this[0][ 2]}][ ${this[1][ 2]}][ ${this[2][ 2]} |"
}
