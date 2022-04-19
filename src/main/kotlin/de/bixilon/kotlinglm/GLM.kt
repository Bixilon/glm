/**
 * Created bY GBarbieri on 06.10.2016.
 */

package de.bixilon.kotlinglm

import de.bixilon.kotlinglm.ext.*
import de.bixilon.kotlinglm.ext.ext_QuaternionCommon
import de.bixilon.kotlinglm.ext.ext_QuaternionTransform
import de.bixilon.kotlinglm.ext.ext_QuaternionTrigonometric
import de.bixilon.kotlinglm.func.*
import de.bixilon.kotlinglm.func.common.*
import de.bixilon.kotlinglm.gtc.gtc_MatrixInverse
import de.bixilon.kotlinglm.gtx.*
import de.bixilon.kotlinglm.gtc.gtc_Quaternion
import de.bixilon.kotlinglm.quaternion.gtx_quat

object GLM :

        func_common,
        func_Vec1Common,
        func_Vec2Common,
        func_Vec3Common,
        func_Vec4Common,

        func_Vec1Relational,
        func_Vec2Relational,
        func_Vec3Relational,
        func_Vec4Relational,
        func_VecBoolRelational,

        func_Mat,

        func_Exponential,
        func_Geometric,
        func_Trigonometric,

        // matrix transform
        gtx_MatrixOperation,

        ext_MatrixClipSpace,
        ext_MatrixProjection,
        ext_MatrixTransform,

        gtc_Quaternion,
        ext_QuaternionCommon,
        ext_QuaternionTransform,
        ext_QuaternionTrigonometric,
        ext_QuaternionExponential,

        ext_ScalarCommon,

        MatrixInterpolation,

        Epsilon,

        ComponentWise,

        Round,

        func_Integer,

    de.bixilon.kotlinglm.Bitfield,

        ColorSpace,

        Gauss,

        Noise,

        Packing,
        func_Packing,

        Random,

        GradientPaint,

        gtc_MatrixInverse,

        gtx_ClosestPoint,
        gtx_Easing,
        gtx_EulerAngles,
        gtx_FastTrigonometry,
        gtx_Integer,
        gtx_Intersect,
        gtx_MatrixCrossProduct,
        gtx_MatrixDecompose,
        gtx_MatrixFactorization,
        gtx_Normal,
        gtx_OptimumPow,
        gtx_quat,
        gtx_RotateVector,
        gxt_Spline,
        gtx_Texture,
        gtx_VectorAngle,

        // ext
        ext_QuaternionGeometric,
//        extQuaternionRelational,
        ext_ScalarRelational,
        MatrixRelational,

        ClosestPointToLines {

    @JvmField
    val detail = DetailGtx

    @JvmField
    val HPI = kotlin.math.PI / 2
    @JvmField
    val HPIf = kotlin.math.PI.f / 2

    @JvmField
    val PI = kotlin.math.PI
    @JvmField
    val PIf = kotlin.math.PI.f
    val π = Math.PI
    val πf = PIf

    @JvmField
    val PI2 = kotlin.math.PI * 2
    @JvmField
    val PI2f = kotlin.math.PI.f * 2

    @JvmField
    val PI3over2f = kotlin.math.PI.f * 1.5f

    @JvmField
    val epsilonF = Float.MIN_VALUE
    val _epsilonF = 1.19209290e-07f // FIXME, quat slerp fails with Float.MIN_VALUE
    @JvmField
    val epsilon = 2.2204460492503131e-16 // TODO check me
    val ε = epsilon
    val εf = epsilonF

    @JvmField
    val Ef = Math.E.f
    @JvmField
    val E = Math.E
}

object DetailGtx :
        Noise,
        TypeHalf,
        detail_Packing,
        detail_gtx_matrixDecompose,
        detail_fastTrigonometry,
        detail_gtx_Integer,
        detail_Integer


typealias uint = Int
typealias ulong = Long

val GLM_VERSION_MAJOR = 0
val GLM_VERSION_MINOR = 9
val GLM_VERSION_PATCH = 9
val GLM_VERSION_REVISION = 1
val GLM_VERSION_BUILD = 3
val GLM_VERSION = GLM_VERSION_MAJOR * 1_000 + GLM_VERSION_MINOR * 100 + GLM_VERSION_PATCH * 10 + GLM_VERSION_REVISION

/*
    TODO:
      Added missing equal and notEqual with epsilon for quaternion types (https://github.com/g-truc/glm/commit/8f0b7c13732b018339697d182ea3a9f437ccaa71)
       Fixed relational code, reduced header dependencies  (https://github.com/g-truc/glm/commit/7086d902e2780e0774830573da7473938290ea73)
 */
