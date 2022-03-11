package persson.berthie2.partlicles.rainparticles.particle

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Particles(
    modifier: Modifier = Modifier,
    iteration: Long,
    parameters: PrecipitationParameters
) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1600, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val particleGenerator by remember {
            mutableStateOf(
                ParticleSystemHelper(
                    parameters, constraints.maxWidth, constraints.maxHeight
                )
            )
        }
        var particles by remember {
            mutableStateOf(
                listOf<Particle>()
            )
        }


        particleGenerator.generateParticles()
        particleGenerator.updateParticles(iteration)
        // Trigger recomp
        particles = particleGenerator.particles

        Canvas(modifier = modifier,
            onDraw = {
                particles.forEach { particle ->
                    when (parameters.shape) {
                        is PrecipitationShape.Line -> {
                            val endX = particle.x - particle.height * cos(
                                Math.toRadians(particle.angle.toDouble())
                            ).toFloat()
                            val endY = particle.y - particle.height * sin(
                                Math.toRadians(particle.angle.toDouble())
                            ).toFloat()
                            drawLine(
                                color = parameters.shape.color,
                                pathEffect = PathEffect.cornerPathEffect(20f),
                                start = Offset(particle.x, particle.y),
                                end = Offset(endX, endY),
                                strokeWidth = particle.width

                            )
                        }
                    }
                }
            }
        )

    }
}


@Preview
@Composable
fun ParticlesPreview() {
    Particles(iteration = 1, parameters = rainParameters)
}