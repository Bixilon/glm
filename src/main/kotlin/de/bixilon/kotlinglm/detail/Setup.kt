package de.bixilon.kotlinglm.detail

/**
 * Created by GBarbieri on 14.12.2016.
 */

///////////////////////////////////////////////////////////////////////////////////
// Clip control

enum class GlmDepthClipSpace { ZERO_TO_ONE, NEGATIVE_ONE_TO_ONE }

var GLM_DEPTH_CLIP_SPACE = GlmDepthClipSpace.NEGATIVE_ONE_TO_ONE


///////////////////////////////////////////////////////////////////////////////////
// Coordinate system

enum class GlmCoordinateSystem {
    /** For DirectX, Metal, Vulkan */
    LEFT_HANDED,
    /** For OpenGL, default in GLM */
    RIGHT_HANDED
}

var GLM_COORDINATE_SYSTEM = GlmCoordinateSystem.RIGHT_HANDED
