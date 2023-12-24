package com.example.salonattask10.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.salonattask10.R
import com.example.salonattask10.data.model.serviceJON.Data

@Composable
fun SingleItem(
    item: Data,
    onClick: (Data) -> Unit
) {
    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 24.dp)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize()
                .clickable { onClick(item) }
        ) {
            val (category, serviceType, icon) = createRefs()
            Text(
                text = item.category_name,
                fontWeight = W600,
                modifier = Modifier
                    .constrainAs(category) {
                        start.linkTo(parent.start, margin = 8.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(color = Color.Transparent),
            )
            Text(
                text = item.name,
                color = colorResource(id = R.color.darkPink),
                modifier = Modifier
                    .constrainAs(serviceType) {
                        start.linkTo(category.end, margin = 4.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(color = Color.Transparent)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(icon) {
                        end.linkTo(parent.end, margin = 4.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(color = Color.Transparent)
            )
        }
    }
}

