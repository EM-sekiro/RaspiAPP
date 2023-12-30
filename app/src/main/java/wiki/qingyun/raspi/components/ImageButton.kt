package wiki.qingyun.raspi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wiki.qingyun.raspi.R
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun ImageButton(title: String, image: Painter, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFFFFF)
            ),
            modifier = Modifier
                .size(60.dp)
                .clickable { onClick() }
        ) {
            Column(
                modifier = Modifier.size(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp))
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = title,
            fontSize = 12.sp)
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