package com.brainx.cmp_base.presentation.screens.main_home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.brainx.cmp_base.presentation.navigation.AppRoutes
import com.brainx.cmp_base.presentation.theme.colors.LocalAppTheme
import com.brainx.utils_extensions.compose_ui_utils.safe_click.clickableSingleWithoutRipple

@Composable
fun MainHomeScreen(
    onNavigate:(AppRoutes)->Unit
) {
    MainContent()
}

@Composable
private fun MainContent(){
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var isKeyboardVisible by remember { mutableStateOf(false) }

    val keyboardHeight = WindowInsets.ime.getBottom(density = LocalDensity.current)

    val appThemeColor =  LocalAppTheme.current

    LaunchedEffect(key1 = keyboardHeight) {
        isKeyboardVisible = keyboardHeight > 0
    }

    Scaffold(
        modifier = Modifier.background(appThemeColor.background.backgroundColor)
            .fillMaxSize()
            .imePadding()
            .clickableSingleWithoutRipple {
                focusManager.clearFocus(force = true)
                keyboardController?.hide()
            }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().background(appThemeColor.background.backgroundColor),
            contentAlignment = Alignment.Center,
        ) {

        }

    }
}

