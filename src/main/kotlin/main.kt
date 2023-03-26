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

    calcComission(sumTransfer = 10_000, transfer = 15_000)
    calcComission("Maestro", 80_000, 1_000)
    calcComission("Visa", 100_000, 200_000)
    calcComission("Mir", 600_000, 50_000)

}

fun calcComission(typeCard: String = "VK Pay", sumTransfer: Int = 0, transfer: Int) {
    var result = 0

    val permission = possibleTransfer(typeCard, sumTransfer, transfer)
    when (permission) {
        1 -> {
            when (typeCard) {
                "Mastercard", "Maestro" -> {
                    if ((sumTransfer + transfer) > limitTransferMasterMaestro) {
                        result = (transfer * comissionMasterMaestro + comissionFixMasterMaestro).roundToInt()
                        println("Перевод на сумму $transfer руб. Размер комиссии составит $result руб.")
                    }
                }

                "Visa", "Mir" -> {
                    result = max((transfer * comissionVisaMir).roundToInt(), minComissionVisaMir)
                    println("Перевод на сумму $transfer руб. Размер комиссии составит $result руб.")
                }

                else -> println("Перевод на сумму $transfer руб. Размер комиссии составит $result руб.")
            }
        }

        -1 -> println("Перевод невозможен! Превышен лимит суточного/разового перевода")
        -2 -> println("Перевод невозможен! Превышен лимит месячного перевода")
    }
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
