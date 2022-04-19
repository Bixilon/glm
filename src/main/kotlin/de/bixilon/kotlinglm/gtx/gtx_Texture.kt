package de.bixilon.kotlinglm.gtx

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.GLM.compMax
import de.bixilon.kotlinglm.vec1.Vec1i
import de.bixilon.kotlinglm.vec2.Vec2i
import de.bixilon.kotlinglm.vec3.Vec3i

interface gtx_Texture {

    fun levels(extent: Vec1i) = GLM.log2(compMax(extent)) + 1
    fun levels(extent: Vec2i) = GLM.log2(compMax(extent)) + 1
    fun levels(extent: Vec3i) = GLM.log2(compMax(extent)) + 1

    fun levels(extent: Int) = GLM.log2(extent) + 1
}
