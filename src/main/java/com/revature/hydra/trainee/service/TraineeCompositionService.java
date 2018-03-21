package com.revature.hydra.trainee.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Batch;
import com.revature.beans.Trainee;
import com.revature.beans.TrainingStatus;
import com.revature.hydra.trainee.data.TraineeRepository;
/**
 * TraineeCompositionService
 * Implemented all the methods to achieve composition of complex Trainee.
 *  
 * 
 */
@Service
public class TraineeCompositionService {
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private TraineeCompositionMessagingService traineeCompositionMessagingService;
	
	/**
	 * 
	 * Saving a simple Trainee bean.
	 * 
	 * @param Trainee - Trainee to save
	 * 
	 */
	public void save(Trainee trainee) {
		traineeRepository.save(trainee);
	}
	
	/**
	 * 
	 * Updating a simple Trainee bean to become a complex Trainee bean?.
	 * 
	 * @param Trainee - Trainee to update
	 * 
	 */
	public void update(Trainee trainee) {
		save(trainee);
	}
	
	/**
	 * 
	 * Deleting a Trainee bean.
	 * 
	 * @param Trainee - Trainee to delete
	 * 
	 */
	public void delete(Trainee trainee) {
		traineeRepository.delete(trainee.getTraineeId());

		traineeCompositionMessagingService.sendSimpleNoteDeleteRequest(trainee.getTraineeId());
		traineeCompositionMessagingService.sendSimpleGradeDeleteRequest(trainee.getTraineeId());
		traineeCompositionMessagingService.sendSimplePanelDeleteRequest(trainee.getTraineeId());
	}
	
	/**
	 * 
	 * Obtain list of Trainees from traineeRepository.
	 * Then obtain a list of Complex Trainee beans from composition.
	 * 
	 * 
	 * @return List<Trainee> - List of complex Trainees
	 */
	public List<Trainee> findAll() {
		List<Trainee> basis = traineeRepository.findAll();
		List<Trainee> trainees = composeListOfTrainees(basis);

		return trainees;
	}
	
	/**
	 * Obtain list of Trainees from traineeRepository that have training status not dropped.
	 * Then obtain a list of Complex Trainee beans from composition.
	 * 
	 * 
	 * @return List<Trainee> - List of complex Trainees
	 */
	public List<Trainee> findAllNotDropped() {
		List<Trainee> basis = traineeRepository.findAllByTrainingStatusNot(TrainingStatus.DROPPED);
		List<Trainee> trainees = composeListOfTrainees(basis);

		return trainees;
	}
	
	/**
	 * Obtain list of Trainees from traineeRepository that have training status not dropped and part of a particular batch.
	 * Then obtain a list of Complex Trainee beans from composition.
	 * 
	 * @param Integer - batchId
	 * @return List<Trainee> - List of complex Trainees
	 */
	public List<Trainee> findAllByBatch(Integer batchId) {
		List<Trainee> basis = traineeRepository.findAllByBatchIdAndTrainingStatusNot(batchId,
				TrainingStatus.DROPPED);
		List<Trainee> trainees = composeListOfTrainees(basis);

		return trainees;
	}
	
	/**
	 * Obtain list of Trainees from traineeRepository that have training status dropped and part of a particular batch.
	 * Then obtain a list of Complex Trainee beans from composition.
	 * 
	 * @param Integer - batchId
	 * @return List<Trainee> - List of complex Trainees
	 */
	public List<Trainee> findAllDroppedByBatch(Integer batchId) {
		List<Trainee> basis = traineeRepository.findAllByBatchIdAndTrainingStatus(batchId,
				TrainingStatus.DROPPED);
		List<Trainee> trainees = composeListOfTrainees(basis);

		return trainees;
	}
	
