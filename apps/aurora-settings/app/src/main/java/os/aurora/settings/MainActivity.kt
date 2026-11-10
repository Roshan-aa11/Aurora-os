package os.aurora.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import os.aurora.settings.ui.theme.AuroraPalette

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AuroraSettingsApp() }
    }
}

@Composable
private fun AuroraSettingsApp() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            background = AuroraPalette.Porcelain,
            surface = AuroraPalette.Porcelain,
            onBackground = AuroraPalette.Charcoal,
            onSurface = AuroraPalette.Charcoal,
            primary = AuroraPalette.Sage,
        )
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AuroraPalette.Porcelain)
                    .padding(horizontal = 24.dp, vertical = 28.dp),
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                Text("AURORA  /  SYSTEM", fontSize = 11.sp, letterSpacing = 1.8.sp, color = AuroraPalette.Sage)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Settings", fontSize = 36.sp, fontWeight = FontWeight.SemiBold, color = AuroraPalette.Charcoal)
                    Text("Make your phone feel like yours.", fontSize = 14.sp, color = AuroraPalette.Charcoal.copy(alpha = 0.66f))
                }
                SearchPanel()
                SettingsSection("Connectivity", listOf("Network & internet", "Connected devices"))
                SettingsSection("Personalisation", listOf("Wallpaper & style", "Display", "Sound & vibration"))
                SettingsSection("Device", listOf("Battery", "Storage", "Security & privacy"))
            }
        }
    }
}

@Composable
private fun SearchPanel() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.62f), RoundedCornerShape(18.dp))
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        Text("⌕   Search settings", color = AuroraPalette.Charcoal.copy(alpha = 0.58f), fontSize = 15.sp)
    }
}

@Composable
private fun SettingsSection(title: String, items: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title.uppercase(), fontSize = 11.sp, letterSpacing = 1.2.sp, color = AuroraPalette.Sage, fontWeight = FontWeight.SemiBold)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.48f), RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            items.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item, color = AuroraPalette.Charcoal, fontSize = 15.sp)
                    Text("›", color = AuroraPalette.Sage, fontSize = 22.sp)
                }
                if (index != items.lastIndex) Spacer(Modifier.height(1.dp))
            }
        }
    }
}
