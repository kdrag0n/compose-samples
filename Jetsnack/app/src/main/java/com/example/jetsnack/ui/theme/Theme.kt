/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetsnack.ui.theme

import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.kdrag0n.android12ext.monet.colors.Oklch
import dev.kdrag0n.android12ext.monet.shade
import dev.kdrag0n.android12ext.monet.theme.DynamicColorScheme
import dev.kdrag0n.android12ext.monet.theme.MaterialYouTargets

private val targets = MaterialYouTargets()

@Composable
fun JetsnackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val hue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 360.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        )
    )

    val colorScheme = DynamicColorScheme(targets, Oklch(1.0, 0.2, hue.toDouble()))

    val colors = with(colorScheme) {
        if (darkTheme) {
            JetsnackColors(
                brand = Shadow1,
                brandSecondary = Ocean2,
                uiBackground = Neutral8,
                uiBorder = Neutral3,
                uiFloated = FunctionalDarkGrey,
                textPrimary = Shadow1,
                textSecondary = Neutral0,
                textHelp = Neutral1,
                textInteractive = Neutral7,
                textLink = Ocean2,
                iconPrimary = Shadow1,
                iconSecondary = Neutral0,
                iconInteractive = Neutral7,
                iconInteractiveInactive = Neutral6,
                error = FunctionalRedDark,
                gradient6_1 = listOf(Shadow5, Ocean7, Shadow9, Ocean7, Shadow5),
                gradient6_2 = listOf(Rose11, Lavender7, Rose8, Lavender7, Rose11),
                gradient3_1 = listOf(Shadow9, Ocean7, Shadow5),
                gradient3_2 = listOf(Rose8, Lavender7, Rose11),
                gradient2_1 = listOf(Ocean3, Shadow3),
                gradient2_2 = listOf(Ocean4, Shadow2),
                gradient2_3 = listOf(Lavender3, Rose3),
                tornado1 = listOf(Shadow4, Ocean3),
                isDark = true
            )
        } else {
            JetsnackColors(
                brand = accent1.shade(600),
                brandSecondary = accent3.shade(300),
                uiBackground = neutral1.shade(50),
                uiBorder = neutral1.shade(400),
                uiFloated = neutral1.shade(100),
                textSecondary = neutral1.shade(700),
                textHelp = neutral1.shade(600),
                textInteractive = neutral1.shade(50),
                textLink = accent3.shade(600),
                iconSecondary = neutral1.shade(700),
                iconInteractive = neutral1.shade(50),
                iconInteractiveInactive = neutral1.shade(100),
                error = accent3.shade(600),
                gradient6_1 = listOf(accent1.shade(400), accent3.shade(200), accent1.shade(100), accent3.shade(200), accent1.shade(400)),
                gradient6_2 = listOf(accent3.shade(400), accent1.shade(200), accent3.shade(100), accent1.shade(200), accent3.shade(400)),
                gradient3_1 = listOf(Shadow2, Ocean3, Shadow4),
                gradient3_2 = listOf(Rose2, Lavender3, Rose4),
                gradient2_1 = listOf(accent1.shade(300), accent1.shade(600)),
                gradient2_2 = listOf(accent3.shade(100), accent1.shade(300)),
                gradient2_3 = listOf(accent1.shade(300), accent1.shade(100)),
                tornado1 = listOf(accent1.shade(400), accent3.shade(200)),
                isDark = false
            )
        }
    }

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.uiBackground.copy(alpha = AlphaNearOpaque)
        )
    }

    ProvideJetsnackColors(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object JetsnackTheme {
    val colors: JetsnackColors
        @Composable
        get() = LocalJetsnackColors.current
}

/**
 * Jetsnack custom Color Palette
 */
@Stable
class JetsnackColors(
    gradient6_1: List<Color>,
    gradient6_2: List<Color>,
    gradient3_1: List<Color>,
    gradient3_2: List<Color>,
    gradient2_1: List<Color>,
    gradient2_2: List<Color>,
    gradient2_3: List<Color>,
    brand: Color,
    brandSecondary: Color,
    uiBackground: Color,
    uiBorder: Color,
    uiFloated: Color,
    interactivePrimary: List<Color> = gradient2_1,
    interactiveSecondary: List<Color> = gradient2_2,
    interactiveMask: List<Color> = gradient6_1,
    textPrimary: Color = brand,
    textSecondary: Color,
    textHelp: Color,
    textInteractive: Color,
    textLink: Color,
    tornado1: List<Color>,
    iconPrimary: Color = brand,
    iconSecondary: Color,
    iconInteractive: Color,
    iconInteractiveInactive: Color,
    error: Color,
    notificationBadge: Color = error,
    isDark: Boolean
) {
    var gradient6_1 by mutableStateOf(gradient6_1)
        private set
    var gradient6_2 by mutableStateOf(gradient6_2)
        private set
    var gradient3_1 by mutableStateOf(gradient3_1)
        private set
    var gradient3_2 by mutableStateOf(gradient3_2)
        private set
    var gradient2_1 by mutableStateOf(gradient2_1)
        private set
    var gradient2_2 by mutableStateOf(gradient2_2)
        private set
    var gradient2_3 by mutableStateOf(gradient2_3)
        private set
    var brand by mutableStateOf(brand)
        private set
    var brandSecondary by mutableStateOf(brandSecondary)
        private set
    var uiBackground by mutableStateOf(uiBackground)
        private set
    var uiBorder by mutableStateOf(uiBorder)
        private set
    var uiFloated by mutableStateOf(uiFloated)
        private set
    var interactivePrimary by mutableStateOf(interactivePrimary)
        private set
    var interactiveSecondary by mutableStateOf(interactiveSecondary)
        private set
    var interactiveMask by mutableStateOf(interactiveMask)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textHelp by mutableStateOf(textHelp)
        private set
    var textInteractive by mutableStateOf(textInteractive)
        private set
    var tornado1 by mutableStateOf(tornado1)
        private set
    var textLink by mutableStateOf(textLink)
        private set
    var iconPrimary by mutableStateOf(iconPrimary)
        private set
    var iconSecondary by mutableStateOf(iconSecondary)
        private set
    var iconInteractive by mutableStateOf(iconInteractive)
        private set
    var iconInteractiveInactive by mutableStateOf(iconInteractiveInactive)
        private set
    var error by mutableStateOf(error)
        private set
    var notificationBadge by mutableStateOf(notificationBadge)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun update(other: JetsnackColors) {
        gradient6_1 = other.gradient6_1
        gradient6_2 = other.gradient6_2
        gradient3_1 = other.gradient3_1
        gradient3_2 = other.gradient3_2
        gradient2_1 = other.gradient2_1
        gradient2_2 = other.gradient2_2
        gradient2_3 = other.gradient2_3
        brand = other.brand
        brandSecondary = other.brandSecondary
        uiBackground = other.uiBackground
        uiBorder = other.uiBorder
        uiFloated = other.uiFloated
        interactivePrimary = other.interactivePrimary
        interactiveSecondary = other.interactiveSecondary
        interactiveMask = other.interactiveMask
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textHelp = other.textHelp
        textInteractive = other.textInteractive
        textLink = other.textLink
        tornado1 = other.tornado1
        iconPrimary = other.iconPrimary
        iconSecondary = other.iconSecondary
        iconInteractive = other.iconInteractive
        iconInteractiveInactive = other.iconInteractiveInactive
        error = other.error
        notificationBadge = other.notificationBadge
        isDark = other.isDark
    }
}

@Composable
fun ProvideJetsnackColors(
    colors: JetsnackColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalJetsnackColors provides colorPalette, content = content)
}

private val LocalJetsnackColors = staticCompositionLocalOf<JetsnackColors> {
    error("No JetsnackColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [JetsnackTheme.colors].
 */
fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)
