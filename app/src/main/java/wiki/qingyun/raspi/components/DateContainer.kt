package wiki.qingyun.raspi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun DateContainer(
    date: String,
    day: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
    ) {
        Row(
            modifier = Modifier
                .padding(top = 15.dp, start = 21.dp)
        ) {
            Text(
                text = date,
                color = Color(0xFF646464),
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = day,
                color = Color(0xFF646464),
                fontSize = 15.sp
            )
        }
        content()
    }
}

@Preview
@Composable
fun DateContainerPreview() {
    RaspiTheme {
        DateContainer("12月6日", "星期三"){
            ConferItem(
                title = "实习大会",
                currentTime = "16:00",
                startTime = "16:20",
                endTime = "17:55",
                date = "2023年12月6日",
                location = "沙河校区 第二教学楼103",
                leader = "李达"
            )
            ConferItem(
                title = "实习大会",
                currentTime = "17:00",
                startTime = "16:20",
                endTime = "17:55",
                date = "2023年12月6日",
                location = "沙河校区 第二教学楼103",
                leader = "李达"
            )
        }
    }
}