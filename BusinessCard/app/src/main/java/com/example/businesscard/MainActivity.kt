package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun NameSection(
    name: String,
    title: String,
    picture: Int
) {
    val image = painterResource(picture)
    val imageModifier = Modifier
        .size(150.dp)
        .border(BorderStroke(1.dp, Color.Black))
    Row {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = imageModifier
            )
            Text(
                text = name,
                fontSize = 34.sp,
                fontWeight = FontWeight.Light
            )
            Text(text = title)
        }
    }
}

@Composable
fun ContactSection(
    phoneNumber: String,
    socialMedia: String,
    emailAddress: String
) {
    Row(
        modifier = Modifier
            .padding(bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactRow(value = phoneNumber, icon = Icons.Rounded.Phone)
            ContactRow(value = socialMedia, icon = Icons.Rounded.Phone)
            ContactRow(value = emailAddress, icon = Icons.Rounded.Phone)
        }
    }
}

@Composable
fun ContactRow(
    value: String,
    icon: ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(icon, contentDescription = null)
        Text(text = value)
    }
}

@Composable
fun BusinessCard() {
    NameSection(
        name = "Benjamin Barreto",
        title = "Software Engineer",
        picture = R.drawable.ben
    )
    ContactSection(
        phoneNumber = "+31 6 999-12-1234",
        socialMedia = "@benalexb",
        emailAddress = "benji@barreto.com"
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}