	/**
	 * Obtain list of Trainees from traineeRepository that have training status not dropped and part of a particular batch.
	 * Then obtain a list of Complex Trainee beans from composition.
	 * 
	 * @param Integer - trainerId
	 * @return List<Trainee> - List of complex Trainees
	 */
	public List<Trainee> findAllByTrainer(Integer trainerId) {
		List<Batch> trainerBatches = traineeCompositionMessagingService.sendListBatchRequest(trainerId);
		List<Trainee> basis = new LinkedList<Trainee>();// traineeRepository.findAllByBatchIdAndTrainingStatusNot(trainerId,
														// TrainingStatus.Dropped);
		System.out.println(basis);
		List<Trainee> trainees = null;

		for (Batch b : trainerBatches) {
			List<Trainee> batchTrainees = traineeRepository.findAllByBatchIdAndTrainingStatusNot(b.getBatchId(),
					TrainingStatus.DROPPED);
			basis.addAll(batchTrainees);
		}

		trainees = composeListOfTrainees(basis);

		return trainees;
	}
	/**
	 * sphuang 02/08/2018 
	 * Obtain a Trainee from traineeRepository that have it's training status not dropped and matches the traineeId.
	 * Then obtain a Complex Trainee from composition.
	 * 
	 * @param Integer - traineeId
	 * @return List<Trainee> - List of complex Trainees
	 */
	public Trainee findOne(Integer traineeId) {
		Trainee result = traineeRepository.findOneByTraineeIdAndTrainingStatusNot(traineeId,
				TrainingStatus.DROPPED);

		return result;
	}
	
	/**
	 * Obtain a List of Trainees from traineeRepository that have it's training status not dropped and matches the email.
	 * Then obtain a List of Complex Trainee from composition.
	 * 
	 * @param String - email
	 * @return List<Trainee> - List of complex Trainees
	 */
	public List<Trainee> findByEmail(String email) {
		List<Trainee> basis = traineeRepository.findAllByEmailLikeAndTrainingStatusNot(email,
				TrainingStatus.DROPPED);
		List<Trainee> trainees = composeListOfTrainees(basis);

		return trainees;
	}
	/**
	 * Obtain a List of Trainees from traineeRepository that have it's training status not dropped and matches the name.
	 * Then obtain a List of Complex Trainees from composition.
	 * 
	 * @param String - name
	 * @return List<Trainee> - List of complex Trainees
	 */
	public List<Trainee> findByName(String name) {
		List<Trainee> basis = traineeRepository.findAllByNameLikeAndTrainingStatusNot(name,
				TrainingStatus.DROPPED);
		List<Trainee> trainees = composeListOfTrainees(basis);

		return trainees;
	}
	
	/**
	 * Obtain a List of Trainees from traineeRepository that have it's training status not dropped and matches the skypeId.
	 * Then obtain a List of Complex Trainees from composition.
	 * 
	 * @param String - skypeId
	 * @return List<Trainee> - List of complex Trainees
	 */
	public List<Trainee> findBySkypeId(String skypeId) {
		List<Trainee> basis = traineeRepository.findAllBySkypeIdLikeAndTrainingStatusNot(skypeId,
				TrainingStatus.DROPPED);
		List<Trainee> trainees = composeListOfTrainees(basis);

		return trainees;
	}
	
	/**
	 * Composing a list of Complex Trainees.
	 * 
	 * 
	 * @param List<Trainee> - List of Trainees
	 * @return List<Trainee> - List of complex Trainees
	 */
	private List<Trainee> composeListOfTrainees(List<Trainee> Trainees) {
		List<Trainee> trainees = new LinkedList<Trainee>();

		for (Trainee trainee : Trainees) {
//			Trainee trainee = composeTrainee(Trainee);
			trainees.add(trainee);
		}

		return trainees;
	}
//	
//	/**
//	 * Composing a complex Trainee from a Trainee. Obtains a batch to build the complex Trainee.
//	 * 
//	 * 
//	 * @param Trainee - Trainee
//	 * @return trainee - complex Trainee
//	 */
//	private Trainee composeTrainee(Trainee Trainee) {
//		Trainee trainee = new Trainee(Trainee);
//
//		Batch Batch = traineeCompositionMessagingService
//				.sendSingleBatchRequest(Trainee.getBatchId());
//		Batch batch = new Batch(Batch);
//		trainee.setBatch(batch);
//
//		return trainee;
//	}
}