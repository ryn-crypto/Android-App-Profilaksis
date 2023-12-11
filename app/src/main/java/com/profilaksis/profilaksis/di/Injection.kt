package com.profilaksis.profilaksis.di

import com.profilaksis.profilaksis.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}