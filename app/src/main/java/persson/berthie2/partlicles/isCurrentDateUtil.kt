package persson.berthie2.partlicles

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
internal fun isCurrentDateBetween(): Boolean {
    val now = LocalDate.now()
    return now.month == Month.MARCH && now.dayOfMonth in 1..10
}