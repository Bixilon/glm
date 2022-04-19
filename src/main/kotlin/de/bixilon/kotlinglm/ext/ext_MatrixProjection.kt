package de.bixilon.kotlinglm.ext

import de.bixilon.kotlinglm.detail.GLM_DEPTH_CLIP_SPACE
import de.bixilon.kotlinglm.detail.GlmDepthClipSpace
import de.bixilon.kotlinglm.mat4x4.Mat4
import de.bixilon.kotlinglm.mat4x4.Mat4d
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec2.Vec2d
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.vec3.Vec3d
import de.bixilon.kotlinglm.vec4.Vec4i

interface ext_MatrixProjection {

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of 0 and +1 respectively. (Direct3D clip volume definition)
     *
     *  @param res the resulting window coordinates
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun projectZo(obj: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i, res: Vec3): Vec3 {
        // tmp = model * Vec4(obj, 1)
        val tmpX = model[0, 0] * obj.x + model[1, 0] * obj.y + model[2, 0] * obj.z + model[3, 0]
        val tmpY = model[0, 1] * obj.x + model[1, 1] * obj.y + model[2, 1] * obj.z + model[3, 1]
        val tmpZ = model[0, 2] * obj.x + model[1, 2] * obj.y + model[2, 2] * obj.z + model[3, 2]
        val tmpW = model[0, 3] * obj.x + model[1, 3] * obj.y + model[2, 3] * obj.z + model[3, 3]
        // tmp = proj * tmp;
        res.put(proj[0, 0] * tmpX + proj[1, 0] * tmpY + proj[2, 0] * tmpZ + proj[3, 0] * tmpW,
                proj[0, 1] * tmpX + proj[1, 1] * tmpY + proj[2, 1] * tmpZ + proj[3, 1] * tmpW,
                proj[0, 2] * tmpX + proj[1, 2] * tmpY + proj[2, 2] * tmpZ + proj[3, 2] * tmpW)
        // tmp /= tmp.w
        res.divAssign(proj[0, 3] * tmpX + proj[1, 3] * tmpY + proj[2, 3] * tmpZ + proj[3, 3] * tmpW)

        res.x = res.x * 0.5f + 0.5f
        res.y = res.y * 0.5f + 0.5f

        res[0] = res[0] * viewport[2] + viewport[0]
        res[1] = res[1] * viewport[3] + viewport[1]

        return res
    }

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of 0 and +1 respectively. (Direct3D clip volume definition)
     *
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun projectZo(obj: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i): Vec3 =
            projectZo(obj, model, proj, viewport, Vec3())

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of -1 and +1 respectively. (OpenGL clip volume definition)
     *
     *  @param res the window coordinates
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun projectNo(obj: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i, res: Vec3): Vec3 {
        // tmp = model * Vec4(obj, 1)
        val tmpX = model[0, 0] * obj.x + model[1, 0] * obj.y + model[2, 0] * obj.z + model[3, 0]
        val tmpY = model[0, 1] * obj.x + model[1, 1] * obj.y + model[2, 1] * obj.z + model[3, 1]
        val tmpZ = model[0, 2] * obj.x + model[1, 2] * obj.y + model[2, 2] * obj.z + model[3, 2]
        val tmpW = model[0, 3] * obj.x + model[1, 3] * obj.y + model[2, 3] * obj.z + model[3, 3]
        // tmp = proj * tmp;
        res.put(proj[0, 0] * tmpX + proj[1, 0] * tmpY + proj[2, 0] * tmpZ + proj[3, 0] * tmpW,
                proj[0, 1] * tmpX + proj[1, 1] * tmpY + proj[2, 1] * tmpZ + proj[3, 1] * tmpW,
                proj[0, 2] * tmpX + proj[1, 2] * tmpY + proj[2, 2] * tmpZ + proj[3, 2] * tmpW)
        // tmp /= tmp.w
        res.divAssign(proj[0, 3] * tmpX + proj[1, 3] * tmpY + proj[2, 3] * tmpZ + proj[3, 3] * tmpW)

        res.x = res.x * 0.5f + 0.5f
        res.y = res.y * 0.5f + 0.5f
        res.z = res.z * 0.5f + 0.5f

        res[0] = res[0] * viewport[2] + viewport[0]
        res[1] = res[1] * viewport[3] + viewport[1]

        return res
    }

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of -1 and +1 respectively. (OpenGL clip volume definition)
     *
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun projectNo(obj: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i): Vec3 =
            projectNo(obj, model, proj, viewport, Vec3())

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates using default near and far clip planes definition.
     *  To change default near and far clip planes definition use ZERO_TO_ONE.
     *
     *  @param res the window coordinates
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun project(obj: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i, res: Vec3): Vec3 =
            when (GLM_DEPTH_CLIP_SPACE) {
                GlmDepthClipSpace.ZERO_TO_ONE -> projectZo(obj, model, proj, viewport, res)
                else -> projectNo(obj, model, proj, viewport, res)
            }

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates using default near and far clip planes definition.
     *  To change default near and far clip planes definition use ZERO_TO_ONE.
     *
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun project(obj: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i): Vec3 =
            when (GLM_DEPTH_CLIP_SPACE) {
                GlmDepthClipSpace.ZERO_TO_ONE -> projectZo(obj, model, proj, viewport, Vec3())
                else -> projectNo(obj, model, proj, viewport, Vec3())
            }

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of 0 and +1 respectively. (Direct3D clip volume definition)
     *
     *  @param res the object coordinates
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProjectZo(win: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i, res: Vec3): Vec3 {

        // proj * model
        val a0 = proj.array[0] * model.array[0] + proj.array[4] * model.array[1] + proj.array[8] * model.array[2] + proj.array[12] * model.array[3]
        val b0 = proj.array[0] * model.array[4] + proj.array[4] * model.array[5] + proj.array[8] * model.array[6] + proj.array[12] * model.array[7]
        val c0 = proj.array[0] * model.array[8] + proj.array[4] * model.array[9] + proj.array[8] * model.array[10] + proj.array[12] * model.array[11]
        val d0 = proj.array[0] * model.array[12] + proj.array[4] * model.array[13] + proj.array[8] * model.array[14] + proj.array[12] * model.array[15]
        val a1 = proj.array[1] * model.array[0] + proj.array[5] * model.array[1] + proj.array[9] * model.array[2] + proj.array[13] * model.array[3]
        val b1 = proj.array[1] * model.array[4] + proj.array[5] * model.array[5] + proj.array[9] * model.array[6] + proj.array[13] * model.array[7]
        val c1 = proj.array[1] * model.array[8] + proj.array[5] * model.array[9] + proj.array[9] * model.array[10] + proj.array[13] * model.array[11]
        val d1 = proj.array[1] * model.array[12] + proj.array[5] * model.array[13] + proj.array[9] * model.array[14] + proj.array[13] * model.array[15]
        val a2 = proj.array[2] * model.array[0] + proj.array[6] * model.array[1] + proj.array[10] * model.array[2] + proj.array[14] * model.array[3]
        val b2 = proj.array[2] * model.array[4] + proj.array[6] * model.array[5] + proj.array[10] * model.array[6] + proj.array[14] * model.array[7]
        val c2 = proj.array[2] * model.array[8] + proj.array[6] * model.array[9] + proj.array[10] * model.array[10] + proj.array[14] * model.array[11]
        val d2 = proj.array[2] * model.array[12] + proj.array[6] * model.array[13] + proj.array[10] * model.array[14] + proj.array[14] * model.array[15]
        val a3 = proj.array[3] * model.array[0] + proj.array[7] * model.array[1] + proj.array[11] * model.array[2] + proj.array[15] * model.array[3]
        val b3 = proj.array[3] * model.array[4] + proj.array[7] * model.array[5] + proj.array[11] * model.array[6] + proj.array[15] * model.array[7]
        val c3 = proj.array[3] * model.array[8] + proj.array[7] * model.array[9] + proj.array[11] * model.array[10] + proj.array[15] * model.array[11]
        val d3 = proj.array[3] * model.array[12] + proj.array[7] * model.array[13] + proj.array[11] * model.array[14] + proj.array[15] * model.array[15]

        // inverse(proj * model)
        val c00 = c2 * d3 - d2 * c3
        val c02 = b2 * d3 - d2 * b3
        val c03 = b2 * c3 - c2 * b3

        val c04 = c1 * d3 - d1 * c3
        val c06 = b1 * d3 - d1 * b3
        val c07 = b1 * c3 - c1 * b3

        val c08 = c1 * d2 - d1 * c2
        val c10 = b1 * d2 - d1 * b2
        val c11 = b1 * c2 - c1 * b2

        val c12 = c0 * d3 - d0 * c3
        val c14 = b0 * d3 - d0 * b3
        val c15 = b0 * c3 - c0 * b3

        val c16 = c0 * d2 - d0 * c2
        val c18 = b0 * d2 - d0 * b2
        val c19 = b0 * c2 - c0 * b2

        val c20 = c0 * d1 - d0 * c1
        val c22 = b0 * d1 - d0 * b1
        val c23 = b0 * c1 - c0 * b1

        val i00 = +(b1 * c00 - b2 * c04 + b3 * c08)
        val i01 = -(a1 * c00 - a2 * c04 + a3 * c08)
        val i02 = +(a1 * c02 - a2 * c06 + a3 * c10)
        val i03 = -(a1 * c03 - a2 * c07 + a3 * c11)

        val i10 = -(b0 * c00 - b2 * c12 + b3 * c16)
        val i11 = +(a0 * c00 - a2 * c12 + a3 * c16)
        val i12 = -(a0 * c02 - a2 * c14 + a3 * c18)
        val i13 = +(a0 * c03 - a2 * c15 + a3 * c19)

        val i20 = +(b0 * c04 - b1 * c12 + b3 * c20)
        val i21 = -(a0 * c04 - a1 * c12 + a3 * c20)
        val i22 = +(a0 * c06 - a1 * c14 + a3 * c22)
        val i23 = -(a0 * c07 - a1 * c15 + a3 * c23)

        val i30 = -(b0 * c08 - b1 * c16 + b2 * c20)
        val i31 = +(a0 * c08 - a1 * c16 + a2 * c20)
        val i32 = -(a0 * c10 - a1 * c18 + a2 * c22)
        val i33 = +(a0 * c11 - a1 * c19 + a2 * c23)

        val oneOverDet = 1 / (a0 * i00 + a1 * i10 + a2 * i20 + a3 * i30)

        val inv00 = i00 * oneOverDet
        val inv01 = i01 * oneOverDet
        val inv02 = i02 * oneOverDet
        val inv03 = i03 * oneOverDet

        val inv10 = i10 * oneOverDet
        val inv11 = i11 * oneOverDet
        val inv12 = i12 * oneOverDet
        val inv13 = i13 * oneOverDet

        val inv20 = i20 * oneOverDet
        val inv21 = i21 * oneOverDet
        val inv22 = i22 * oneOverDet
        val inv23 = i23 * oneOverDet

        val inv30 = i30 * oneOverDet
        val inv31 = i31 * oneOverDet
        val inv32 = i32 * oneOverDet
        val inv33 = i33 * oneOverDet

        var tmpX = win.x
        var tmpY = win.y
        val tmpZ = win.z
        tmpX = (tmpX - viewport[0]) / viewport[2]
        tmpY = (tmpY - viewport[1]) / viewport[3]
        tmpX = tmpX * 2f - 1f
        tmpY = tmpY * 2f - 1f

        // obj = Inverse * tmp;
        // obj /= obj.w
        res(
                inv00 * tmpX + inv10 * tmpY + inv20 * tmpZ + inv30,
                inv01 * tmpX + inv11 * tmpY + inv21 * tmpZ + inv31,
                inv02 * tmpX + inv12 * tmpY + inv22 * tmpZ + inv32) /= inv03 * tmpX + inv13 * tmpY + inv23 * tmpZ + inv33

        return res
    }

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of 0 and +1 respectively. (Direct3D clip volume definition)
     *
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProjectZo(win: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i): Vec3 =
            unProjectZo(win, model, proj, viewport, Vec3())

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of -1 and +1 respectively. (OpenGL clip volume definition)
     *
     *  @param res the object coordinates
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProjectNo(win: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i, res: Vec3): Vec3 {

        // proj * model
        val a0 = proj.array[0] * model.array[0] + proj.array[4] * model.array[1] + proj.array[8] * model.array[2] + proj.array[12] * model.array[3]
        val b0 = proj.array[0] * model.array[4] + proj.array[4] * model.array[5] + proj.array[8] * model.array[6] + proj.array[12] * model.array[7]
        val c0 = proj.array[0] * model.array[8] + proj.array[4] * model.array[9] + proj.array[8] * model.array[10] + proj.array[12] * model.array[11]
        val d0 = proj.array[0] * model.array[12] + proj.array[4] * model.array[13] + proj.array[8] * model.array[14] + proj.array[12] * model.array[15]
        val a1 = proj.array[1] * model.array[0] + proj.array[5] * model.array[1] + proj.array[9] * model.array[2] + proj.array[13] * model.array[3]
        val b1 = proj.array[1] * model.array[4] + proj.array[5] * model.array[5] + proj.array[9] * model.array[6] + proj.array[13] * model.array[7]
        val c1 = proj.array[1] * model.array[8] + proj.array[5] * model.array[9] + proj.array[9] * model.array[10] + proj.array[13] * model.array[11]
        val d1 = proj.array[1] * model.array[12] + proj.array[5] * model.array[13] + proj.array[9] * model.array[14] + proj.array[13] * model.array[15]
        val a2 = proj.array[2] * model.array[0] + proj.array[6] * model.array[1] + proj.array[10] * model.array[2] + proj.array[14] * model.array[3]
        val b2 = proj.array[2] * model.array[4] + proj.array[6] * model.array[5] + proj.array[10] * model.array[6] + proj.array[14] * model.array[7]
        val c2 = proj.array[2] * model.array[8] + proj.array[6] * model.array[9] + proj.array[10] * model.array[10] + proj.array[14] * model.array[11]
        val d2 = proj.array[2] * model.array[12] + proj.array[6] * model.array[13] + proj.array[10] * model.array[14] + proj.array[14] * model.array[15]
        val a3 = proj.array[3] * model.array[0] + proj.array[7] * model.array[1] + proj.array[11] * model.array[2] + proj.array[15] * model.array[3]
        val b3 = proj.array[3] * model.array[4] + proj.array[7] * model.array[5] + proj.array[11] * model.array[6] + proj.array[15] * model.array[7]
        val c3 = proj.array[3] * model.array[8] + proj.array[7] * model.array[9] + proj.array[11] * model.array[10] + proj.array[15] * model.array[11]
        val d3 = proj.array[3] * model.array[12] + proj.array[7] * model.array[13] + proj.array[11] * model.array[14] + proj.array[15] * model.array[15]

        // inverse(proj * model)
        val c00 = c2 * d3 - d2 * c3
        val c02 = b2 * d3 - d2 * b3
        val c03 = b2 * c3 - c2 * b3

        val c04 = c1 * d3 - d1 * c3
        val c06 = b1 * d3 - d1 * b3
        val c07 = b1 * c3 - c1 * b3

        val c08 = c1 * d2 - d1 * c2
        val c10 = b1 * d2 - d1 * b2
        val c11 = b1 * c2 - c1 * b2

        val c12 = c0 * d3 - d0 * c3
        val c14 = b0 * d3 - d0 * b3
        val c15 = b0 * c3 - c0 * b3

        val c16 = c0 * d2 - d0 * c2
        val c18 = b0 * d2 - d0 * b2
        val c19 = b0 * c2 - c0 * b2

        val c20 = c0 * d1 - d0 * c1
        val c22 = b0 * d1 - d0 * b1
        val c23 = b0 * c1 - c0 * b1

        val i00 = +(b1 * c00 - b2 * c04 + b3 * c08)
        val i01 = -(a1 * c00 - a2 * c04 + a3 * c08)
        val i02 = +(a1 * c02 - a2 * c06 + a3 * c10)
        val i03 = -(a1 * c03 - a2 * c07 + a3 * c11)

        val i10 = -(b0 * c00 - b2 * c12 + b3 * c16)
        val i11 = +(a0 * c00 - a2 * c12 + a3 * c16)
        val i12 = -(a0 * c02 - a2 * c14 + a3 * c18)
        val i13 = +(a0 * c03 - a2 * c15 + a3 * c19)

        val i20 = +(b0 * c04 - b1 * c12 + b3 * c20)
        val i21 = -(a0 * c04 - a1 * c12 + a3 * c20)
        val i22 = +(a0 * c06 - a1 * c14 + a3 * c22)
        val i23 = -(a0 * c07 - a1 * c15 + a3 * c23)

        val i30 = -(b0 * c08 - b1 * c16 + b2 * c20)
        val i31 = +(a0 * c08 - a1 * c16 + a2 * c20)
        val i32 = -(a0 * c10 - a1 * c18 + a2 * c22)
        val i33 = +(a0 * c11 - a1 * c19 + a2 * c23)

        val oneOverDet = 1 / (a0 * i00 + a1 * i10 + a2 * i20 + a3 * i30)

        val inv00 = i00 * oneOverDet
        val inv01 = i01 * oneOverDet
        val inv02 = i02 * oneOverDet
        val inv03 = i03 * oneOverDet

        val inv10 = i10 * oneOverDet
        val inv11 = i11 * oneOverDet
        val inv12 = i12 * oneOverDet
        val inv13 = i13 * oneOverDet

        val inv20 = i20 * oneOverDet
        val inv21 = i21 * oneOverDet
        val inv22 = i22 * oneOverDet
        val inv23 = i23 * oneOverDet

        val inv30 = i30 * oneOverDet
        val inv31 = i31 * oneOverDet
        val inv32 = i32 * oneOverDet
        val inv33 = i33 * oneOverDet

        var tmpX = win.x
        var tmpY = win.y
        var tmpZ = win.z
        tmpX = (tmpX - viewport[0]) / viewport[2]
        tmpY = (tmpY - viewport[1]) / viewport[3]
        tmpX = tmpX * 2f - 1f
        tmpY = tmpY * 2f - 1f
        tmpZ = tmpZ * 2f - 1f

        // obj = Inverse * tmp;
        // obj /= obj.w
        res(
                inv00 * tmpX + inv10 * tmpY + inv20 * tmpZ + inv30,
                inv01 * tmpX + inv11 * tmpY + inv21 * tmpZ + inv31,
                inv02 * tmpX + inv12 * tmpY + inv22 * tmpZ + inv32) /= inv03 * tmpX + inv13 * tmpY + inv23 * tmpZ + inv33

        return res
    }

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of -1 and +1 respectively. (OpenGL clip volume definition)
     *
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProjectNo(win: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i): Vec3 =
            unProjectNo(win, model, proj, viewport, Vec3())

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates using default near and far clip planes definition.
     *  To change default near and far clip planes definition use ZERO_TO_ONE.
     *
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProject(win: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i, res: Vec3): Vec3 =
            when (GLM_DEPTH_CLIP_SPACE) {
                GlmDepthClipSpace.ZERO_TO_ONE -> unProjectZo(win, model, proj, viewport, res)
                else -> unProjectNo(win, model, proj, viewport, res)
            }

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates using default near and far clip planes definition.
     *  To change default near and far clip planes definition use ZERO_TO_ONE.
     *
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProject(win: Vec3, model: Mat4, proj: Mat4, viewport: Vec4i): Vec3 =
            when (GLM_DEPTH_CLIP_SPACE) {
                GlmDepthClipSpace.ZERO_TO_ONE -> unProjectZo(win, model, proj, viewport, Vec3())
                else -> unProjectNo(win, model, proj, viewport, Vec3())
            }


