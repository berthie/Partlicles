package persson.berthie2.partlicles.rainparticles.particle

import androidx.compose.ui.graphics.Color

sealed class PrecipitationShape {

    data class Line(
        val minStrokeWidth: Int,
        val maxStrokeWidth: Int,
        val minHeight: Int,
        val maxHeight: Int,
        val color: Color
    ): PrecipitationShape()

}
