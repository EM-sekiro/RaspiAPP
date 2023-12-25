package wiki.qingyun.raspi.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import wiki.qingyun.raspi.ui.theme.RaspiTheme

@Composable
fun StaffList(jump : (String) -> Unit) {

}

@Preview
@Composable
fun StaffListPreview() {
    RaspiTheme {
        StaffList(fun(String){})
    }
}