    // -----------------------------------------------------------------------------------------------------------------
    // Mat4d version
    // -----------------------------------------------------------------------------------------------------------------


    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of 0 and +1 respectively. (Direct3D clip volume definition)
     *
     *  @param res the resulting window coordinates
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun projectZo(obj: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i, res: Vec3d): Vec3d {
        // tmp = model * Vec4d(obj, 1)
        val tmpX = model[0, 0] * obj.x + model[1, 0] * obj.y + model[2, 0] * obj.z + model[3, 0]
        val tmpY = model[0, 1] * obj.x + model[1, 1] * obj.y + model[2, 1] * obj.z + model[3, 1]
        val tmpZ = model[0, 2] * obj.x + model[1, 2] * obj.y + model[2, 2] * obj.z + model[3, 2]
        val tmpW = model[0, 3] * obj.x + model[1, 3] * obj.y + model[2, 3] * obj.z + model[3, 3]
        // tmp = proj * tmp;
        res.put(proj[0, 0] * tmpX + proj[1, 0] * tmpY + proj[2, 0] * tmpZ + proj[3, 0] * tmpW,
                proj[0, 1] * tmpX + proj[1, 1] * tmpY + proj[2, 1] * tmpZ + proj[3, 1] * tmpW,
                proj[0, 2] * tmpX + proj[1, 2] * tmpY + proj[2, 2] * tmpZ + proj[3, 2] * tmpW)
        // tmp /= tmp.w
        res.divAssign(proj[0, 3] * tmpX + proj[1, 3] * tmpY + proj[2, 3] * tmpZ + proj[3, 3] * tmpW)

        res.x = res.x * 0.5 + 0.5
        res.y = res.y * 0.5 + 0.5

        res[0] = res[0] * viewport[2] + viewport[0]
        res[1] = res[1] * viewport[3] + viewport[1]

        return res
    }

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of 0 and +1 respectively. (Direct3D clip volume definition)
     *
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun projectZo(obj: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i): Vec3d =
            projectZo(obj, model, proj, viewport, Vec3d())

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of -1 and +1 respectively. (OpenGL clip volume definition)
     *
     *  @param res the window coordinates
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun projectNo(obj: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i, res: Vec3d): Vec3d {
        // tmp = model * Vec4d(obj, 1)
        val tmpX = model[0, 0] * obj.x + model[1, 0] * obj.y + model[2, 0] * obj.z + model[3, 0]
        val tmpY = model[0, 1] * obj.x + model[1, 1] * obj.y + model[2, 1] * obj.z + model[3, 1]
        val tmpZ = model[0, 2] * obj.x + model[1, 2] * obj.y + model[2, 2] * obj.z + model[3, 2]
        val tmpW = model[0, 3] * obj.x + model[1, 3] * obj.y + model[2, 3] * obj.z + model[3, 3]
        // tmp = proj * tmp;
        res.put(proj[0, 0] * tmpX + proj[1, 0] * tmpY + proj[2, 0] * tmpZ + proj[3, 0] * tmpW,
                proj[0, 1] * tmpX + proj[1, 1] * tmpY + proj[2, 1] * tmpZ + proj[3, 1] * tmpW,
                proj[0, 2] * tmpX + proj[1, 2] * tmpY + proj[2, 2] * tmpZ + proj[3, 2] * tmpW)
        // tmp /= tmp.w
        res.divAssign(proj[0, 3] * tmpX + proj[1, 3] * tmpY + proj[2, 3] * tmpZ + proj[3, 3] * tmpW)

        res.x = res.x * 0.5 + 0.5
        res.y = res.y * 0.5 + 0.5
        res.z = res.z * 0.5 + 0.5

        res[0] = res[0] * viewport[2] + viewport[0]
        res[1] = res[1] * viewport[3] + viewport[1]

        return res
    }

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of -1 and +1 respectively. (OpenGL clip volume definition)
     *
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun projectNo(obj: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i): Vec3d =
            projectNo(obj, model, proj, viewport, Vec3d())

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates using default near and far clip planes definition.
     *  To change default near and far clip planes definition use ZERO_TO_ONE.
     *
     *  @param res the window coordinates
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun project(obj: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i, res: Vec3d): Vec3d =
            when (GLM_DEPTH_CLIP_SPACE) {
                GlmDepthClipSpace.ZERO_TO_ONE -> projectZo(obj, model, proj, viewport, res)
                else -> projectNo(obj, model, proj, viewport, res)
            }

