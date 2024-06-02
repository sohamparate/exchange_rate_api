package exchange_rate

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ExchangeRateResponse(
    val base_code: String,
    val target_code: String,
    val conversion_rate: Double
)
