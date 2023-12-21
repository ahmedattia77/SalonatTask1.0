package com.example.salonattask10.presentation.addServiceScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salonattask10.data.repository.CategoriesRepositoryImp
import com.example.salonattask10.data.repository.CategoryServicesRepositoryImp
import com.example.salonattask10.presentation.addServiceScreen.state.CategoryServicesState
import com.example.salonattask10.presentation.addServiceScreen.state.CategoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class AddServiceViewModel @Inject constructor(
    private val categoriesRepositoryImp: CategoriesRepositoryImp,
    private val categoryServicesRepositoryImp: CategoryServicesRepositoryImp
) : ViewModel() {

    private val _categorySate = mutableStateOf(CategoryState())
    val categoryState: State<CategoryState>
        get() = _categorySate

    private val _categoryServicesSate = mutableStateOf(CategoryServicesState())
    val categoryServicesSate: State<CategoryServicesState>
        get() = _categoryServicesSate

    init {
        getServices()
    }

    private fun getServices() {
        viewModelScope.launch {
            try {
                val data = categoriesRepositoryImp.getCategories()
                _categorySate.value = CategoryState(
                    data = data
                )
            } catch (e: Exception) {
                _categorySate.value =
                    e.localizedMessage?.let { CategoryState(error = it.toString()) }!!
            } catch (e: IOException) {
                _categorySate.value =
                    e.localizedMessage?.let { CategoryState(error = it.toString()) }!!
            } catch (e: HttpException) {
                _categorySate.value =
                    e.localizedMessage?.let { CategoryState(error = it.toString()) }!!
            }
        }
    }

    fun getCategoryService(categoryId: Int) {
        viewModelScope.launch {
            try {
                val data = categoryServicesRepositoryImp.getCategories(id = categoryId)
                _categoryServicesSate.value = CategoryServicesState(
                    data = data
                )
            } catch (e: Exception) {
                _categoryServicesSate.value =
                    e.localizedMessage?.let { CategoryServicesState(error = it) }!!
            } catch (e: IOException) {
                _categoryServicesSate.value =
                    e.localizedMessage?.let { CategoryServicesState(error = it) }!!
            } catch (e: HttpException) {
                _categoryServicesSate.value =
                    e.localizedMessage?.let { CategoryServicesState(error = it) }!!
            }
        }
    }

}