package de.bixilon.kotlinglm

import de.bixilon.kotlinglm.vec1.Vec1i
import de.bixilon.kotlinglm.vec2.Vec2i
import de.bixilon.kotlinglm.vec3.Vec3i
import de.bixilon.kotlinglm.vec4.Vec4i

/**
 * Created by GBarbieri on 03.04.2017.
 */

interface ComponentWise {

    // TODO
    fun compAdd(a: Vec1i) = a.x

    fun compMul(a: Vec1i) = a.x
    fun compMin(a: Vec1i) = a.x
    fun compMax(a: Vec1i) = a.x

    fun compAdd(a: Vec2i) = a.x + a.y
    fun compMul(a: Vec2i) = a.x * a.y
    fun compMin(a: Vec2i) = GLM.min(a.x, a.y)
    fun compMax(a: Vec2i) = GLM.max(a.x, a.y)

    fun compAdd(a: Vec3i) = a.x + a.y + a.z
    fun compMul(a: Vec3i) = a.x * a.y * a.z
    fun compMin(a: Vec3i) = GLM.min(GLM.min(a.x, a.y), a.z)
    fun compMax(a: Vec3i) = GLM.max(GLM.max(a.x, a.y), a.z)

    fun compAdd(a: Vec4i) = a.x + a.y + a.z + a.w
    fun compMul(a: Vec4i) = a.x * a.y * a.z + a.w
    fun compMin(a: Vec4i) = GLM.min(GLM.min(a.x, a.y), GLM.min(a.z, a.w))
    fun compMax(a: Vec4i) = GLM.max(GLM.max(a.x, a.y), GLM.min(a.z, a.w))

}
