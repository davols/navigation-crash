package com.example.crashexample

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.crashexample.ui.theme.CrashExampleTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrashExampleTheme {
                val backStack = remember { mutableStateListOf("home") }

                NavDisplay(
                    modifier= Modifier.safeDrawingPadding(),
                    backStack = backStack,
                    onBack = { backStack.removeAt(backStack.lastIndex) },
                    entryProvider = entryProvider {
                        entry("home") {
                            Button(onClick = { backStack.add("details") }) {
                                Text("Go to Details")
                            }
                        }
                        entry("details") {
                            Column {
                                SelectionContainer {
                                    Text("Selectable Text on Details")
                                }
                                Button(onClick = { backStack.removeLast() }) {
                                    Text("Go Back")
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}