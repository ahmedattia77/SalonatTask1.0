package com.example.salonattask10.presentation.login.verify

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salonattask10.data.repository.VerifyRepositoryImp
import com.example.salonattask10.domain.usecase.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(
    private val repo: VerifyRepositoryImp ,
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private var _state = mutableStateOf(VerifyState())


    val state: State<VerifyState>
        get() = _state


    fun verify(phone: String, code: String) {
        viewModelScope.launch {
            try {
                val data = repo.verify(phone = phone, code = code)
                _state.value = VerifyState(
                    data = data
                )
            } catch (e: Exception) {
                _state.value = e.localizedMessage?.let {
                    VerifyState(
                        error = it
                    )
                }!!
            } catch (e: IOException) {
                _state.value = e.localizedMessage?.let {
                    VerifyState(
                        error = it
                    )
                }!!
            } catch (e: HttpException) {
                _state.value = e.localizedMessage?.let {
                    VerifyState(
                        error = it
                    )
                }!!
            }
        }
    }

    fun onEvent(event: AppEntryEvent , token:String , centerId: String){
        when(event){
            AppEntryEvent.SaveAppEntry ->{
                saveAppEntry()
                saveToken(token = token)
                saveCenterId(centerId = centerId)
            }
        }
    }

    private fun saveAppEntry(){
        viewModelScope.launch {
            appEntryUseCases.saveUserEntry()
        }
    }


    private fun saveToken(token: String) {
        viewModelScope.launch {
            appEntryUseCases.saveUserEntry()
        }
    }

    private fun saveCenterId(centerId: String) {
        viewModelScope.launch {
            appEntryUseCases.saveUserEntry()
        }
    }

}

