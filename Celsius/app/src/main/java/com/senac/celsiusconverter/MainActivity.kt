package com.senac.celsiusconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatureConverterApp()
        }
    }
}

@Composable
fun TemperatureConverterApp() {
    var temperatureInCelsius by remember { mutableStateOf("") }
    var temperatureInFahrenheit by remember { mutableStateOf("") }
    var lastEdited by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = temperatureInCelsius,
                onValueChange = { value ->
                    if (value.toDoubleOrNull() != null || value.isEmpty()) {
                        temperatureInCelsius = value
                        if (lastEdited != "Fahrenheit") {
                            temperatureInFahrenheit = ((value.toDoubleOrNull() ?: 0.0) * 1.8 + 32).toString()
                        }
                        lastEdited = "Celsius"
                    }
                },
                label = { Text("Celsius") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = temperatureInFahrenheit,
                onValueChange = { value ->
                    if (value.toDoubleOrNull() != null || value.isEmpty()) {
                        temperatureInFahrenheit = value
                        if (lastEdited != "Celsius") {
                            temperatureInCelsius = (((value.toDoubleOrNull() ?: 0.0) - 32) / 1.8).toString()
                        }
                        lastEdited = "Fahrenheit"
                    }
                },
                label = { Text("Fahrenheit") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        temperatureInCelsius = ""
                        temperatureInFahrenheit = ""
                        lastEdited = ""
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Limpar")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        if (lastEdited == "Celsius") {
                            temperatureInFahrenheit = ((temperatureInCelsius.toDoubleOrNull() ?: 0.0) * 1.8 + 32).toString()
                        } else {
                            temperatureInCelsius = (((temperatureInFahrenheit.toDoubleOrNull() ?: 0.0) - 32) / 1.8).toString()
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Converter")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TemperatureConverterApp()
}