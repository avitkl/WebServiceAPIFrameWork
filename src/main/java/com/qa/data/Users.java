package com.qa.data;

//pojo - plain old java object
public class Users {

	String name;
	String job;
	String createdAt;
	String id;
	
	public Users()
	{
		
	}
	
	public Users(String name,String job)
	{
		this.name=name;
		this.job=job;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//getters and setters methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
}
