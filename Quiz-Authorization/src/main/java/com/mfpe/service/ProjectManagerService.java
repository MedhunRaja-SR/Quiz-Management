package com.mfpe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.repository.ProjectManagerRepo;
import com.mfpe.exception.ProjectManagerNotFoundException;
import com.mfpe.model.ProjectManager;

@Service
public class ProjectManagerService  {

	@Autowired
	private ProjectManagerRepo projectManagerRepo;
	
	public ProjectManager getProjectManagerByUserName(String username) throws ProjectManagerNotFoundException{
		ProjectManager projectManager = null;
		projectManager = projectManagerRepo.getProjectManagerByUserName(username);
		if(projectManager==null) {
			throw new ProjectManagerNotFoundException("Given Project-Manager-Details does not exist in our Database!!");
		}
		return projectManager;
	}
}
