package exchange_rate

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IntegrationTest {

    private val exchangeRateService = StubExchangeRateService()
    private val currencyExchangeService = CurrencyExchangeService(exchangeRateService)

    @Test
    fun `it should take two currencies and return the conversion rate`() {
        val rate = currencyExchangeService.getConversionRate(Currency.USD, Currency.INR)

        val expectedRate = exchangeRateService.getConversionRate(Currency.USD, Currency.INR)

        assertEquals(expectedRate, rate)
    }
}