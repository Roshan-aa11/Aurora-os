package os.aurora.settings.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AuroraLight = lightColorScheme(
    primary = AuroraPalette.Sage,
    onPrimary = Color.White,
    secondary = AuroraPalette.Clay,
    onSecondary = AuroraPalette.Charcoal,
    tertiary = AuroraPalette.Mist,
    background = AuroraPalette.Porcelain,
    onBackground = AuroraPalette.Charcoal,
    surface = Color(0xFFF8F7F1),
    onSurface = AuroraPalette.Charcoal,
    surfaceVariant = Color(0xFFE3E3DA),
    onSurfaceVariant = Color(0xFF59615B),
    outline = Color(0xFFB7BDB4)
)

private val AuroraDark = darkColorScheme(
    primary = Color(0xFFA9B9A9),
    onPrimary = Color(0xFF202522),
    secondary = Color(0xFFD0A18F),
    onSecondary = Color(0xFF252A27),
    tertiary = Color(0xFFA9B9C0),
    background = AuroraPalette.DarkSurface,
    onBackground = AuroraPalette.DarkText,
    surface = AuroraPalette.DarkSurfaceRaised,
    onSurface = AuroraPalette.DarkText,
    surfaceVariant = Color(0xFF39423C),
    onSurfaceVariant = AuroraPalette.DarkMutedText,
    outline = Color(0xFF68736B)
)

@Composable
fun AuroraTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) AuroraDark else AuroraLight,
        content = content
    )
}
