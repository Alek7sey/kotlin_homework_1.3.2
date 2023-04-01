import java.lang.Integer.max
import kotlin.math.roundToInt

const val comissionVisaMir = 0.0075
const val minComissionVisaMir = 35
const val comissionFixMasterMaestro = 20
const val comissionMasterMaestro = 0.006
const val limitTransferMasterMaestro = 75_000
const val cardLimitDay = 150_000
const val cardLimitMonth = 600_000
const val VKPayLimitTransaction = 15_000
const val VKPayLimitMonth = 40_000

fun main() {

    val typeCard1 = "Maestro"
    val sumTransfer2 = 80_000
    val transfer2 = 1_000

    printComission(sumTransfer = 10_000, transfer = 15_000)
    printComission(typeCard1, sumTransfer2, transfer2)
    printComission("Visa", 100_000, 200_000)
    printComission("Mir", 600_000, 50_000)

}

fun calcComission(typeCard: String = "VK Pay", sumTransfer: Int = 0, transfer: Int): Int {
    var result = 0

    val permission = possibleTransfer(typeCard, sumTransfer, transfer)
    when (permission) {
        1 -> {
            when (typeCard) {
                "Mastercard", "Maestro" -> {
                    if ((sumTransfer + transfer) > limitTransferMasterMaestro) {
                        result = (transfer * comissionMasterMaestro + comissionFixMasterMaestro).roundToInt()
                    }
                }

                "Visa", "Mir" -> {
                    result = max((transfer * comissionVisaMir).roundToInt(), minComissionVisaMir)
                }
            }
        }

        -1 -> result = -1
        -2 -> result = -2
    }
    return result
}

fun possibleTransfer(typeCard: String = "VK Pay", sumTransfer: Int = 0, transfer: Int): Int {
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

        else -> {
            if (transfer > cardLimitDay) {
                signTransfer = -1
            }
            if ((sumTransfer + transfer) > cardLimitMonth) {
                signTransfer = -2
            }
        }
    }

    return signTransfer
}

fun printComission(typeCard: String = "VK Pay", sumTransfer: Int = 0, transfer: Int) {
    when (val result = calcComission(typeCard, sumTransfer, transfer)) {
        -1 -> println("Перевод $typeCard невозможен! Превышен лимит суточного/разового перевода")
        -2 -> println("Перевод $typeCard невозможен! Превышен лимит месячного перевода")
        else -> println("Перевод $typeCard на сумму $transfer руб. Размер комиссии составит $result руб.")
    }
}
