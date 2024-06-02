package exchange_rate

class ExchangeRateService: ExchangeRateServiceContract {
    override fun getConversionRate(from: Currency, to: Currency): Double {
        return 80.00
    }
}
