package com.example.salonattask10.presentation.showService

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salonattask10.data.repository.DeleteServiceRepositoryImp
import com.example.salonattask10.data.repository.DeleteServiceTypeRepositoryImp
import com.example.salonattask10.data.repository.ServiceDetailsRepositoryImp
import com.example.salonattask10.presentation.showService.state.DeleteServiceState
import com.example.salonattask10.presentation.showService.state.ShowServiceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ShowServiceViewModel @Inject constructor(
    private val repo: ServiceDetailsRepositoryImp,
    private val repoDelete: DeleteServiceRepositoryImp,
    private val repoDeleteServiceType: DeleteServiceTypeRepositoryImp,
) : ViewModel() {

    private val _state = mutableStateOf(ShowServiceState())
    val state: State<ShowServiceState>
        get() = _state

    private val _stateDelete = mutableStateOf(DeleteServiceState())
    val stateDelete: State<DeleteServiceState>
        get() = _stateDelete

    private val _stateDeleteServiceType = mutableStateOf(DeleteServiceState())
    val stateDeleteServiceType: State<DeleteServiceState>
        get() = _stateDeleteServiceType

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

    fun deleteService(centerId: Int, serviceId: Int) {
        viewModelScope.launch {
            try {
                val data = repoDelete.deleteService(serviceId = serviceId, centerId = centerId)
                _stateDelete.value = DeleteServiceState(
                    data = data
                )
            } catch (e: Exception) {
                _stateDelete.value = e.localizedMessage?.let { DeleteServiceState(error = it) }!!
            } catch (e: IOException) {
                _stateDelete.value = e.localizedMessage?.let { DeleteServiceState(error = it) }!!
            } catch (e: HttpException) {
                _stateDelete.value = e.localizedMessage?.let { DeleteServiceState(error = it) }!!
            }
        }
    }

    fun deleteServiceType(
        centerId: Int,
        serviceId: Int,
        serviceTypeId: Int
    ) {
        try {

        } catch (e: Exception) {
            e.localizedMessage?.let {
                _stateDeleteServiceType.value = DeleteServiceState(
                    error = it
                )
            }

        } catch (e: HttpException) {
            e.localizedMessage?.let {
                _stateDeleteServiceType.value = DeleteServiceState(
                    error = it
                )
            }

        } catch (e: IOException) {
            e.localizedMessage?.let {
                _stateDeleteServiceType.value = DeleteServiceState(
                    error = it
                )
            }
        }


    }

}