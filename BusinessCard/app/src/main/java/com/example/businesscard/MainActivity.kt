package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
        .fillMaxSize()
        .padding(bottom = 16.dp)
        .border(BorderStroke(2.dp, Color.Gray))

    Row {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Text(
                text = name,
                fontSize = 34.sp,
                fontWeight = FontWeight.Light,
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(text = title, color = Color.White)
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
        modifier = Modifier.padding(bottom = 32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactRowSeparator()
            ContactRow(value = phoneNumber, icon = Icons.Rounded.Phone)
            ContactRowSeparator()
            ContactRow(value = socialMedia, icon = Icons.Rounded.Share)
            ContactRowSeparator()
            ContactRow(value = emailAddress, icon = Icons.Rounded.Email)
        }
    }
}

@Composable
fun ContactRow(
    value: String,
    icon: ImageVector
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 4.dp)
    ) {
        Icon(
            icon,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier.padding(end = 24.dp)
        )
        Text(text = value, color = Color.White)
    }
}

@Composable
fun BusinessCard() {
    Box(modifier = Modifier.background(Color.Black)) {
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
}

@Composable
fun ContactRowSeparator() {
    Spacer(
        Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.White)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}