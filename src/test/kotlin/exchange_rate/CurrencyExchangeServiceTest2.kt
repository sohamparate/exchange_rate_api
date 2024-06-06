package exchange_rate

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import io.micronaut.http.client.HttpClient
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@WireMockTest
@MicronautTest
class CurrencyExchangeServiceTest2 {
    private lateinit var wireMockServer: WireMockServer

    @Inject
    lateinit var httpClient: HttpClient

    @BeforeEach
    fun setUp() {
        wireMockServer = WireMockServer(WireMockConfiguration.options().port(9999))
        wireMockServer.start()
        WireMock.configureFor("localhost", 9999)
    }

    @AfterEach
    fun tearDown() {
        wireMockServer.stop()
    }

    @Test
    fun `it should return the rate for a pair of currencies`() {

        val apiKey = "649fef065808f519a808ffef"
        val fromCurrency = Currency.INR.toString()
        val toCurrency = Currency.USD.toString()

        val mockRate = 100.00
        WireMock.stubFor(
            WireMock.get(WireMock.urlEqualTo("/v6/$apiKey/pair/$fromCurrency/$toCurrency"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                            """
                            {
                                "base_code": "$fromCurrency",
                                "target_code": "$toCurrency",
                                "conversion_rate": $mockRate
                            }
                        """.trimIndent()
                        )
                )
        )

        val response = httpClient.toBlocking().retrieve(
            "http://localhost:9999/v6/$apiKey/pair/$fromCurrency/$toCurrency",
            ExchangeRateResponse::class.java
        )

        assertEquals(mockRate, response.conversion_rate)
    }
}