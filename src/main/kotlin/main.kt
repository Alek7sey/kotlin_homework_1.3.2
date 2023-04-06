import java.lang.Integer.max
import kotlin.math.roundToInt

const val comissionVisaMir = 0.0075
const val minComissionVisaMir = 35
const val comissionFixMasterMaestro = 20
const val comissionMasterMaestro = 0.006
const val limitMinTransferMasterMaestro = 300
const val limitMaxTransferMasterMaestro = 75_000
const val cardLimitDay = 150_000
const val cardLimitMonth = 600_000
const val VKPayLimitTransaction = 15_000
const val VKPayLimitMonth = 40_000

//fun main() {
//
//    val typeCard1 = "Maestro"
//    val sumTransfer2 = 10_000
//    val transfer2 = 2_000
//
//    printComission(sumTransfer = 10_000, transfer = 15_000)
//    printComission(typeCard1, sumTransfer2, transfer2)
//    printComission("Visa", 100_000, 200_000)
//    printComission("Mir", 600_000, 50_000)
//    printComission("VKPay", 600_000, 50_000)
//
//}

fun calcComission(typeCard: String = "VK Pay", sumTransfer: Int = 0, transfer: Int): Int {
    var result = 0

    when (possibleTransfer(typeCard, sumTransfer, transfer)) {
        1 -> {
            when (typeCard) {
                "Mastercard", "Maestro" -> {
                    if ((sumTransfer + transfer) > limitMaxTransferMasterMaestro
                        || (sumTransfer + transfer) < limitMinTransferMasterMaestro
                    ) {
                        result = (transfer * comissionMasterMaestro + comissionFixMasterMaestro).roundToInt()
                    }
                }

                "Visa", "Mir" -> {
                    result = max((transfer * comissionVisaMir).roundToInt(), minComissionVisaMir)
                }

                "VK Pay" -> result = 0
            }
        }

        -1 -> result = -1
        -2 -> result = -2
        -3 -> result = -3
    }
    return result
}

fun possibleTransfer(typeCard: String, sumTransfer: Int, transfer: Int): Int {
    var signTransfer = 1
    when (typeCard) {
        "VK Pay" -> {
            if (transfer > VKPayLimitTransaction) {
                signTransfer = -1
            }
            if ((sumTransfer + transfer) > VKPayLimitMonth) {
                signTransfer = -2
            }
        }

        "Visa", "Mir", "Mastercard", "Maestro" -> {
            if (transfer > cardLimitDay) {
                signTransfer = -1
            }
            if ((sumTransfer + transfer) > cardLimitMonth) {
                signTransfer = -2
            }
        }

        else -> signTransfer = -3
    }

    return signTransfer
}

//fun printComission(typeCard: String = "VK Pay", sumTransfer: Int = 0, transfer: Int) {
//    when (val result = calcComission(typeCard, sumTransfer, transfer)) {
//        -1 -> println("Перевод $typeCard невозможен! Превышен лимит суточного/разового перевода")
//        -2 -> println("Перевод $typeCard невозможен! Превышен лимит месячного перевода")
//        -3 -> println("$typeCard - неизвестная платежная система")
//        else -> println("Перевод $typeCard на сумму $transfer руб. Размер комиссии составит $result руб.")
//    }
//}
