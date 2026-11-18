package os.aurora.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import os.aurora.settings.ui.theme.AuroraPalette

private data class Entry(val title: String, val subtitle: String, val page: String)
private data class Group(val title: String, val entries: List<Entry>)

private val groups = listOf(
    Group("Connectivity", listOf(
        Entry("Network & internet", "Wi-Fi, mobile data, hotspot and SIMs", "Network & internet"),
        Entry("Connected devices", "Bluetooth, pairing and connection preferences", "Connected devices")
    )),
    Group("Personalisation", listOf(
        Entry("Wallpaper & style", "Wallpaper, colours, icons and themes", "Wallpaper & style"),
        Entry("Display", "Brightness, refresh rate, timeout and dark theme", "Display"),
        Entry("Sound & vibration", "Volume, ringtones, vibration and audio", "Sound & vibration")
    )),
    Group("Device", listOf(
        Entry("Battery", "Battery usage and power preferences", "Battery"),
        Entry("Storage", "Space used by apps and files", "Storage"),
        Entry("Performance", "Performance and thermal profiles", "Performance")
    )),
    Group("Security & privacy", listOf(
        Entry("Security & privacy", "Screen lock, permissions and privacy", "Security & privacy"),
        Entry("Apps", "Installed apps, defaults and permissions", "Apps")
    )),
    Group("System", listOf(
        Entry("Gestures & navigation", "Navigation and gesture preferences", "Gestures & navigation"),
        Entry("System updates", "Software update status", "System updates"),
        Entry("About phone", "Device information and build details", "About phone")
    ))
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AuroraSettingsApp() }
    }
}

@Composable
private fun AuroraSettingsApp() {
    var page by remember { mutableStateOf<String?>(null) }
    var query by remember { mutableStateOf("") }
    MaterialTheme(colorScheme = lightColorScheme(
        background = AuroraPalette.Porcelain, surface = Color.White,
        onBackground = AuroraPalette.Charcoal, onSurface = AuroraPalette.Charcoal,
        primary = AuroraPalette.Sage
    )) {
        Surface(Modifier.fillMaxSize(), color = AuroraPalette.Porcelain) {
            if (page == null) HomeScreen(query, { query = it }, { page = it })
            else DetailScreen(page!!, { page = null })
        }
    }
}

@Composable
private fun HomeScreen(query: String, onQuery: (String) -> Unit, onOpen: (String) -> Unit) {
    val entries = groups.flatMap { it.entries }
    val results = entries.filter { it.title.contains(query, true) || it.subtitle.contains(query, true) }
    Column(Modifier.fillMaxSize().background(AuroraPalette.Porcelain).padding(horizontal = 24.dp, vertical = 24.dp)) {
        Text("AURORA  /  SYSTEM", fontSize = 11.sp, letterSpacing = 1.8.sp, color = AuroraPalette.Sage)
        Spacer(Modifier.height(22.dp))
        Text("Settings", fontSize = 36.sp, fontWeight = FontWeight.SemiBold, color = AuroraPalette.Charcoal)
        Text("Make your phone feel like yours.", fontSize = 14.sp, color = AuroraPalette.Charcoal.copy(alpha = 0.66f))
        Spacer(Modifier.height(22.dp))
        OutlinedTextField(value = query, onValueChange = onQuery, modifier = Modifier.fillMaxWidth(),
            singleLine = true, shape = RoundedCornerShape(18.dp), placeholder = { Text("⌕  Search settings") })
        Spacer(Modifier.height(14.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(18.dp)) {
            if (query.isBlank()) {
                items(groups) { group ->
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        GroupLabel(group.title)
                        Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp))
                            .background(Color.White.copy(alpha = 0.58f)).padding(8.dp)) {
                            group.entries.forEach { EntryRow(it, onOpen) }
                        }
                    }
                }
            } else {
                item { GroupLabel("Search results") }
                item {
                    Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp))
                        .background(Color.White.copy(alpha = 0.58f)).padding(8.dp)) {
                        if (results.isEmpty()) Text("No settings found", Modifier.padding(16.dp))
                        results.forEach { EntryRow(it, onOpen) }
                    }
                }
            }
        }
    }
}

@Composable
private fun GroupLabel(label: String) {
    Text(label.uppercase(), fontSize = 11.sp, letterSpacing = 1.2.sp,
        color = AuroraPalette.Sage, fontWeight = FontWeight.SemiBold)
}

@Composable
private fun EntryRow(entry: Entry, onOpen: (String) -> Unit) {
    Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).clickable { onOpen(entry.page) }
        .padding(horizontal = 16.dp, vertical = 13.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(entry.title, color = AuroraPalette.Charcoal, fontSize = 15.sp)
            Text(entry.subtitle, color = AuroraPalette.Charcoal.copy(alpha = 0.58f), fontSize = 11.sp)
        }
        Text("›", color = AuroraPalette.Sage, fontSize = 22.sp)
    }
}