    /** Map the specified object coordinates (obj.x, obj.y, obj.z) into window coordinates using default near and far clip planes definition.
     *  To change default near and far clip planes definition use ZERO_TO_ONE.
     *
     *  @param obj Specify the object coordinates.
     *  @param model Specifies the current modelview matrix
     *  @param proj Specifies the current projection matrix
     *  @param viewport Specifies the current viewport
     *  @return Return the computed window coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluProject.xml">gluProject man page</a>
     */
    fun project(obj: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i): Vec3d =
            when (GLM_DEPTH_CLIP_SPACE) {
                GlmDepthClipSpace.ZERO_TO_ONE -> projectZo(obj, model, proj, viewport, Vec3d())
                else -> projectNo(obj, model, proj, viewport, Vec3d())
            }

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of 0 and +1 respectively. (Direct3D clip volume definition)
     *
     *  @param res the object coordinates
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProjectZo(win: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i, res: Vec3d): Vec3d {

        // proj * model
        val a0 = proj.array[0] * model.array[0] + proj.array[4] * model.array[1] + proj.array[8] * model.array[2] + proj.array[12] * model.array[3]
        val b0 = proj.array[0] * model.array[4] + proj.array[4] * model.array[5] + proj.array[8] * model.array[6] + proj.array[12] * model.array[7]
        val c0 = proj.array[0] * model.array[8] + proj.array[4] * model.array[9] + proj.array[8] * model.array[10] + proj.array[12] * model.array[11]
        val d0 = proj.array[0] * model.array[12] + proj.array[4] * model.array[13] + proj.array[8] * model.array[14] + proj.array[12] * model.array[15]
        val a1 = proj.array[1] * model.array[0] + proj.array[5] * model.array[1] + proj.array[9] * model.array[2] + proj.array[13] * model.array[3]
        val b1 = proj.array[1] * model.array[4] + proj.array[5] * model.array[5] + proj.array[9] * model.array[6] + proj.array[13] * model.array[7]
        val c1 = proj.array[1] * model.array[8] + proj.array[5] * model.array[9] + proj.array[9] * model.array[10] + proj.array[13] * model.array[11]
        val d1 = proj.array[1] * model.array[12] + proj.array[5] * model.array[13] + proj.array[9] * model.array[14] + proj.array[13] * model.array[15]
        val a2 = proj.array[2] * model.array[0] + proj.array[6] * model.array[1] + proj.array[10] * model.array[2] + proj.array[14] * model.array[3]
        val b2 = proj.array[2] * model.array[4] + proj.array[6] * model.array[5] + proj.array[10] * model.array[6] + proj.array[14] * model.array[7]
        val c2 = proj.array[2] * model.array[8] + proj.array[6] * model.array[9] + proj.array[10] * model.array[10] + proj.array[14] * model.array[11]
        val d2 = proj.array[2] * model.array[12] + proj.array[6] * model.array[13] + proj.array[10] * model.array[14] + proj.array[14] * model.array[15]
        val a3 = proj.array[3] * model.array[0] + proj.array[7] * model.array[1] + proj.array[11] * model.array[2] + proj.array[15] * model.array[3]
        val b3 = proj.array[3] * model.array[4] + proj.array[7] * model.array[5] + proj.array[11] * model.array[6] + proj.array[15] * model.array[7]
        val c3 = proj.array[3] * model.array[8] + proj.array[7] * model.array[9] + proj.array[11] * model.array[10] + proj.array[15] * model.array[11]
        val d3 = proj.array[3] * model.array[12] + proj.array[7] * model.array[13] + proj.array[11] * model.array[14] + proj.array[15] * model.array[15]

        // inverse(proj * model)
        val c00 = c2 * d3 - d2 * c3
        val c02 = b2 * d3 - d2 * b3
        val c03 = b2 * c3 - c2 * b3

        val c04 = c1 * d3 - d1 * c3
        val c06 = b1 * d3 - d1 * b3
        val c07 = b1 * c3 - c1 * b3

        val c08 = c1 * d2 - d1 * c2
        val c10 = b1 * d2 - d1 * b2
        val c11 = b1 * c2 - c1 * b2

        val c12 = c0 * d3 - d0 * c3
        val c14 = b0 * d3 - d0 * b3
        val c15 = b0 * c3 - c0 * b3

        val c16 = c0 * d2 - d0 * c2
        val c18 = b0 * d2 - d0 * b2
        val c19 = b0 * c2 - c0 * b2

        val c20 = c0 * d1 - d0 * c1
        val c22 = b0 * d1 - d0 * b1
        val c23 = b0 * c1 - c0 * b1

        val i00 = +(b1 * c00 - b2 * c04 + b3 * c08)
        val i01 = -(a1 * c00 - a2 * c04 + a3 * c08)
        val i02 = +(a1 * c02 - a2 * c06 + a3 * c10)
        val i03 = -(a1 * c03 - a2 * c07 + a3 * c11)

        val i10 = -(b0 * c00 - b2 * c12 + b3 * c16)
        val i11 = +(a0 * c00 - a2 * c12 + a3 * c16)
        val i12 = -(a0 * c02 - a2 * c14 + a3 * c18)
        val i13 = +(a0 * c03 - a2 * c15 + a3 * c19)

        val i20 = +(b0 * c04 - b1 * c12 + b3 * c20)
        val i21 = -(a0 * c04 - a1 * c12 + a3 * c20)
        val i22 = +(a0 * c06 - a1 * c14 + a3 * c22)
        val i23 = -(a0 * c07 - a1 * c15 + a3 * c23)

        val i30 = -(b0 * c08 - b1 * c16 + b2 * c20)
        val i31 = +(a0 * c08 - a1 * c16 + a2 * c20)
        val i32 = -(a0 * c10 - a1 * c18 + a2 * c22)
        val i33 = +(a0 * c11 - a1 * c19 + a2 * c23)

        val oneOverDet = 1 / (a0 * i00 + a1 * i10 + a2 * i20 + a3 * i30)

        val inv00 = i00 * oneOverDet
        val inv01 = i01 * oneOverDet
        val inv02 = i02 * oneOverDet
        val inv03 = i03 * oneOverDet

        val inv10 = i10 * oneOverDet
        val inv11 = i11 * oneOverDet
        val inv12 = i12 * oneOverDet
        val inv13 = i13 * oneOverDet

        val inv20 = i20 * oneOverDet
        val inv21 = i21 * oneOverDet
        val inv22 = i22 * oneOverDet
        val inv23 = i23 * oneOverDet

        val inv30 = i30 * oneOverDet
        val inv31 = i31 * oneOverDet
        val inv32 = i32 * oneOverDet
        val inv33 = i33 * oneOverDet

        var tmpX = win.x
        var tmpY = win.y
        val tmpZ = win.z
        tmpX = (tmpX - viewport[0]) / viewport[2]
        tmpY = (tmpY - viewport[1]) / viewport[3]
        tmpX = tmpX * 2 - 1
        tmpY = tmpY * 2 - 1

        // obj = Inverse * tmp;
        // obj /= obj.w
        res(
                inv00 * tmpX + inv10 * tmpY + inv20 * tmpZ + inv30,
                inv01 * tmpX + inv11 * tmpY + inv21 * tmpZ + inv31,
                inv02 * tmpX + inv12 * tmpY + inv22 * tmpZ + inv32) /= inv03 * tmpX + inv13 * tmpY + inv23 * tmpZ + inv33

        return res
    }

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of 0 and +1 respectively. (Direct3D clip volume definition)
     *
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProjectZo(win: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i): Vec3d =
            unProjectZo(win, model, proj, viewport, Vec3d())

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of -1 and +1 respectively. (OpenGL clip volume definition)
     *
     *  @param res the object coordinates
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProjectNo(win: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i, res: Vec3d): Vec3d {

        // proj * model
        val a0 = proj.array[0] * model.array[0] + proj.array[4] * model.array[1] + proj.array[8] * model.array[2] + proj.array[12] * model.array[3]
        val b0 = proj.array[0] * model.array[4] + proj.array[4] * model.array[5] + proj.array[8] * model.array[6] + proj.array[12] * model.array[7]
        val c0 = proj.array[0] * model.array[8] + proj.array[4] * model.array[9] + proj.array[8] * model.array[10] + proj.array[12] * model.array[11]
        val d0 = proj.array[0] * model.array[12] + proj.array[4] * model.array[13] + proj.array[8] * model.array[14] + proj.array[12] * model.array[15]
        val a1 = proj.array[1] * model.array[0] + proj.array[5] * model.array[1] + proj.array[9] * model.array[2] + proj.array[13] * model.array[3]
        val b1 = proj.array[1] * model.array[4] + proj.array[5] * model.array[5] + proj.array[9] * model.array[6] + proj.array[13] * model.array[7]
        val c1 = proj.array[1] * model.array[8] + proj.array[5] * model.array[9] + proj.array[9] * model.array[10] + proj.array[13] * model.array[11]
        val d1 = proj.array[1] * model.array[12] + proj.array[5] * model.array[13] + proj.array[9] * model.array[14] + proj.array[13] * model.array[15]
        val a2 = proj.array[2] * model.array[0] + proj.array[6] * model.array[1] + proj.array[10] * model.array[2] + proj.array[14] * model.array[3]
        val b2 = proj.array[2] * model.array[4] + proj.array[6] * model.array[5] + proj.array[10] * model.array[6] + proj.array[14] * model.array[7]
        val c2 = proj.array[2] * model.array[8] + proj.array[6] * model.array[9] + proj.array[10] * model.array[10] + proj.array[14] * model.array[11]
        val d2 = proj.array[2] * model.array[12] + proj.array[6] * model.array[13] + proj.array[10] * model.array[14] + proj.array[14] * model.array[15]
        val a3 = proj.array[3] * model.array[0] + proj.array[7] * model.array[1] + proj.array[11] * model.array[2] + proj.array[15] * model.array[3]
        val b3 = proj.array[3] * model.array[4] + proj.array[7] * model.array[5] + proj.array[11] * model.array[6] + proj.array[15] * model.array[7]
        val c3 = proj.array[3] * model.array[8] + proj.array[7] * model.array[9] + proj.array[11] * model.array[10] + proj.array[15] * model.array[11]
        val d3 = proj.array[3] * model.array[12] + proj.array[7] * model.array[13] + proj.array[11] * model.array[14] + proj.array[15] * model.array[15]

        // inverse(proj * model)
        val c00 = c2 * d3 - d2 * c3
        val c02 = b2 * d3 - d2 * b3
        val c03 = b2 * c3 - c2 * b3

        val c04 = c1 * d3 - d1 * c3
        val c06 = b1 * d3 - d1 * b3
        val c07 = b1 * c3 - c1 * b3

        val c08 = c1 * d2 - d1 * c2
        val c10 = b1 * d2 - d1 * b2
        val c11 = b1 * c2 - c1 * b2

        val c12 = c0 * d3 - d0 * c3
        val c14 = b0 * d3 - d0 * b3
        val c15 = b0 * c3 - c0 * b3

        val c16 = c0 * d2 - d0 * c2
        val c18 = b0 * d2 - d0 * b2
        val c19 = b0 * c2 - c0 * b2

        val c20 = c0 * d1 - d0 * c1
        val c22 = b0 * d1 - d0 * b1
        val c23 = b0 * c1 - c0 * b1

        val i00 = +(b1 * c00 - b2 * c04 + b3 * c08)
        val i01 = -(a1 * c00 - a2 * c04 + a3 * c08)
        val i02 = +(a1 * c02 - a2 * c06 + a3 * c10)
        val i03 = -(a1 * c03 - a2 * c07 + a3 * c11)

        val i10 = -(b0 * c00 - b2 * c12 + b3 * c16)
        val i11 = +(a0 * c00 - a2 * c12 + a3 * c16)
        val i12 = -(a0 * c02 - a2 * c14 + a3 * c18)
        val i13 = +(a0 * c03 - a2 * c15 + a3 * c19)

        val i20 = +(b0 * c04 - b1 * c12 + b3 * c20)
        val i21 = -(a0 * c04 - a1 * c12 + a3 * c20)
        val i22 = +(a0 * c06 - a1 * c14 + a3 * c22)
        val i23 = -(a0 * c07 - a1 * c15 + a3 * c23)

        val i30 = -(b0 * c08 - b1 * c16 + b2 * c20)
        val i31 = +(a0 * c08 - a1 * c16 + a2 * c20)
        val i32 = -(a0 * c10 - a1 * c18 + a2 * c22)
        val i33 = +(a0 * c11 - a1 * c19 + a2 * c23)

        val oneOverDet = 1 / (a0 * i00 + a1 * i10 + a2 * i20 + a3 * i30)

        val inv00 = i00 * oneOverDet
        val inv01 = i01 * oneOverDet
        val inv02 = i02 * oneOverDet
        val inv03 = i03 * oneOverDet

        val inv10 = i10 * oneOverDet
        val inv11 = i11 * oneOverDet
        val inv12 = i12 * oneOverDet
        val inv13 = i13 * oneOverDet

        val inv20 = i20 * oneOverDet
        val inv21 = i21 * oneOverDet
        val inv22 = i22 * oneOverDet
        val inv23 = i23 * oneOverDet

        val inv30 = i30 * oneOverDet
        val inv31 = i31 * oneOverDet
        val inv32 = i32 * oneOverDet
        val inv33 = i33 * oneOverDet

        var tmpX = win.x
        var tmpY = win.y
        var tmpZ = win.z
        tmpX = (tmpX - viewport[0]) / viewport[2]
        tmpY = (tmpY - viewport[1]) / viewport[3]
        tmpX = tmpX * 2 - 1
        tmpY = tmpY * 2 - 1
        tmpZ = tmpZ * 2 - 1

        // obj = Inverse * tmp;
        // obj /= obj.w
        res(
                inv00 * tmpX + inv10 * tmpY + inv20 * tmpZ + inv30,
                inv01 * tmpX + inv11 * tmpY + inv21 * tmpZ + inv31,
                inv02 * tmpX + inv12 * tmpY + inv22 * tmpZ + inv32) /= inv03 * tmpX + inv13 * tmpY + inv23 * tmpZ + inv33

        return res
    }

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates.
     *  The near and far clip planes correspond to z normalized device coordinates of -1 and +1 respectively. (OpenGL clip volume definition)
     *
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProjectNo(win: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i): Vec3d =
            unProjectNo(win, model, proj, viewport, Vec3d())

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates using default near and far clip planes definition.
     *  To change default near and far clip planes definition use ZERO_TO_ONE.
     *
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProject(win: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i, res: Vec3d): Vec3d =
            when (GLM_DEPTH_CLIP_SPACE) {
                GlmDepthClipSpace.ZERO_TO_ONE -> unProjectZo(win, model, proj, viewport, res)
                else -> unProjectNo(win, model, proj, viewport, res)
            }

