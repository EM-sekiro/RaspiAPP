package wiki.qingyun.raspi.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.components.DropdownList
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun StaffList(confPerson: MutableList<Person>, jump : (String, MutableList<Person>) -> Unit) {
    val people1 = mutableListOf(
        Person(0, "张三", R.drawable.images),
        Person(1, "李四", R.drawable.images),
        Person(2, "王五", R.drawable.images),
        Person(3, "赵六", R.drawable.images),
        Person(4, "钱七", R.drawable.images),
    )

    val people2 = mutableListOf(
        Person(5, "孙八", R.drawable.images),
        Person(6, "周九", R.drawable.images),
        Person(7, "吴十", R.drawable.images),
    )

    val people3 = mutableListOf(
        Person(8, "郑十一", R.drawable.images),
    )

    val people4 = mutableListOf(
        Person(9, "刘十二", R.drawable.images),
        Person(10, "陈十三", R.drawable.images),
    )

    val people5 = mutableListOf(
        Person(11, "黄十五", R.drawable.images),
        Person(12, "罗十六", R.drawable.images),
        Person(13, "梁十七", R.drawable.images),
        Person(14, "杨十四", R.drawable.images),
    )

    var people: MutableList<Person> = mutableListOf()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        TopBarS(){page, process ->
            if(process == "cancel")
                jump("Preserve", confPerson)
            else if(process == "finish"){
                jump("Preserve", people)
            }
        }
        DropdownList(title = "研发部", people = people1){flag, target ->
            if(flag)
                people.add(target)
            else people.remove(target)
        }
        DropdownList(title = "销售部", people = people2){flag, target ->
            if(flag)
                people.add(target)
            else people.remove(target)
        }
        DropdownList(title = "人事部", people = people3){flag, target ->
            if(flag)
                people.add(target)
            else people.remove(target)
        }
        DropdownList(title = "财务部", people = people4){flag, target ->
            if(flag)
                people.add(target)
            else people.remove(target)
        }
        DropdownList(title = "市场部", people = people5){flag, target ->
            if(flag)
                people.add(target)
            else people.remove(target)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarS(jump: (page: String, result: String) -> Unit) {
    CenterAlignedTopAppBar(
        title = {  },
        navigationIcon = {
            IconButton(onClick = { jump("Preserve", "cancel") }) {
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
                    .clickable { jump("Preserve", "finish") }
            )
        }
    )
}

@Preview
@Composable
fun StaffListPreview() {
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
        StaffList(conf.people){a, b ->

        }
    }
}