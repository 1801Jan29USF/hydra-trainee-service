package com.revature.hydra.trainee;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.Trainee;
import com.revature.beans.TrainingStatus;
import com.revature.hydra.trainee.data.TraineeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraineeRepositoryTests {
	
	@Autowired
	TraineeRepository test;
	
	@Test
	public void TestfindOneByTraineeIdAndTrainingStatusNot() {
		Trainee trainee = test.findOne(1);
		
		trainee = test.findOneByTraineeIdAndTrainingStatusNot((Integer) trainee.getTraineeId(), TrainingStatus.DROPPED);
		assertNotNull(trainee.getTraineeId());
	}
	
	@Test
	public void TestfindAllByEmailLikeAndTrainingStatusNot() {
		List<Trainee> trainee = test.findAllByEmailLikeAndTrainingStatusNot("howard.johnson@hotmail.com", TrainingStatus.DROPPED);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllByNameLikeAndTrainingStatusNot() {
		List<Trainee> trainee = test.findAllByNameLikeAndTrainingStatusNot("Howard Johnson", TrainingStatus.DROPPED);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllBySkypeIdLikeAndTrainingStatusNot() {
		List<Trainee> trainee = test.findAllBySkypeIdLikeAndTrainingStatusNot("osher.y.cohen", TrainingStatus.DROPPED);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllByBatchIdAndTrainingStatusNot() {
		List<Trainee> trainee = test.findAllByBatchIdAndTrainingStatusNot(2003, TrainingStatus.DROPPED);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllByBatchIdAndTrainingStatus() {
		List<Trainee> trainee = test.findAllByBatchIdAndTrainingStatusNot(2003, TrainingStatus.TRAINING);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllByTrainingStatusNot() {
		List<Trainee> trainee = test.findAllByTrainingStatusNot(TrainingStatus.DROPPED);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllByBatchId() {
		List<Trainee> trainee = test.findAllByBatchId(2003);
		assertNotNull(trainee);
	}
}