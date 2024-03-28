package nemo.mestman.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import io.netty.handler.codec.http.HttpHeaderValues;

@Configuration
public class WebClientConfiguration {

	private static final String BASE_URL = "https://open.api.nexon.com/maplestory/v1";

	private final String apiKey;

	public WebClientConfiguration(@Value("${maple.api-key}") String apiKey) {
		this.apiKey = apiKey;
	}

	@Bean
	public WebClient webClient(ObjectMapper baseConfig) {
		ObjectMapper newMapper = baseConfig.copy();
		newMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

		ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
			.codecs(configurer ->
				configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(newMapper)))
			.build();
		return WebClient.builder()
			.baseUrl(BASE_URL)
			.defaultHeaders(header -> {
				header.add("x-nxopen-api-key", apiKey);
				header.add("accept", HttpHeaderValues.APPLICATION_JSON.toString());
			})
			.exchangeStrategies(exchangeStrategies)
			.build();
	}
}
