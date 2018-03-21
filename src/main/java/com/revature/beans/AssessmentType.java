package com.revature.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AssessmentType implements Serializable{
	@JsonProperty("Exam")
	EXAM,
	@JsonProperty("Verbal")
	VERBAL,
	@JsonProperty("Project")
	PROJECT,
	@JsonProperty("Presentation")
	PRESENTATION,
	@JsonProperty("Other")
	OTHER
}