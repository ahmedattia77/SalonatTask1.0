package com.example.salonattask10.presentation.addServiceScreen


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.salonattask10.R
import com.example.salonattask10.data.model.categoriesJON.Data
import com.example.salonattask10.presentation.Dimens
import com.example.salonattask10.presentation.addServiceScreen.component.AddServiceCard
import com.example.salonattask10.presentation.addServiceScreen.component.Checkbox
import com.example.salonattask10.presentation.addServiceScreen.component.CustomButton
import com.example.salonattask10.presentation.addServiceScreen.component.DropDown
import com.example.salonattask10.presentation.addServiceScreen.component.RadioButtonComponent
import com.example.salonattask10.presentation.addServiceScreen.component.TextField
import com.example.salonattask10.presentation.login.component.CircleProgressbar


@Composable
fun AddServiceScreen(
    categoryList: List<Data>?
) {
    val context = LocalContext.current
    val categoryId = remember { mutableIntStateOf(0)}

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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
                text = "Add Service",
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
                contentDescription = "not",
                modifier = Modifier.constrainAs(backArrow) {
                    start.linkTo(parent.start, margin = 12.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (categoryList == null)
            CircleProgressbar()
        else
            DropDown(
                hint = R.string.categories,
                list = categoryList,
                onClick = { categoryId.intValue = it.id })

        Spacer(modifier = Modifier.height(20.dp))
        if (categoryId.value != 0) {
            val viewmodel: AddServiceViewModel = hiltViewModel()
            viewmodel.getCategoryService(categoryId.value)
            val categoryServices = viewmodel.categoryServicesSate.value.data?.data
            if (categoryServices != null)
                DropDown(
                    hint = R.string.service,
                    listService = categoryServices,
                    onClickService = {}
                )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .border(
                    border = BorderStroke(width = 1.dp, colorResource(id = R.color.lightPink)),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            //Home Details
            Spacer(modifier = Modifier.height(8.dp))
            RadioButtonComponent(text = "Home Details", onSelect = {/*TODO*/ })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(hint = R.string.main_price, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(hint = R.string.no_person, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(
                text = "Add Offer",
                onClick = { Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show() }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .border(
                    border = BorderStroke(width = 1.dp, colorResource(id = R.color.lightPink)),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            //Vip Details
            Spacer(modifier = Modifier.height(8.dp))
            RadioButtonComponent(text = "VIP Details", onSelect = {/*TODO*/ })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(hint = R.string.main_price, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(hint = R.string.no_person, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Add Offer",
                onClick = { Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show() })
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Additions",
                onClick = { Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show() })
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .border(
                    border = BorderStroke(width = 1.dp, colorResource(id = R.color.lightPink)),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {

            //Normal Details
            Spacer(modifier = Modifier.height(8.dp))
            RadioButtonComponent(text = "Normal Details", onSelect = {/*TODO*/ })
            Spacer(modifier = Modifier.height(8.dp))
            val detailsNo = TextField(hint = R.string.main_price, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(hint = R.string.no_person, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Add Offer",
                onClick = { Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show() })
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Additions",
                onClick = { Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show() })
            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .border(
                    border = BorderStroke(width = 1.dp, colorResource(id = R.color.lightPink)),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {

            Spacer(modifier = Modifier.height(8.dp))
            RadioButtonComponent(
                text = "Professional Details",
                onSelect = {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(hint = R.string.main_price, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(hint = R.string.no_person, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Add Offer",
                onClick = { Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show() })
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Additions",
                onClick = { Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show() })
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(label = "Save", onClick = { })
            Spacer(modifier = Modifier.height(20.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))

    }

}