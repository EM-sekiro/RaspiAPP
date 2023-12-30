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
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.components.ConferItem
import wiki.qingyun.raspi.components.DateContainer
import wiki.qingyun.raspi.components.ImageButton
import wiki.qingyun.raspi.components.NaviItem
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(confList: MutableList<Confer>, jump: (String) -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDraw(jump)
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
            ButtonGroup(jump)
            ConferList(confList)
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbar(openDraw : () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//            containerColor = Color(0xFFD8E9E4)
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
            var mesNum by remember {
                mutableStateOf("2")
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(60.dp)
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
    )
}

@Composable
fun ButtonGroup(jump: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
//            .background(color = Color(0xFFD8E9E4)),
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
            jump("Preserve")
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

data class Confer (
    var title: String,
    var currentTime: String,
    var startTime: String,
    var endTime: String,
    var date: String,
    var day : String,
    var location: String,
    var leader: String,
)

@Composable
fun ConferList(confList : MutableList<Confer>) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        var currentDate = ""
        val list = confList.sortedWith(compareBy({ it.date }, { it.startTime }))
        val size = list.size
        var flag = 0
        while(flag < size){
            DateContainer(date = list[flag].date, day = list[flag].day) {
                currentDate = list[flag].date
                while (flag < size && list[flag].date == currentDate){
                    ConferItem(
                        title = list[flag].title,
                        currentTime = list[flag].currentTime,
                        startTime = list[flag].startTime,
                        endTime = list[flag].endTime,
                        date = list[flag].date,
                        location = list[flag].location,
                        leader = list[flag].leader
                    )
                    flag++
                }
            }
        }
    }
}

@Composable
fun NavigationDraw(jump: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(Color(0xFFFFFFFF))
    ) {
        NaviImage()
        NaviInfo()
        NaviList(jump)
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
fun NaviList(jump: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        NaviItem(
            label = "个人信息",
            image = painterResource(id = R.drawable.info)
        ) {jump("Info")}
        NaviItem(
            label = "日程",
            image = painterResource(id = R.drawable.info)
        ) {jump("Calendar")}
        NaviItem(
            label = "设置",
            image = painterResource(id = R.drawable.info)
        ) {jump("Setting")}
        Spacer(modifier = Modifier.weight(1f))
        NaviItem(
            label = "退出登录",
            image = painterResource(id = R.drawable.info)
        ) {jump("Login")}
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
fun MainPreview() {
    RaspiTheme {
        var confList =  mutableListOf(
            Confer(
                title = "实习大会",
                currentTime = "17:00",
                startTime = "16:20",
                endTime = "17:55",
                date = "2023年12月6日",
                day = "星期三",
                location = "沙河校区 第二教学楼103",
                leader = "李达"
            ),
            Confer(
                title = "技术讲座",
                currentTime = "15:30",
                startTime = "14:00",
                endTime = "16:30",
                date = "2023年12月8日",
                day = "星期五",
                location = "沙河校区 第二教学楼103",
                leader = "张华"
            ),
            Confer(
                title = "学术研讨",
                currentTime = "10:00",
                startTime = "09:30",
                endTime = "12:00",
                date = "2023年12月11日",
                day = "星期一",
                location = "沙河校区 第二教学楼104",
                leader = "王明"
            ),
            Confer(
                title = "创业峰会",
                currentTime = "14:30",
                startTime = "13:00",
                endTime = "16:00",
                date = "2023年12月13日",
                day = "星期三",
                location = "主校区 创新中心大厅",
                leader = "陈志"
            ),
            Confer(
                title = "行业论坛",
                currentTime = "11:30",
                startTime = "10:00",
                endTime = "13:00",
                date = "2023年12月15日",
                day = "星期五",
                location = "沙河校区 综合楼205",
                leader = "刘文"
            ),
            Confer(
                title = "就业指导",
                currentTime = "16:30",
                startTime = "15:00",
                endTime = "17:30",
                date = "2023年12月18日",
                day = "星期一",
                location = "主校区 就业中心",
                leader = "张明"
            ),
            Confer(
                title = "学术报告",
                currentTime = "09:30",
                startTime = "09:00",
                endTime = "11:00",
                date = "2023年12月20日",
                day = "星期三",
                location = "沙河校区 图书馆报告厅",
                leader = "王磊"
            ),
            Confer(
                title = "创新竞赛",
                currentTime = "14:00",
                startTime = "13:00",
                endTime = "16:00",
                date = "2023年12月22日",
                day = "星期五",
                location = "主校区 创新中心",
                leader = "李华"
            ),
            Confer(
                title = "学科讲座",
                currentTime = "15:30",
                startTime = "14:00",
                endTime = "16:30",
                date = "2023年12月25日",
                day = "星期一",
                location = "沙河校区 第一教学楼201",
                leader = "刘明"
            ),
            Confer(
                title = "学术研究会",
                currentTime = "11:00",
                startTime = "10:30",
                endTime = "13:00",
                date = "2023年12月28日",
                day = "星期四",
                location = "主校区 学术报告厅",
                leader = "张磊"
            )
        )
        Main(confList) {}
//        NavigationDraw {}
    }
}
