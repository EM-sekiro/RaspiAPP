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
import wiki.qingyun.raspi.presentation.Info
import wiki.qingyun.raspi.presentation.Login
import wiki.qingyun.raspi.presentation.Main
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
                            Main {
                                layout = it
                            }
                        }
                        "Info" -> {
                            Info {
                                layout = it
                            }
                        }
                        "Preserve" -> {
                            Preserve {page, msg ->
                                layout = page
                                if(msg != "") {
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        "Calendar" -> {
                            Calendar(this) {
                                layout = it
                            }
                        }
                        "StaffList" -> {
                            StaffList {
                                layout = it
                            }
                        }
                        else -> {
                            Main {
                                layout = it
                            }
                        }
                    }
                }
            }
        }
    }
}




