import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    val horizontalTiny: Dp = 8.dp,
    val horizontalMedium: Dp = 33.dp,
    val defaultCornerRadius: Dp = 17.dp,
    val buttonCornerRadius: Dp = 17.dp,
    val cardCornerRadius: Dp = 20.dp,
    val horizontalDialog: Dp = 30.dp,
    val verticalDialog: Dp = 20.dp,
    val iconDefaultSize: Dp = 24.dp,
    val verticalTiny: Dp = 4.dp,
    val verticalSmall: Dp = 17.dp,
    val verticalMedium: Dp = 40.dp,
    val badgePadding: Dp = 10.dp,
    val horizontalSmall: Dp = 8.dp,
    val defaultPadding: Dp = 25.dp,
    val fieldsSpacer: Dp = 4.dp,
    val dropDownMenuMinHeight: Dp = 40.dp,
    val textCardPadding: Dp = 16.dp,
    val borderStrokeButtonWidth: Dp = 2.dp,
    val borderStrokeInputWidth: Dp = 1.dp,
    val defaultButtonHeight: Dp = 60.dp,
)

val LocalDimensions = compositionLocalOf { Dimensions() }