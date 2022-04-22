package com.abcdotcom.dto;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class RewardPointsDto {

	private String customerId;
	private Map<String, Long> rewardPoints;

	public RewardPointsDto(String customerId, Map<String, Long> rewardPoints) {
		this.customerId = customerId;
		this.rewardPoints = rewardPoints;
	}

}
