package persson.berthie2.partlicles

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.isActive
import persson.berthie2.partlicles.rainparticles.HomeViewModel
import persson.berthie2.partlicles.rainparticles.particle.*
import persson.berthie2.partlicles.ui.appendIf
import persson.berthie2.partlicles.ui.theme.PartliclesTheme
import persson.berthie2.partlicles.vectorsnow.simba
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PartliclesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    SnowFall()
                }
            }
        }
    }
}


@Composable
fun Particles(
    modifier: Modifier = Modifier,
    iteration: Long,
    parameters: PrecipitationParameters
) {
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

        androidx.compose.foundation.Canvas(modifier = modifier,
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SnowFall() {
    val viewModel = viewModel<HomeViewModel>()
    val particleAnimationIteration by viewModel.particleAnimationIteration.collectAsState()
    Image(
        painter = painterResource(id = R.drawable.panjid),
        contentDescription = null,
        //modifier = Modifier.appendIf(isCurrentDateBetween()) { simba() }



    )
    Particles(iteration = particleAnimationIteration, parameters = rainParameters)  // Animation Iteration restart, Create new file from rainparameters
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PartliclesTheme {
        SnowFall()
    }
}