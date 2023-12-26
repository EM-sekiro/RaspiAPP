package wiki.qingyun.raspi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.presentation.Person
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun DropdownList(title: String, people: MutableList<Person>?, add: (flag: Boolean, person: Person) -> Unit) {
    var expended by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { expended = !expended },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painter =
                    if(!expended)
                        painterResource(id = R.drawable.arrow_right)
                    else painterResource(id = R.drawable.arrow_down),
                contentDescription = ""
            )
            Text(text = title, fontSize = 17.sp)
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (people != null) {
                    Text(
                        text = people.size.toString() + "人",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }else {
                    Text(
                        text = "0人",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
        if(expended) {
            people?.forEach {target ->
                var check by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                        .clickable {
                            check = !check
                            add(check, target)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        //用户头像
                        painter = painterResource(id = target.photo),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(40.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = target.name, fontSize = 15.sp)
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = check,
                            onCheckedChange = {
                                check = !check
                                add(check, target)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun DropdownListPreview() {
    val people = mutableListOf(
        Person(0, "张三", R.drawable.images),
        Person(0, "李四", R.drawable.images),
        Person(0, "王五", R.drawable.images),
        Person(0, "赵六", R.drawable.images),
        Person(0, "钱七", R.drawable.images),
    )
    RaspiTheme {
        DropdownList("研发部门", people){a, b ->

        }
    }
}