package com.brainx.cmp_base.presentation.screens.main_home.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brainx.cmp_base.presentation.navigation.AppRoutes
import com.brainx.cmp_base.presentation.screens.main_home.ui_events.MainHomeScreenUiEvents
import com.brainx.cmp_base.presentation.screens.main_home.ui_state.MainHomeScreenUiState
import com.brainx.cmp_base.presentation.screens.main_home.ui_intents.MainHomeScreenUiIntents
import com.brainx.cmp_base.presentation.theme.LocalAppTheme
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.ui_components.button.PrimaryButton
import com.brainx.cmp_base.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.cmp_base.presentation.ui_components.textfield.SearchBar
import com.brainx.utils_extensions.compose_ui_utils.ConsumeUIEffects
import com.brainx.utils_extensions.compose_ui_utils.safe_click.clickableSingleWithoutRipple
import com.brainx.utils_extensions.constants.ExtConstants
import com.brainx.utils_extensions.navigation.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import basecmp.composeapp.generated.resources.Res
import basecmp.composeapp.generated.resources.search

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
        modifier = Modifier.background(appThemeColor.mainBackgroundColor)
            .fillMaxSize()
            .imePadding()
            .clickableSingleWithoutRipple {
                focusManager.clearFocus(force = true)
                keyboardController?.hide()
            }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().background(appThemeColor.mainBackgroundColor),
            contentAlignment = Alignment.Center,
        ) {

        }

    }
}

