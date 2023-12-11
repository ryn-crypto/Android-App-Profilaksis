package com.profilaksis.profilaksis.data

import com.profilaksis.profilaksis.data.model.HistoryData
import com.profilaksis.profilaksis.data.model.UserData
import java.util.Date

class Repository {


    fun getLastHistory(): HistoryData {
        return HistoryData(
            "1",
            "username",
            90f,
            "Hearth",
            java.util.Date(),
            "description",
            0.5f
        )
    }

    fun getProfile(): UserData {
        return UserData(
            id = "1",
            username = "Riyan",
            avatar = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            token = " ",
        )
    }

    companion object {
        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }

        @Volatile
        private var instance: Repository? = null
    }
}