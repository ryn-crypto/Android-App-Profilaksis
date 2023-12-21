package com.profilaksis.profilaksis.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.profilaksis.profilaksis.data.model.RadioButtonInfo
import com.profilaksis.profilaksis.utils.Status

class RadioButtonStatus {
    var genderStatus by mutableStateOf(Status.LowHighStatus.Low)
    var diabetesStatus by mutableStateOf(Status.TreeLevel.Low)
    var heartStatus by mutableStateOf(Status.TreeLevel.Low)
    var strokeStatus by mutableStateOf(Status.LowHighStatus.Low)
    var bloodPressureStatus by mutableStateOf(Status.LowHighStatus.Low)
    var cholesterolStatus by mutableStateOf(Status.LowHighStatus.Low)
    var smokingStatus by mutableStateOf(Status.LowHighStatus.Low)
    var alcoholStatus by mutableStateOf(Status.LowHighStatus.Low)
    var exerciseStatus by mutableStateOf(Status.LowHighStatus.Low)
    var fruitStatus by mutableStateOf(Status.LowHighStatus.Low)
    var vegetableStatus by mutableStateOf(Status.LowHighStatus.Low)
    var walkStatus by mutableStateOf(Status.LowHighStatus.Low)
}

val radioButtonStatus = RadioButtonStatus()

val diabetesData = listOf(
    RadioButtonInfo(Status.TreeLevel.Low, "Tidak", radioButtonStatus.diabetesStatus) { status ->
        radioButtonStatus.diabetesStatus = status
    },
    RadioButtonInfo(Status.TreeLevel.Mid, "Pra-Diabetes", radioButtonStatus.diabetesStatus) { status ->
        radioButtonStatus.diabetesStatus = status
    },
    RadioButtonInfo(Status.TreeLevel.Height, "Ya", radioButtonStatus.diabetesStatus) { status ->
        radioButtonStatus.diabetesStatus = status
    }
)

val heartData = listOf(
    RadioButtonInfo(Status.TreeLevel.Low, "Tidak", radioButtonStatus.heartStatus) { status ->
        radioButtonStatus.heartStatus = status
    },
    RadioButtonInfo(Status.TreeLevel.Height, "Ya", radioButtonStatus.heartStatus) { status ->
        radioButtonStatus.heartStatus = status
    }
)

val strokeData = listOf(
    RadioButtonInfo(Status.LowHighStatus.Low, "Tidak", radioButtonStatus.strokeStatus) { status ->
        radioButtonStatus.strokeStatus = status
    },
    RadioButtonInfo(Status.LowHighStatus.Height, "Ya", radioButtonStatus.strokeStatus) { status ->
        radioButtonStatus.strokeStatus = status
    }
)

val genderData = listOf(
    RadioButtonInfo(Status.LowHighStatus.Low, "Laki-Laki", radioButtonStatus.genderStatus) { status ->
        radioButtonStatus.genderStatus = status
    },
    RadioButtonInfo(Status.LowHighStatus.Height, "Perempuan", radioButtonStatus.genderStatus) { status ->
        radioButtonStatus.genderStatus = status
    }
)

val bloodPressureData = listOf(
    RadioButtonInfo(Status.LowHighStatus.Low, "Normal", radioButtonStatus.bloodPressureStatus) { status ->
        radioButtonStatus.bloodPressureStatus = status
    },
    RadioButtonInfo(Status.LowHighStatus.Height, "Tinggi (<150)", radioButtonStatus.bloodPressureStatus) { status ->
        radioButtonStatus.bloodPressureStatus = status
    }
)

val cholesterolData = listOf(
    RadioButtonInfo(Status.LowHighStatus.Low, "Normal", radioButtonStatus.cholesterolStatus) { status ->
        radioButtonStatus.cholesterolStatus = status
    },
    RadioButtonInfo(Status.LowHighStatus.Height, "Tinggi", radioButtonStatus.cholesterolStatus) { status ->
        radioButtonStatus.cholesterolStatus = status
    }
)

val smokingData = listOf(
    RadioButtonInfo(Status.LowHighStatus.Low, "Tidak", radioButtonStatus.smokingStatus) { status ->
        radioButtonStatus.smokingStatus = status
    },
    RadioButtonInfo(Status.LowHighStatus.Height, "Ya(<100 Rokok)", radioButtonStatus.smokingStatus) { status ->
        radioButtonStatus.smokingStatus = status
    }
)

val alcoholData = listOf(
    RadioButtonInfo(Status.LowHighStatus.Low, "Tidak", radioButtonStatus.alcoholStatus) { status ->
        radioButtonStatus.alcoholStatus = status
    },
    RadioButtonInfo(Status.LowHighStatus.Height, "Ya", radioButtonStatus.alcoholStatus) { status ->
        radioButtonStatus.alcoholStatus = status
    }
)

val exerciseData = listOf(
    RadioButtonInfo(Status.LowHighStatus.Low, "Tidak", radioButtonStatus.exerciseStatus) { status ->
        radioButtonStatus.exerciseStatus = status
    },
    RadioButtonInfo(Status.LowHighStatus.Height, "Ya", radioButtonStatus.exerciseStatus) { status ->
        radioButtonStatus.exerciseStatus = status
    }
)

val fruitData = listOf(
    RadioButtonInfo(Status.LowHighStatus.Low, "Tidak", radioButtonStatus.fruitStatus) { status ->
        radioButtonStatus.fruitStatus = status
    },
    RadioButtonInfo(Status.LowHighStatus.Height, "Ya", radioButtonStatus.fruitStatus) { status ->
        radioButtonStatus.fruitStatus = status
    }
)

val vegetableData = listOf(
    RadioButtonInfo(Status.LowHighStatus.Low, "Tidak", radioButtonStatus.vegetableStatus) { status ->
        radioButtonStatus.vegetableStatus = status
    },
    RadioButtonInfo(Status.LowHighStatus.Height, "Ya", radioButtonStatus.vegetableStatus) { status ->
        radioButtonStatus.vegetableStatus = status
    }
)

val walkData = listOf(
    RadioButtonInfo(Status.LowHighStatus.Low, "Tidak", radioButtonStatus.walkStatus) { status ->
        radioButtonStatus.walkStatus = status
    },
    RadioButtonInfo(Status.LowHighStatus.Height, "Ya", radioButtonStatus.walkStatus) { status ->
        radioButtonStatus.walkStatus = status
    }
)