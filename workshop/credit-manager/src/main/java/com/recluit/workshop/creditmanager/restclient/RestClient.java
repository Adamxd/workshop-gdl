package com.recluit.workshop.creditmanager.restclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {
	public String connect(String action, String data){
		Client client = Client.create();
		data.replace(" ","%20");
		data.replace("|","%7C");
		WebResource webResource = client.resource("http://localhost:8080/communication-adapter-1.0/cb/"+action+data);
		ClientResponse response = webResource.accept("text/plain").get(ClientResponse.class);

			if (response.getStatus() != 200){
			throw new RuntimeException("Filed!!:"+response.getStatus());
	}
	return response.getEntity(String.class);
}
}
