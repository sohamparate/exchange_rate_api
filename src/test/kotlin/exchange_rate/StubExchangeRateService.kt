package exchange_rate

class StubExchangeRateService(): ExchangeRateServiceContract {
    override fun getConversionRate(from: Currency, to: Currency): Double {
        return 80.00
    }
}
