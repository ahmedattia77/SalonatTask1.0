package com.example.salonattask10.presentation.addServiceScreen


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import com.example.salonattask10.data.model.addService.AddServiceInput
import com.example.salonattask10.data.model.addService.AddServices
import com.example.salonattask10.data.model.categoriesJON.Data
import com.example.salonattask10.presentation.Dimens
import com.example.salonattask10.presentation.addServiceScreen.component.Checkbox
import com.example.salonattask10.presentation.addServiceScreen.component.CustomButton
import com.example.salonattask10.presentation.addServiceScreen.component.DropDown
import com.example.salonattask10.presentation.addServiceScreen.component.RadioButtonComponent
import com.example.salonattask10.presentation.addServiceScreen.component.TextField
import com.example.salonattask10.presentation.common.CircleProgressbar


@Composable
fun AddServiceScreen(
    categoryList: List<Data>?
) {
    val context = LocalContext.current
    val categoryId = remember { mutableIntStateOf(0) }
    val serviceId = remember { mutableIntStateOf(0) }
    //home Field
    val isHomeSelected = remember { mutableStateOf(false) }
    val isHomeAddOfferSelected = remember { mutableStateOf(false) }
    val homePrice = remember { mutableStateOf("") }
    val homePersonNo = remember { mutableStateOf("") }
    //VIP
    val isVipSelected = remember { mutableStateOf(false) }
    val isVipAddOfferSelected = remember { mutableStateOf(false) }
    val isVipAdditionSelected = remember { mutableStateOf(false) }
    val vipPrice = remember { mutableIntStateOf(0) }
    val vipPersonNo = remember { mutableIntStateOf(0) }
    //normal
    val isNormalSelected = remember { mutableStateOf(false) }
    val isNormalAddOfferSelected = remember { mutableStateOf(false) }
    val isNormalAdditionSelected = remember { mutableStateOf(false) }
    val normalPrice = remember { mutableIntStateOf(0) }
    val normalPersonNo = remember { mutableIntStateOf(0) }
    //pro
    val isProSelected = remember { mutableStateOf(false) }
    val isProAddOfferSelected = remember { mutableStateOf(false) }
    val isProAdditionSelected = remember { mutableStateOf(false) }
    val proPrice = remember { mutableIntStateOf(0) }
    val proPersonNo = remember { mutableIntStateOf(0) }
    //------------ objects
    val homeService: ServiceField = ServiceField(false, false, "", "")

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
                onClick = {
                    categoryId.intValue = it.id
                    Log.i("service", it.id.toString())
                })

        Spacer(modifier = Modifier.height(20.dp))
        if (categoryId.intValue != 0) {
            val viewmodel: AddServiceViewModel = hiltViewModel()
            viewmodel.getCategoryService(categoryId.intValue)
            val categoryServices = viewmodel.categoryServicesSate.value.data?.data
            if (categoryServices != null)
                DropDown(
                    hint = R.string.service,
                    listService = categoryServices,
                    onClickService = {
                        serviceId.intValue = it.id
                        Log.i("serviceId", it.id.toString())
                    }
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
            RadioButtonComponent(text = "Home Details", onSelect = {
                isHomeSelected.value = !isHomeSelected.value
                homeService.isSelected = it
            })
            Spacer(modifier = Modifier.height(8.dp))
            homeService.price =
                TextField(hint = R.string.main_price, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            homeService.personNo =
                TextField(hint = R.string.no_person, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(
                text = "Add Offer",
                onClick = {
                    isHomeAddOfferSelected.value = !isHomeAddOfferSelected.value
                }
            )
        }
        /*
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
            RadioButtonComponent(text = "VIP Details", onSelect = {
                isVipSelected.value = !isVipSelected.value
            })
            Spacer(modifier = Modifier.height(8.dp))
            vipPrice.intValue = TextField(hint = R.string.main_price, type = KeyboardType.Number).toInt()
            Spacer(modifier = Modifier.height(8.dp))
            vipPersonNo.intValue = TextField(hint = R.string.no_person, type = KeyboardType.Number).toInt()
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Add Offer",
                onClick = { isVipAddOfferSelected.value = !isVipAddOfferSelected.value })
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Additions",
                onClick = { isVipAdditionSelected.value = !isVipAdditionSelected.value })
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
            RadioButtonComponent(text = "Normal Details", onSelect = {
                isNormalSelected.value = !isNormalSelected.value
            })
            Spacer(modifier = Modifier.height(8.dp))
            normalPrice.value = TextField(hint = R.string.main_price, type = KeyboardType.Number).toInt()
            Spacer(modifier = Modifier.height(8.dp))
            normalPersonNo.value = TextField(hint = R.string.no_person, type = KeyboardType.Number).toInt()
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Add Offer",
                onClick = { isNormalAddOfferSelected.value = !isNormalAddOfferSelected.value })
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Additions",
                onClick = { isNormalAdditionSelected.value = !isNormalAdditionSelected.value })
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
            //Professional
            Spacer(modifier = Modifier.height(8.dp))
            RadioButtonComponent(
                text = "Professional Details",
                onSelect = {
                    isProSelected.value = !isProSelected.value
                })
            Spacer(modifier = Modifier.height(8.dp))
            proPrice.intValue = TextField(hint = R.string.main_price, type = KeyboardType.Number).toInt()
            Spacer(modifier = Modifier.height(8.dp))
            proPersonNo.intValue = TextField(hint = R.string.no_person, type = KeyboardType.Number).toInt()
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Add Offer",
                onClick = { isProAddOfferSelected.value = !isProAddOfferSelected.value })
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Additions",
                onClick = { isProAdditionSelected.value = !isProAdditionSelected.value })
            Spacer(modifier = Modifier.height(20.dp))
        }
        */
        Spacer(modifier = Modifier.height(20.dp))
        val viewmodel: AddServiceViewModel = hiltViewModel()
        CustomButton(label = "Save", onClick = {
            val list: ArrayList<AddServices> = ArrayList()
            //1 normal
            //2 pro
            //3 VIP
            //4 home
            if (homeService.isSelected) {
                list.add(
                    AddServices(
                        typeId = 2, // home
                        price = homeService.price,
                        noPerson = homeService.personNo,
                        addition = 1,
                        offerPrice = null,
                        from = "2022-07-22 15:48:00",
                        to = "2024-09-28 15:48:00"
                    )
                )
            }
            val service = AddServiceInput(
                serviceId = serviceId.intValue,
                centerId = 123,
                services = list
            )
            viewmodel.addService(service = service)
            viewmodel.addServicesSate.value.data?.message?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            viewmodel.addServicesSate.value.error.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })
        Spacer(modifier = Modifier.height(20.dp))
    }

}