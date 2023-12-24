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

    private var _stateLogin = mutableStateOf(LoginState())
    private var _stateVerify = mutableStateOf(VerifyState())

    val stateLogin: State<LoginState>
        get() = _stateLogin

    val stateVerify: State<VerifyState>
        get() = _stateVerify

    fun login(phone: String) {
        viewModelScope.launch {
            try {
                val data = repo.login(phone = phone)
                _stateLogin.value = LoginState(
                    data = data
                )
            } catch (e: Exception) {

            } catch (e: HttpException) {

            } catch (e: IOException) {

            }
        }
    }

    fun verify(phone: String, code: String) {
        viewModelScope.launch {
            try {
                val data = repoV.verify(phone = phone, code = code)
                _stateVerify.value = VerifyState(
                    data = data
                )
            } catch (e: Exception) {
                _stateLogin.value = e.localizedMessage?.let {
                    LoginState(
                        error = it
                    )
                }!!
            } catch (e: IOException) {
                _stateLogin.value = e.localizedMessage?.let {
                    LoginState(
                        error = it
                    )
                }!!
            } catch (e: HttpException) {
                _stateLogin.value = e.localizedMessage?.let {
                    LoginState(
                        error = it
                    )
                }!!
            }
        }
    }

}