package de.bixilon.kotlinglm

import de.bixilon.kotlinglm.mat2x2.Mat2
import de.bixilon.kotlinglm.mat3x3.Mat3
import de.bixilon.kotlinglm.mat4x4.Mat4
import de.bixilon.kotlinglm.mat4x4.Mat4d
import de.bixilon.kotlinglm.quaternion.Quat
import de.bixilon.kotlinglm.quaternion.QuatD
import de.bixilon.kotlinglm.vec1.Vec1
import de.bixilon.kotlinglm.vec1.Vec1d
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec2.Vec2d
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec3.Vec3d
import de.bixilon.kotlinglm.vec4.Vec4
import de.bixilon.kotlinglm.vec4.Vec4d
import io.kotest.matchers.shouldBe

infix fun Float.shouldEqual(f: Float) = shouldEqual(f, GLM.εf)
fun Float.shouldEqual(f: Float, epsilon: Float) = GLM.equal(this, f, epsilon) shouldBe true

infix fun Double.shouldEqual(d: Double) = shouldEqual(d, GLM.ε)
fun Double.shouldEqual(d: Double, epsilon: Double) = GLM.equal(this, d, epsilon) shouldBe true

infix fun Quat.shouldEqual(q: Quat) = shouldEqual(q, GLM.εf)
fun Quat.shouldEqual(q: Quat, epsilon: Float) = allEqual(q, epsilon) shouldBe true

infix fun QuatD.shouldEqual(q: QuatD) = shouldEqual(q, GLM.ε)
fun QuatD.shouldEqual(q: QuatD, epsilon: Double) = allEqual(q, epsilon) shouldBe true

infix fun Vec1.shouldEqual(v: Vec1) = shouldEqual(v, GLM.εf)
fun Vec1.shouldEqual(v: Vec1, epsilon: Float) = GLM.equal(x, v.x, epsilon) shouldBe true

infix fun Vec1d.shouldEqual(v: Vec1d) = shouldEqual(v, GLM.ε)
fun Vec1d.shouldEqual(v: Vec1d, epsilon: Double) = GLM.equal(x, v.x, epsilon) shouldBe true

infix fun Vec2.shouldEqual(v: Vec2) = shouldEqual(v, GLM.εf)
fun Vec2.shouldEqual(v: Vec2, epsilon: Float) = allEqual(v, epsilon) shouldBe true

infix fun Vec2d.shouldEqual(v: Vec2d) = shouldEqual(v, GLM.ε)
fun Vec2d.shouldEqual(v: Vec2d, epsilon: Double) = allEqual(v, epsilon) shouldBe true

infix fun Vec3.shouldEqual(v: Vec3) = shouldEqual(v, GLM.εf)
fun Vec3.shouldEqual(v: Vec3, epsilon: Float) = allEqual(v, epsilon) shouldBe true

infix fun Vec3d.shouldEqual(v: Vec3d) = shouldEqual(v, GLM.ε)
fun Vec3d.shouldEqual(v: Vec3d, epsilon: Double) = allEqual(v, epsilon) shouldBe true

infix fun Vec4.shouldEqual(v: Vec4) = shouldEqual(v, GLM.εf)
fun Vec4.shouldEqual(v: Vec4, epsilon: Float) = allEqual(v, epsilon) shouldBe true

infix fun Vec4d.shouldEqual(v: Vec4d) = shouldEqual(v, GLM.ε)
fun Vec4d.shouldEqual(v: Vec4d, epsilon: Double) = allEqual(v, epsilon) shouldBe true

infix fun Mat2.shouldEqual(v: Mat2) = shouldEqual(v, GLM.εf)
fun Mat2.shouldEqual(v: Mat2, epsilon: Float) = allEqual(v, epsilon) shouldBe true

infix fun Mat3.shouldEqual(v: Mat3) = shouldEqual(v, GLM.εf)
fun Mat3.shouldEqual(v: Mat3, epsilon: Float) = allEqual(v, epsilon) shouldBe true

infix fun Mat4.shouldEqual(v: Mat4) = shouldEqual(v, GLM.εf)
fun Mat4.shouldEqual(v: Mat4, epsilon: Float) = allEqual(v, epsilon) shouldBe true

infix fun Mat4d.shouldEqual(v: Mat4d) = shouldEqual(v, GLM.ε)
fun Mat4d.shouldEqual(v: Mat4d, epsilon: Double) = allEqual(v, epsilon) shouldBe true
