package org.scoula.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Main {
	private Object temp;

	@JsonProperty("temp_min")
	private Object tempMin;

	private int humidity;
	private int pressure;

	@JsonProperty("feels_like")
	private Object feelsLike;

	@JsonProperty("temp_max")
	private Object tempMax;
}
