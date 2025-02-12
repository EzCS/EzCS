package ru.gmasalskikh.ezcs.screens.preview.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import ru.gmasalskikh.ezcs.screens.preview.PreviewViewState
import ru.gmasalskikh.ezcs.ui.theme.fontSize12Sp
import java.util.*

@Composable
fun PreviewContent(
    currentTopic: String,
    mainColor: Color,
    accentColor: Color,
    backgroundColor: Color,
    border: BorderStroke,
    shape: CornerBasedShape,
    setPagerState: (PagerState) -> Unit,
    pagerState: PagerState,
    items: List<PreviewViewState.PreviewItem>,
    currentIndexPage: Int,
    navigateToMainMenu: () -> Unit,
    skipText: String,
) {
    Column {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = currentTopic.toUpperCase(Locale.getDefault()),
                color = mainColor
            )
        }
        ViewPager(
            modifier = Modifier.weight(1f),
            items = items,
            pagerState = pagerState
        ) { scope, item ->
            scope.state
            setPagerState(scope.state)
            PreviewItem(
                border = border,
                shape = shape,
                backgroundColor = backgroundColor
            ) {
                CoilImage(
                    modifier = Modifier.padding(vertical = 10.dp),
                    data = item.imageRes,
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator()
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Dots(
                modifier = Modifier.align(Alignment.Center),
                size = 8.dp,
                amountDots = items.size,
                color = mainColor, accentColor = accentColor,
                indexSelectedDot = currentIndexPage
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(onClick = navigateToMainMenu)
                    .padding(end = 20.dp),
                text = "${skipText.toUpperCase(Locale.getDefault())} >",
                color = accentColor,
                fontSize = fontSize12Sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}