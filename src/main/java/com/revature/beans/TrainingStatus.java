package com.revature.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TrainingStatus implements Serializable{
	@JsonProperty("Signed")
	SIGNED,
	@JsonProperty("Selected")
	SELECTED,
	@JsonProperty("Training")
	TRAINING,
	@JsonProperty("Marketing")
	MARKETING,
	@JsonProperty("Confirmed")
	CONFIRMED,
	@JsonProperty("Employed")
	EMPLOYED,
	@JsonProperty("Dropped")
	DROPPED
}