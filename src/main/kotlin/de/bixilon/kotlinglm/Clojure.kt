package de.bixilon.kotlinglm

class Clojure {

    companion object {
        @JvmStatic
        fun frustum(left: Float, right: Float, bottom: Float, top: Float, near: Float, far: Float) = GLM.frustum(left, right, bottom, top, near, far)
    }
}
