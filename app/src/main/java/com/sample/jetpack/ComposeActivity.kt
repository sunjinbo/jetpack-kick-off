package com.sample.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.sample.jetpack.ui.theme.JetpackTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Jetpack Compose!")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column {
        Text(text = "Hello $name!")

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "app icon",
                modifier = Modifier.size(20.dp).clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = "Good", color = MaterialTheme.colors.secondaryVariant)
            Text(text = " bye!", style = MaterialTheme.typography.body2)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackTheme {
        Greeting("Android")
    }
}