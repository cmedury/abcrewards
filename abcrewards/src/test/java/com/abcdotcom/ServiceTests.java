package com.abcdotcom;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.abcdotcom.db.entities.CustomerTransactions;
import com.abcdotcom.db.repo.TransactionsRepo;
import com.abcdotcom.dto.RewardPointsDto;
import com.abcdotcom.service.RewardsServiceImpl;

@SpringBootTest(classes = Application.class)
@ExtendWith(MockitoExtension.class)
class ServiceTests {

	@InjectMocks
	RewardsServiceImpl rewardsService;

	@Mock
	TransactionsRepo txRepo;

	@Test
	public void testCalculateRewardPoints() throws Exception {
		List<CustomerTransactions> list = new ArrayList<CustomerTransactions>();
		CustomerTransactions tx1 = new CustomerTransactions("T1234", LocalDate.of(2022, Month.JANUARY, 1), 11.23);
		CustomerTransactions tx2 = new CustomerTransactions("T1234", LocalDate.of(2022, Month.JANUARY, 10), 32.45);
		CustomerTransactions tx3 = new CustomerTransactions("T1234", LocalDate.of(2022, Month.JANUARY, 15), 14.56);
		CustomerTransactions tx4 = new CustomerTransactions("T1234", LocalDate.of(2022, Month.FEBRUARY, 7), 120.00);
		CustomerTransactions tx5 = new CustomerTransactions("T1234", LocalDate.of(2022, Month.MARCH, 7), 100.00);
		
		list.add(tx1);
		list.add(tx2);
		list.add(tx3);
		list.add(tx4);
		list.add(tx5);

		when(txRepo.findAll()).thenReturn(list);

		// test
		List<RewardPointsDto> rewardPointsList = rewardsService.getRewardPointsForAll();

		assertEquals(1, rewardPointsList.size());
		assertEquals(rewardPointsList.get(0).getRewardPoints().get("Jan-2022"), 8);
		assertEquals(rewardPointsList.get(0).getRewardPoints().get("Feb-2022"), 90);		
		assertEquals(rewardPointsList.get(0).getRewardPoints().get("Mar-2022"), 50);
		assertEquals(rewardPointsList.get(0).getRewardPoints().get("Total"), 148);
	}

}
