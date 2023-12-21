package com.example.salonattask10.presentation.login.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salonattask10.data.repository.LoginRepositoryImp
import com.example.salonattask10.data.repository.VerifyRepositoryImp
import com.example.salonattask10.presentation.login.verify.VerifyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: LoginRepositoryImp,
    private val repoV: VerifyRepositoryImp
) : ViewModel() {

    private var _state = mutableStateOf(LoginState())
    private var _stateV = mutableStateOf(VerifyState())

    val state: State<LoginState>
        get() = _state

    val stateV: State<VerifyState>
        get() = _stateV

//
//    fun login(phone: String) {
//        viewModelScope.launch {
//            repo.login(phone = phone).onEach {
//                when (it) {
//                    is Resource.Loading -> {
//                        _state.value = LoginState(
//                            isLoading = true
//                        )
//                    }
//
//                    is Resource.Success -> {
//                        _state.value = LoginState(
//                            data = it.data
//                        )
//                    }
//
//                    is Resource.Error -> {
//                        _state.value = LoginState(
//                            error = it.message ?: "An unexpected value"
//                        )
//                    }
//                }
//            }
//        }
//    }

    fun login(phone: String) {
        viewModelScope.launch {
            val data = repo.login(phone = phone)
            _state.value = LoginState(
                data = data
            )
        }
    }

    fun verify(phone: String, code: String) {
        viewModelScope.launch {
            try {
                val data = repoV.verify(phone = phone, code = code)
                _stateV.value = VerifyState(
                    data = data
                )
            } catch (e: Exception) {
                _state.value = e.localizedMessage?.let {
                    LoginState(
                        error = it
                    )
                }!!
            } catch (e: IOException) {
                _state.value = e.localizedMessage?.let {
                    LoginState(
                        error = it
                    )
                }!!
            } catch (e: HttpException) {
                _state.value = e.localizedMessage?.let {
                    LoginState(
                        error = it
                    )
                }!!
            }
            Log.i("responseV", state.value.data.toString())
        }
    }

}