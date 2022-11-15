package com.mfpe.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mfpe.model.ProjectManager;
import com.mfpe.model.ProjectManagerDetails;


@ExtendWith(MockitoExtension.class)
public class ProjectManagerDetailsServiceTest{
	
	@Mock
	private ProjectManagerService projectManagerService;
	
	@InjectMocks
	private ProjectManagerDetailsService projectManagerdetailsService;	// assert from here
	
	@Test
	public void loadUserByUsernameTest() throws UsernameNotFoundException {
		// correct - test
		String username1 = "user1";
		ProjectManager projectManager = null;
		
		// making correct ProjectManager
		// test ProjectManager object -- for correct
		projectManager = new ProjectManager();
		projectManager.setId(1);
		projectManager.setName("user1");	// same username
		projectManager.setPassword("abcd1234");
		
		when(projectManagerService.getProjectManagerByUserName(username1)).thenReturn(projectManager);
		assertEquals(new ProjectManagerDetails(projectManager).getName(), projectManagerdetailsService.loadUserByUsername(username1).getName());
		
		//ProjectManager projectManager = projectManagerService.getProjectManagerByUserName(username);
		// for wrong
		projectManager = null;
		String username2 = "InvalidUser1";
		when(projectManagerService.getProjectManagerByUserName(username2)).thenReturn(projectManager);
		assertEquals(null, projectManagerdetailsService.loadUserByUsername(username2));
		
	}
	
}
