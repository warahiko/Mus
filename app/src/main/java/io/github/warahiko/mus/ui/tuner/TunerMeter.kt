package io.github.warahiko.mus.ui.tuner

import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import io.github.warahiko.mus.ui.theme.MusAppTheme
import io.github.warahiko.mus.ui.util.PreviewThemes
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TunerMeter(
    value: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    tickColor: Color = MaterialTheme.colorScheme.onBackground,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
) {
    BoxWithConstraints(modifier = modifier) {
        val centerCircleRadiusDp = 8.dp
        val textSizeDp = 8.dp
        val textMarginDp = 2.dp
        val longLineLengthDp = 8.dp
        val shortLineLengthDp = 6.dp

        val radiusDp = maxWidth / 2 - longLineLengthDp
        val centerDp = DpOffset(x = maxWidth / 2, y = textSizeDp + textMarginDp + longLineLengthDp / 2 + radiusDp)

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(centerDp.y + centerCircleRadiusDp / 2),
        ) {
            val textPaint = Paint().asFrameworkPaint().also { paint ->
                paint.isAntiAlias = true
                paint.textSize = textSizeDp.toPx()
            }

            val centerCircleRadius = centerCircleRadiusDp.toPx()
            val textMargin = textMarginDp.toPx()
            val longLineLength = longLineLengthDp.toPx()
            val shortLineLength = shortLineLengthDp.toPx()
            val center = Offset(x = centerDp.x.toPx(), y = centerDp.y.toPx())
            val radius = radiusDp.toPx()

            val longLineInner = radius - longLineLength / 2
            val longLineOuter = radius + longLineLength / 2

            val shortLineInner = radius - shortLineLength / 2
            val shortLineOuter = radius + shortLineLength / 2

            drawArc(
                color = backgroundColor,
                topLeft = center - Offset(radius, radius),
                size = Size(width = 2 * radius, height = 2 * radius),
                startAngle = 187.5f,
                sweepAngle = 165f,
                useCenter = true,
            )
            repeat(11) {
                val radian = -(2 * PI * ((1 + it) / 24f)).toFloat()
                val cosR = cos(radian)
                val sinR = sin(radian)
                drawLine(
                    color = tickColor,
                    start = center + Offset(x = longLineInner * cosR, y = longLineInner * sinR),
                    end = center + Offset(x = longLineOuter * cosR, y = longLineOuter * sinR),
                    strokeWidth = 4f,
                )
                withTransform({
                    rotate(degrees = (it - 5) * -15f, pivot = center)
                }) {
                    drawIntoCanvas { canvas ->
                        val bottomCenter = center + Offset(x = 0f, y = -(longLineOuter + textMargin))
                        val text = ((it - 5) * -10).toString()

                        val bounds = Rect()
                        textPaint.getTextBounds(text, 0, text.length, bounds)
                        canvas.nativeCanvas.drawText(
                            text,
                            bottomCenter.x - bounds.width() / 2f,
                            bottomCenter.y,
                            textPaint,
                        )
                    }
                }
            }
            repeat(10) {
                val radian = -(2 * PI * ((1.5f + it) / 24f)).toFloat()
                val cosR = cos(radian)
                val sinR = sin(radian)
                drawLine(
                    color = tickColor,
                    start = center + Offset(x = shortLineInner * cosR, y = shortLineInner * sinR),
                    end = center + Offset(x = shortLineOuter * cosR, y = shortLineOuter * sinR),
                    strokeWidth = 1f,
                )
            }

            drawCircle(
                color = indicatorColor,
                center = center,
                radius = centerCircleRadius,
            )

            val radian = -(PI * (60f - value) / 120f).toFloat()
            drawLine(
                color = indicatorColor,
                start = center,
                end = center + Offset(x = longLineInner * cos(radian), y = longLineInner * sin(radian)),
                strokeWidth = 8f,
            )
        }
    }
}

@PreviewThemes
@Composable
fun TunerMeterPreview() {
    MusAppTheme {
        TunerMeter(value = 21f)
    }
}
