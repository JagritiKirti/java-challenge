package jp.co.axa.apidemo.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.axa.apidemo.constants.JWTConstant;
import jp.co.axa.apidemo.model.JwtRequest;
import jp.co.axa.apidemo.model.JwtResponse;
import jp.co.axa.apidemo.services.JwtUserDetailsService;
import jp.co.axa.apidemo.utils.JwtTokenUtil;

@SpringBootTest
public class JwtAuthenticationControllerTest {

	@InjectMocks
	JwtAuthenticationController jwtAuthenticationController;
	
	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtTokenUtil jwtTokenUtil;

	@Mock
	private JwtUserDetailsService userDetailsService;
	
	private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9"
			+ ".eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY0NDgyODk1NSwiaWF0IjoxNjQ0ODEwOTU1fQ"
			+ ".-TE9vqOQ1Yf4ryDYmEsx8B_WC_pMQw-fsq134xKfmst7H4PAYffHGV5bw21arIr0xIsYmfW5QX8_bkguHNwUrw";
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void createAuthenticationTokenTest() throws Exception {
		
		JwtRequest request = new JwtRequest();
		request.setUsername(JWTConstant.USERNAME);
		request.setPassword("adminpassword");
		UserDetails userDetails = new User(JWTConstant.USERNAME, JWTConstant.BCRYPT_PASSWORD,
				new ArrayList<>());
		when(userDetailsService.loadUserByUsername(JWTConstant.USERNAME)).thenReturn(userDetails);
		when(jwtTokenUtil.generateToken(userDetails)).thenReturn(TOKEN);
		ResponseEntity<JwtResponse> response = jwtAuthenticationController.createAuthenticationToken(request);
		assertEquals(response.getBody().getToken(), TOKEN);
	}
}
