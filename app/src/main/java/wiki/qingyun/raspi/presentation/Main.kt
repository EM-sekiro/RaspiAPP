package wiki.qingyun.raspi.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.relay.compose.BoxScopeInstanceImpl.align
import com.google.relay.compose.ColumnScopeInstanceImpl.weight
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun Main() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Fragment()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
        ) {
            NavigationBar()
        }
    }
}

@Composable
fun Fragment() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFFFFFFF))
    ) {

    }
}

@Composable
fun NavigationBar() {
    var selectedItem by remember {
        mutableStateOf(0)
    }

    NavigationBar (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ){
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 },
            icon = { Icon(Icons.Filled.Home, contentDescription = "") },
            label = { Text(text = "主页") })
        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            icon = { Icon(Icons.Filled.Email, contentDescription = "") },
            label = { Text(text = "消息")})
        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 },
            icon = { Icon(Icons.Filled.Person, contentDescription = "") },
            label = { Text(text = "我的")})
    }
}


@Preview
@Composable
fun MainPreview() {
    RaspiTheme {
        Main()
    }
}
