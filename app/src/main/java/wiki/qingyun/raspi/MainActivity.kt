package wiki.qingyun.raspi
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import wiki.qingyun.raspi.presentation.Login
import wiki.qingyun.raspi.presentation.Main
import wiki.qingyun.raspi.presentation.NavigationDraw
import wiki.qingyun.raspi.ui.theme.RaspiTheme

class MainActivity : ComponentActivity() {
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
                            Main()
                        }
                    }
                }
            }
        }
    }
}




