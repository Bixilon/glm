![glm](logo-mini.png)

[![license](https://img.shields.io/badge/License-MIT-orange.svg)](https://github.com/kotlin-graphics/glm/blob/master/LICENSE) 

This is a fork of [kotlin-graphics/glm](https://github.com/kotlin-graphics/glm)

This is the Kotlin port of [OpenGL Mathematics](http://glm.g-truc.net/) (*GLM*), written by [g-truc](https://github.com/Groovounet) ([repository](https://github.com/g-truc/glm)), a header only C++ mathematics library for graphics software based on the [OpenGL Shading Language (GLSL) specifications](https://www.opengl.org/registry/doc/GLSLangSpec.4.50.diff.pdf).

*GLM* provides classes and functions designed and implemented with the same naming conventions and functionality than *GLSL* so that anyone who knows *GLSL*, can use *GLM* as well in Kotlin and Java.

This project isn't limited to *GLSL* features. An extension system, based on the *GLSL* extension conventions, provides extended capabilities: matrix transformations, quaternions, data packing, random numbers, noise, etc...

This library works perfectly with *[OpenGL](https://www.opengl.org)*, but it also ensures interoperability with other third party libraries and SDK. It is a good candidate for software rendering (raytracing / rasterisation), image processing, physic simulations and any development context that requires a simple and convenient mathematics library.

*GLM* is written entirely in Kotlin, but can be also used from Java. It is a platform independent library with no dependencies other than [kotlin-unsigned](https://github.com/elect86/kotlin-unsigned) for unsigned support and [kotlin-test](https://github.com/kotlintest/kotlintest) for testing.

For more information about *GLM*, please have a look at the [manual](https://github.com/kotlin-graphics/glm/wiki) and the original [API reference documentation](http://glm.g-truc.net/0.9.8/api/index.html).
The source code and the documentation are licensed under both the [Happy Bunny License (Modified MIT) or the MIT License](https://github.com/kotlin-graphics/glm/wiki/Manual#section0).

Don't hesitate to contribute to the project by submitting [issues](https://github.com/kotlin-graphics/glm/issues) or [pull requests](https://github.com/kotlin-graphics/glm/pulls) for bugs and features. Any feedback is welcome at [elect86@gmail.com](mailto://elect86@gmail.com).

```kotlin
import de.bixilon.kotlinglm.vec2.Vec2
import de.bixilon.kotlinglm.vec3.Vec3
import de.bixilon.kotlinglm.mat4x4.Mat4
import de.bixilon.kotlinglm.GLM

fun camera(translate: Float, rotate: Vec2): Mat4 {
    val projection = GLM.perspective(GLM.PIf * 0.25f, 4.0f / 3.0f, 0.1f, 100.0f)
    var view = GLM.translate(Mat4(1.0f), Vec3(0.0f, 0.0f, -translate))
    view = GLM.rotate(view, rotate.y, Vec3(-1.0f, 0.0f, 0.0f))
    view = GLM.rotate(view, rotate.x, Vec3(0.0f, 1.0f, 0.0f))
    val model = GLM.scale(Mat4(1.0f), Vec3(0.5f))
    return projection * view * model
}
```

### How to retrieve it:


### Maven

```xml
<dependency>
    <groupId>de.bixilon</groupId>
    <artifactId>kotlin-glm</artifactId>
    <version>0.9.9.1-12</version>
</dependency>
```

### Gradle

```groovy
implementation 'de.bixilon:kotlin-glm:0.9.9.1-12'
```

### Additional dependencies

If you plan to use unsigned primitives add [kotlin-unsigned](https://github.com/kotlin-graphics/kotlin-unsigned)

## Differences to kotlin-graphics/glm

- All classes in a proper namespace
- VecNt<*> is an interface instead of an abstract class (less memory allocation needed; faster)
- inlining a lot of functions (Far less memory allocation; a lot faster)
- don't require lwjgl (only a build dependency)
- get it easily from maven central without adding any additional repositories
- use primitives over wrapped classes
