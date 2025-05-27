import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.Gray
import com.example.crmedumobile.presentation.theme.RegularMontserrat16
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat14
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat24


@Composable
fun PrimaryTextField(
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    value: String = "",
    placeholder: String? = null,
    supportText: String? = null,
    errorText: String? = null,
    maxQuantityOfChar: Int? = null,
    isMaxQuantityOfCharVisible: Boolean = true,
    maxLines: Int = 1,
    minLines: Int = 1,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    isError: Boolean = false,
    isOnlyNumbers: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: ImageVector? = null,
    trailingIconModifier: Modifier = Modifier,
    onTrailingIconClicked: () -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val fillMaxWidthModifier = Modifier.fillMaxWidth()
    val dimensions = LocalDimensions.current


    Column(modifier = modifier) {
        title?.let {
            Text(
                text = title,
                style = SemiBoldMontserrat24.copy(color = Black)
            )
            Spacer(modifier = Modifier.height(dimensions.fieldsSpacer))
        }
        BasicTextField(
            modifier = fillMaxWidthModifier,
            value = value,
            onValueChange = { text ->
                if (text.length <= (maxQuantityOfChar ?: (text.length + 1))) {
                    onTextChange(text)
                }
            },
            visualTransformation = visualTransformation,
            textStyle = SemiBoldMontserrat24.copy(
                color = Black,
                lineHeight = 24.sp
            ),
            readOnly = readOnly,
            maxLines = maxLines,
            minLines = minLines,
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isOnlyNumbers) {
                    KeyboardType.Number
                } else {
                    keyboardType
                }
            ),
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(dimensions.defaultCornerRadius))
                        .background(White)
                        .border(
                            width = dimensions.borderStrokeInputWidth,
                            color = DarkPurple,
                            shape = RoundedCornerShape(dimensions.defaultCornerRadius)
                        )
                        .padding(
                            top = dimensions.verticalTiny,
                            bottom = dimensions.verticalTiny,
                            start = dimensions.horizontalSmall,
                            end = dimensions.horizontalTiny
                        )
                        .requiredHeightIn(min = dimensions.verticalMedium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(dimensions.horizontalTiny)
                ) {
                    trailingIcon?.let { trailingIcon ->
                        IconButton(
                            modifier = trailingIconModifier.size(dimensions.iconDefaultSize),
                            onClick = onTrailingIconClicked
                        ) {
                            Icon(
                                imageVector = trailingIcon,
                                contentDescription = null,
                                tint = DarkPurple,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(5f)
                            .padding(end = dimensions.horizontalTiny)
                    ) {
                        if (value.isEmpty()) {
                            placeholder?.let { placeholderText ->
                                Text(
                                    text = placeholderText,
                                    style = RegularMontserrat16,
                                    color = Gray
                                )
                            }
                        }
                        innerTextField()
                    }
                }
            }
        )

        Row(
            modifier = fillMaxWidthModifier.padding(top = dimensions.fieldsSpacer)
        ) {
            if (isError) {
                errorText?.let { text ->
                    Spacer(Modifier.width(dimensions.fieldsSpacer))
                    Text(
                        modifier = fillMaxWidthModifier,
                        text = text,
                        style = SemiBoldMontserrat14,
                        color = Red,
                        textAlign = TextAlign.Start
                    )
                }
            } else {
                supportText?.let { text ->
                    Spacer(Modifier.width(dimensions.fieldsSpacer))
                    Text(
                        text = text,
                        style = SemiBoldMontserrat14,
                        color = DarkPurple,
                        textAlign = TextAlign.Left
                    )
                }
            }
            maxQuantityOfChar?.let {
                if (isMaxQuantityOfCharVisible) {
                    Text(
                        modifier = fillMaxWidthModifier,
                        text = stringResource(
                            id = R.string.max_count_char,
                            value.length,
                            maxQuantityOfChar
                        ),
                        style = SemiBoldMontserrat14,
                        color = DarkPurple,
                        textAlign = TextAlign.Right
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PrimaryTextFieldPreview() {
    PrimaryTextField(
        modifier = Modifier.fillMaxWidth(),
        title = "Title",
        value = "Value",
        onTextChange = {}
    )
}