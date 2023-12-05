package wiki.qingyun.raspi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.ui.theme.RaspiTheme

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
fun NaviItemPreview() {
    RaspiTheme {
        NaviItem(
            label = "个人信息",
            image = painterResource(id = R.drawable.info)
        ) {}
    }
}