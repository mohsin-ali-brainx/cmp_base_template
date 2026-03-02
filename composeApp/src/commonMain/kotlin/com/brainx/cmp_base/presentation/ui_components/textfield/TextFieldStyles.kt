package com.brainx.cmp_base.presentation.ui_components.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.colors.LocalAppTheme
import com.brainx.cmp_base.presentation.theme.appPrimaryFontFamily

@Composable
fun editTextStyle(
    fontSize: TextUnit = AppDimens.Fonts.font16,
    textColor: Color = LocalAppTheme.current.editText.tertiaryWhiteTextColor,
    textAlign: TextAlign = TextAlign.Start
)= TextStyle(
    textColor,
    fontWeight = FontWeight.Normal,
    fontSize = fontSize,
    fontFamily = appPrimaryFontFamily(),
    textAlign = textAlign)