    /** Map the specified window coordinates (win.x, win.y, win.z) into object coordinates using default near and far clip planes definition.
     *  To change default near and far clip planes definition use ZERO_TO_ONE.
     *
     *  @param win Specify the window coordinates to be mapped.
     *  @param model Specifies the modelview matrix
     *  @param proj Specifies the projection matrix
     *  @param viewport Specifies the viewport
     *  @return Returns the computed object coordinates.
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluUnProject.xml">gluUnProject man page</a>
     */
    fun unProject(win: Vec3d, model: Mat4d, proj: Mat4d, viewport: Vec4i): Vec3d =
            when (GLM_DEPTH_CLIP_SPACE) {
                GlmDepthClipSpace.ZERO_TO_ONE -> unProjectZo(win, model, proj, viewport, Vec3d())
                else -> unProjectNo(win, model, proj, viewport, Vec3d())
            }


    /** Define a picking region
     *
     *  @param res the pick matrix
     *  @param center Specify the center of a picking region in window coordinates.
     *  @param delta Specify the width and height, respectively, of the picking region in window coordinates.
     *  @param viewport Rendering viewport
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluPickMatrix.xml">gluPickMatrix man page</a>
     */
    fun pickMatrix(center: Vec2, delta: Vec2, viewport: Vec4i, res: Mat4): Mat4 {

        assert(delta.x > 0f && delta.y > 0f)
        res put 1f

        if (!(delta.x > 0f && delta.y > 0f))
            throw Error()

        val tmpX = (viewport[2] - 2f * (center.x - viewport[0])) / delta.x
        val tmpY = (viewport[3] - 2f * (center.y - viewport[1])) / delta.y
        //val tmpZ = 0f

        // Translate and scale the picked region to the entire window
        //Result = translate(Result, Temp)
        val x = res[0, 0] * tmpX + res[1, 0] * tmpY + res[3, 0]
        val y = res[0, 1] * tmpX + res[1, 1] * tmpY + res[3, 1]
        val z = res[0, 2] * tmpX + res[1, 2] * tmpY + res[3, 2]
        val w = res[0, 3] * tmpX + res[1, 3] * tmpY + res[3, 3]
        res[3, 0] = x
        res[3, 1] = y
        res[3, 2] = z
        res[3, 3] = w
        //return scale(res, Vec3(viewport[2] / delta.x, viewport[3] / delta.y, 1f))
        val vX = viewport[2] / delta.x
        val vY = viewport[3] / delta.y
        res[0, 0] *= vX
        res[0, 1] *= vX
        res[0, 2] *= vX
        res[0, 3] *= vX
        res[1, 0] *= vY
        res[1, 1] *= vY
        res[1, 2] *= vY
        res[1, 3] *= vY
        return res
    }

