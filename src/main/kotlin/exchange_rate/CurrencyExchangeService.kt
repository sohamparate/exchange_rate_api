package exchange_rate

class CurrencyExchangeService(private val exchangeRateService: ExchangeRateServiceContract) {
    fun getConversionRate(from: Currency, to: Currency): Double {
        val rate = exchangeRateService.getConversionRate(from, to)
        return rate
    }
}
