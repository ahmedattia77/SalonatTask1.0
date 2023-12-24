package com.example.salonattask10.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.colorResource
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.salonattask10.R
import com.example.salonattask10.data.model.serviceJON.Data

fun Modifier.shimmerEffect(cornerRadius: CornerRadius = CornerRadius(x = 12f, y = 12f)) = composed {
    val transition = rememberInfiniteTransition(label = "shimmer effect")
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "transparency of the background color"
    ).value
    val color = Color.Gray.copy(alpha = alpha)
    drawBehind {
        drawRoundRect(
            color = color,
            cornerRadius = cornerRadius
        )
    }
}

@Composable
fun SingleItemShimmerEffect() {
    val shape = RoundedCornerShape(12.dp)

    ConstraintLayout(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 24.dp)
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .shimmerEffect()
    ) {
        val (category, serviceType, icon) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .constrainAs(category) {
                    start.linkTo(parent.start, margin = 4.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .constrainAs(serviceType) {
                    start.linkTo(category.end, margin = 4.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}