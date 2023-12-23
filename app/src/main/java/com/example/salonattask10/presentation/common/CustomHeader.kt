package com.example.salonattask10.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.salonattask10.R
import com.example.salonattask10.presentation.Dimens

@Composable
fun CustomHeader(headerTitle: String, onClick: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.NavigationButtonHeight2)
            .background(
                colorResource(id = R.color.lightPink)
            )
    ) {
        val (addService, backArrow) = createRefs()
        Text(
            text = headerTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(addService) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "icon_back",
            modifier = Modifier
                .constrainAs(backArrow) {
                    start.linkTo(parent.start, margin = 12.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickable { onClick() }
        )
    }
}