package com.example.salonattask10.presentation.addServiceScreen


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
import com.example.salonattask10.Constants
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
    categoryList: List<Data>?,
    navigateBack: () -> Unit,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val handler: android.os.Handler = android.os.Handler();
    val isLoading = remember { mutableStateOf(false) }

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
    val vipPrice = remember { mutableStateOf("") }
    val vipPersonNo = remember { mutableStateOf("") }
    //normal
    val isNormalSelected = remember { mutableStateOf(false) }
    val isNormalAddOfferSelected = remember { mutableStateOf(false) }
    val isNormalAdditionSelected = remember { mutableStateOf(false) }
    val normalPrice = remember { mutableStateOf("") }
    val normalPersonNo = remember { mutableStateOf("") }
    //pro
    val isProSelected = remember { mutableStateOf(false) }
    val isProAddOfferSelected = remember { mutableStateOf(false) }
    val isProAdditionSelected = remember { mutableStateOf(false) }
    val proPrice = remember { mutableStateOf("") }
    val proPersonNo = remember { mutableStateOf("") }
    /* home field*/
    val homeField = ServiceField(0, 0)
    val vipField = ServiceField(0, 0)
    val proField = ServiceField(0, 0)
    val norField = ServiceField(0, 0)

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
                modifier = Modifier
                    .constrainAs(backArrow) {
                        start.linkTo(parent.start, margin = 12.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable { navHostController.popBackStack() }
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
            })
            Spacer(modifier = Modifier.height(8.dp))
            homePrice.value =
                TextField(hint = R.string.main_price, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            homePersonNo.value =
                TextField(hint = R.string.no_person, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(
                text = "Add Offer",
                onClick = {
                    isHomeAddOfferSelected.value = !isHomeAddOfferSelected.value
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
            //Vip Details
            Spacer(modifier = Modifier.height(8.dp))
            RadioButtonComponent(text = "VIP Details", onSelect = {
                isVipSelected.value = !isVipSelected.value
            })
            Spacer(modifier = Modifier.height(8.dp))
            vipPrice.value = TextField(hint = R.string.main_price, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            vipPersonNo.value = TextField(hint = R.string.no_person, type = KeyboardType.Number)
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
            normalPrice.value = TextField(hint = R.string.main_price, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            normalPersonNo.value = TextField(hint = R.string.no_person, type = KeyboardType.Number)
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
            proPrice.value = TextField(hint = R.string.main_price, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            proPersonNo.value = TextField(hint = R.string.no_person, type = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Add Offer",
                onClick = { isProAddOfferSelected.value = !isProAddOfferSelected.value })
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(text = "Additions",
                onClick = { isProAdditionSelected.value = !isProAdditionSelected.value })
            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))
        val viewmodel: AddServiceViewModel = hiltViewModel()
        if (isLoading.value)
            CircleProgressbar()
        CustomButton(label = "Save", onClick = {
            val list: ArrayList<AddServices> = ArrayList()
            //1 normal
            //2 pro
            //3 VIP
            //4 home
            if (isHomeSelected.value)
                list.add(
                    AddServices(
                        typeId = 4, // home
                        price = homePrice.value,
                        noPerson = homePersonNo.value,
                        addition = 0,
                        offerPrice = null,
                        from = "2022-07-22 15:48:00",
                        to = "2024-09-28 15:48:00"
                    )
                )
            if (isVipSelected.value)
                list.add(
                    AddServices(
                        typeId = 3, // vip
                        price = vipPrice.value,
                        noPerson = vipPersonNo.value,
                        addition = if (isVipAdditionSelected.value) 1 else 0,
                        offerPrice = null,
                        from = "2022-07-22 15:48:00",
                        to = "2024-09-28 15:48:00"
                    )
                )
            if (isProSelected.value)
                list.add(
                    AddServices(
                        typeId = 2, // pro
                        price = proPrice.value,
                        noPerson = proPersonNo.value,
                        addition = if (isProAdditionSelected.value) 1 else 0,
                        offerPrice = null,
                        from = "2022-07-22 15:48:00",
                        to = "2024-09-28 15:48:00"
                    )
                )
            if (isNormalSelected.value)
                list.add(
                    AddServices(
                        typeId = 1, // normal
                        price = normalPrice.value,
                        noPerson = normalPersonNo.value,
                        addition = if (isNormalAddOfferSelected.value) 1 else 0,
                        offerPrice = null,
                        from = "2022-07-22 15:48:00",
                        to = "2024-09-28 15:48:00"
                    )
                )
            val service = AddServiceInput(
                serviceId = serviceId.intValue,
                centerId = Constants.LOCAL_CENTER_ID.toInt(),
                services = list
            )
            if (isHomeSelected.value || isVipSelected.value ||
                isProSelected.value || isNormalSelected.value && serviceId.intValue != 0
            ) {
                if (categoryId.value != 0 && serviceId.value != 0) {

                    homeField.price = if (homePrice.value != "") homePrice.value.toInt() else 0
                    homeField.personeNo =
                        if (homePersonNo.value != "") homePersonNo.value.toInt() else 0

                    proField.price = if (proPrice.value != "") proPrice.value.toInt() else 0
                    proField.personeNo =
                        if (proPersonNo.value != "") proPersonNo.value.toInt() else 0

                    vipField.price = if (vipPrice.value != "") vipPrice.value.toInt() else 0
                    vipField.personeNo =
                        if (vipPersonNo.value != "") vipPersonNo.value.toInt() else 0

                    norField.price = if (normalPrice.value != "") normalPrice.value.toInt() else 0
                    norField.personeNo =
                        if (normalPersonNo.value != "") normalPersonNo.value.toInt() else 0

                    if (isHomeSelected.value ||
                        isProSelected.value ||
                        isVipSelected.value ||
                        isNormalSelected.value
                    ) {
                        isLoading.value = true
                        viewmodel.addService(service = service)
                        handler.postDelayed(Runnable {

                            viewmodel.addServicesSate.value.data?.message?.let {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            }

                            if (viewmodel.addServicesSate.value.data?.status == true) {
                                navigateBack()
                            } else {
                                isLoading.value = false
                                Toast.makeText(
                                    context,
                                    R.string.addServiceError,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }, 1000)
                    }
                } else Toast.makeText(
                    context,
                    "select category && service type",
                    Toast.LENGTH_SHORT
                ).show()

            } else
                Toast.makeText(context, "At least select one field", Toast.LENGTH_SHORT).show()
        })
        Spacer(modifier = Modifier.height(20.dp))
    }
}

data class ServiceField(
    var price: Int,
    var personeNo: Int
)

private fun isFieldsEmty(obj: ServiceField, obj2: ServiceField): Boolean {

    if (obj.price == 0 && obj.personeNo == 0 || obj2.personeNo == 0 && obj2.price == 0)
        return false

    return true
}

private fun checkFields(
    homeField: ServiceField,
    vipField: ServiceField,
    proField: ServiceField,
    norField: ServiceField,
): Boolean {
    if (isFieldsEmty(homeField, vipField) || isFieldsEmty(proField, norField))
        return true
    else
        return false
}

private fun checkFields(
    homeField: ServiceField,
    vipField: ServiceField,
    proField: ServiceField,
) = isFieldsEmty(homeField, vipField) &&
        isFieldsEmty(vipField, proField)