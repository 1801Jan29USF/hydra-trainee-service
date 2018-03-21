package com.revature.hydra.trainee.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Trainee;
import com.revature.hydra.trainee.service.TraineeService;

/**
 * Services requests for Trainer, Trainee, and Batch information
 *
 * @author Patrick Walsh
 *
 */
@RestController
//@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RequestMapping("trainees")
public class TraineeController {

	private static final Logger log = Logger.getLogger(TraineeController.class);
	private TraineeService traineeService;

	@Autowired
	public void setTrainingService(TraineeService traineeService) {
		this.traineeService = traineeService;
	}

	/*
	 *******************************************************
	 * TRAINEE SERVICES
	 *
	 *******************************************************
	 */
	
	/**
	 * get all trainees within a batch
	 * @param batchId - id of the batch desired
	 * @return list of trainees within that batch id
	 */
	@GetMapping("batch/{batchId}")
	//@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //possibly not used 2/8/18, 10:53am edt
	//@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	//@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING', 'PANEL')")
	public ResponseEntity<List<Trainee>> findAllByBatch(@PathVariable Integer batchId) {
		log.info("Finding trainees for batch: " + batchId);
		List<Trainee> trainees = traineeService.findAllTraineesByBatch(batchId);
		return new ResponseEntity<>(trainees, HttpStatus.OK);
	}
	
	/**
	 * get all dropped trainees within a batch
	 * @param batchId - id of the batch desired
	 * @return list of dropped trainees within that batch id
	 */
	@GetMapping("batch/dropped/{batchId}")
	//@RequestMapping(value = "/all/trainee/dropped", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	//@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING', 'PANEL')")
	public ResponseEntity<List<Trainee>> findAllDroppedByBatch(@PathVariable Integer batchId) {
		log.info("Finding dropped trainees for batch: " + batchId);
		List<Trainee> trainees = traineeService.findAllDroppedTraineesByBatch(batchId);
		return new ResponseEntity<>(trainees, HttpStatus.OK);
	}

	/**
	 * create a trainee
	 * @param trainee - the trainee to be created
	 * @return the created trainee
	 */
	@PostMapping
	//@RequestMapping(value = "/all/trainee/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	//@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'PANEL')")
	public ResponseEntity<Trainee> createTrainee(@Valid @RequestBody Trainee trainee) {
		log.info("Saving trainee: " + trainee);
		traineeService.save(trainee);
		return new ResponseEntity<>(trainee, HttpStatus.CREATED);
	}

	/**
	 * update a given trainee
	 * @param trainee - the trainee to be updated
	 * @return a response entity signifying a successful update
	 */
	@PutMapping
	//@RequestMapping(value = "/all/trainee/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	//@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER','PANEL')")
	public ResponseEntity<Void> updateTrainee(@Valid @RequestBody Trainee trainee) {
		log.info("Updating trainee: " + trainee);
		traineeService.update(trainee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * delete a given trainee
	 *
	 * @param traineeId - the id of the trainee to delete
	 * @return a response entity signifying a successful deletion
	 */
	@DeleteMapping("/{traineeId}")
	//@RequestMapping(value = "/all/trainee/delete/{id}", method = RequestMethod.DELETE)
	//@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	//@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER','PANEL')")
	public ResponseEntity<Void> deleteTrainee(@PathVariable Integer traineeId) {
		Trainee trainee = new Trainee();
		trainee.setTraineeId(traineeId);
		log.info("Deleting trainee: " + traineeId);
		traineeService.delete(trainee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/all/trainee/search/{searchTerm}", method = RequestMethod.GET)
	//@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	//@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER','PANEL')")
	public ResponseEntity<Set<Trainee>> searchTrainee(@PathVariable String searchTerm) {
		Set<Trainee> trainee = traineeService.search(searchTerm);
		if(trainee.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(trainee, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/trainee/getByEmail/{traineeEmail}", method = RequestMethod.GET)
	//@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	//@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'PANEL')")
	public ResponseEntity<Trainee> retreiveTraineeByEmail(@PathVariable String traineeEmail) {
		/* 
		 1. at some point, we will have unique constraint on trainee email.
		 	this method will check the database before adding the new trainee
		 2. before we can enable this functionality, we need to allow
		 	trainees to be assigned to more than one batch, or added with a batch at all
		 	(for tech screening feedback functionality)
		 */
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	/**
	 * Convenience method for accessing the Trainer information from the User
	 * Principal.
	 *
	 * @param auth
	 * @return
	 */
/*	private Trainer getPrincipal(Authentication auth) {
		return ((SalesforceUser) auth.getPrincipal()).getCaliberUser();
	}*/
}