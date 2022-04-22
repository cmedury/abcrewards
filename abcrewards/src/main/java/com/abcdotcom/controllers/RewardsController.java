package com.abcdotcom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abcdotcom.CustomerNotFoundException;
import com.abcdotcom.InternalServerException;
import com.abcdotcom.NoDataFoundException;
import com.abcdotcom.dto.RewardPointsDto;
import com.abcdotcom.service.RewardsService;

@RestController
@RequestMapping(value = "/api/rewards", produces = MediaType.APPLICATION_JSON_VALUE)
public class RewardsController {

	@Autowired
	RewardsService rewardsService;

	@GetMapping("/get/{customerId}")
	@ResponseStatus(code = HttpStatus.OK)
	public RewardPointsDto getRewardPointsForACustomer(@PathVariable String customerId) {
		RewardPointsDto rewardPointsDto = null;
		try {
			rewardPointsDto = rewardsService.getRewardPointsForACustomer(customerId);

			if (rewardPointsDto == null) {
				throw new CustomerNotFoundException();
			}
		} catch (CustomerNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException();
		}
		return rewardPointsDto;
	}

	@GetMapping("/get")
	@ResponseStatus(code = HttpStatus.OK)
	public List<RewardPointsDto> getRewardPointsForAll() {
		List<RewardPointsDto> rewardPointsDtoList = null;
		try {
			rewardPointsDtoList = rewardsService.getRewardPointsForAll();

			if (null == rewardPointsDtoList || rewardPointsDtoList.size() == 0)
				throw new NoDataFoundException();
		} catch (NoDataFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException();
		}
		return rewardPointsDtoList;
	}
}
