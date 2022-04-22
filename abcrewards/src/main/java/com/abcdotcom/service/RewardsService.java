package com.abcdotcom.service;

import java.util.List;

import com.abcdotcom.dto.RewardPointsDto;

public interface RewardsService {

	public List<RewardPointsDto> getRewardPointsForAll() throws Exception;
	public RewardPointsDto getRewardPointsForACustomer(String customerId) throws Exception;
	
}
