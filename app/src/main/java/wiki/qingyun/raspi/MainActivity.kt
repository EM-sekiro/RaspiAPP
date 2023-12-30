package wiki.qingyun.raspi
import Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import getUserLocation
import wiki.qingyun.raspi.presentation.Confer
import wiki.qingyun.raspi.presentation.Info
import wiki.qingyun.raspi.presentation.Login
import wiki.qingyun.raspi.presentation.Main
import wiki.qingyun.raspi.presentation.Person
import wiki.qingyun.raspi.presentation.PreConf
import wiki.qingyun.raspi.presentation.Preserve
import wiki.qingyun.raspi.presentation.StaffList
import wiki.qingyun.raspi.presentation.StaffListPreview
import wiki.qingyun.raspi.ui.theme.RaspiTheme

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RaspiTheme {
                var layout by remember {
                    mutableStateOf("Login")
                }
                var conf = PreConf(
                    title = "",
                    date = "",
                    room = "",
                    hours_S = 0,
                    hours_E = 0,
                    minutes_S = 0,
                    minutes_E = 0,
                    people = mutableListOf(),
                    brief = ""
                )
                var confList =  mutableListOf(
                    Confer(
                        title = "产品发布会",
                        currentTime = "16:30",
                        startTime = "15:00",
                        endTime = "17:30",
                        date = "2023年12月01日",
                        day = "星期五",
                        location = "主校区 会议室A",
                        leader = "张三"
                    ),
                    Confer(
                        title = "市场调研会",
                        currentTime = "10:20",
                        startTime = "09:30",
                        endTime = "11:00",
                        date = "2023年12月01日",
                        day = "星期五",
                        location = "主校区 会议室B",
                        leader = "李四"
                    ),
                    Confer(
                        title = "团队讨论会",
                        currentTime = "14:45",
                        startTime = "14:00",
                        endTime = "15:30",
                        date = "2023年12月05日",
                        day = "星期二",
                        location = "沙河校区 第二教学楼103",
                        leader = "王五"
                    ),
                    Confer(
                        title = "项目进展汇报",
                        currentTime = "09:30",
                        startTime = "09:00",
                        endTime = "10:30",
                        date = "2023年12月05日",
                        day = "星期二",
                        location = "主校区 会议室C",
                        leader = "赵六"
                    ),
                    Confer(
                        title = "新产品策划会",
                        currentTime = "15:20",
                        startTime = "14:30",
                        endTime = "16:30",
                        date = "2023年12月10日",
                        day = "星期日",
                        location = "主校区 会议室D",
                        leader = "杨九"
                    ),
                    Confer(
                        title = "研发团队会议",
                        currentTime = "11:10",
                        startTime = "10:30",
                        endTime = "12:00",
                        date = "2023年12月10日",
                        day = "星期日",
                        location = "沙河校区 第三教学楼302",
                        leader = "刘七"
                    ),
                    Confer(
                        title = "战略规划会",
                        currentTime = "16:40",
                        startTime = "16:00",
                        endTime = "17:30",
                        date = "2023年12月15日",
                        day = "星期五",
                        location = "主校区 会议室A",
                        leader = "陈八"
                    ),
                    Confer(
                        title = "市场推广会议",
                        currentTime = "09:50",
                        startTime = "09:00",
                        endTime = "11:00",
                        date = "2023年12月15日",
                        day = "星期五",
                        location = "主校区 会议室B",
                        leader = "王九"
                    ),
                    Confer(
                        title = "团队建设活动",
                        currentTime = "14:20",
                        startTime = "14:00",
                        endTime = "16:00",
                        date = "2023年12月18日",
                        day = "星期一",
                        location = "沙河校区 第二教学楼103",
                        leader = "赵十"
                    ),
                    Confer(
                        title = "项目总结会",
                        currentTime = "10:40",
                        startTime = "10:00",
                        endTime = "12:00",
                        date = "2023年12月18日",
                        day = "星期一",
                        location = "主校区 会议室C",
                        leader = "李十一"
                    ),
                    Confer(
                        title = "市场竞争分析",
                        currentTime = "15:10",
                        startTime = "14:30",
                        endTime = "16:30",
                        date = "2023年12月22日",
                        day = "星期五",
                        location = "主校区 会议室D",
                        leader = "张十二"
                    ),
                    Confer(
                        title = "产品改进会",
                        currentTime = "11:20",
                        startTime = "10:30",
                        endTime = "12:00",
                        date = "2023年12月22日",
                        day = "星期五",
                        location = "主校区 会议室E",
                        leader = "王十三"
                    ),
                    Confer(
                    title = "团队讨论会",
                        currentTime = "14:50",
                        startTime = "14:00",
                        endTime = "15:30",
                        date = "2023年12月25日",
                        day = "星期一",
                        location = "沙河校区 第三教学楼302",
                        leader = "杨十四"
                    ),
                    Confer(
                        title = "新产品策划会",
                        currentTime = "09:40",
                        startTime = "09:00",
                        endTime = "10:30",
                        date = "2023年12月25日",
                        day = "星期一",
                        location = "主校区 会议室F",
                        leader = "刘十五"
                    ),
                    Confer(
                        title = "项目进展汇报",
                        currentTime = "15:30",
                        startTime = "14:30",
                        endTime = "16:30",
                        date = "2023年12月28日",
                        day = "星期四",
                        location = "主校区 会议室G",
                        leader = "陈十六"
                    ),
                    Confer(
                        title = "研发团队会议",
                        currentTime = "11:20",
                        startTime = "10:30",
                        endTime = "12:00",
                        date = "2023年12月28日",
                        day = "星期四",
                        location = "沙河校区 第二教学楼103",
                        leader = "赵十七"
                    ),
                    Confer(
                        title = "战略规划会",
                        currentTime = "16:50",
                        startTime = "16:00",
                        endTime = "17:30",
                        date = "2023年12月29日",
                        day = "星期五",
                        location = "主校区 会议室A",
                        leader = "李十八"
                    ),
                    Confer(
                        title = "市场推广会议",
                        currentTime = "09:50",
                        startTime = "09:00",
                        endTime = "11:00",
                        date = "2023年12月29日",
                        day = "星期五",
                        location = "主校区 会议室B",
                        leader = "王十九"
                    ),
                    Confer(
                        title = "团队建设活动",
                        currentTime = "14:20",
                        startTime = "14:00",
                        endTime = "16:00",
                        date = "2023年12月31日",
                        day = "星期日",
                        location = "沙河校区 第二教学楼103",
                        leader = "赵二十"
                    ),
                    Confer(
                        title = "项目总结会",
                        currentTime = "10:40",
                        startTime = "10:00",
                        endTime = "12:00",
                        date = "2023年12月31日",
                        day = "星期日",
                        location = "主校区 会议室C",
                        leader = "李二十一"
                    )
                )
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    when(layout) {
                        "Login" -> {
                            Login { login ->
                                if(login){
                                    layout = "Main"
                                }
                            }
                        }
                        "Main" -> {
                            Main(confList) {
                                layout = it
                            }
                        }
                        "Info" -> {
                            Info {
                                layout = it
                            }
                        }
                        "Preserve" -> {
                            Preserve(conf) {page, msg, confer ->
                                if(msg != "") {
                                    confList.add(Confer(
                                        title = confer.title,
                                        currentTime = "16:30",
                                        startTime = confer.hours_S.toString().padStart(2, '0') + ":" + confer.minutes_S.toString().padStart(2, '0'),
                                        endTime = confer.hours_E.toString().padStart(2, '0') + ":" + confer.minutes_E.toString().padStart(2, '0'),
                                        date = confer.date,
                                        day = "星期五",
                                        location = confer.room,
                                        leader = "张睿"
                                    ))
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                                }
                                if(page == "Main")
                                    conf.initialize()
                                if(page == "StaffList")
                                    conf = confer
                                layout = page
                            }
                        }
                        "Calendar" -> {
                            Calendar(this) {
                                layout = it
                            }
                        }
                        "StaffList" -> {
                            StaffList(conf.people) {page, person ->
                                conf.people = person
                                layout = page
                            }
                        }
                        else -> {
                            Main(confList) {
                                layout = it
                            }
                        }
                    }
                }
            }
        }
    }
}




