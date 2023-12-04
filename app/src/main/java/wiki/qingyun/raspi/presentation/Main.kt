package wiki.qingyun.raspi.presentation

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun Main() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppbar()
        ButtonGroup()
        ConferList()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbar() {
    CenterAlignedTopAppBar(
        title = {  },
        navigationIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(all = 5.dp),
                    onClick = { /*TODO*/ }) {
                    Image(
                        //用户头像
                        painter = painterResource(id = R.drawable.images),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape))
                }
                Column {
                    Text(
                        text = "姓名",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = "部门",
                        fontSize = 10.sp,
                        color = Color(0xFF969696)
                    )
                }
            }
        },
        actions = {
            Row {
                var mesNum by remember {
                    mutableStateOf("0")
                }
                IconButton(
                    modifier = Modifier
                        .padding(all = 5.dp),
                    onClick = { /*TODO*/ }
                ) {
                    if(mesNum == "0") {
                        Icon(Icons.Filled.MailOutline, contentDescription = "")
                    }else {
                        BadgedBox(
                            badge = { Badge { Text(text = mesNum) } }
                        ) {
                            Icon(Icons.Filled.MailOutline, contentDescription = "")
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ButtonGroup() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color(0xFFFFFFFF)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ImageButton(
            title = "扫一扫",
            image = painterResource(id = R.drawable.scan)
        ) {
            //onClick
        }
        ImageButton(
            title = "会议预定",
            image = painterResource(id = R.drawable.preserve)
        ) {
            //onClick
        }
        ImageButton(
            title = "会议签到",
            image = painterResource(id = R.drawable.signup)
        ) {
            //onClick
        }
    }
}

@Composable
fun ConferList() {
    ConferItem()
    ConferItem()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConferItem() {
    Card {
        ListItem(headlineText = { /*TODO*/ })
    }
}

@Composable
fun ImageButton(title: String, image: Painter, onClick: () -> Unit) {
    IconButton(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        onClick = { onClick() }
    ) {
        Column(
            modifier = Modifier
                .width(100.dp)
                .height(70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp))
            Text(
                text = title,
                fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    RaspiTheme {
        Main()
    }
}
