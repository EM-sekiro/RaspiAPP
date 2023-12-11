package wiki.qingyun.raspi.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.ui.theme.RaspiTheme
import kotlin.concurrent.thread


@Composable
fun Info() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F1F5))
    ) {
        TopAppbar()
        PersonBar()
        InfoCard()
        PermissionCard()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbar() {
    CenterAlignedTopAppBar(
        title = {  },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = ""
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFFF0F1F5)
        )
    )
}

@Composable
fun PersonBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color(0xFFF0F1F5))
            .padding(start = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.images),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(45.dp)
        )
        Text(
            text = "姓名",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }
}

@Composable
fun InfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        )
    ) {
        Column {
            var lineCount by remember {
                mutableStateOf(0)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        start = 15.dp,
                        top = 20.dp,
                        bottom = 20.dp,
                        end = 15.dp
                    )
            ) {
                Text(
                    text = "部门",
                    modifier = Modifier
                        .width(100.dp)
                )
                Text(
                    text = "电子科技大学（沙河校区）-信息与软件学院-本科生-2021-软件工程（系统与技术）",
                    textAlign = if(lineCount > 1) TextAlign.Start else TextAlign.End,
                    color = Color(0xFFAAAAAA),
                    onTextLayout = {textLayoutResult: TextLayoutResult ->
                        lineCount = textLayoutResult.lineCount
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Divider(
                    modifier = Modifier
                        .width(340.dp),
                    thickness = 1.dp,
                    color = Color(0xFFD9D9D9)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        start = 15.dp,
                        top = 20.dp,
                        bottom = 20.dp,
                        end = 15.dp
                    )
            ) {
                Text(
                    text = "ID",
                    modifier = Modifier
                        .width(100.dp)
                )
                Text(
                    text = "2021090908006",
                    textAlign = TextAlign.End,
                    color = Color(0xFFAAAAAA),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun PermissionCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 10.dp, end = 10.dp, top = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    start = 15.dp,
                    top = 20.dp,
                    bottom = 20.dp,
                    end = 15.dp
                )
        ) {
            Text(
                text = "您可预定",
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp),
                color = Color(0xFF646464)
            )
            Text(
                text = "* 中小型会议室 2 次",
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
            )
            Text(
                text = "* 大型会议室 1 次",
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
            )
        }
    }
}

@Preview
@Composable
fun InfoPreview() {
    RaspiTheme {
        Info()
    }
}