package fi.oamk.bodymassindex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Bmi(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Bmi(modifier: Modifier = Modifier) {
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }

    val height = heightInput.replace(',', '.').toFloatOrNull() ?: 0.0f
    val weight = weightInput.replace(',', '.').toIntOrNull() ?: 0
    val formatter = DecimalFormat("0.00")
    val bmi = if (weight > 0 && height > 0) formatter.format(weight / (height * height)) else ""

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Body mass index",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        )
        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it },
            label = { Text("Height") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it },
            label = { Text("Weight") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        if (bmi.isNotEmpty()) {
            Text(
                text = "Body mass index is $bmi",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BmiPreview() {
    MaterialTheme {
        Bmi()
    }
}
