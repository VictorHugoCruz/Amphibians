package com.devtor.amphibians.ui.screens

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.devtor.amphibians.AmphibiansApplication
import com.devtor.amphibians.data.AmphibiansDataRepository
import com.devtor.amphibians.network.dto.AmphibiansItem
import kotlinx.coroutines.launch

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<AmphibiansItem>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class AmphibiansViewModel(
    private val amphibiansDataRepository : AmphibiansDataRepository
) : ViewModel() {

    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getAmphibians() {
        viewModelScope.launch {
            try {
                val result = amphibiansDataRepository.getAmphibiansPhotos()
                amphibiansUiState = AmphibiansUiState.Success(result)
            } catch (e: Exception) {
                amphibiansUiState = AmphibiansUiState.Error
            } catch (e: HttpException) {
                amphibiansUiState = AmphibiansUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansDataRepository = application.container.amphibiansDataRepository
                AmphibiansViewModel(amphibiansDataRepository = amphibiansDataRepository)
            }
        }
    }

}
