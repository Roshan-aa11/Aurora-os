package os.aurora.settings.ui.theme

import androidx.compose.ui.unit.dp

/** Shared spacing, shape and motion tokens for every Aurora surface. */
object AuroraSpacing {
    val XSmall = 4.dp
    val Small = 8.dp
    val Medium = 12.dp
    val Large = 16.dp
    val XLarge = 24.dp
    val XXLarge = 32.dp
}

object AuroraShapes {
    val Control = 12.dp
    val Row = 14.dp
    val Card = 20.dp
    val Panel = 24.dp
    val Search = 18.dp
}

object AuroraMotion {
    const val QuickMillis = 120
    const val StandardMillis = 220
    const val EmphasisedMillis = 320
}
