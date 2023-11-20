package wiki.qingyun.raspi.presentation

import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun Login() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .height(150.dp)
            .paint(
                painter = painterResource(id = R.drawable.images),
                contentScale = ContentScale.FillWidth,
                alpha = 0.6f,
                alignment = Alignment.TopCenter
            ),
    ) {
        LoginTitle()
        LoginContent()
    }
}

@Composable
fun LoginTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "登录",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 30.dp, top = 50.dp)
        )
    }
}

@Composable
fun LoginContent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD8E9E4)
        )
    ) {
        Column(
        ) {
            LoginInputField()
            LoginButton()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginInputField() {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 25.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFD8E9E4)
            ),
            value = username,
            onValueChange = { username = it },
            placeholder = { Text(text = "请输入账号")},
            label = { Text(text = "账号") },
            leadingIcon = { Icon(Icons.Filled.AccountCircle, contentDescription = "") }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 25.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFD8E9E4)
            ),
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = "请输入密码")},
            label = { Text(text = "密码") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "") },
            trailingIcon = {
                val painter = if (passwordVisible)
                    painterResource(id = R.drawable.visibility)
                else painterResource(id = R.drawable.visibilityoff)
                IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(painter = painter, "")
                }
            }
        )
    }
}

@Composable
fun LoginButton() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        TextButton(
            modifier = Modifier
                .padding(end = 15.dp),
            content = { Text(
                text = "忘记密码?",
                textDecoration = TextDecoration.Underline
            ) },
            onClick = { /*TODO*/ }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(start = 30.dp, end = 30.dp, top = 10.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "登 录",
                fontSize = 17.sp,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    RaspiTheme {
        Login()
    }
}