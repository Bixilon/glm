package de.bixilon.kotlinglm.vec3

import de.bixilon.kotlinglm.GLM
import java.io.PrintStream

/**
 * Created by elect on 08/10/16.
 */

data class Vec3bool(var x: Boolean = false, var y: Boolean = false, var z: Boolean = false) {

    // -- Explicit basic, conversion other main.and conversion vector constructors --

    constructor(b: Boolean) : this(b, b, b)

    constructor(ba: BooleanArray) : this(ba[0], ba[1], ba[2])

    constructor(ba: Array<Boolean>) : this(ba[0], ba[1], ba[2])

    constructor(init: (Int) -> Boolean): this(init(0), init(1), init(2))

    // -- Component accesses --

    operator fun get(i: Int): Boolean = when (i) {
        0 -> x
        1 -> y
        2 -> z
        else -> throw IndexOutOfBoundsException()
    }

    operator fun set(i: Int, b: Boolean) = when (i) {
        0 -> x = b
        1 -> y = b
        2 -> z = b
        else -> throw IndexOutOfBoundsException()
    }


    fun put(b: Boolean): Vec3bool {
        x = b
        y = b
        z = b
        return this
    }

    fun put(x: Boolean, y: Boolean, z: Boolean): Vec3bool {
        this.x = x
        this.y = y
        this.z = z
        return this
    }

    fun put(ba: BooleanArray): Vec3bool {
        x = ba[0]
        y = ba[1]
        z = ba[2]
        return this
    }

    fun put(ba: Array<Boolean>): Vec3bool {
        x = ba[0]
        y = ba[1]
        z = ba[2]
        return this
    }

    operator fun invoke(init: (Int) -> Boolean): Vec3bool {
        x = init(0)
        y = init(1)
        z = init(2)
        return this
    }

    // -- Unary arithmetic vecOperators --

    operator fun not(): Vec3bool = Vec3bool(!x, !y, !z)

    fun notAssign(): Vec3bool {
        x = !x
        y = !y
        z = !z
        return this
    }

    infix fun not(res: Vec3bool): Vec3bool {
        res.x = !x
        res.y = !y
        res.z = !z
        return this
    }

    val all: Boolean
        get() = GLM.all(this)

    infix fun and(b: Vec3bool): Vec3bool = and(b, Vec3bool())
    fun and(b: Vec3bool, res: Vec3bool): Vec3bool {
        return res.put(x && b.x, y && b.y, z && b.z)
    }

    infix fun or(b: Vec3bool): Vec3bool = or(b, Vec3bool())
    fun or(b: Vec3bool, res: Vec3bool): Vec3bool {
        return res.put(x || b.x, y || b.y, z || b.z)
    }

    fun all() = x && y && z


    fun print(name: String = "", stream: PrintStream = System.out) = stream.println("$name [$x, $y, $z]")
    override fun toString(): String = "($x, $y, $z)"
}
