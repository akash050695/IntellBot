package com.intellbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class MakeCall {
	public static String getResponse(String arg) {

		AIConfiguration configuration = new AIConfiguration("593033b2fa9c42378347306195eb0f1b");

		AIDataService dataService = new AIDataService(configuration);

		String line = arg;
		String responseString = "Sorry couldn't reach our service";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			AIRequest request = new AIRequest(line);

			AIResponse response = null;
			try {
				response = dataService.request(request);
			} catch (AIServiceException e) {
				e.printStackTrace();
			}

			if (response.getStatus().getCode() == 200) {
				responseString = response.getResult().getFulfillment().getSpeech();
			} else {
				responseString = response.getStatus().getErrorDetails();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return responseString;
	}
}