    /** Define a picking region
     *
     *  @param center Specify the center of a picking region in window coordinates.
     *  @param delta Specify the width and height, respectively, of the picking region in window coordinates.
     *  @param viewport Rendering viewport
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluPickMatrix.xml">gluPickMatrix man page</a>
     */
    fun pickMatrix(center: Vec2, delta: Vec2, viewport: Vec4i): Mat4 =
            pickMatrix(center, delta, viewport, Mat4())


    /** Define a picking region
     *
     *  @param res the pick matrix
     *  @param center Specify the center of a picking region in window coordinates.
     *  @param delta Specify the width and height, respectively, of the picking region in window coordinates.
     *  @param viewport Rendering viewport
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluPickMatrix.xml">gluPickMatrix man page</a>
     */
    fun pickMatrix(center: Vec2d, delta: Vec2d, viewport: Vec4i, res: Mat4d): Mat4d {

        assert(delta.x > 0.0 && delta.y > 0.0)
        res put 1.0

        if (!(delta.x > 0.0 && delta.y > 0.0))
            throw Error()

        val tmpX = (viewport[2] - 2.0 * (center.x - viewport[0])) / delta.x
        val tmpY = (viewport[3] - 2.0 * (center.y - viewport[1])) / delta.y
        //val tmpZ = 0.0

        // Translate and scale the picked region to the entire window
        //Result = translate(Result, Temp)
        val x = res[0, 0] * tmpX + res[1, 0] * tmpY + res[3, 0]
        val y = res[0, 1] * tmpX + res[1, 1] * tmpY + res[3, 1]
        val z = res[0, 2] * tmpX + res[1, 2] * tmpY + res[3, 2]
        val w = res[0, 3] * tmpX + res[1, 3] * tmpY + res[3, 3]
        res[3, 0] = x
        res[3, 1] = y
        res[3, 2] = z
        res[3, 3] = w
        //return scale(res, Vec3(viewport[2] / delta.x, viewport[3] / delta.y, 1.0))
        val vX = viewport[2] / delta.x
        val vY = viewport[3] / delta.y
        res[0, 0] *= vX
        res[0, 1] *= vX
        res[0, 2] *= vX
        res[0, 3] *= vX
        res[1, 0] *= vY
        res[1, 1] *= vY
        res[1, 2] *= vY
        res[1, 3] *= vY
        return res
    }

    /** Define a picking region
     *
     *  @param center Specify the center of a picking region in window coordinates.
     *  @param delta Specify the width and height, respectively, of the picking region in window coordinates.
     *  @param viewport Rendering viewport
     *
     *  @see gtc_matrix_transform
     *  @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluPickMatrix.xml">gluPickMatrix man page</a>
     */
    fun pickMatrix(center: Vec2d, delta: Vec2d, viewport: Vec4i): Mat4d =
            pickMatrix(center, delta, viewport, Mat4d())
}
