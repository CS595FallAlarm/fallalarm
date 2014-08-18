package com.fallalarm.web.data;

public class Admin {
	private String username;
	private String fname;
	private	String lname;
	private String password;
	private String role;
	
	public Admin(){fname="";fname="";password="";role="";}
	public Admin(String username, String fname, String lname, String password, String role)
	{
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.role=role;
	}
	public void setFname(String fname)
	{
		this.fname = fname;
	}
	public String getFname()
	{
		return fname;
	}
	public void setLname(String lname)
	{
		this.lname = lname;
	}
	public String getLname()
	{
		return lname;
	}
	public void setRole(String role)
	{
		this.role =role;
	}
	public String getRole()
	{
		return this.role;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
