package com.example.banquemisr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banquemisr.ui.theme.BanqueMisrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanqueMisrTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Login(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Login(modifier: Modifier = Modifier) {
    var emailField by rememberSaveable { mutableStateOf("") }
    var passwordField by rememberSaveable { mutableStateOf("") }
    var showPassword by rememberSaveable { mutableStateOf(false) }
    var isArabic by rememberSaveable { mutableStateOf(false) }
    val isFormValid = emailField.isNotBlank() && passwordField.isNotBlank()

    CompositionLocalProvider(
        LocalLayoutDirection provides if (isArabic) LayoutDirection.Rtl else LayoutDirection.Ltr
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {

            // 🔹 رأس الصفحة
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.bm_icon),
                    contentDescription = "Banque Misr icon",
                    modifier = Modifier.size(120.dp)
                )
                Text(
                    text = if (isArabic) "En" else "العربية",
                    fontSize = 22.sp,
                    color = Color(0xFFc82b2b),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.clickable {
                        isArabic = !isArabic
                    }
                )
            }


            OutlinedTextField(
                value = emailField,
                onValueChange = { emailField = it },
                label = { Text(if (isArabic) "اسم المستخدم" else "Username") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                textStyle = TextStyle(color = Color.Black),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 18.dp)
            )

            OutlinedTextField(
                value = passwordField,
                onValueChange = { passwordField = it },
                label = { Text(if (isArabic) "كلمة المرور" else "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = TextStyle(color = Color.Black),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = if (isArabic) "هل نسيت اسم المستخدم أو كلمة المرور؟" else "Forgot Username/Password?",
                fontSize = 14.sp,
                color = Color.Gray,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
            )

            Spacer(modifier = Modifier.height(38.dp))

            // 🔹 زر الدخول
            Button(
                onClick = { },
                enabled = isFormValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFFc82b2b) else Color(0xFFB0BEC5),
                    contentColor = Color.White
                )
            ) {
                Text(
                    if (isArabic) "تسجيل الدخول" else "Login",
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = buildAnnotatedString {
                    append(if (isArabic) "هل تحتاج مساعدة؟ " else "Need help? ")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFFc82b2b),
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(if (isArabic) "اتصل بنا" else "Contact us")
                    }
                },
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 28.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.3f)
            )

            // 🔹 الأيقونات السفلية
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                val itemModifier = Modifier.width(80.dp)

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = itemModifier) {
                    Icon(
                        painter = painterResource(R.drawable.our_products),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = if (isArabic) "منتجاتنا" else "Our products",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.height(36.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = itemModifier) {
                    Icon(
                        painter = painterResource(R.drawable.exchange_rate),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = if (isArabic) "سعر الصرف" else "Exchange rate",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.height(36.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = itemModifier) {
                    Icon(
                        painter = painterResource(R.drawable.security_tips),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = if (isArabic) "نصائح الأمان" else "Security tips",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.height(36.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = itemModifier) {
                    Icon(
                        painter = painterResource(R.drawable.nearest_branch_or_atm),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = if (isArabic) "أقرب فرع أو ماكينة" else "Nearest branch or ATM",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.height(40.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Login_Preview() {
    Login()
}
