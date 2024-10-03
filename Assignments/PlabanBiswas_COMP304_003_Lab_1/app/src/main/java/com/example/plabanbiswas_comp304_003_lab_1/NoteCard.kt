package com.example.plabanbiswas_comp304_003_lab_1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.sql.Timestamp

@Composable
fun NoteCardData(title: String, content: String, image: Int, timestamp: Timestamp)
{
    val timestampInMinsResolution =
        timestamp.toString().substring(0, timestamp.toString().length - 7)

    ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium

    ) {
        Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
        ) {
//            Image(
//                    painter = painterResource(id = image),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(130.dp)
//                        .padding(8.dp),
//                    contentScale = ContentScale.Fit
//            )

            Column(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
            ) {
                Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = content,
                        style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                        modifier = Modifier.fillMaxWidth().alpha(0.5f),
                        textAlign = TextAlign.Right,
                        text = timestampInMinsResolution,
                        style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}
