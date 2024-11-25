package com.solutions.app.modules.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.solutions.app.R
import com.solutions.app.ui.composables.CustomStyleTextField


/**
 * @Created by akash on 21-11-2024.
 * Know more about author at https://akash.cloudemy.in
 */
@Composable
fun LoginScreen(mainNavController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    Scaffold {
        val context = LocalContext.current
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ConstraintLayout {
                    val (image, loginForm) = createRefs()
                    Image(
                        modifier = Modifier
                            .height(280.dp)
                            .constrainAs(image) {
                                top.linkTo(loginForm.top)
                                bottom.linkTo(loginForm.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxWidth(.6f),
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "logo"
                    )
                    Card(
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp)
                            .constrainAs(loginForm) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        val emailTextState = remember { mutableStateOf(TextFieldValue("")) }
                        val passTextState = remember { mutableStateOf(TextFieldValue("")) }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(30.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 20.dp),
                                text = "Log in to your account.",
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Text(
                                text = "Email Address",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                            )

                            CustomStyleTextField(
                                placeHolder = "Email Address",
                                leadingIconId = Icons.Default.Email,
                                keyboardType = KeyboardType.Email,
                                visualTransformation = VisualTransformation.None,
                                emailTextState.value
                            ) {
                                emailTextState.value = it
                            }

                            Text(
                                text = "Password",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )
                            CustomStyleTextField(
                                placeHolder = "Password",
                                leadingIconId = Icons.Default.Lock,
                                keyboardType = KeyboardType.Password,
                                visualTransformation = PasswordVisualTransformation(),
                                textState = passTextState.value
                            ) {
                                passTextState.value = it
                            }

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                text = "Forgot Password",
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.labelLarge
                            )
                            ElevatedButton(
                                onClick = {
                                    if (emailTextState.value.text.isEmpty()) {
                                        Toast.makeText(
                                            context,
                                            "Please enter your email",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else if (passTextState.value.text.isEmpty()) {
                                        Toast.makeText(
                                            context,
                                            "Please enter your password",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }  else {
                                        viewModel.login(
                                            email = emailTextState.value.text,
                                            password = passTextState.value.text
                                        )
                                      //  mainNavController.navigate(Destinations.Dashboard.route)
                                    }
                                },
                                modifier = Modifier
                                    .padding(top = 30.dp, bottom = 34.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(4.dp),
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                    contentColor = MaterialTheme.colorScheme.secondaryContainer
                                )

                            ) {
                                Text(
                                    modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                                    text = "Login",


                                    )
                            }

                            val signInText = "Don't have an account? Sign Up"
                            val signInWord = "Sign Up"
                            val signInAnnotatedString = buildAnnotatedString {
                                append(signInText)
                                addStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.secondary,

                                        ),
                                    start = 0,
                                    end = signInText.length
                                )
                                addStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,

                                        ),
                                    start = signInText.indexOf(signInWord),
                                    end = signInText.length
                                )
                            }

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = signInAnnotatedString,
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(200.dp))
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun HeaderView() {


}