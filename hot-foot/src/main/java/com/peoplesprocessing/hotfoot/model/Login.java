package com.peoplesprocessing.hotfoot.model;

public class Login {

	private String username;
	private String password;
	private String credentialId;
	private String grant_type;
	private String scope;
	private String client_id;
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCredentialId() {
		return credentialId;
	}
	public void setCredentialId(String credentialId) {
		this.credentialId = credentialId;
	}
	
	
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", credentialId=" + credentialId
				+ ", grant_type=" + grant_type + ", scope=" + scope + ", client_id=" + client_id + "]";
	}
	
	
	
	
}
