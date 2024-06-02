package exchange_rate

interface ExchangeRateServiceContract {
    fun getConversionRate(from: Currency, to: Currency): Double
}
