package com.revature.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RecordingConsent implements Serializable{
	@JsonProperty("Yes")
	YES,
	@JsonProperty("No")
	NO,
	@JsonProperty("Did Not Ask")
	DID_NOT_ASK
}