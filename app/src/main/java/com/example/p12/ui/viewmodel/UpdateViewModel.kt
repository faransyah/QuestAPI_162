package com.example.p12.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p12.model.Mahasiswa
import com.example.p12.repository.MahasiswaRepository
import com.example.p12.ui.view.DestinasiUpdate
import kotlinx.coroutines.launch
import toDetailUiEvent


class UpdateViewModel (
    savedStateHandle: SavedStateHandle,
    private val mhs: MahasiswaRepository
): ViewModel(){

    val nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    var uiState = mutableStateOf(InsertUiState())
        private set

    init {
        ambilMhs()
    }
    private fun ambilMhs(){
        viewModelScope.launch {
            try {
                val mahasiswa = mhs.getMahasiswabyNim(nim)
                mahasiswa?.let {
                    uiState.value = it.toInsertUIEvent()
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun updateMhs(
        nim: String,
        mahasiswa: Mahasiswa
    ){
        viewModelScope.launch {
            try {
                mhs.updateMahasiswa(nim, mahasiswa)
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun updateMhsState(insertUiEvent: InsertUiEvent){
        uiState.value = uiState.value.copy(insertUiEvent = insertUiEvent)
    }
}



fun Mahasiswa.toInsertUIEvent(): InsertUiState = InsertUiState(
    insertUiEvent = this.toDetailUiEvent()
)
