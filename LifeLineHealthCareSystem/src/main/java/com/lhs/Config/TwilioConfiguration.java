package com.lhs.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TwilioConfiguration {
	
	private String accountSid="ACf31f6624b86b3fe7ad9f39cf95839e01";
	private String authToken="b408f9a6a5bead01b9c9e5b4c3d708a2";
	private String trialNumber="+19896372364";

}
