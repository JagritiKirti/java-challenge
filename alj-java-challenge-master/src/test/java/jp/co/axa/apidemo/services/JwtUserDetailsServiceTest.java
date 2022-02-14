package jp.co.axa.apidemo.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jp.co.axa.apidemo.constants.JWTConstant;

@SpringBootTest
public class JwtUserDetailsServiceTest {

	private JwtUserDetailsService jwtUserDetailsService = new JwtUserDetailsService();
	
	@Test
	public void loadUserByUsernameTest() {
		
		UserDetails response = jwtUserDetailsService.loadUserByUsername(JWTConstant.USERNAME);
		assertEquals(response.getUsername(), JWTConstant.USERNAME);
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsernameTest_whenIncorrectUser() {
		jwtUserDetailsService.loadUserByUsername("testUser");	
	}
}
