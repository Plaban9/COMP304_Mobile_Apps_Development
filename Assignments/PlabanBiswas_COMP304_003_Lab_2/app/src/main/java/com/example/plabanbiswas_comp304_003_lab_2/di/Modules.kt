package com.example.plabanbiswas_comp304_003_lab_2.di

import com.example.plabanbiswas_comp304_003_lab_2.model.TasksRepository
import com.example.plabanbiswas_comp304_003_lab_2.model.TasksRepositoryImpl
import com.example.plabanbiswas_comp304_003_lab_2.viewmodel.TasksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { TasksRepositoryImpl() as TasksRepository }
    viewModel { TasksViewModel(get()) }
}