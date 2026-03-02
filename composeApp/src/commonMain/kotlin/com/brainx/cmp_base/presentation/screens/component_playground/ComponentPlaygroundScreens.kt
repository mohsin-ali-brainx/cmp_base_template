package com.brainx.cmp_base.presentation.screens.component_playground

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import basecmp.composeapp.generated.resources.Res
import basecmp.composeapp.generated.resources.email
import basecmp.composeapp.generated.resources.ic_search
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.AppTheme
import com.brainx.cmp_base.presentation.theme.colors.LocalAppTheme
import com.brainx.cmp_base.presentation.ui_components.button.CustomButton
import com.brainx.cmp_base.presentation.ui_components.button.defaultFullWidthButtonModifier
import com.brainx.cmp_base.presentation.ui_components.button.defaultWrapContentButtonModifier
import com.brainx.cmp_base.presentation.ui_components.text.CustomText
import com.brainx.cmp_base.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.cmp_base.presentation.ui_components.text_fields.basic_text_field.CustomTextField
import com.brainx.cmp_base.presentation.ui_components.text_fields.underline_text_field.CustomBasicUnderlineTextField
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

private val ScreenPadding = 16.dp

@OptIn(ExperimentalResourceApi::class)
private val labelEmail: StringResource = Res.string.email

@Composable
fun ComponentButtonsScreen() {
    val appTheme = LocalAppTheme.current

    var isLoading by remember { mutableStateOf(false) }
    var clickCount by remember { mutableStateOf(0) }

    AppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.background.backgroundColor)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appTheme.background.backgroundColor)
                    .padding(paddingValues)
                    .padding(ScreenPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = CustomTextToDisplay.StringText("Buttons playground"),
                    fontSize = AppDimens.Fonts.font20,
                    color = appTheme.textView.primaryBlackTextColor
                )

                CustomButton(
                    modifier = Modifier.defaultFullWidthButtonModifier(),
                    buttonText = CustomTextToDisplay.StringText("Primary button (clicks: $clickCount)"),
                    buttonColor = appTheme.button.primaryColor,
                    textColor = appTheme.button.secondaryWhiteTextColor,
                    showLoader = isLoading,
                    onClickAction = {
                        clickCount++
                        isLoading = !isLoading
                    }
                )

                CustomButton(
                    modifier = Modifier.defaultWrapContentButtonModifier(),
                    buttonText = CustomTextToDisplay.StringText("Gradient background"),
                    buttonBrush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFE3F2FD),
                            Color(0xFFBBDEFB),
                            Color(0xFF90CAF9)
                        )
                    ),
                    textColor = Color.Black,
                    onClickAction = { }
                )

                CustomButton(
                    modifier = Modifier.defaultFullWidthButtonModifier(),
                    buttonText = CustomTextToDisplay.StringText("Gradient border"),
                    buttonColor = appTheme.background.whiteColor,
                    textColor = appTheme.textView.primaryBlackTextColor,
                    borderBrush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFBBDEFB),
                            Color(0xFF90CAF9),
                            Color(0xFF64B5F6)
                        )
                    ),
                    borderWidth = 2.dp,
                    onClickAction = { }
                )
            }
        }
    }
}

@Composable
fun ComponentTextFieldsScreen() {
    val appTheme = LocalAppTheme.current

    var searchText by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.background.backgroundColor)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appTheme.background.backgroundColor)
                    .padding(paddingValues)
                    .padding(ScreenPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = CustomTextToDisplay.StringText("Filled text fields"),
                    fontSize = AppDimens.Fonts.font20,
                    color = appTheme.textView.primaryBlackTextColor
                )

                CustomTextField(
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    text = searchText,
                    contentPaddingStart = AppDimens.Padding.padding8,
                    contentPaddingEnd = AppDimens.Padding.padding8,
                    placeHolderText = "Search movies",
                    leadingIcon = {
                        Image(
                            painter = painterResource(Res.drawable.ic_search),
                            contentDescription = ExtConstants.StringConstants.EMPTY
                        )
                    },
                    backgroundBrush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFE3F2FD),
                            Color(0xFFBBDEFB),
                            Color(0xFF90CAF9)
                        )
                    ),
                    onValueChange = { searchText = it }
                )

                CustomTextField(
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    text = password,
                    placeHolderText = "Password",
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    backgroundBrush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFE3F2FD),
                            Color(0xFFBBDEFB),
                            Color(0xFF90CAF9)
                        )
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = { password = it }
                )
            }
        }
    }
}

@Composable
fun ComponentUnderlineTextFieldsScreen() {
    val appTheme = LocalAppTheme.current

    var email by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val isEmailValid = remember(email) {
        email.contains("@") && email.contains(".")
    }

    AppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.background.backgroundColor)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appTheme.background.backgroundColor)
                    .padding(paddingValues)
                    .padding(ScreenPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = CustomTextToDisplay.StringText("Underline text fields"),
                    fontSize = AppDimens.Fonts.font20,
                    color = appTheme.textView.primaryBlackTextColor
                )

                CustomBasicUnderlineTextField(
                    text = email,
                    label = labelEmail,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    isValid = isEmailValid,
                    supportText = if (isEmailValid) {
                        CustomTextToDisplay.StringText("Valid email")
                    } else {
                        CustomTextToDisplay.StringText("Enter a valid email address")
                    },
                    onValueChange = { email = it }
                )

            }
        }
    }
}

@Composable
fun ComponentTextScreen() {
    val appTheme = LocalAppTheme.current

    var useGradient by remember { mutableStateOf(true) }

    AppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.background.backgroundColor)
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appTheme.background.backgroundColor)
                    .padding(paddingValues)
                    .padding(ScreenPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (useGradient) {
                        CustomText(
                            modifier = Modifier.fillMaxWidth(),
                            text = CustomTextToDisplay.StringText("Gradient text"),
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFE53935),
                                    Color(0xFF1E88E5)
                                )
                            ),
                            fontSize = AppDimens.Fonts.font24
                        )
                    } else {
                        CustomText(
                            modifier = Modifier.fillMaxWidth(),
                            text = CustomTextToDisplay.StringText("Solid text"),
                            color = appTheme.textView.blueTextColor,
                            fontSize = AppDimens.Fonts.font24
                        )
                    }

                    CustomButton(
                        modifier = Modifier.defaultWrapContentButtonModifier(),
                        buttonText = CustomTextToDisplay.StringText("Toggle style"),
                        buttonColor = appTheme.button.primaryColor,
                        textColor = appTheme.button.secondaryWhiteTextColor,
                        onClickAction = { useGradient = !useGradient }
                    )
                }
            }
        }
    }
}

