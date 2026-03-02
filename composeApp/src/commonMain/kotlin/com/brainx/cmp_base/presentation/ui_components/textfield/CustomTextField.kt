package com.brainx.cmp_base.presentation.ui_components.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.brainx.cmp_base.presentation.theme.LocalAppTheme
import com.brainx.cmp_base.presentation.theme.defaultEditTextShape
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import basecmp.composeapp.generated.resources.Res
import basecmp.composeapp.generated.resources.ic_search
import basecmp.composeapp.generated.resources.search_movies
import com.brainx.cmp_base.presentation.theme.AppTheme

@Composable
fun CustomBasicTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    placeHolderText:String?=null,
    cursorBrush: Color = Color.White,
    textStyle: TextStyle = editTextStyle(),
    hintTextColor: Color?=null,
    hintTextStyle: TextStyle = editTextStyle(
        textColor = hintTextColor ?: MaterialTheme.colorScheme.onSecondaryContainer
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
    cursorBrush: Color = Color.White,
    textStyle: TextStyle = editTextStyle(),
    hintTextColor: Color?=null,
    hintTextStyle: TextStyle = editTextStyle(
        textColor = hintTextColor ?: MaterialTheme.colorScheme.onSecondaryContainer
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
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    onCursorPositionChange: ((Int) -> Unit)?=null,
) {
    Layout(
        modifier = modifier.padding(
            vertical = AppDimens.Padding.xxSmallPadding,
            horizontal = AppDimens.Padding.xxSmallPadding
        ),
        content = {
            if (leadingIcon != null) leadingIcon()
            Box(
                modifier = Modifier.padding(
                    start = paddingLeadingIconEnd,
                    end = paddingTrailingIconStart
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
            if (trailingIcon != null) trailingIcon()
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

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    imeAction: ImeAction = ImeAction.Search,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit
){
    val appThemeColor =  LocalAppTheme.current
    val editTextModifier = modifier
        .height(AppDimens.EditText.searchBarHeight)
        .background(appThemeColor.mainEditTextColor, shape = defaultEditTextShape())
        .clip(defaultEditTextShape())
    CustomTextField(
        text = text,
        placeHolderText = stringResource(Res.string.search_movies),
        modifier = editTextModifier,
        hintTextColor = appThemeColor.primaryWhiteTextColor.copy(alpha = 0.3F),
        onValueChange = onValueChange,
        imeAction = imeAction,
        keyboardActions = keyboardActions,
        leadingIcon = {
            Image(
                modifier = Modifier.padding(start = AppDimens.Padding.smallPadding),
                painter = painterResource( Res.drawable.ic_search), contentDescription = ExtConstants.StringConstants.EMPTY )
        }
    )
}

// region — Grouped previews (all examples for comparison)

@Preview(showBackground = true, name = "BasicTextField — All")
@Composable
private fun PreviewCustomBasicTextField_All() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Empty", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomBasicTextField(text = "", placeHolderText = "Enter text…", onValueChange = { })
            Text("With text", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomBasicTextField(text = "Sample input text", placeHolderText = "Placeholder", onValueChange = { })
            Text("Disabled", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomBasicTextField(text = "Disabled field", placeHolderText = "Placeholder", enabled = false, onValueChange = { })
            Text("Multiline", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomBasicTextField(
                text = "Line one\nLine two\nLine three",
                placeHolderText = "Multiline…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "CustomTextField — All (states & icons)")
@Composable
private fun PreviewCustomTextField_All() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Empty", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(text = "", placeHolderText = "Type here…", onValueChange = { })
            Text("With text", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(text = "Some content", placeHolderText = "Placeholder", onValueChange = { })
            Text("Leading icon", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                text = "",
                placeHolderText = "Search…",
                onValueChange = { },
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(start = AppDimens.Padding.smallPadding),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
            Text("Trailing icon", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                text = "With trailing",
                placeHolderText = "Placeholder",
                onValueChange = { },
                trailingIcon = {
                    Image(
                        modifier = Modifier.padding(end = AppDimens.Padding.smallPadding),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
            Text("Both icons", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                text = "",
                placeHolderText = "Search movies…",
                onValueChange = { },
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(start = AppDimens.Padding.smallPadding),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                trailingIcon = {
                    Image(
                        modifier = Modifier.padding(end = AppDimens.Padding.smallPadding),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "CustomTextField — All (background, border, shapes)")
@Composable
private fun PreviewCustomTextField_BackgroundBorderShapes_All() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Background only", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        color = Color(0xFFE8E8E8),
                        shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius)
                    )
                    .clip(RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius))
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "",
                placeHolderText = "Background only…",
                onValueChange = { }
            )
            Text("Border only", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF2196F3),
                        shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius)
                    )
                    .clip(RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius))
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "Bordered",
                placeHolderText = "Border only…",
                onValueChange = { }
            )
            Text("Background + border", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFF9E9E9E), RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "",
                placeHolderText = "Background + border…",
                onValueChange = { }
            )
            Text("Rounded (large)", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = Color(0xFFE3F2FD), shape = RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "",
                placeHolderText = "Large rounded corners…",
                onValueChange = { }
            )
            Text("Cut corners", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = Color(0xFFFFF3E0), shape = CutCornerShape(12.dp))
                    .clip(CutCornerShape(12.dp))
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "",
                placeHolderText = "Cut corners…",
                onValueChange = { }
            )
            Text("Pill shape", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            val pillShape = RoundedCornerShape(28.dp)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(color = Color(0xFFE8F5E9), shape = pillShape)
                    .clip(pillShape)
                    .padding(horizontal = 20.dp, vertical = 4.dp),
                text = "",
                placeHolderText = "Pill shape…",
                onValueChange = { }
            )
            Text("Rectangle (sharp)", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            val rectShape = RoundedCornerShape(0.dp)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = Color(0xFFECEFF1), shape = rectShape)
                    .border(1.dp, Color(0xFF90A4AE), rectShape)
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "",
                placeHolderText = "Sharp rectangle…",
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "SearchBar — All")
@Composable
private fun PreviewSearchBar_All() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Empty", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            SearchBar(modifier = Modifier, text = "") { }
            Text("With text", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            SearchBar(modifier = Modifier, text = "action movie") { }
        }
    }
}

@Preview(showBackground = true, name = "Dark theme — All")
@Composable
private fun PreviewAll_DarkTheme() {
    AppTheme(darkTheme = true, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("BasicTextField", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomBasicTextField(text = "Dark theme", placeHolderText = "Placeholder", onValueChange = { })
            Text("CustomTextField", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(text = "Dark theme field", placeHolderText = "Placeholder", onValueChange = { })
            Text("SearchBar", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            SearchBar(modifier = Modifier, text = "") { }
        }
    }
}

// endregion
