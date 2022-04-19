package de.bixilon.kotlinglm

import de.bixilon.kotlinglm.ext.equal
import de.bixilon.kotlinglm.mat4x4.Mat4
import de.bixilon.kotlinglm.mat4x4.Mat4d
import de.bixilon.kotlinglm.mat4x4.operators.div
import de.bixilon.kotlinglm.mat4x4.operators.times
import de.bixilon.kotlinglm.vec4.Vec4
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import de.bixilon.kotlinkool.BYTES

/**
 * Created by GBarbieri on 12.12.2016.
 */

class testCoreMat4 : StringSpec() {

    init {

        val epsilon = 0.001f

        "operators"         {

            val M = Mat4(2f)
            val N = Mat4(1f)
            val U = Vec4(2f)

            run {
                val P = N * 2f
                P.shouldEqual(M, epsilon)

                val Q = M / 2f
                Q.shouldEqual(N, epsilon)
            }

            run {
                val V = M * U
                V.shouldEqual(Vec4(4f), epsilon)

//                val W = U / M TODO? Wtf vec4/mat4?
//                Error += glm::all(GLM::equal(W, vecType(1.f), Epsilon)) ? 0 : 1;
            }

            run {
                val O = M * N
                O.shouldEqual(Mat4(2f), epsilon)
            }
        }

        "inverse mat4" {

            val matrix = Mat4(
                    0.6f, 0.2f, 0.3f, 0.4f,
                    0.2f, 0.7f, 0.5f, 0.3f,
                    0.3f, 0.5f, 0.743f, 0.2f,
                    0.4f, 0.3f, 0.2f, 0.6f)
            val inverse = matrix.inverse()
            val identity = matrix * inverse

            matrix.print("mat")

            identity.shouldEqual(Mat4(1f), epsilon)
        }

        "inverse mat4d" {
            val matrix = Mat4d(
                    0.6, 0.2, 0.3, 0.4,
                    0.2, 0.7, 0.5, 0.3,
                    0.3, 0.5, 0.7, 0.2,
                    0.4, 0.3, 0.2, 0.6)
            val identity = matrix / matrix

            identity.shouldEqual(Mat4d(1f), 0.01)
        }

        "operators2" {

            val l = Mat4(1f)
            val m = Mat4(1f)
            val u = Vec4(1f)
            val v = Vec4(1f)
            val x = 1f
            val a = m * u
            val b = v * m
            val n = x / m
            val o = m / x
            val p = x * m
            val q = m * x
            val R = m.anyNotEqual(q, Float.MIN_VALUE)
            val S = m.allEqual(l, Float.MIN_VALUE)

            (S && !R) shouldBe true
        }

        "inverse"        {

            run {
                val matrix = Mat4(
                        Vec4(0.6f, 0.2f, 0.3f, 0.4f),
                        Vec4(0.2f, 0.7f, 0.5f, 0.3f),
                        Vec4(0.3f, 0.5f, 0.7f, 0.2f),
                        Vec4(0.4f, 0.3f, 0.2f, 0.6f))
                val inverse = matrix.inverse()
                val identity = matrix * inverse
//                print(Matrix);
//                print(Inverse);
//                print(Identity);
                identity.shouldEqual(Mat4(1f), epsilon)
            }
        }

        "ctr"        {

            //            #if GLM_HAS_TRIVIAL_QUERIES
            //Error += std::is_trivially_default_constructible<glm::mat4>::value ? 0 : 1;
            //Error += std::is_trivially_copy_assignable<glm::mat4>::value ? 0 : 1;
//            Error += std::is_trivially_copyable<glm::mat4>::value ? 0 : 1;
            //Error += std::is_copy_constructible<glm::mat4>::value ? 0 : 1;
            //Error += std::has_trivial_copy_constructor<glm::mat4>::value ? 0 : 1;
//            #endif
//            #if GLM_HAS_INITIALIZER_LISTS
            val m0 = Mat4(
                    Vec4(0, 1, 2, 3),
                    Vec4(4, 5, 6, 7),
                    Vec4(8, 9, 10, 11),
                    Vec4(12, 13, 14, 15))
            m0.size() shouldBe 4 * 4 * Float.BYTES
            val V = Vec4(0, 1, 2, 3)
            val m1 = Mat4(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
            val m2 = Mat4 { i -> i }

            m0 shouldEqual m2
            m1 shouldEqual m2

            val m3 = arrayOf(
                    Mat4(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
                    Mat4(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
                    Mat4(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
                    Mat4(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
            val m4 = Mat4(
                    1, 0, 0, 0,
                    0, 1, 0, 0,
                    0, 0, 1, 0,
                    0, 0, 0, 1)
            m4[0][0].equal(1f, 0.0001f) shouldBe true
            m4[3][3].equal(1f, 0.0001f) shouldBe true
//            val v1 = arrayOf(
//                Mat4(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
//                Mat4(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
//            std::vector < glm::mat4 > v2 {
//                {
//                    { 0, 1, 2, 3 },
//                    { 4, 5, 6, 7 },
//                    { 8, 9, 10, 11 },
//                    { 12, 13, 14, 15 }
//                },
//                {
//                    { 0, 1, 2, 3 },
//                    { 4, 5, 6, 7 },
//                    { 8, 9, 10, 11 },
//                    { 12, 13, 14, 15 }
//                }
//            };
//            #endif//GLM_HAS_INITIALIZER_LISTS
        }

        "rotate" {

            val rotate = GLM.rotate(Mat4(), 1f, 2f, 3f, 4f)

            rotate[0].shouldEqual(Vec4(x = 0.60370886, y = 0.7201388, z = -0.34195852, w = 0.0), 0.01f)
            rotate[1].shouldEqual(Vec4(x = -0.529919, y = 0.68296707, z = 0.5027342, w = 0.0), 0.01f)
            rotate[2].shouldEqual(Vec4(x = 0.59558487, y = -0.12229471, z = 0.7939286, w = 0.0), 0.01f)
            rotate[3].shouldEqual(Vec4(x = 0.0, y = 0.0, z = 0.0, w = 1.0), 0.01f)
        }

        "test multiplication" {

            val m1f = Mat4(List(16) { it + 1f })
            val m2f = Mat4(List(16) { it + 17f })

            val m1d = Mat4d(List(16) { it + 1.0 })
            val m2d = Mat4d(List(16) { it + 17.0 })

            val expectedF = Mat4(538, 612, 686, 760,
                    650, 740, 830, 920,
                    762, 868, 974, 1080,
                    874, 996, 1118, 1240)

            val resultF = m1f * m2f

            val expectedD = Mat4d(538, 612, 686, 760,
                    650, 740, 830, 920,
                    762, 868, 974, 1080,
                    874, 996, 1118, 1240)
            val resultD = m1d * m2d

            resultF.allEqual(expectedF, 0.000001f) shouldBe true
            resultD.allEqual(expectedD, 0.000001) shouldBe true
        }

//        namespace cast TODO
//                {
//                    template<typename genType>
//                    int entry()
//                    {
//                        int Error = 0;
//
//                        genType A(1.0f);
//                        glm::mat4x4 B(A);
//                        glm::mat4x4 Identity(1.0f);
//
//                        for(GLM::length_t i = 0, length = B.length(); i < length; ++i)
//                        Error += glm::all(GLM::equal(B[i], Identity[i], glm::epsilon<float>())) ? 0 : 1;
//
//                        return Error;
//                    }
//
//                    int test()
//                    {
//                        int Error = 0;
//
//                        Error += entry<glm::mat2x2>();
//                        Error += entry<glm::mat2x3>();
//                        Error += entry<glm::mat2x4>();
//                        Error += entry<glm::mat3x2>();
//                        Error += entry<glm::mat3x3>();
//                        Error += entry<glm::mat3x4>();
//                        Error += entry<glm::mat4x2>();
//                        Error += entry<glm::mat4x3>();
//                        Error += entry<glm::mat4x4>();
//
//                        return Error;
//                    }
//                }
    }
}
