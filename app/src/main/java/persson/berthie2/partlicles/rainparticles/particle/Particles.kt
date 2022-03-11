package persson.berthie2.partlicles.rainparticles.particle

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Particles(
    modifier: Modifier = Modifier,
    iteration: Long,
    parameters: PrecipitationParameters
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val particleGenerator by remember {
            mutableStateOf(
                Particle
            )
        }
    }
}


@Preview
@Composable
fun ParticlesPreview() {
    Particles(iteration = 1, parameters = rainParameters)
}