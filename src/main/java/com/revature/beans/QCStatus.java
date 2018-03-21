package com.revature.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QCStatus implements Serializable{
	@JsonProperty("Superstar")
	SUPERSTAR,
	@JsonProperty("Good")
	GOOD,
	@JsonProperty("Average")
	AVERAGE,
	@JsonProperty("Poor")
	POOR,
	@JsonProperty("Undefined")
	UNDEFINED
}
