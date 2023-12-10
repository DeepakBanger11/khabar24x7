package com.startlearning.khabar24x7.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.startlearning.khabar24x7.R
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel

@Composable
fun LoginScreen(
    navigateToHomeScreen: () -> Unit,
    newsViewModel: NewsViewModel
    )
{
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.newslogo), // Your login background image
                contentDescription = "Login Background",
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Khabar 24x7",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = { Text("Email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { /* Handle Next button press */ }
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it},
                label = { Text("Password") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* Handle Done button press */ }
                ),
                visualTransformation = if (showPassword.value) {

                    VisualTransformation.None

                } else {

                    PasswordVisualTransformation()

                },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    val image = if (showPassword.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Localized description for accessibility services
                    val description =
                        if (showPassword.value) stringResource(id = R.string.hide_password) else stringResource(
                            id = R.string.show_password
                        )
                    IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                },
                    )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { if(password.isNotEmpty() && email.isNotEmpty())
                {
                    showToast(context, "Login successful!")
                    newsViewModel.setLogin(email,password)
                    navigateToHomeScreen()
                }
                          else{
                    showToast(context, "Email or Password isn't correct")
                          }},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }
    }
}
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

//@Preview
//@Composable
//fun PreviewLoginScreen(){
//    LoginScreen()
//}