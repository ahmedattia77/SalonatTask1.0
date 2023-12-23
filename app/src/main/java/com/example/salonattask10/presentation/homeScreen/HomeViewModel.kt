package com.example.salonattask10.presentation.homeScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salonattask10.Constants
import com.example.salonattask10.data.repository.ServiceRepositoryImp
import com.example.salonattask10.domain.usecase.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appUseCase: AppEntryUseCases,
    private val repo: ServiceRepositoryImp
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    private val _centerId = mutableStateOf("")

    val state: State<HomeState>
        get() = _state

    init {
        getService()
    }

    private fun getService() {
        viewModelScope.launch {
            delay(150)
            try {
                val data = repo.getService(
                    centerId = Constants.LOCAL_CENTER_ID
                )
                _state.value = HomeState(
                    data = data
                )

            } catch (e: Exception) {
                _state.value = e.localizedMessage?.let { HomeState(error = it) }!!
            } catch (e: HttpException) {
                _state.value = e.localizedMessage?.let { HomeState(error = it) }!!
            } catch (e: IOException) {
                _state.value = e.localizedMessage?.let { HomeState(error = it) }!!
            }
        }
    }


}