package com.pajprod.agecounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.Period

// MainActivity class that extends ComponentActivity
class MainActivity : ComponentActivity() {
    // Override the onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content to AgeCalculatorUI composable function
        setContent {
            AgeCalculatorUI()
        }
    }
}

// Composable function for the Age Calculator UI
@Composable
fun AgeCalculatorUI() {
    // State variables for year, month, day, and result
    var year by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Column layout for the UI elements
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // OutlinedTextField for year input
        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Year") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        // OutlinedTextField for month input
        OutlinedTextField(
            value = month,
            onValueChange = { month = it },
            label = { Text("Month") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        // OutlinedTextField for day input
        OutlinedTextField(
            value = day,
            onValueChange = { day = it },
            label = { Text("Day") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Button to calculate age
        Button(
            onClick = {
                result = calculateAge(year.toIntOrNull(), month.toIntOrNull(), day.toIntOrNull())
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Calculate Age")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Display the result
        ResultText(result)
    }
}

// Composable function to display the result
@Composable
fun ResultText(result: String) {
    Text(text = result)
}

// Function to calculate age based on year, month, and day
fun calculateAge(year: Int?, month: Int?, day: Int?): String {
    if (year == null || month == null || day == null) {
        return "Invalid input"
    }

    val today = LocalDate.now()
    val birthDate = LocalDate.of(year, month, day)

    return if (birthDate.isAfter(today)) {
        "Not born yet"
    } else {
        val age = Period.between(birthDate, today).years
        "Person is $age years old"
    }
}