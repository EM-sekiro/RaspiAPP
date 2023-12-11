package wiki.qingyun.raspi.presentation

import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun Preserve() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F1F5))
    ) {
        TopAppBar()
        Spacer(modifier = Modifier.height(15.dp))
        InputTitle()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTitle() {
    var title by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .width(400.dp),
            placeholder = { Text(text = "会议主题", fontWeight = FontWeight.Bold)}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {  },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = ""
            )
        },
        actions = {
            Text(
                text = "完成",
                fontSize = 18.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier
                    .padding(end = 5.dp)
            )
        }
    )
}

@Preview
@Composable
fun PreservePreview() {
    RaspiTheme {
        Preserve()
    }
}