import org.junit.Test

import org.junit.Assert.*

class MainKtTest {


//    @Test
//    fun possibleTransfer() {
//        val result = possibleTransfer("Maestro", 150_000, 50_000)
//        assertEquals(-1, result)
//    }

    @Test
    fun calcComission() {
        val result1 = calcComission("Maestro", 35_000, 15_000)
        assertEquals(0, result1)
        val result2 = calcComission("Maestro", 100_000, 35_000)
        assertEquals(230, result2)
        val result3 = calcComission("Mir", 50_000, 10_000)
        assertEquals(75, result3)
        val result4 = calcComission("Mir", 600_000, 50_000)
        assertEquals(-2, result4)
        val result5 = calcComission("VK Pay", 10_000, 20_000)
        assertEquals(-1, result5)

    }

}