package exchange_rate

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class ExchangeRateServiceTest {

    @Inject
    lateinit var httpClient: HttpClient

    @Test
    fun `it should take get conversion rates for a currency`() {
        val currency = Currency.INR

        val request = HttpRequest.GET<ExchangeRateResponse>("https://v6.exchangerate-api.com/v6/649fef065808f519a808ffef/latest/${currency}")
        val response = httpClient.toBlocking().exchange(request, ExchangeRateResponse::class.java)
        val expectedResponseStatus = HttpStatus.OK

        assertEquals(expectedResponseStatus, response.status)
    }
}