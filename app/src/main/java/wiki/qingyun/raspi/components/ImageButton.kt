package wiki.qingyun.raspi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun ImageButton(title: String, image: Painter, onClick: () -> Unit) {
    IconButton(
        modifier = Modifier
            .size(100.dp),
        onClick = { onClick() }
    ) {
        Column(
            modifier = Modifier
                .width(100.dp)
                .height(70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp))
            Text(
                text = title,
                fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun ImageButtonPreview() {
    RaspiTheme {
        ImageButton(
            title = "扫一扫",
            image = painterResource(id = R.drawable.scan)
        ) {
            //onClick
        }
    }
}