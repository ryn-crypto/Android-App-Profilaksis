package com.profilaksis.profilaksis.data

class Repository {

//    fun getInstance(): Repository =
//        instance ?: synchronized(this) {
//            Repository().apply {
//                instance = this
//            }
//        }

    fun getPercentage(): Int {
        return 50
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