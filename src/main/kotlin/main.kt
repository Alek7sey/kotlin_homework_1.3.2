import java.lang.Integer.max
import kotlin.math.roundToInt

const val comissionVisaMir = 0.0075
const val minComissionVisaMir = 35
const val comissionFixMasterMaestro = 20
const val comissionMasterMaestro = 0.006
const val limitTransferMasterMaestro = 75_000

fun main() {

    val sumTransfer1 = 20_000
    val transfer1 = 150
    val sumTransfer2 = 65_000
    val transfer2 = 10100

    val amount1 = calcComission("", sumTransfer1, transfer1)
    val amount2 = calcComission("Maestro", sumTransfer2, transfer2)
    println("Перевод на сумму $transfer1 руб. Размер комиссии составит $amount1 руб.")
    println("Перевод на сумму $transfer2 руб. Размер комиссии составит $amount2 руб.")
}

fun calcComission(typeCard: String = "VK Pay", sumTransfer: Int = 0, transfer: Int): Int {
    var result = 0

    when (typeCard) {
        "Mastercard", "Maestro" -> {
            if ((sumTransfer + transfer) > limitTransferMasterMaestro) {
                result = (transfer * comissionMasterMaestro + comissionFixMasterMaestro).roundToInt()
            }
        }

        "Visa", "Mir" -> result = max((transfer * comissionVisaMir).roundToInt(), minComissionVisaMir)
    }
    return result
}
