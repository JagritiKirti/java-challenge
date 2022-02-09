package jp.co.axa.apidemo.constants;

public class JWTConstant {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static final String API_KEY_NAME = "JWT";

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

	public static final String JWT_TOKEN_START_WITH = "Bearer ";

	public static final String[] AUTH_WHITELIST = {"/v2/api-docs",
			"/swagger-resources", 
			"/swagger-resources/configuration/ui", 
			"/swagger-resources/configuration/security",
			"/swagger-ui.html", "/webjars/**",
			"/authenticate","/h2-console/**"};
	
	public static final String USERNAME = "admin";
	
	public static final String BCRYPT_PASSWORD = "$2a$12$5p5IOs6UXLL8PqOTE1FSluOLWiZ5dLWBqPcT8mlHiLC6hnL8I6gC2";

}
