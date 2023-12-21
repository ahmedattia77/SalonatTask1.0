package com.example.salonattask10.presentation.homeScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salonattask10.data.repository.ServiceRepositoryImp
import com.example.salonattask10.domain.usecase.app_entry.AppEntryUseCases
import com.example.salonattask10.presentation.Dimens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
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
    private val _token = mutableStateOf<Flow<String>>(emptyFlow())
    private val _centerId = mutableStateOf<Flow<String>>(emptyFlow())

    val state: State<HomeState>
        get() = _state

    init {
        getService()
    }

    // access token center_id needed
    fun getService() {
        viewModelScope.launch {
            try {
                val data = repo.getService(bearer = Dimens.token, centerId = Dimens.centerId)
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

    private fun getToken() {
        viewModelScope.launch {
            try {
                _token.value = appUseCase.readToken.invoke()
            } catch (e: Exception) { }
        }
    }

    private fun getCenterId() {
        viewModelScope.launch {
            try {
                _centerId.value = appUseCase.readCenterId.invoke()
            } catch (e: Exception) { }
        }
    }
}