import org.junit.Test

import org.junit.Assert.*

class MainKtTest {


    @Test
    fun possibleTransfer() {
        val result1 = possibleTransfer("Maestro", 150_000, 50_000)
        assertEquals(1, result1)
        val result2 = possibleTransfer("Mir", 600_000, 50_000)
        assertEquals(-2, result2)
        val result3 = possibleTransfer("VK Pay", 40_000, 20_000)
        assertEquals(-2, result3)
        val result4 = possibleTransfer("Visa", 10_000, 20_000)
        assertEquals(1, result4)
        val result5 = possibleTransfer("VK Pay", 10_000, 50_000)
        assertEquals(-2, result5)
    }

    @Test
    fun calcComission() {
        val result1 = calcComission("Maestro", 35_000, 15_000)
        assertEquals(0, result1)
        val result2 = calcComission("Maestro", 100_000, 35_000)
        assertEquals(230, result2)
        val result3 = calcComission("Mir", 50_000, 10_000)
        assertEquals(75, result3)
        val result4 = calcComission("VK Pay", 40_000, 30_000)
        assertEquals(-2, result4)
        val result5 = calcComission("Mastercard", 10_000, 200_000)
        assertEquals(-1, result5)
        val result6 = calcComission("Visa", 35_000, 20_000)
        assertEquals(150, result6)



    }

}