package com.revature.hydra.trainee.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Trainee;
import com.revature.beans.TrainingStatus;
/**
 * TraineeRepository
 * Data Access Object with various methods to search database.
 * 
 * @author Samuel Huang
 */
@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

	Trainee findOneByResourceId(String resourceId);
	
	/**
	 * Find a Trainee by traineeId and without this status
	 *
	 * @param traineeId, status
	 *
	 * @return Trainee
	 */
  
	Trainee findOneByTraineeIdAndTrainingStatusNot(Integer traineeId, TrainingStatus status);
	
	/**
	 * find a list of Trainee by email and without this status
	 *
	 * @param email, status
	 *
	 * @return List of Trainee
	 */
	List<Trainee> findAllByEmailLikeAndTrainingStatusNot(String email, TrainingStatus status);

	/**
	 * find a list of Trainee by name and without this status
	 *
	 * @param name, status
	 *
	 * @return List of Trainee
	 */
	List<Trainee> findAllByNameLikeAndTrainingStatusNot(String name, TrainingStatus status);

	/**
	 * find a list of Trainee by skypeId and without this status
	 *
	 * @param skypeId, status
	 *
	 * @return List of Trainee
	 */
	List<Trainee> findAllBySkypeIdLikeAndTrainingStatusNot(String skypeId, TrainingStatus status);

	/**
	 * find a list of Trainee by batchId and without this status
	 *
	 * @param batchId, status
	 *
	 * @return List of Trainee
	 */
	List<Trainee> findAllByBatchIdAndTrainingStatusNot(Integer batchId, TrainingStatus status);
	
	/**
	 * find a list of Trainee by batchId and status
	 *
	 * @param batchId, status
	 *
	 * @return List of Trainee
	 */
	List<Trainee> findAllByBatchIdAndTrainingStatus(Integer batchId, TrainingStatus status);
	
	/**
	 * find a list of Trainee without this status
	 *
	 * @param status
	 *
	 * @return List of Trainee
	 */
	List<Trainee> findAllByTrainingStatusNot(TrainingStatus status);
	
	/**
	 * find a list of Trainee with this batchId
	 *
	 * @param batchId
	 *
	 * @return List of Trainee
	 */
	List<Trainee> findAllByBatchId(Integer batchId);
	
}
