import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.usecase.MarkAttendanceByQr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrScanViewModel @Inject constructor(
    private val markAttendanceByQr: MarkAttendanceByQr
) : ViewModel() {

    private val _result = MutableStateFlow<String?>(null)
    val result: StateFlow<String?> = _result

    fun submitQr(qrPayload: String) {
        viewModelScope.launch {
            try {
                markAttendanceByQr(qrPayload)
                _result.value = "Посещение зафиксировано!"
            } catch (e: Exception) {
                _result.value = "Ошибка: ${e.message}"
            }
        }
    }
}
