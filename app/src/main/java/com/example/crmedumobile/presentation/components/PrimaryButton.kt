import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crmedumobile.presentation.theme.BoldMontserrat20
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.Purple

@Composable
fun PrimaryButton(
    text: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null,
    isEnabled: Boolean = true
) {
    val dimensions = LocalDimensions.current

    Button(
        modifier = modifier.height(dimensions.defaultButtonHeight),
        enabled = isEnabled,
        onClick = onButtonClick,
        shape = RoundedCornerShape(dimensions.buttonCornerRadius),
        border = BorderStroke(width = dimensions.borderStrokeButtonWidth, DarkPurple),
        colors = ButtonDefaults.buttonColors(
            containerColor = Purple,
            contentColor = White,
            disabledContainerColor = DarkPurple.copy(alpha = 0.5f),
            disabledContentColor = White.copy(alpha = 0.5f)
        )
    ) {
        leftIcon?.let { icon ->
            Icon(
                imageVector = ImageVector.vectorResource(id = icon), contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(dimensions.horizontalTiny))
        Text(
            text = text,
            style = BoldMontserrat20,
            color = Black
        )
        Spacer(modifier = Modifier.width(dimensions.horizontalTiny))
        rightIcon?.let { icon ->
            Icon(
                imageVector = ImageVector.vectorResource(id = icon), contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Click",
        onButtonClick = { }
    )
}