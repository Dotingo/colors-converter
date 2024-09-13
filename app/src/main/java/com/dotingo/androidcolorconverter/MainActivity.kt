package com.dotingo.androidcolorconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dotingo.androidcolorconverter.ui.theme.AndroidColorConverterTheme
import com.dotingo.colorsconverter.toCmyk
import com.dotingo.colorsconverter.toHex
import com.dotingo.colorsconverter.toHsl
import com.dotingo.colorsconverter.toHsv
import com.dotingo.colorsconverter.toRgb
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidColorConverterTheme {
                var randomColor by remember { mutableStateOf(Color.Unspecified) }

                Card(
                    modifier = Modifier.fillMaxSize(),
                    colors = CardDefaults.cardColors(containerColor = randomColor),
                    onClick = {
                        randomColor = Color(
                            red = Random.nextFloat(),
                            green = Random.nextFloat(),
                            blue = Random.nextFloat(),
                            alpha = Random.nextFloat()
                        )
                    }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "RGB(${randomColor.toRgb()})")
                        Text(text = "HEX(${randomColor.toHex()})")
                        Text(text = "alpha HEX(${randomColor.toHex(true)})")
                        Text(text = "HSL(${randomColor.toHsl()})")
                        Text(text = "HSV(${randomColor.toHsv()})")
                        Text(text = "CMYK(${randomColor.toCmyk()})")
                    }
                }
            }
        }
    }
}
