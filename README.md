<h1>Android Colors Converter Plugin</h1>
<h4>A simple plugin that allows you to convert a Color object to other popular formats (HEX, RGB, HSL, HSV, CMYK)</h4>
<h2>Usage</h2>
<ul>use the Color class from the library androidx.compose.ui.graphics.Color</ul>

```kotlin
import androidx.compose.ui.graphics.Color
```

<ul>create a Color object that you want to convert</ul>

```kotlin
val color = Color.Red
```

<ul>apply the necessary extension function to the object to convert the color format</ul>

```kotlin
color.toRgb() //return RGB(255, 0, 0)
```

- `.toRgb()` - Convert a color to an `RGB(R, G, B)` string with components scaled from 0 to 255.  
- `.toHex()` - Converts the color object to a `#RRGGBB` hexadecimal representation (and optionally supports an alpha channel).
  - **Parameter** `withAlpha` - If true, the alpha component of the color will be included in the hexadecimal string.  
- `.toHsl()` - Representation of color as hue, saturation and lightness in the `HSL(H°, S%, L%)` format.  
- `.toHsv()` - Convert color to hue, saturation, and brightness model in the `HSV(H°, S%, V%)` format.  
- `.toCmyk()` - Convert color to `CMYK(C%, M%, Y%, K%)` string format.  

<h2>Download</h2>
<p>Add the dependency in build.gradle file:</p>

```kotlin
implementation ("com.github.Dotingo:colors-converter:1.0.1")
```

<p>And add the repository in settings.gradle:</p>

```kotlin
repositories {
...
maven { url = uri("https://jitpack.io") }
...
}
```
