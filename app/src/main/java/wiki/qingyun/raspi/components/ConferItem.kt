package wiki.qingyun.raspi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun ConferItem(
    title: String,
    startTime: String,
    endTime: String,
    date: String,
    location: String,
    leader: String,
//    containerColor: Color = Color(0xFFD8E9E4),
//    contentColor: Color = Color(0xFFcff0da)
    containerColor: Color = Color(0xFFFFFFFF),
    contentColor: Color = Color(0xFFFFFFFF)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(containerColor),
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
                    .background(containerColor),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFFF)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .background(contentColor)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 10.dp)
                    ) {
                        Text(
                            text = title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    ConferTime(
                        startTime = startTime,
                        endTime = endTime,
                        date = date,
                        background = contentColor
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .padding(start = 10.dp, top = 10.dp)
                    ) {
                        Text(
                            text = "会议地点",
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.width(30.dp))
                        Text(
                            text = location,
                            fontSize = 13.sp
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = "发起人",
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.width(43.dp))
                        Image(
                            painter = painterResource(id = R.drawable.images),
                            contentDescription = "",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(20.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = leader,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConferTime(
    startTime : String,
    endTime : String,
    date : String,
    background: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = background),
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
                fontSize = 10.sp
            )
        }
    }
}

@Preview
@Composable
fun ConferItemPreview() {
    RaspiTheme {
        ConferItem(
            title = "实习大会",
            startTime = "16:20",
            endTime = "17:55",
            date = "2023年12月6日",
            location = "沙河校区 第二教学楼103",
            leader = "李达"
        )
    }
}