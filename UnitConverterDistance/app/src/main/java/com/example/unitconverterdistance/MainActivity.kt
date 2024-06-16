package com.example.unitconverterdistance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverterdistance.ui.theme.UnitConverterDistanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            UnitConverterDistanceTheme {
                UnitConverter()
            }
        }
    }
}


@Composable
fun UnitConverter() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var inputText by remember { mutableStateOf("0")}
        var textUnit1 by remember { mutableStateOf("Select Unit 1")}
        var textUnit2 by remember { mutableStateOf("Select Unit 2")}
        var expand1 by remember { mutableStateOf(false)}
        var expand2 by remember { mutableStateOf(false)}
        var inpConversion by remember { mutableStateOf(-1.0)}
        var outConversion by remember { mutableStateOf(-1.0)}
        var output by remember { mutableStateOf("0")}

        fun conversion(){
            var num = inputText.toDoubleOrNull() ?: 0.0
            output = (num * (inpConversion/outConversion)).toString()
        }
        Text("Unit Converter")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = inputText, onValueChange = {
            inputText =  it
            conversion()
        })

        Spacer(modifier = Modifier.height(16.dp))

        Row() {
            Box {
                Button(onClick = {
                    expand1 = !expand1;
                }) {
                    Text(text  = textUnit1)
                    Icon(Icons.Default.ArrowDropDown , contentDescription ="icon" )

                }
                
                DropdownMenu(expanded = expand1, onDismissRequest = {
                    expand1 = false;
                }) {
                    DropdownMenuItem(text = {Text("-")}, onClick = {
                        expand1 = false
                        inpConversion = -1.0
                        textUnit1 = "Select Unit 1"
                        conversion()
                    })
                    DropdownMenuItem(text = {Text("cm")}, onClick = {
                        expand1 = false
                        inpConversion = 0.01
                        textUnit1 = "cm"
                        conversion()
                    })
                    DropdownMenuItem(text = {Text("m")}, onClick = {
                        expand1 = false
                        inpConversion = 1.0
                        textUnit1 = "m"
                        conversion()
                    })
                    DropdownMenuItem(text = {Text("km")}, onClick = {
                        expand1 = false
                        inpConversion = 1000.0
                        textUnit1 = "km"
                        conversion()
                    })
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box {
                Button(onClick = {
                    expand2 = !expand2;
                }) {
                    Text(text  = textUnit2)
                    Icon(Icons.Default.ArrowDropDown , contentDescription ="icon" )
                    
                }

                DropdownMenu(expanded = expand2, onDismissRequest = {
                    expand2 = false;
                }) {
                    DropdownMenuItem(text = {Text("-")}, onClick = {
                        expand2 = false
                        outConversion = -1.0
                        textUnit2 = "Select Unit 2"
                        conversion()
                    })
                    DropdownMenuItem(text = {Text("cm")}, onClick = {
                        expand2 = false
                        outConversion = 0.01
                        textUnit2 = "cm"
                        conversion()
                    })
                    DropdownMenuItem(text = {Text("m")}, onClick = {
                        expand2 = false
                        outConversion = 1.0
                        textUnit2 = "m"
                        conversion()
                    })
                    DropdownMenuItem(text = {Text("km")}, onClick = {
                        expand2 = false
                        outConversion = 1000.0
                        textUnit2 = "km"
                        conversion()
                    })
                }
            }


        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text("Result:")

            Spacer(modifier = Modifier.width(8.dp))

            if(inpConversion == -1.0 || outConversion == -1.0){
                Text("Please select appropriate units")
            }
            else{
                Text(output)
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}


