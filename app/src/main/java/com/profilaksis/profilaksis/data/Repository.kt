package com.profilaksis.profilaksis.data

import com.profilaksis.profilaksis.data.model.HistoryData
import com.profilaksis.profilaksis.data.model.ResponseArticleItem
import com.profilaksis.profilaksis.data.model.ResponseResult
import com.profilaksis.profilaksis.data.model.UserData
import com.profilaksis.profilaksis.data.model.UserLogin
import java.util.Date

class Repository {

    fun login(email: String, password: String): UserLogin {
        return UserLogin(
            id = 1,
            username = "Riyan First",
            email = "riyan@mail.com",
            role = "user",
            avatar = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            token = "yriajfa",
        )
    }

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

    fun sendData(): ResponseResult {
        return ResponseResult(
            prediction = 90f,
            message = "Hearth",
            date = Date(),
            userName = "Riyan",
            description = "you have hearth disease with 90% probability of certainty and you need to go to the doctor immediately"
        )
    }

    fun getArticle(): List<ResponseArticleItem> {
        return listOf(
            ResponseArticleItem(
                updatedAt = "2021-10-04T09:00:00.000Z",
                imageUrl = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = Date(),
                id = 1,
                title = "How to get a good night's sleep",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. ",
                tags = "sleep"
            ),
            ResponseArticleItem(
                updatedAt = "2021-10-04T09:00:00.000Z",
                imageUrl = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = Date(),
                id = 2,
                title = "How to get a good night's sleep",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod",
                tags = "sleep"
            ),
            ResponseArticleItem(
                updatedAt = "2021-10-04T09:00:00.000Z",
                imageUrl = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = Date(),
                id = 3,
                title = "How to get a good night's sleep",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod",
                tags = "sleep"
            ),
            ResponseArticleItem(
                updatedAt = "2021-10-04T09:00:00.000Z",
                imageUrl = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = Date(),
                id = 4,
                title = "How to get a good night's sleep",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod",
                tags = "sleep"
            ),
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