package persson.berthie2.partlicles.rainparticles.particle

import androidx.compose.animation.core.FloatTweenSpec

data class Particle(
    var n: Long =0,
    var x: Float,
    var y: Float,
    var width: Float,
    var height: Float,
    var speed: Float,
    var angle: Int,
)
