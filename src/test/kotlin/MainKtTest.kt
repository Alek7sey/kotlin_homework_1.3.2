import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calcComissionMaestro1() {
        val result1 = calcComission("Maestro", 100_000, 35_000)
        assertEquals(230, result1)
    }

    @Test
    fun calcComissionMaestro2() {
        val result2 = calcComission("Maestro", 10_000, 2_000)
        assertEquals(0, result2)
    }

    @Test
    fun calcComissionMir() {
        val result = calcComission("Mir", 50_000, 10_000)
        assertEquals(75, result)
    }

    @Test
    fun calcComissionVisa() {
        val result = calcComission("Visa", 35_000, 20_000)
        assertEquals(150, result)
    }

    @Test
    fun calcComissionVKPayDefault() {
        val result = calcComission(sumTransfer = 10_000, transfer = 1_000)
        assertEquals(0, result)
    }


    @Test
    fun calcComissionMastercard1() {
        val result1 = calcComission("Mastercard", 10_000, 2_000)
        assertEquals(0, result1)
    }

    @Test
    fun calcComissionMastercard2() {
        val result2 = calcComission("Mastercard", 100_000, 1_000)
        assertEquals(26, result2)
    }

    @Test
    fun comissionVKPayPerDay() {
        val result = calcComission(transfer = 20_000)
        assertEquals(-1, result)
    }

    @Test
    fun comissionVKPayPerMonth() {
        val result = calcComission(sumTransfer = 40_000, transfer = 10_000)
        assertEquals(-2, result)
    }

    @Test
    fun comissionMastercardPerDay() {
        val result = calcComission("Mastercard", 10_000, 200_000)
        assertEquals(-1, result)
    }

    @Test
    fun comissionMastercardPerMonth() {
        val result = calcComission("Mastercard", 590_000, 20_000)
        assertEquals(-2, result)
    }

    @Test
    fun unknownPaymentSystem() {
        val result = calcComission("VKPay", 10_000, 15_000)
        assertEquals(-3, result)
    }
}