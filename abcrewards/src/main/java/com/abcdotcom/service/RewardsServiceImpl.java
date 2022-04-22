package com.abcdotcom.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcdotcom.db.entities.CustomerTransactions;
import com.abcdotcom.db.repo.TransactionsRepo;
import com.abcdotcom.dto.RewardPointsDto;

@Service
public class RewardsServiceImpl implements RewardsService {

	@Autowired
	TransactionsRepo txRepo;

	private static String dateFormat = "MMM-YYYY";

	public RewardPointsDto getRewardPointsForACustomer(String customerId) throws Exception {
		RewardPointsDto rewardPointsDto = null;
		List<CustomerTransactions> customerTxList = null;

		customerTxList = txRepo.findByCustomerId(customerId);

		if (customerTxList != null && customerTxList.size() > 0) {
			rewardPointsDto = new RewardPointsDto();
			rewardPointsDto.setCustomerId(customerId);
			rewardPointsDto.setRewardPoints(calculateRewardPoints(customerTxList));
		}

		return rewardPointsDto;
	}

	public List<RewardPointsDto> getRewardPointsForAll() throws Exception {
		List<RewardPointsDto> rewardPointsDtoList = new ArrayList<>();
		List<CustomerTransactions> customerTxList = null;

		try {
			customerTxList = txRepo.findAll();
			if (customerTxList != null && customerTxList.size() > 0) {

				Map<String, List<CustomerTransactions>> customerTxMap = customerTxList.stream()
						.collect(Collectors.groupingBy(CustomerTransactions::getCustomerId));

				customerTxMap.keySet().stream().forEach((custId) -> {

					RewardPointsDto rewardPointsDto = new RewardPointsDto();

					rewardPointsDto.setCustomerId(custId);
					try {
						rewardPointsDto.setRewardPoints(calculateRewardPoints(customerTxMap.get(custId)));
					} catch (Exception e) {
					}
					rewardPointsDtoList.add(rewardPointsDto);
				});
			}
		} catch (Exception ex) {
			throw ex;
		}
		return rewardPointsDtoList;
	}

	private Map<String, Long> calculateRewardPoints(List<CustomerTransactions> customerTxList) throws Exception {
		Map<String, Long> rewardPointsMap = new HashMap<>();
		Map<String, Double> monthlyAmountMap = new HashMap<>();

		try {
			if (null != customerTxList) {
				customerTxList.stream().forEach((custTx) -> {

					LocalDate txDate = custTx.getTransactionDate();
					String monthYear = txDate.format(DateTimeFormatter.ofPattern(dateFormat));
					Double txAmount = custTx.getTransactionAmount();

					if (!monthlyAmountMap.containsKey(monthYear))
						monthlyAmountMap.put(monthYear, 0.0);

					monthlyAmountMap.put(monthYear, monthlyAmountMap.get(monthYear) + txAmount);
				});

				long totalRewardPoints = 0;
				for (String key : monthlyAmountMap.keySet()) {
					Double monthlyAmt = monthlyAmountMap.get(key);
					long monthlyRewardPoints = 0;
					if (monthlyAmt > 100)
						monthlyRewardPoints = (long) ((monthlyAmt - 100) * 2 + 50); // 2 points for each dollar > 100
																					// and 1 point for each dollar > 50
					else if (monthlyAmt > 50)
						monthlyRewardPoints = (long) (monthlyAmt - 50); // 1 point for each dollar > 50;

					rewardPointsMap.put(key, monthlyRewardPoints);
					totalRewardPoints += monthlyRewardPoints;
				}

				rewardPointsMap.put("Total", totalRewardPoints);
			}

		} catch (Exception ex) {
			throw ex;
		}
		return rewardPointsMap;
	}
}
