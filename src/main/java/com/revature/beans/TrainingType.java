package com.revature.beans;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TrainingType implements Serializable {
	@JsonProperty("Revature")
	REVATURE,
	@JsonProperty("Corporate")
	CORPORATE,
	@JsonProperty("University")
	UNIVERSITY,
	@JsonProperty("Other")
	OTHER
}