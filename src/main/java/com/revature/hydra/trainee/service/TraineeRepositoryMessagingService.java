package com.revature.hydra.trainee.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.beans.Trainee;
/**
 * Message Listener
 * The queue in which the message comes in determines the type of composition.
 *  
 */
@Service
public class TraineeRepositoryMessagingService {

	@Autowired
	private TraineeRepositoryRequestDispatcher traineeRepositoryRequestDispatcher;

	/**
	 * 
	 * Parses message in queue to a string json object.
	 * RequestDispatcher then processes the message and returns a Trainee.
	 * 
	 * 
	 * @param String - message
	 * @return Trainee 
	 */
	@RabbitListener(queues = "revature.hydra.repos.trainee")
	public Trainee receiveSingleTraineeRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return traineeRepositoryRequestDispatcher.processSingleTraineeRequest(request);
	}

	/**
	 * Parses message in queue to a string json object.
	 * RequestDispatcher then processes the message and returns a List of Trainee.
	 * 
	 * 
	 * @param String - message
	 * @return List<Trainee> - List of simple Trainee
	 * Parse a String for List of Trainee
	 *
	 * @param Message
	 *
	 * @return List of Trainee
	 */
	@RabbitListener(queues = "revature.hydra.repos.trainee.list")
	public List<Trainee> receiveListTraineeRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return traineeRepositoryRequestDispatcher.processListTraineeRequest(request);
	}
	
	/**
//	 * Parses message in queue to a string json object.
//	 * RequestDispatcher then processes the message and returns a List of ComplexTrainees.
//	 * 
//	 * 
//	 * @param String - message
//	 * @return List<Trainee> - List of Complex Trainees
//	 */
//	@RabbitListener(queues = "revature.hydra.service.trainee.list")
//	public List<Trainee> receiveListTraineeRequest(String message) {
//		JsonParser parser = new JsonParser();
//		JsonElement element = parser.parse(message);
//		JsonObject request = element.getAsJsonObject();
//		return traineeRepositoryRequestDispatcher.processListTraineeRequest(request);
//	}
}
