package ru.gr05307.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.gr05307.viewmodels.MainViewModel


// Custom stop icon during keyframe playback
@Composable
fun StopIcon(
    modifier: Modifier = Modifier,
    iconSize: Dp = 48.dp,
    isActive: Boolean = false,
    color: Color = Color.Black,
    padding: Float = 0.1f // Relative padding inside icon (0-0.5)
) {
    Canvas(
        modifier = modifier.size(iconSize)
    ) {
        val totalWidth = size.width
        val totalHeight = size.height

        // Calculate padding in pixels
        val paddingPx = totalWidth * padding

        // Draw square
        drawRect(
            color = color,
            alpha = if (!isActive) .5f else 1.0f,
            topLeft = Offset(x = paddingPx, y = paddingPx),
            size = Size(
                width = totalWidth - 2 * paddingPx,
                height = totalHeight - 2 * paddingPx
            )
        )
    }
}



@Composable
fun keyframeItem(
    keyframe: MainViewModel.TourKeyframe,
    onGoto: () -> Unit,
    onDelete: () -> Unit,
    isPlaying: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        elevation = 1.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    keyframe.name,
                    style = MaterialTheme.typography.body2,
                    maxLines = 1
                )
                Text(
                    "X: [${String.format("%.3f", keyframe.xMin)}, ${String.format("%.3f", keyframe.xMax)}] " +
                            "Y: [${String.format("%.3f", keyframe.yMin)}, ${String.format("%.3f", keyframe.yMax)}]",
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray
                )
            }

            IconButton(
                onClick = onGoto,
                enabled = !isPlaying
            ) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Go to",
                    tint = NeutralDark
                )
            }


            IconButton(
                onClick = onDelete,
                enabled = !isPlaying
            ) {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colors.error
                )
            }
        }
    }
}
