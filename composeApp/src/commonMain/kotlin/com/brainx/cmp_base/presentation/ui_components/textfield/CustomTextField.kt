package com.brainx.cmp_base.presentation.ui_components.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.colors.LocalAppTheme
import com.brainx.cmp_base.presentation.theme.defaultEditTextShape
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import basecmp.composeapp.generated.resources.Res
import basecmp.composeapp.generated.resources.ic_search
import basecmp.composeapp.generated.resources.search_movies
import com.brainx.cmp_base.presentation.theme.AppTheme

@Composable
private fun CustomBasicTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    placeHolderText:String?=null,
    cursorBrush: Color = Color.White,
    textStyle: TextStyle = editTextStyle(),
    hintTextColor: Color?=null,
    hintTextStyle: TextStyle = editTextStyle(
        textColor = hintTextColor ?: Color.Gray
    ),
    maxLength:Int?=null,
    singleLine:Boolean=true,
    minLines:Int?=null,
    maxLines:Int?=null,
    enabled:Boolean=true,
    hintTextAlignment: Alignment = Alignment.Center,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onCursorPositionChange: ((Int) -> Unit)?=null,
    onValueChange: (String) -> Unit,
){

    // Holds the latest internal TextFieldValue state. We need to keep it to have the correct value
    // of the composition.
    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
                text = text, selection = when {
                    text.isEmpty() -> TextRange.Zero
                    else -> TextRange(text.length, text.length)
                }
            )
        )
    }

    // Holds the latest TextFieldValue that BasicTextField was recomposed with. We couldn't simply
    // pass `TextFieldValue(text = value)` to the CoreTextField because we need to preserve the
    // composition.
    val textFieldValue = textFieldValueState.copy(text = text)

    SideEffect {
        if (textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition
        ) {
            textFieldValueState = textFieldValue
        }
    }
    // Last String value that either text field was recomposed with or updated in the onValueChange
    // callback. We keep track of it to prevent calling onValueChange(String) for same String when
    // CoreTextField's onValueChange is called multiple times without recomposition in between.
    var lastTextValue by remember(text) { mutableStateOf(text) }

    BasicTextField(
        modifier= modifier,
        value = textFieldValue,
        cursorBrush = SolidColor(cursorBrush),
        enabled = enabled,
        onValueChange = { newTextFieldValueState ->
            if (maxLength!=null && newTextFieldValueState.text.length>maxLength) return@BasicTextField
            textFieldValueState = newTextFieldValueState

            val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValueState.text
            lastTextValue = newTextFieldValueState.text

            if (stringChangedSinceLastInvocation) {
                onValueChange(newTextFieldValueState.text)
            }
            if (onCursorPositionChange!=null)
                onCursorPositionChange(newTextFieldValueState.selection.start)

        },
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(
            capitalization = if (keyboardType == KeyboardType.Password) KeyboardCapitalization.None else KeyboardCapitalization.Sentences,
            autoCorrectEnabled = false,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines?: ExtConstants.IntegerConstants.ONE,
        maxLines = if (singleLine) ExtConstants.IntegerConstants.ONE else maxLines?:Int.MAX_VALUE,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = hintTextAlignment
            ) {
                if (text.isEmpty()) {
                    placeHolderText?.let {
                        Text(
                            modifier = Modifier.padding(AppDimens.Padding.zero),
                            text = it,
                            textAlign = TextAlign.Start,
                            style = hintTextStyle,
                        )
                    }
                }
            }
            innerTextField()
        })
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    placeHolderText:String?=null,
    cursorBrush: Color = Color.Black,
    textStyle: TextStyle = editTextStyle(),
    hintTextColor: Color?=null,
    hintTextStyle: TextStyle = editTextStyle(
        textColor = hintTextColor ?: Color.Gray
    ),
    maxLength:Int?=null,
    singleLine:Boolean=true,
    minLines:Int?=null,
    maxLines:Int?=null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
    paddingLeadingIconEnd: Dp = AppDimens.Padding.smallPadding,
    paddingTrailingIconStart: Dp = AppDimens.Padding.smallPadding,
    paddingTrailingIconEnd: Dp = AppDimens.Padding.smallPadding,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    onCursorPositionChange: ((Int) -> Unit)? = null,
    backgroundColor: Color? = null,
    shape: Shape? = null,
    borderColor: Color? = null,
    borderWidth: Dp? = null,
    contentPaddingStart: Dp = AppDimens.Padding.smallPadding,
    contentPaddingEnd: Dp = AppDimens.Padding.smallPadding,
    contentPaddingTop: Dp = AppDimens.Padding.smallPadding,
    contentPaddingBottom: Dp = AppDimens.Padding.smallPadding,
) {
    val hasBorder = borderColor != null && borderWidth != null && borderWidth > 0.dp
    val hasStyling = backgroundColor != null || shape != null || hasBorder
    val containerShape = when {
        shape != null -> shape
        hasStyling -> defaultEditTextShape()
        else -> null
    }
    val layoutModifier = Modifier
        .then(
            when {
                backgroundColor != null && containerShape != null ->
                    Modifier.background(backgroundColor, containerShape)
                else -> Modifier
            }
        )
        .then(
            when {
                hasBorder && containerShape != null ->
                    Modifier.border(borderWidth!!, borderColor!!, containerShape)
                else -> Modifier
            }
        )
        .then(if (containerShape != null) Modifier.clip(containerShape) else Modifier)
        .padding(
            start = contentPaddingStart,
            end = contentPaddingEnd,
            top = contentPaddingTop,
            bottom = contentPaddingBottom
        )
    Layout(
        modifier = modifier.then(layoutModifier),
        content = {
            if (leadingIcon != null) leadingIcon()
            Box(
                modifier = Modifier.then(
                    if (leadingIcon != null && trailingIcon != null){
                        Modifier.padding(
                            start = paddingLeadingIconEnd,
                            end = paddingTrailingIconStart
                        )
                    }else if (leadingIcon != null){
                        Modifier.padding(
                            start = paddingLeadingIconEnd
                        )
                    }else if (trailingIcon != null){
                        Modifier.padding(
                            start = AppDimens.Padding.smallPadding,
                            end = paddingTrailingIconStart
                        )
                    }else{
                        Modifier.padding(
                                horizontal = AppDimens.Padding.smallPadding,
                            )
                    }
                )
            ) {
                CustomBasicTextField(
                    text = text,
                    cursorBrush = cursorBrush,
                    textStyle = textStyle,
                    placeHolderText = placeHolderText,
                    hintTextColor = hintTextColor,
                    maxLength = maxLength,
                    keyboardType = keyboardType,
                    singleLine = singleLine,
                    minLines = minLines,
                    maxLines = maxLines,
                    hintTextStyle = hintTextStyle,
                    imeAction = imeAction,
                    hintTextAlignment = Alignment.TopStart,
                    keyboardActions = keyboardActions,
                    onValueChange = { onValueChange(it) },
                    onCursorPositionChange = onCursorPositionChange
                )
            }
            if (trailingIcon != null) {
                Box(modifier = Modifier.padding(end = paddingTrailingIconEnd)) {
                    trailingIcon()
                }
            }
        }
    ) { measurables, constraints ->
        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val leadingPlaceable = if (leadingIcon != null) measurables[0].measure(looseConstraints) else null
        val centerIndex = if (leadingIcon != null) 1 else 0
        val trailingPlaceable = if (trailingIcon != null) measurables[centerIndex + 1].measure(looseConstraints) else null
        val leadingWidth = leadingPlaceable?.width ?: 0
        val trailingWidth = trailingPlaceable?.width ?: 0
        val centerWidth = (constraints.maxWidth - leadingWidth - trailingWidth)
            .coerceAtLeast(0)
        val centerConstraints = constraints.copy(
            minWidth = centerWidth,
            maxWidth = centerWidth,
            minHeight = 0
        )
        val centerPlaceable = measurables[centerIndex].measure(centerConstraints)
        val height = maxOf(
            leadingPlaceable?.height ?: 0,
            centerPlaceable.height,
            trailingPlaceable?.height ?: 0
        ).coerceIn(constraints.minHeight, constraints.maxHeight)
        layout(constraints.maxWidth, height) {
            var x = 0
            leadingPlaceable?.placeRelative(x, (height - (leadingPlaceable.height)) / 2)
            x += leadingWidth
            centerPlaceable.placeRelative(x, (height - centerPlaceable.height) / 2)
            x += centerWidth
            trailingPlaceable?.placeRelative(x, (height - (trailingPlaceable.height)) / 2)
        }
    }
}
