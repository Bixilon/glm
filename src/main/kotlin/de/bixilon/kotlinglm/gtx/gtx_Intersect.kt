package de.bixilon.kotlinglm.gtx

import de.bixilon.kotlinglm.GLM
import de.bixilon.kotlinglm.GLM.epsilonF
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec3.Vec3

/// @ref gtx_intersect
/// @file glm/gtx/intersect.hpp
///
/// @see core (dependence)
/// @see gtx_closest_point (dependence)
///
/// @defgroup gtx_intersect GLM_GTX_intersect
/// @ingroup gtx
///
/// Include <glm/gtx/intersect.hpp> to use the features of this extension.
///
/// Add intersection functions

interface gtx_Intersect {

    /** Compute the intersection of a ray and a plane.
     *  Ray direction and plane normal must be unit length.
     *  From GLM_GTX_intersect extension.
     *  @return intersection distance if intersected, null otherwise   */
    fun intersectRayPlane(orig: Vec3, dir: Vec3, planeOrig: Vec3, planeNormal: Vec3): Float? {
        val d = dir dot planeNormal
        return if (d < -epsilonF) GLM.dot(planeOrig - orig, planeNormal) / d else null
    }

    /** Compute the intersection of a ray and a triangle.
     *  Based om Tomas Möller implementation http://fileadmin.cs.lth.se/cs/Personal/Tomas_Akenine-Moller/raytri/
     *  @return distance if intersected, null otherwise  */
    fun intersectRayTriangle(orig: Vec3, dir: Vec3, vert0: Vec3, vert1: Vec3, vert2: Vec3, baryPosition: Vec2): Float? {
        // find vectors for two edges sharing vert0
        val edge1 = vert1 - vert0
        val edge2 = vert2 - vert0

        // begin calculating determinant - also used to calculate U parameter
        val p = dir cross edge2

        // if determinant is near zero, ray lies in plane of triangle
        val det = edge1 dot p

        val perpendicular: Vec3

        when {
            det > epsilonF -> {
                // calculate distance from vert0 to ray origin
                val dist = orig - vert0

                // calculate U parameter and test bounds
                baryPosition.x = dist dot p
                if (baryPosition.x < 0 || baryPosition.x > det)
                    return null

                // prepare to test V parameter
                perpendicular = dist cross edge1

                // calculate V parameter and test bounds
                baryPosition.y = dir dot perpendicular
                if (baryPosition.y < 0 || (baryPosition.x + baryPosition.y) > det)
                    return null
            }
            det < -epsilonF -> {
                // calculate distance from vert0 to ray origin
                val dist = orig - vert0

                // calculate U parameter and test bounds
                baryPosition.x = dist dot p
                if (baryPosition.x > 0 || baryPosition.x < det)
                    return null

                // prepare to test V parameter
                perpendicular = dist cross edge1

                // calculate V parameter and test bounds
                baryPosition.y = dir dot perpendicular
                if (baryPosition.y > 0 || baryPosition.x + baryPosition.y < det)
                    return null
            }
            else -> return null // ray is parallel to the plane of the triangle
        }

        val invDet = 1 / det

        baryPosition *= invDet

        // return distance, ray intersects triangle
        return (edge2 dot perpendicular) * invDet
    }

    /** Compute the intersection of a line and a triangle.
     *  From GLM_GTX_intersect extension.   */
    fun intersectLineTriangle(orig: Vec3, dir: Vec3,
                              vert0: Vec3, vert1: Vec3, vert2: Vec3,
                              position: Vec3): Boolean {

        val edge1 = vert1 - vert0
        val edge2 = vert2 - vert0

        val perpendicular = dir cross edge2

        val det = edge1 dot perpendicular

        if (det > -Float.MIN_VALUE && det < Float.MIN_VALUE)
            return false
        val invDet = 1f / det

        val tengant = orig - vert0

        position.y = (tengant dot perpendicular) * invDet
        if (position.y < 0f || position.y > 1f)
            return false

        val cotengant = tengant cross edge1

        position.z = (dir dot cotengant) * invDet
        if (position.z < 0f || position.y + position.z > 1f)
            return false

        position.x = (edge2 dot cotengant) * invDet

        return true
    }

    /** Compute the intersection distance of a ray and a sphere.
     *  The ray direction vector is unit length.
     *  From GLM_GTX_intersect extension.
     *  @return intersection distance if intersected, null otherwise   */
    fun intersectRaySphere(rayStarting: Vec3, rayNormalizedDirection: Vec3, sphereCenter: Vec3, sphereRadiusSquered: Float): Float? {
        val diff = sphereCenter - rayStarting
        val t0 = diff dot rayNormalizedDirection
        val dSquared = (diff dot diff) - t0 * t0
        if (dSquared > sphereRadiusSquered) return null
        val t1 = GLM.sqrt(sphereRadiusSquered - dSquared)
        val intersectionDistance = if (t0 > t1 + epsilonF) t0 - t1 else t0 + t1
        return if (intersectionDistance > epsilonF) intersectionDistance else null
    }

    /** Compute the intersection of a ray and a sphere.
     *  From GLM_GTX_intersect extension.
     *  @return intersection   */
    fun intersectRaySphere(rayStarting: Vec3, rayNormalizedDirection: Vec3, sphereCenter: Vec3, sphereRadius: Float,
                           intersectionPosition: Vec3, intersectionNormal: Vec3): Boolean {

        intersectRaySphere(rayStarting, rayNormalizedDirection, sphereCenter, sphereRadius * sphereRadius)?.let { distance ->
            intersectionPosition put rayStarting + rayNormalizedDirection * distance
            intersectionNormal put (intersectionPosition - sphereCenter) / sphereRadius
            return true
        }
        return false
    }

    /** Compute the intersection of a line and a sphere.
     *  From GLM_GTX_intersect extension    */
    fun intersectLineSphere(point0: Vec3, point1: Vec3, sphereCenter: Vec3, sphereRadius: Float,
                            intersectionPoint1: Vec3, intersectionNormal1: Vec3,
                            intersectionPoint2: Vec3? = null, intersectionNormal2: Vec3? = null): Boolean {

        val dir = point1 - point0
        dir.normalizeAssign()
        val diff = sphereCenter - point0
        val t0 = diff dot dir
        val dSquared = (diff dot diff) - t0 * t0
        if (dSquared > sphereRadius * sphereRadius) return false
        var t1 = GLM.sqrt(sphereRadius * sphereRadius - dSquared)
        if (t0 < t1 + epsilonF)
            t1 = -t1
        intersectionPoint1 put point0 + dir * (t0 - t1)
        intersectionNormal1 put (intersectionPoint1 - sphereCenter) / sphereRadius
        intersectionPoint2?.let {
            it.put(point0 + dir * (t0 + t1))
            intersectionNormal2?.put((it - sphereCenter) / sphereRadius)
        }
        return true
    }
}
