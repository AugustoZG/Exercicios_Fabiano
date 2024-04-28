package com.senac.restaurante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorApp()
        }
    }
}

@Composable
fun TipCalculatorApp() {
    var totalBill by remember { mutableStateOf("") }
    var customTipPercentage by remember { mutableStateOf(18f) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Calculadora de gorjetas", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = totalBill,
                onValueChange = {
                    if (it.toDoubleOrNull() != null || it.isEmpty()) {
                        totalBill = it
                    }
                },
                label = { Text("Valor da conta") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Porcentagem customizada: ${customTipPercentage.toInt()}%", style = MaterialTheme.typography.titleLarge)
            Slider(
                value = customTipPercentage,
                onValueChange = { customTipPercentage = it },
                valueRange = 0f..30f,
                onValueChangeFinished = {
                    // Esta callback é chamada quando o usuário termina de ajustar o slider.
                },
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            val billAmount = totalBill.toDoubleOrNull() ?: 0.0
            val standardTip = billAmount * 0.15
            val customTip = billAmount * (customTipPercentage / 100)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Gorjeta padrão (15%): $${"%.2f".format(standardTip)}")
            Text("Gorjeta personalizada: $${"%.2f".format(customTip)}")
            Text("Total com gorjeta padrão: $${"%.2f".format(billAmount + standardTip)}")
            Text("Total com gorjeta personalizada: $${"%.2f".format(billAmount + customTip)}")

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                totalBill = ""
                customTipPercentage = 18f
            }) {
                Text("Limpar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorApp()
}