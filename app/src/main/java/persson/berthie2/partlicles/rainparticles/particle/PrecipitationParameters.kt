package persson.berthie2.partlicles.rainparticles.particle

import androidx.compose.ui.graphics.Color

data class PrecipitationParameters(
    val particleCount: Int,
    val distancePerStep: Int,
    val minSpeed: Float,
    val maxSpeed: Float,
    val minAngle: Int,
    val maxAngle: Int,
    val shape: PrecipitationShape,
    val sourceEdge: PrecipitationSourceEdge
)

val rainParameters = PrecipitationParameters(
    particleCount = 600,
    distancePerStep = 30,
    minSpeed = 0.7f,
    maxSpeed = 1f,
    minAngle = 265,
    maxAngle = 285,
    shape = PrecipitationShape.Line(
        minStrokeWidth = 1,
        maxStrokeWidth = 3,
        minHeight = 10,
        maxHeight = 15,
        color = Color.Gray
    ),
    sourceEdge = PrecipitationSourceEdge.TOP
)
