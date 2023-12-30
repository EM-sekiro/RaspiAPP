package wiki.qingyun.raspi.presentation

import android.app.TimePickerDialog
import android.nfc.Tag
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.TestModifierUpdaterLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fragment.project.components.WheelPicker
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.components.ImageButton
import wiki.qingyun.raspi.ui.theme.RaspiTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.sin

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Preserve(
    conf: PreConf,
    jump : (page: String, msg: String, conf: PreConf) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(){page, msg ->
            jump(page, msg, conf)
        }
        Spacer(modifier = Modifier.height(15.dp))
        InputTitle(conf.title){
            conf.title = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        InputRoom(conf.room){
            conf.room = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        InputDate(conf.date){
            conf.date = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        InputTime(conf.hours_S, conf.hours_E, conf.minutes_S, conf.minutes_E){hours_S, hours_E, minutes_S, minutes_E ->
            conf.hours_S = hours_S
            conf.hours_E = hours_E
            conf.minutes_S = minutes_S
            conf.minutes_E = minutes_E
            Log.i("hours_S", hours_S.toString())
            Log.i("minutes_S", minutes_S.toString())
            Log.i("hours_E", hours_E.toString())
            Log.i("minutes_E", minutes_E.toString())
        }
        Spacer(modifier = Modifier.height(20.dp))
        InputPeople(conf.people, conf, jump)
        Spacer(modifier = Modifier.height(20.dp))
        InputBrief(conf.brief){
            conf.brief = it
        }
    }
}

@Composable
fun InputBrief(record: String, change: (String) -> Unit) {
    var brief by remember {
        mutableStateOf(record)
    }
    Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .background(Color.Transparent),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = brief,
            onValueChange = { brief = it; change(it) },
            colors = OutlinedTextFieldDefaults.colors(
//                disabledTextColor = Color.Transparent,
                focusedBorderColor = Color(0xFF4F7B39),
                unfocusedBorderColor = Color(0xFFB0B4B4),
//                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            label = { Text(text = "会议简介") },
            modifier = Modifier
                .width(350.dp)
                .fillMaxHeight(),
            placeholder = { Text(text = "请输入会议简介")}
        )
    }
}

data class Person(val id: Int, val name: String, val photo: Int)

data class PreConf (
    var title: String,
    var date: String,
    var room: String,
    var hours_S: Int,
    var hours_E: Int,
    var minutes_S: Int,
    var minutes_E: Int,
    var people: MutableList<Person>,
    var brief: String) {
    fun initialize() {
        title = ""
        date = ""
        room = ""
        hours_S = 0
        hours_E = 0
        minutes_S = 0
        minutes_E = 0
        people = mutableListOf()
        brief = ""
    }
}

@Composable
fun PeopleItem(
    person: Person,
    page: String = "",
    jump: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { jump() }
    ) {
        Image(
            //用户头像
            painter = painterResource(id = person.photo),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(30.dp))
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = person.name,
            fontSize = 10.sp
        )
    }
}


