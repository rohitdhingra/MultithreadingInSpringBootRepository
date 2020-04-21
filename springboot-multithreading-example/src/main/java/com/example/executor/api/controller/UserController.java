package com.example.executor.api.controller;



import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.executor.api.entity.User;
import com.example.executor.api.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@PostMapping(value = "/users",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = "application/json")
	public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception
	{
//		logger.info("Started the call of the method saveUsers");
		for(MultipartFile file: files)
		{
			logger.info("Calling the services method");
			userService.saveUsers(file);
		}
//		logger.info("Ending the call of the method saveUsers");
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(value = "/users",produces = "application/json")
	public CompletableFuture<ResponseEntity> findAllUsers()
	{
		return userService.findAllUsers().thenApply(ResponseEntity::ok);
	}
	@GetMapping(value="/getUsersByThread",produces = "application/json")
	public ResponseEntity getUsers()
	{
		CompletableFuture<List<User>> users1 = userService.findAllUsers();
		CompletableFuture<List<User>> users2 = userService.findAllUsers();
		CompletableFuture<List<User>> users3 = userService.findAllUsers();
		CompletableFuture.allOf(users1,users2,users3).join();
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
}
