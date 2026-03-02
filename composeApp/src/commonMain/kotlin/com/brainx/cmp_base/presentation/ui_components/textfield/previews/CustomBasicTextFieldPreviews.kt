package com.brainx.cmp_base.presentation.ui_components.textfield.previews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import basecmp.composeapp.generated.resources.Res
import basecmp.composeapp.generated.resources.ic_search
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.AppTheme
import com.brainx.cmp_base.presentation.ui_components.textfield.CustomTextField
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.painterResource

// region — Grouped previews (all examples for comparison)

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
                ,
                text = "",
                placeHolderText = "Background only…",
                backgroundColor = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius),
                onValueChange = { }
            )

            Text("Background with Leading Icon only", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "",
                placeHolderText = "Background only…",
                backgroundColor = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(start = AppDimens.Padding.smallPadding),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onValueChange = { },
            )

            Text("Background with Trailing Icon", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                ,
                text = "",
                placeHolderText = "Background only…",
                backgroundColor = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius),
                trailingIcon = {
                    Image(
                        modifier = Modifier.padding(start = AppDimens.Padding.smallPadding),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onValueChange = { },
            )
            Text("Border only", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "Bordered",
                placeHolderText = "Border only…",
                borderColor = Color(0xFF2196F3),
                borderWidth = 2.dp,
                shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius),
                onValueChange = { }
            )
            Text("Background + border", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "",
                placeHolderText = "Background + border…",
                backgroundColor = Color(0xFFF5F5F5),
                borderColor = Color(0xFF9E9E9E),
                borderWidth = 1.dp,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { }
            )
            Text("Rounded (large)", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                ,
                text = "",
                placeHolderText = "Large rounded corners…",
                backgroundColor = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(24.dp),
                onValueChange = { }
            )
            Text("Cut corners", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "",
                placeHolderText = "Cut corners…",
                backgroundColor = Color(0xFFFFF3E0),
                shape = CutCornerShape(12.dp),
                onValueChange = { }
            )
            Text("Pill shape", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            val pillShape = RoundedCornerShape(28.dp)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                ,
                text = "",
                placeHolderText = "Pill shape…",
                backgroundColor = Color(0xFFE8F5E9),
                shape = pillShape,
                onValueChange = { },
            )
            Text("Rectangle (sharp)", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            val rectShape = RoundedCornerShape(0.dp)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(AppDimens.Padding.xxSmallPadding),
                text = "",
                placeHolderText = "Sharp rectangle…",
                backgroundColor = Color(0xFFECEFF1),
                shape = rectShape,
                borderColor = Color(0xFF90A4AE),
                borderWidth = 1.dp,
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Multiline — All (background, border, shapes)")
@Composable
private fun PreviewCustomTextField_Multiline_BackgroundBorderShapes_All() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val multilineSample = "First line of text.\nSecond line here.\nThird line for demo."
            val multilineHeight = 100.dp
            val multilineShape = RoundedCornerShape(12.dp)

            Text("Multiline + background", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(multilineHeight)
                ,
                text = multilineSample,
                placeHolderText = "Multiline with background…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                backgroundColor = Color(0xFFE8E8E8),
                shape = multilineShape,
                onValueChange = { }
            )

            Text("Multiline + border", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(multilineHeight),
                text = "",
                placeHolderText = "Multiline with border…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                borderColor = Color(0xFF2196F3),
                borderWidth = 2.dp,
                shape = multilineShape,
                onValueChange = { }
            )

            Text("Multiline + background + border", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(multilineHeight)
                ,
                text = multilineSample,
                placeHolderText = "Background + border…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                backgroundColor = Color(0xFFF5F5F5),
                borderColor = Color(0xFF9E9E9E),
                borderWidth = 1.dp,
                shape = multilineShape,
                onValueChange = { }
            )

            Text("Multiline + rounded (large)", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            val roundedShape = RoundedCornerShape(20.dp)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                ,
                text = "",
                placeHolderText = "Large rounded multiline…",
                singleLine = false,
                minLines = 3,
                maxLines = 6,
                backgroundColor = Color(0xFFE3F2FD),
                shape = roundedShape,
                onValueChange = { }
            )

            Text("Multiline + cut corners", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            val cutShape = CutCornerShape(16.dp)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(multilineHeight)
//                    .padding(12.dp)
                ,
                text = multilineSample,
                placeHolderText = "Cut corner multiline…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                backgroundColor = Color(0xFFFFF3E0),
                shape = cutShape,
                onValueChange = { }
            )

            Text("Multiline + rectangle + border", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            val rectShape = RoundedCornerShape(0.dp)
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                ,
                text = "",
                placeHolderText = "Sharp rectangle multiline…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                backgroundColor = Color(0xFFECEFF1),
                shape = rectShape,
                borderColor = Color(0xFF90A4AE),
                borderWidth = 1.dp,
                onValueChange = { }
            )
        }
    }
}

// endregion

// region — Previews (using Background, Shape, Border params)

@Preview(showBackground = true, name = "CustomTextField — Params (background, shape, border)")
@Composable
private fun PreviewCustomTextField_WithParams() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Default", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(text = "", placeHolderText = "No styling…", onValueChange = { })

            Text("Background only", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier.height(56.dp),
                text = "",
                placeHolderText = "Background only…",
                onValueChange = { },
                backgroundColor = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius)
            )

            Text("Border only", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier.height(56.dp),
                text = "Bordered",
                placeHolderText = "Border only…",
                onValueChange = { },
                shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius),
                borderColor = Color(0xFF2196F3),
                borderWidth = 2.dp
            )

            Text("Background + border", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier.height(56.dp),
                text = "",
                placeHolderText = "Background + border…",
                onValueChange = { },
                backgroundColor = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(12.dp),
                borderColor = Color(0xFF9E9E9E),
                borderWidth = 1.dp
            )

            Text("Rounded (large)", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier.height(56.dp),
                text = "",
                placeHolderText = "Large rounded…",
                onValueChange = { },
                backgroundColor = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(24.dp)
            )

            Text("Cut corners", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier.height(56.dp),
                text = "",
                placeHolderText = "Cut corners…",
                onValueChange = { },
                backgroundColor = Color(0xFFFFF3E0),
                shape = CutCornerShape(12.dp)
            )

            Text("Rectangle + border", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CustomTextField(
                modifier = Modifier.height(56.dp),
                text = "",
                placeHolderText = "Sharp rectangle…",
                onValueChange = { },
                backgroundColor = Color(0xFFECEFF1),
                shape = RoundedCornerShape(0.dp),
                borderColor = Color(0xFF90A4AE),
                borderWidth = 1.dp
            )
        }
    }
}

// endregion
