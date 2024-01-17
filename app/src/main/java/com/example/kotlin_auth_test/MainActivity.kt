package com.keitheyre.kotlin_auth_test

import AuthViewModel
import HomeActivity
import LoginActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.keitheyre.kotlin_auth_test.ui.theme.MyKotlinAuthenticationWithFirebaseApplicationTheme

class MainActivity : ComponentActivity() {
    private lateinit var authViewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        setContent {
            MyKotlinAuthenticationWithFirebaseApplicationTheme {
                val isUserAuthenticated = authViewModel.isUserAuthenticated

                // Observe the user's authentication state
                when (val userUid = authViewModel.isUserAuthenticated) {
                    null -> {
                        // User is authenticated, navigate to the home activity
                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                        finish()
                    }

                    else -> {
                        // User is not authenticated, navigate to the login activity
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyKotlinAuthenticationWithFirebaseApplicationTheme {
        Greeting("Android")
    }
}