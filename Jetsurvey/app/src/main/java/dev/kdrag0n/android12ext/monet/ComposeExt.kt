package dev.kdrag0n.android12ext.monet

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color as ComposeColor
import dev.kdrag0n.android12ext.monet.colors.Color
import dev.kdrag0n.android12ext.monet.theme.ColorScheme
import dev.kdrag0n.android12ext.monet.theme.ColorSwatch

fun Color.toComposeColor() = ComposeColor(toLinearSrgb().toSrgb().quantize8())

fun ColorSwatch.shade(shade: Int) = this[shade]!!.toComposeColor()

fun ColorScheme.toMaterialColors(isLight: Boolean) = if (isLight) {
/*
    val colorCache = HashMap<ColorSwatch, MutableMap<Int, ComposeColor>>()

    fun ColorSwatch.shade(shade: Int): ComposeColor {
        val swatchCache = colorCache[this] ?: HashMap<Int, ComposeColor>().also {
            colorCache[this] = it
        }

        return swatchCache[shade] ?: this[shade]!!.toComposeColor().also {
            swatchCache[shade] = it
        }
    }
*/

    Colors(
        primary = accent1.shade(600),
        primaryVariant = accent1.shade(600),
        secondary = accent3.shade(600),
        secondaryVariant = accent3.shade(600),
        background = neutral1.shade(50),
        surface = neutral1.shade(100),
        error = accent3.shade(600),
        onPrimary = neutral1.shade(900),
        onSecondary = neutral2.shade(700),
        onBackground = neutral1.shade(900),
        onSurface = neutral1.shade(900),
        onError = neutral1.shade(900),
        isLight = true,
    )
} else {
    Colors(
        primary = accent1.shade(100),
        primaryVariant = accent1.shade(100),
        secondary = accent3.shade(100),
        secondaryVariant = accent3.shade(100),
        background = neutral1.shade(900),
        surface = neutral1.shade(700),
        error = accent3.shade(100),
        onPrimary = neutral1.shade(50),
        onSecondary = neutral2.shade(50),
        onBackground = neutral1.shade(50),
        onSurface = neutral1.shade(0),
        onError = neutral1.shade(50),
        isLight = false,
    )
}
