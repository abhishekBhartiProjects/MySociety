package io.github.abhishekbhartiprojects.mysociety.feature.viewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.abhishekbhartiprojects.mysociety.feature.android.repository.SheetRepository
import io.github.abhishekbhartiprojects.mysociety.feature.viewModel.SheetViewModel

class SheetViewModelFactory (context: Context): ViewModelProvider.NewInstanceFactory() {
    val repository = SheetRepository(context)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SheetViewModel(repository) as T
    }
}