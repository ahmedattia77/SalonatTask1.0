package com.example.salonattask10.presentation.showService

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salonattask10.data.repository.ServiceDetailsRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ShowServiceViewModel @Inject constructor(
    private val repo: ServiceDetailsRepositoryImp
) : ViewModel() {

    private val _state = mutableStateOf(ShowServiceState())
    val state: State<ShowServiceState>
        get() = _state

    fun getService(centerId: Int, serviceId: Int) {
        viewModelScope.launch {
            try {
                val data = repo.getServiceDetails(serviceId = serviceId, centerId = centerId)
                _state.value = ShowServiceState(
                    data = data
                )
            } catch (e: Exception) {
                _state.value = e.localizedMessage?.let { ShowServiceState(error = it) }!!
            } catch (e: IOException) {
                _state.value = e.localizedMessage?.let { ShowServiceState(error = it) }!!
            } catch (e: HttpException) {
                _state.value = e.localizedMessage?.let { ShowServiceState(error = it) }!!
            }
        }
    }


}