package com.sachira.spring_backend;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootTest
class SpringBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void Test(){
		SecretKey key = Jwts.SIG.HS256.key().build();
		byte[] encodedKey = key.getEncoded();
		String encodedBase64= Base64.getEncoder().encodeToString(encodedKey);
		System.out.println(encodedBase64);
	}


}
