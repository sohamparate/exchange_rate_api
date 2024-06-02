package exchange_rate

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import reactor.core.publisher.Mono

const val API_KEY = "649fef065808f519a808ffef"

class ExchangeRateService(private val httpClient: HttpClient): ExchangeRateServiceContract {

    override fun getConversionRate(from: Currency, to: Currency): Double {
        val url = "https://v6.exchangerate-api.com/v6/${API_KEY}/pair/${from}/${to}"
        val request = HttpRequest.GET<ExchangeRateResponse>(url)
        val response = httpClient.retrieve(request, ExchangeRateResponse::class.java)

        val rate = Mono.from(response).map {
            it.conversion_rate
        }

        return rate.block() ?: -1.0
    }
}
