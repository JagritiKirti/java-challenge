package jp.co.axa.apidemo.model;

import java.io.Serializable;

public class JwtResponse  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2785799803149203878L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
	this.jwttoken = jwttoken;
	}

	public String getToken() {
	return this.jwttoken;
	}
}
