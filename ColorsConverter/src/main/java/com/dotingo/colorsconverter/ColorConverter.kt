package com.dotingo.colorsconverter


import androidx.compose.ui.graphics.Color
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Converts the Color object to a hexadecimal color code in the format "#RRGGBB".
 *
 * Each component (red, green, and blue) is scaled from \[0.0, 1.0\] to \[0, 255\] and then converted to a hexadecimal string.
 * The resulting string represents the color in the standard hexadecimal format used in web and UI design.
 * @param withAlpha If true, the alpha component of the color will be included in the hexadecimal string.
 * @return a string in the format "#RRGGBB" where RR, GG, and BB are the red, green, and blue components in hexadecimal.
 * If withAlpha is true, in the format "#AARRGGBB" where AA is the alpha component.
 */
fun Color.toHex(withAlpha: Boolean = false): String {
    val (red, green, blue) = this.rgbTo255()
    return if (withAlpha) {
        val alpha = (this.alpha * 255).roundToInt()
        String.format("#%02X%02X%02X%02X", alpha, red, green, blue)
    } else {
        String.format("#%02X%02X%02X", red, green, blue)
    }
}

/**
 * Converts the Color object to an RGB string in the format "RGB(R, G, B)".
 * The red, green, and blue values are scaled from \[0.0, 1.0\] to \[0, 255\].
 *
 * @return a string in the format "RGB(R, G, B)" where R, G, and B are the red, green, and blue components.
 */
fun Color.toRgb(): String {
    val (red, green, blue) = this.rgbTo255()
    return "RGB($red, $green, $blue)"
}

/**
 * Converts the Color object to an HSL (Hue, Saturation, Lightness) string in the format "HSL(H°, S%, L%)".
 *
 * The hue (H) is calculated in degrees and represents the color angle on the color wheel.
 * The saturation (S) and lightness (L) are percentages representing color intensity and brightness, respectively.
 *
 * @return a string in the format "HSL(H°, S%, L%)" where:
 * - H is the hue, measured in degrees \[0, 360).
 * - S is the saturation, a percentage \[0, 100\].
 * - L is the lightness, a percentage \[0, 100\].
 */
fun Color.toHsl(): String {
    val r = this.red
    val g = this.green
    val b = this.blue

    val max = max(r, max(g, b))
    val min = min(r, min(g, b))
    val delta = max - min

    // Lightness
    val l = (max + min) / 2

    // Saturation
    val s = if (delta == 0f) {
        0f
    } else {
        delta / (1f - abs(2f * l - 1f))
    }

    // Hue
    val h = when {
        delta == 0f -> 0f
        max == r -> ((g - b) / delta) % 6f
        max == g -> ((b - r) / delta) + 2f
        else -> ((r - g) / delta) + 4f
    } * 60f

    val hue = if (h < 0) h + 360f else h
    val saturation = s * 100
    val lightness = l * 100

    return "HSL(${hue.roundToInt()}°, ${saturation.roundToInt()}%, ${lightness.roundToInt()}%)"
}

/**
 * Converts the Color object to an HSV (Hue, Saturation, Value) string in the format "HSV(H°, S%, V%)".
 *
 * The hue (H) is calculated in degrees, representing the angle on the color wheel.
 * The saturation (S) is a percentage representing the intensity of the color.
 * The value (V) is a percentage representing the brightness of the color.
 *
 * @return a string in the format "HSV(H°, S%, V%)" where:
 * - H is the hue, measured in degrees \[0, 360).
 * - S is the saturation, a percentage \[0, 100\].
 * - V is the value (brightness), a percentage \[0, 100\].
 */
fun Color.toHsv(): String {
    val r = this.red
    val g = this.green
    val b = this.blue

    val max = max(r, max(g, b))
    val min = min(r, min(g, b))
    val delta = max - min

    // Value (V)
    val v = max

    // Saturation (S)
    val s = if (max == 0f) {
        0f
    } else {
        delta / max
    }

    // Hue (H)
    val h = when {
        delta == 0f -> 0f
        max == r -> ((g - b) / delta) % 6f
        max == g -> ((b - r) / delta) + 2f
        else -> ((r - g) / delta) + 4f
    } * 60f

    val hue = if (h < 0) h + 360f else h
    val saturation = s * 100
    val value = v * 100

    return "HSV(${hue.roundToInt()}°, ${saturation.roundToInt()}%, ${value.roundToInt()}%)"
}

/**
 * Converts the Color object to a CMYK (Cyan, Magenta, Yellow, Black) string in the format "CMYK(C%, M%, Y%, K%)".
 *
 * The CMYK model is primarily used in printing, where colors are expressed as percentages of cyan, magenta, yellow, and black.
 * The black (K) component is determined first, and the other components (C, M, Y) are calculated relative to the black value.
 *
 * @return a string in the format "CMYK(C%, M%, Y%, K%)" where:
 * - C is the cyan percentage \[0, 100\].
 * - M is the magenta percentage \[0, 100\].
 * - Y is the yellow percentage \[0, 100\].
 * - K is the black percentage \[0, 100\].
 */
fun Color.toCmyk(): String {
    val r = this.red
    val g = this.green
    val b = this.blue

    // K (Black)
    val k = 1f - max(r, max(g, b))

    // if K = 1, то color black
    val c = if (k == 1f) 0f else (1f - r - k) / (1f - k)
    val m = if (k == 1f) 0f else (1f - g - k) / (1f - k)
    val y = if (k == 1f) 0f else (1f - b - k) / (1f - k)

    val cyan = (c * 100).roundToInt()
    val magenta = (m * 100).roundToInt()
    val yellow = (y * 100).roundToInt()
    val black = (k * 100).roundToInt()

    return "CMYK($cyan%, $magenta%, $yellow%, $black%)"
}


private fun Color.rgbTo255(): Triple<Int, Int, Int> {
    val red = (this.red * 255).roundToInt()
    val green = (this.green * 255).roundToInt()
    val blue = (this.blue * 255).roundToInt()
    return Triple(red, green, blue)
}