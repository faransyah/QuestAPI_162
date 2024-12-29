package com.example.p12.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.p12.model.Mahasiswa
import com.example.p12.repository.MahasiswaRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeUiState{
    data class Success(val mahasiswa: List<Mahasiswa>): HomeUiState()
    object Error: HomeUiState()
    object Loading: HomeUiState()

}

class HomeViewModel(private val mhs: MahasiswaRepository): ViewModel(){
    var mhsUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getMhs()
    }

    fun getMhs(){
        viewModelScope.launch {
            mhsUIState = HomeUiState.Loading
            mhsUIState = try {
                HomeUiState.Success(mhs.getMahasiswa())
            }catch (e:IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }

    }
}