package wiki.qingyun.raspi.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDraw()
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppbar {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }
            ButtonGroup()
            ConferList()
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbar(openDraw : () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        title = {  },
        navigationIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(all = 5.dp),
                    onClick = {
                        openDraw()
                    }) {
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
                    mutableStateOf("2")
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
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            color = Color(0xA6DCDCDC),
            thickness = 0.7.dp,
            modifier = Modifier
                .width(375.dp)
        )
    }
}

@Composable
fun ConferList() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        repeat(10) {
            ConferItem()
        }
    }
}

@Composable
fun ConferItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(color = Color(0xFFFFFFFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .width(350.dp)
                    .height(150.dp)
                    .background(Color(0xFFFFFFFF)),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFFF)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 10.dp)
                    ) {
                        Text(
                            text = "会议主题",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    ConferTime(
                        startTime = "18:00",
                        endTime = "18:30",
                        date = "2023年12月05日"
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .padding(start = 10.dp, top = 10.dp)
                    ) {
                        Text(
                            text = "发起人",
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConferTime(startTime : String, endTime : String, date : String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = Color(0xFFFFFFFF)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = startTime,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = date,
                fontSize = 10.sp
            )
        }
        Column(
            modifier = Modifier
                .width(60.dp)
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "待开始",
                fontSize = 12.sp,
                color = Color(0xFFFF9800)
            )
            Divider()
            Text(
                text = "30分钟",
                fontSize = 12.sp
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = endTime,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = date,
                fontSize = 8.sp
            )
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDraw() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(Color(0xFFFFFFFF))
    ) {
        NaviImage()
        NaviInfo()
        NaviList()
    }
}

@Composable
fun NaviImage() {
    Row(
        modifier = Modifier
            .padding(
                start = 25.dp,
                top = 10.dp
            )
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.images),
            contentDescription = "",
            modifier = Modifier
                .clip(CircleShape)
                .size(70.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun NaviInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 25.dp,
                top = 10.dp
            )
    ) {
        Column {
            Text(
                text = "姓名",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "部门",
                fontSize = 12.sp,
                color = Color(0xFF969696)
            )
        }
    }
}

@Composable
fun NaviList() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        NaviItem(
            label = "个人信息",
            image = painterResource(id = R.drawable.info)
        ) {}
        NaviItem(
            label = "日程",
            image = painterResource(id = R.drawable.info)
        ) {}
        NaviItem(
            label = "设置",
            image = painterResource(id = R.drawable.info)
        ) {}
        Spacer(modifier = Modifier.weight(1f))
        NaviItem(
            label = "退出登录",
            image = painterResource(id = R.drawable.info)
        ) {}
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NaviItem(label : String, image : Painter, onClick : (String) -> Unit) {
    NavigationDrawerItem(
        label = { Text(text = label) },
        selected = false,
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        onClick = { onClick(label) },
        icon = {
            Image(painter = image, contentDescription = "")
        }
    )
}

@Preview
@Composable
fun MainPreview() {
    RaspiTheme {
        Main()
//        NavigationDraw()
    }
}
