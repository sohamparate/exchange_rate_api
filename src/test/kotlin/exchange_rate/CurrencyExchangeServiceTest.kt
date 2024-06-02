package exchange_rate

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CurrencyExchangeServiceTest {
    @Test
    fun `it should take two currency types as input and return the conversion rate as output`() {
        val currencyExchangeService = CurrencyExchangeService()

        val exchangeRateForUSDtoINR = currencyExchangeService.getExchangeRate(Currency.USD, Currency.INR)
        val expectedConversionRate = 80.00

        assertEquals(expectedConversionRate, exchangeRateForUSDtoINR)
    }
}