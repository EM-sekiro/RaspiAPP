package wiki.qingyun.raspi.presentation

import android.app.TimePickerDialog
import android.os.Build
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.TestModifierUpdaterLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.ui.theme.RaspiTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.sin

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Preserve(jump : (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCFCF5))
    ) {
        TopAppBar(jump)
        Spacer(modifier = Modifier.height(15.dp))
        InputTitle()
        Spacer(modifier = Modifier.height(20.dp))
        InputRoom()
        Spacer(modifier = Modifier.height(20.dp))
        InputDate()
        Spacer(modifier = Modifier.height(20.dp))
        InputTime()
        Spacer(modifier = Modifier.height(20.dp))
        InputPeople()
        Spacer(modifier = Modifier.height(20.dp))
        InputBrief()
    }
}

@Composable
fun InputBrief() {
    var brief by remember {
        mutableStateOf("")
    }
    Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(Color.Transparent),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = brief,
            onValueChange = { brief = it },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputPeople() {
    val roomName = arrayOf(
        "沙河校区 二教103",
        "沙河校区 二教204",
        "沙河校区 信软楼304",
        "沙河校区 信软楼306",
        "沙河校区 主楼224"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(roomName[0]) }

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
                onValueChange = {},
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
                label = { Text(text = "参会人员") },
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
fun InputTime() {
    var selectedTime_S by remember {
        mutableStateOf("")
    }
    var expanded_S by remember {
        mutableStateOf(false)
    }
    var selectedTime_E by remember {
        mutableStateOf("")
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
            value = selectedTime_S,
            onValueChange = {},
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
            trailingIcon = { IconButton(onClick = { expanded_S = !expanded_S  },) {
                Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = "")
            } },
        )
        OutlinedTextField(
            value = selectedTime_E,
            onValueChange = {},
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
            trailingIcon = { IconButton(onClick = { expanded_E = !expanded_E  },) {
                Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = "")
            } },
        )
        if(expanded_E) {
            AlertDialog(onDismissRequest = { expanded_E = false }) {
                TimeInput(
                    state = rememberTimePickerState(),
                    colors = TimePickerDefaults.colors(
                        containerColor = Color(0xFFFFFFFF)
                    )
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDate() {
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
            mutableStateOf("")
        }
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
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
fun InputRoom() {
    val roomName = arrayOf(
        "沙河校区 二教103",
        "沙河校区 二教204",
        "沙河校区 信软楼304",
        "沙河校区 信软楼306",
        "沙河校区 主楼224"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(roomName[0]) }

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
                onValueChange = {},
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
fun InputTitle() {
    var title by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
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
fun TopAppBar(jump: (String) -> Unit) {
    CenterAlignedTopAppBar(
        title = {  },
        navigationIcon = {
            IconButton(onClick = { jump("Main") }) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFFFCFCF5)
        ),
        actions = {
            Text(
                text = "完成",
                fontSize = 18.sp,
                color = Color(0xFF386A20),
                modifier = Modifier
                    .padding(end = 5.dp)
            )
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreservePreview() {
    RaspiTheme {
        Preserve(fun(String){})
    }
}