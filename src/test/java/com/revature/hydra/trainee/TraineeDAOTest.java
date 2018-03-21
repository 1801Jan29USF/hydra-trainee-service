package com.revature.hydra.trainee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.Batch;
import com.revature.beans.Trainee;
import com.revature.hydra.trainee.data.TraineeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TraineeDAOTest {
	private static final Logger log = Logger.getLogger(TraineeDAOTest.class);

	@Autowired
	TraineeRepository dao;

	Trainee test;

	@Before
	public void initialize() {
		log.info("Initalizing a Test Trainee for use in Tests");
		test = new Trainee();
		test.setName("TRAINEE_NAME");
		test.setBatch(new Batch());
		test.setCollege("COLLEGE");
		test.setDegree("DEGREE");
		test.setEmail("TRAINEE_EMAIL@EMAIL.COM");
		test.setMajor("TEST_MAJOR");
		test.setName("TEST_NAME");
		test.setPhoneNumber("TEST_NUMBER");
		test.setProfileUrl("WWW.TEST.COM");
		test.setProjectCompletion("TEST_COMPLETE");
		test.setRecruiterName("TEST_RECRUTER");
		test.setResourceId("TEST_RESOURCEID");
		test.setSkypeId("TEST_SKYPE");
		test.setTechScreenerName("TEST_SCREENER");
		test.setTraineeId(555);
		
	}

	@Test
	public void testFindAll() {
		log.info("Getting All Trainees");
		List<Trainee> trainees = dao.findAll();

		assertFalse(trainees.isEmpty());
	}

//	@Test
//	public void findByTrainerId() {
//		log.info("Getting by traineeId");
//		Trainee trainee = dao.findOne(555);
//		assertEquals(555, trainee.getTraineeId());
//	}

	@Test
	public void addTrainee() {
		log.info("Adding Trainee");
		Trainee savedTrainee = dao.save(test);
		assertEquals(test.getTraineeId(), savedTrainee.getTraineeId());
	}

	@Test
	public void updateTrainee() {
		log.info("Updating Trainee");
		Trainee savedTrainee = dao.save(test);
		savedTrainee.setName("UPDATED_NAME");
		Trainee updatedTrainee = dao.save(savedTrainee);
		assertEquals(savedTrainee.getName(), updatedTrainee.getName());
	}

	@Test
	public void deleteTrainee() {
		log.info("Deleting Trainee");
		Trainee savedTrainee = dao.save(test);
		dao.delete(savedTrainee);
		assertNull(dao.findOne(savedTrainee.getTraineeId()));
	}

}
