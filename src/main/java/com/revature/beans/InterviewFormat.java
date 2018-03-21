package com.revature.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InterviewFormat implements Serializable{

	@JsonProperty("Skype")
	SKYPE,
	@JsonProperty("Phone")
	PHONE,
	@JsonProperty("On-Site")
	ON_SITE
	
}