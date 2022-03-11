package persson.berthie2.partlicles.rainparticles.particle

import kotlin.random.Random

class ParticleSystemHelper(
    private val parameters: PrecipitationParameters,
    private val frameWidth: Int,
    private val frameHeight: Int,
) {
    private val _particles = mutableListOf<Particle>()
    val particles: List<Particle> = _particles


    fun generateParticles() {
        while (particles.size < parameters.particleCount) {
            _particles.add(createParticle())
        }
    }

    private fun createParticle(): Particle {
        val randomWidth = getRandomWidth()
        val randomHeight = getRandomHeight(randomWidth)
        val angle = computeAngle()
    }

    private fun generateX(startFromSourceEdge: Boolean = false): Float {
        val randomX = Random.nextInt(frameWidth).toFloat()
        return when (parameters.sourceEdge) {
            PrecipitationSourceEdge.TOP -> randomX
            PrecipitationSourceEdge.RIGHT -> if (startFromSourceEdge) frameWidth.toFloat() else randomX
            PrecipitationSourceEdge.BOTTOM -> randomX
            PrecipitationSourceEdge.LEFT -> if (startFromSourceEdge) 0f else randomX
        }
    }

    private fun isOutOfFrame(particle: Particle): Boolean {
        return when (parameters.sourceEdge) {
            PrecipitationSourceEdge.TOP -> {
                particle.y > frameHeight || particle.x < 0 || particle.x > frameWidth
            }
            PrecipitationSourceEdge.RIGHT -> {
                val result = particle.y - particle.height > frameHeight ||
                        particle.y + particle.height < 0 ||
                        particle.x + particle.width < 0
                result
            }
            PrecipitationSourceEdge.BOTTOM -> {
                particle.y < 0 || particle.x < 0 || particle.x > frameWidth
            }
            PrecipitationSourceEdge.LEFT -> {
                particle.y - particle.height > frameHeight ||
                        particle.y + particle.height < 0 ||
                        particle.x - particle.width > frameWidth
            }
        }
    }

    private fun generateY(startFromSourceEdge: Boolean = false): Float {
        val randomY = Random.nextInt(frameHeight).toFloat()
        return when (parameters.sourceEdge) {
            PrecipitationSourceEdge.TOP -> if (startFromSourceEdge) 0f else randomY
            PrecipitationSourceEdge.RIGHT-> randomY
            PrecipitationSourceEdge.BOTTOM->  if (startFromSourceEdge) frameHeight.toFloat() else randomY
            PrecipitationSourceEdge.LEFT -> randomY

        }
    }
    private fun computeAngle(): Int {
        return if (parameters.minAngle == parameters.maxAngle) {
            parameters.maxAngle
        }           else {
            Random.nextInt(parameters.minAngle, parameters.maxAngle)
        }
    }

    private fun getRandomHeight(width: Float): Float {
        return when (parameters.shape) {
            is PrecipitationShape.Line -> Random.nextInt(
                parameters.shape.minHeight,
                parameters.shape.maxHeight
            ).toFloat()
        }
    }


    private fun getRandomWidth(): Float {
        return when (parameters.shape) {
            is PrecipitationShape.Line -> Random.nextInt(
                parameters.shape.minStrokeWidth,
                parameters.shape.maxStrokeWidth
            ).toFloat()
        }
    }
}