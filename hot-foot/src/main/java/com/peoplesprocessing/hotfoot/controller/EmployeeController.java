package com.peoplesprocessing.hotfoot.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.peoplesprocessing.hotfoot.model.Login;
import com.peoplesprocessing.hotfoot.model.User;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	private static List<User> user;
	
	static {
		user =  new ArrayList<>(Arrays.asList( new User("Venkat","Dev"),
				new User("Anand","Architect"),
				new User("Saravanan","BA")));
		
	}

//	@CrossOrigin
	@GetMapping(path = "/employees")
	public ResponseEntity<List<User>> getEmployee() {
		return ResponseEntity.ok(user);
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<User> addEmployee(@RequestBody User newUser) {
		user.add(newUser);
		return ResponseEntity.ok(newUser);
	}
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping(path="/hotfoot/login")
//	@CrossOrigin
	public ResponseEntity<Object> login(@RequestBody Login login){
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl
		  = "http://localhost:8080/auth/realms/hotfoot/protocol/openid-connect/token";
		
		HttpHeaders headers = new org.springframework.http.HttpHeaders();
		login.setGrant_type("password");
		login.setClient_id("hotfoot-keycloak");
		login.setScope("openid");
       // headers.set("client_id", "hotfoot-keycloak");
		//headers.set("Content-type", "application/json");
		//headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	//	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
		headers.add("Accept", MediaType.APPLICATION_JSON.toString()); 
		
       
        	MultiValueMap<String, String> map = new LinkedMultiValueMap<> ();
        			//ConvertObjectToMap(login);
        	//headers.set("Content-length",new String(map.size()));
        	map.add("grant_type","password");
        	  map.add("username",login.getUsername());
        	  map.add("password",login.getPassword());
        	  map.add("scope","openid");
        	  map.add("client_id","hotfoot-keycloak");
        	//  map.add("client_id","hotfoot-web-client");
        //	  map.add("redirect_uri","http://127.0.0.1:9094/employee");
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        	
//        	 ResponseEntity<Object> test = (ResponseEntity<Object>) 
//             		restTemplate.postForObject(fooResourceUrl, request, Object.class);
//            ResponseEntity<String> test = restTemplate.exchange(
//            		fooResourceUrl, HttpMethod.POST, request,
//                    String.class);
            
            Object response = WebClient.create()
            	    .post()
            	    .uri(fooResourceUrl)
            	    .accept(MediaType.APPLICATION_JSON)
            	    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            	    .body(BodyInserters.fromFormData(map))
            	    .exchange()
            	    .block()
            	    .bodyToMono(Object.class)
            	    .block();
             System.out.println(response);
		
       
		return ResponseEntity.ok(response);
	}
	
	private static MultiValueMap<String, String> ConvertObjectToMap(Object obj) throws 
    IllegalAccessException, 
    IllegalArgumentException, 
    InvocationTargetException {
        Class<?> pomclass = obj.getClass();
        pomclass = obj.getClass();
        Method[] methods = obj.getClass().getMethods();


        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Method m : methods) {
           if (m.getName().startsWith("get") && !m.getName().startsWith("getClass")) {
              Object value = (Object) m.invoke(obj);
              map.add(m.getName().substring(3), (String) value);
           }
        }
    return map;
}
}
