package persson.berthie2.partlicles.rainparticles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {


    private val _particleAnimationIteration = MutableStateFlow(0L)
    val particleAnimationIteration: StateFlow<Long> = _particleAnimationIteration

    init {
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                _particleAnimationIteration.value++
                delay(1L)
            }
        }
    }
}