@Composable
fun InputPeople(people: MutableList<Person>, confer: PreConf, jump: (page: String, msg: String, conf: PreConf) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier
            .width(350.dp)
            .drawWithContent {
                drawContent()
                clipRect {
                    val strokeWidth = Stroke.DefaultMiter
                    val y = size.height

                    drawLine(
                        brush = SolidColor(Color(0xFFAFB3B3)),
                        strokeWidth = strokeWidth,
                        cap = StrokeCap.Square,
                        start = Offset(x = 0f, y = 12 * density),
                        end = Offset(x = 11 * density, y = 12 * density)
                    )

                    drawLine(
                        brush = SolidColor(Color(0xFFAFB3B3)),
                        strokeWidth = strokeWidth,
                        cap = StrokeCap.Square,
                        start = Offset(x = 72 * density, y = 12 * density),
                        end = Offset(x = size.width, y = 12 * density)
                    )

                    drawLine(
                        brush = SolidColor(Color(0xFFAFB3B3)),
                        strokeWidth = strokeWidth * 2,
                        cap = StrokeCap.Square,
                        start = Offset.Zero.copy(y = 13 * density),
                        end = Offset.Zero.copy(y = size.height)
                    )

                    drawLine(
                        brush = SolidColor(Color(0xFFAFB3B3)),
                        strokeWidth = strokeWidth * 2,
                        cap = StrokeCap.Square,
                        start = Offset.Zero.copy(y = y),
                        end = Offset(x = size.width, y = y)
                    )

                    drawLine(
                        brush = SolidColor(Color(0xFFAFB3B3)),
                        strokeWidth = strokeWidth * 2,
                        cap = StrokeCap.Square,
                        start = Offset(x = size.width, y = 13 * density),
                        end = Offset(x = size.width, y = size.height)
                    )

                }
            }
        ) {

            Row(modifier = Modifier.padding(start = 16.dp, top = 3.dp)) {
                Text(
                    text = "参会人员",
                    fontSize = 12.sp,
                    color = Color(0xFF40484D)
                )
            }

            Row (
                modifier = Modifier
                    .width(350.dp)
                    .background(Color.Transparent)
                    .padding(top = 10.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (people.size < 6) {
                    people.forEach {
                        PeopleItem(person = it){}
                    }
                }else {
                    people.take(5).forEach{
                        PeopleItem(person = it){}
                    }
                }
                PeopleItem( person = Person(999, "添加", R.drawable.add) ){
                    jump("StaffList", "", confer)
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTime(
    recordhours_S: Int,
    recordhours_E: Int,
    recordminutes_S: Int,
    recordminutes_E: Int,
    change: (hours_S: Int, hours_E: Int, minutes_S: Int, minutes_E: Int) -> Unit,
) {
    var hours_S by remember {
        mutableStateOf(recordhours_S)
    }
    var minutes_S by remember {
        mutableStateOf(recordminutes_S)
    }
    var expanded_S by remember {
        mutableStateOf(false)
    }
    var hours_E by remember {
        mutableStateOf(recordhours_E)
    }
    var minutes_E by remember {
        mutableStateOf(recordminutes_E)
    }
    var expanded_E by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            value = hours_S.toString().padStart(2, '0') + "点" + minutes_S.toString().padStart(2, '0') + "分",
            onValueChange = {  },
            colors = OutlinedTextFieldDefaults.colors(
//                disabledTextColor = Color.Transparent,
                focusedBorderColor = Color(0xFF4F7B39),
                unfocusedBorderColor = Color(0xFFB0B4B4),
//                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true,
            modifier = Modifier
                .width(165.dp),
            label = { Text(text = "开始时间") },
            readOnly = true,
            trailingIcon = { IconButton(onClick = { expanded_S = true  },) {
                Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = "")
            } },
        )
        OutlinedTextField(
            value = hours_E.toString().padStart(2, '0') + "点" + minutes_E.toString().padStart(2, '0') + "分",
            onValueChange = {  },
            colors = OutlinedTextFieldDefaults.colors(
//                disabledTextColor = Color.Transparent,
                focusedBorderColor = Color(0xFF4F7B39),
                unfocusedBorderColor = Color(0xFFB0B4B4),
//                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true,
            modifier = Modifier
                .width(165.dp),
            label = { Text(text = "结束时间") },
            readOnly = true,
            trailingIcon = { IconButton(onClick = { expanded_E = true  },) {
                Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = "")
            } },
        )
        if(expanded_S || expanded_E) {
            val hours = List(24) { it.toString().padStart(2, '0') + "点" }
            val minutes = List(60) { it.toString().padStart(2, '0') + "分"}
            var selectedHours by remember { mutableStateOf(0) }
            var selectedMinutes by remember { mutableStateOf(0) }
            AlertDialog(
                onDismissRequest = {
                    expanded_S = false
                    expanded_E = false
                },
                title = { if(expanded_E) Text("会议结束时间") else Text("会议开始时间")},
                confirmButton = {
                    Button(onClick = {
                        if(expanded_E){
                            hours_E = selectedHours
                            minutes_E = selectedMinutes
                        }else {
                            hours_S = selectedHours
                            minutes_S = selectedMinutes
                        }
                        change(hours_S, hours_E, minutes_S, minutes_E)
                        expanded_S = false
                        expanded_E = false
                    }) {
                        Text("确认")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        expanded_S = false
                        expanded_E = false
                    }) {
                        Text("取消")
                    }
                },
                text = {
                    Row(
                        modifier = Modifier
                            .width(300.dp)
                            .height(100.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        WheelPicker(
                            modifier = Modifier
                                .width(80.dp)
                                .height(150.dp)
                                .background(Color(0xFFE0EBF2)),
                            data = hours,
                            selectIndex = selectedHours,
                            visibleCount = 4,
                            onSelect = { index, item ->
                                selectedHours = index
                            },
                            content = { item ->
                                Text(text = item, fontSize = 18.sp)
                            }
                        )
                        WheelPicker(
                            modifier = Modifier
                                .width(80.dp)
                                .height(150.dp)
                                .background(Color(0xFFE0EBF2)),
                            data = minutes,
                            selectIndex = selectedMinutes,
                            visibleCount = 4,
                            onSelect = { index, item ->
                                selectedMinutes = index
                            },
                            content = { item ->
                                Text(text = item, fontSize = 18.sp)
                            }
                        )
                    }
                }
            )

        }
    }
}

fun<T> a(index: Int, item: T) {

}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDate(record: String, change: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        var expand by remember {
            mutableStateOf(false)
        }
        val datePickerState = rememberDatePickerState()
        var selectedDate by remember {
            mutableStateOf(record)
        }
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {  },
            colors = OutlinedTextFieldDefaults.colors(
//                disabledTextColor = Color.Transparent,
                focusedBorderColor = Color(0xFF4F7B39),
                unfocusedBorderColor = Color(0xFFB0B4B4),
//                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            modifier = Modifier
                .width(350.dp),
            label = { Text(text = "会议日期") },
            readOnly = true,
            trailingIcon = { IconButton(onClick = { expand = !expand  },) {
                Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = "")
            } },
        )
        if(expand) {
            DatePickerDialog(
                onDismissRequest = { expand = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            expand = false
                            val selectedDateMillis = datePickerState.selectedDateMillis
                            val instant = selectedDateMillis?.let { Instant.ofEpochMilli(it) }
                            val date = instant?.atZone(ZoneId.systemDefault())?.toLocalDate()
                            val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")
                            val formattedDate = date?.format(formatter)
                            if (formattedDate != null) {
                                selectedDate = formattedDate
                                change(selectedDate)
                            }
                        }
                    ) {
                        Text("确定")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputRoom(record: String, change: (String) -> Unit) {
    val roomName = arrayOf(
        "沙河校区 二教103",
        "沙河校区 二教204",
        "沙河校区 信软楼304",
        "沙河校区 信软楼306",
        "沙河校区 主楼224"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(if(record != "") record else roomName[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier
                .width(350.dp)
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { change(it) },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
//                disabledTextColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF4F7B39),
                    unfocusedBorderColor = Color(0xFFB0B4B4),
//                disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                label = { Text(text = "会议室") },
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                roomName.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            change(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTitle(record: String, change: (String) -> Unit) {
    var title by remember {
        mutableStateOf(record)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it; change(it) },
            colors = OutlinedTextFieldDefaults.colors(
//                disabledTextColor = Color.Transparent,
                focusedBorderColor = Color(0xFF4F7B39),
                unfocusedBorderColor = Color(0xFFB0B4B4),
//                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            label = { Text(text = "会议主题", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .width(350.dp),
            placeholder = { Text(text = "请输入会议主题")},
            singleLine = true
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(jump: (page: String, msg: String) -> Unit) {
    CenterAlignedTopAppBar(
        title = {  },
        navigationIcon = {
            IconButton(onClick = { jump("Main", "") }) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        ),
        actions = {
            Text(
                text = "完成",
                fontSize = 18.sp,
                color = Color(0xFF79bd9a),
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clickable { jump("Main", "预定成功") }
            )
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreservePreview() {
    RaspiTheme {
        val conf = PreConf(
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
        Preserve(conf, fun(String, S, conf){})
    }
}