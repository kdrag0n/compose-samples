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

package com.example.compose.jetsurvey.theme

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import dev.kdrag0n.android12ext.monet.colors.Oklch
import dev.kdrag0n.android12ext.monet.theme.DynamicColorScheme
import dev.kdrag0n.android12ext.monet.theme.MaterialYouTargets
import dev.kdrag0n.android12ext.monet.toMaterialColors

val Colors.snackbarAction: Color
    @Composable
    get() = if (isLight) Purple300 else Purple700

val Colors.progressIndicatorBackground: Color
    @Composable
    get() = if (isLight) Color.Black.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.24f)

@Composable
fun JetsurveyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val hue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 360.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        )
    )

    val colorScheme = DynamicColorScheme(MaterialYouTargets(), Oklch(1.0, 0.2, hue.toDouble()))
/*
    val colors = if (darkTheme) {
        darkColors(
            primary = colorScheme.accent1.shade(300),
            primaryVariant = colorScheme.accent1.shade(600),
            onPrimary = colorScheme.neutral1.shade(900),
            secondary = colorScheme.neutral1.shade(900),
            onSecondary = colorScheme.neutral1.shade(50),
            background = colorScheme.neutral1.shade(900),
            onBackground = colorScheme.neutral1.shade(50),
            surface = colorScheme.neutral1.shade(900),
            onSurface = colorScheme.neutral1.shade(50),
            error = Red300,
            onError = colorScheme.neutral1.shade(900),
        )
    } else {
        lightColors(
            primary = colorScheme.accent1.shade(700),
            primaryVariant = colorScheme.accent1.shade(800),
            onPrimary = colorScheme.neutral1.shade(50),
            secondary = colorScheme.neutral1.shade(50),
            onSecondary = colorScheme.neutral1.shade(900),
            background = colorScheme.neutral1.shade(50),
            onBackground = colorScheme.neutral1.shade(900),
            surface = colorScheme.neutral1.shade(50),
            onSurface = colorScheme.neutral1.shade(900),
            error = Red800,
            onError = colorScheme.neutral1.shade(50),
        )
    }
*/
    MaterialTheme(
        colors = colorScheme.toMaterialColors(!darkTheme),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