@Composable
private fun DetailScreen(page: String, onBack: () -> Unit) {
    val context = LocalContext.current
    val previews = remember { mutableStateMapOf<String, Boolean>() }
    var amount by remember { mutableFloatStateOf(0.5f) }
    val description = when (page) {
        "Network & internet" -> "Manage how your phone connects."
        "Connected devices" -> "Pair accessories and manage nearby connections."
        "Wallpaper & style" -> "Shape the look and feel of Aurora."
        "Display" -> "Make the screen comfortable to use."
        "Sound & vibration" -> "Tune sounds and haptic feedback."
        "Battery" -> "Understand power use and battery health."
        "Storage" -> "See what's taking up space."
        "Performance" -> "Choose how your phone balances speed and efficiency."
        "Security & privacy" -> "Review device protection and app access."
        "Apps" -> "Manage installed apps and permissions."
        "Gestures & navigation" -> "Choose how you move around your phone."
        "System updates" -> "Keep your system up to date."
        else -> "Device details and system information."
    }
    val systemAction = when (page) {
        "Network & internet" -> Settings.ACTION_WIRELESS_SETTINGS
        "Connected devices" -> Settings.ACTION_BLUETOOTH_SETTINGS
        "Display" -> Settings.ACTION_DISPLAY_SETTINGS
        "Sound & vibration" -> Settings.ACTION_SOUND_SETTINGS
        "Battery" -> Settings.ACTION_BATTERY_SAVER_SETTINGS
        "Storage" -> Settings.ACTION_INTERNAL_STORAGE_SETTINGS
        "Security & privacy" -> Settings.ACTION_SECURITY_SETTINGS
        "Apps" -> Settings.ACTION_APPLICATION_SETTINGS
        "About phone" -> Settings.ACTION_DEVICE_INFO_SETTINGS
        else -> Settings.ACTION_SETTINGS
    }
    val options = when (page) {
        "Network & internet" -> listOf("Wi-Fi and mobile network", "Hotspot and tethering", "SIM preferences")
        "Connected devices" -> listOf("Bluetooth and pairing", "Connection preferences")
        "Wallpaper & style" -> listOf("Wallpaper", "Colour palette", "Icon style")
        "Display" -> listOf("Brightness", "Refresh rate", "Screen timeout", "Dark theme")
        "Sound & vibration" -> listOf("Media volume", "Ringtone", "Vibration and haptics", "Audio effects")
        "Battery" -> listOf("Battery usage", "Battery saver", "Charging information")
        "Storage" -> listOf("Storage overview", "App storage", "Free up space")
        "Performance" -> listOf("Balanced profile", "Performance profile", "Battery saver profile", "Thermal information")
        "Security & privacy" -> listOf("Screen lock", "Privacy dashboard", "Permission manager")
        "Apps" -> listOf("Installed apps", "Default apps", "Special app access")
        "Gestures & navigation" -> listOf("System navigation", "Gesture preferences", "One-handed mode")
        "System updates" -> listOf("Check for updates", "Update history")
        else -> listOf("Device information", "Android version", "Build number", "Legal information")
    }
    Column(Modifier.fillMaxSize().background(AuroraPalette.Porcelain).padding(horizontal = 24.dp, vertical = 24.dp)) {
        Text("‹  BACK", Modifier.clip(RoundedCornerShape(12.dp)).clickable { onBack() }.padding(vertical = 8.dp),
            color = AuroraPalette.Sage, fontSize = 12.sp, letterSpacing = 1.4.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(18.dp))
        Text("AURORA  /  SETTINGS", fontSize = 11.sp, letterSpacing = 1.8.sp, color = AuroraPalette.Sage)
        Spacer(Modifier.height(14.dp))
        Text(page, fontSize = 30.sp, fontWeight = FontWeight.SemiBold, color = AuroraPalette.Charcoal)
        Text(description, fontSize = 14.sp, color = AuroraPalette.Charcoal.copy(alpha = 0.65f))
        Spacer(Modifier.height(20.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            item {
                Card(colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.62f)),
                    shape = RoundedCornerShape(20.dp)) {
                    Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Quick controls", fontWeight = FontWeight.SemiBold)
                        if (page == "Display" || page == "Sound & vibration") {
                            Text(if (page == "Display") "Display level preview" else "Volume preview", fontSize = 13.sp)
                            Slider(value = amount, onValueChange = { amount = it })
                        }
                        if (page == "Performance") {
                            Text("Profile preview", fontSize = 13.sp)
                            listOf("Balanced", "Performance", "Battery saver").forEach { name ->
                                Row(Modifier.fillMaxWidth().clickable { previews["profile"] = name == "Performance" }
                                    .padding(vertical = 5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(name)
                                    Text(if ((previews["profile"] == true) == (name == "Performance")) "Selected" else "›",
                                        color = AuroraPalette.Sage)
                                }
                            }
                        } else {
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Preview setting", fontSize = 14.sp)
                                Switch(checked = previews[page] ?: false, onCheckedChange = { previews[page] = it })
                            }
                        }
                        Text("These controls are previews. System-level integration is being developed.",
                            fontSize = 11.sp, color = AuroraPalette.Charcoal.copy(alpha = 0.58f))
                    }
                }
            }
            item { GroupLabel("Available settings") }
            item {
                Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp))
                    .background(Color.White.copy(alpha = 0.58f)).padding(8.dp)) {
                    options.forEach { option ->
                        Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).clickable {
                            try { context.startActivity(Intent(systemAction)) }
                            catch (_: Exception) { context.startActivity(Intent(Settings.ACTION_SETTINGS)) }
                        }.padding(horizontal = 16.dp, vertical = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(option, color = AuroraPalette.Charcoal, fontSize = 14.sp)
                            Text("›", color = AuroraPalette.Sage, fontSize = 21.sp)
                        }
                    }
                }
            }
        }
    }
}
