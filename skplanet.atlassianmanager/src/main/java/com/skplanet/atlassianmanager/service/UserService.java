package com.skplanet.atlassianmanager.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.skplanet.atlassianmanager.vo.UserVo;

import org.springframework.boot.devtools.remote.client.HttpHeaderInterceptor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

@Service
public class UserService {

	private final String CROWD_APP_ID  = "skpjira";
    private final String CROWD_APP_PWD = "jirapassword";
    
	public void authUser(UserVo user) throws Exception{
		System.out.println(user.toString());
		
		String crowdUrl = "http://crowd.skplanet.com:8095/crowd/rest/usermanagement/latest/authentication?username=" + user.getId();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(CROWD_APP_ID, CROWD_APP_PWD));
        restTemplate.getInterceptors().add(new HttpHeaderInterceptor("Content-Type", "application/json"));
        restTemplate.getInterceptors().add(new HttpHeaderInterceptor("Accept", "application/json"));
        
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                System.out.println("hasError");
                System.out.println(clientHttpResponse.getStatusCode());
                System.out.println(clientHttpResponse.getBody());
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
                System.out.println("handleError");
                System.out.println(clientHttpResponse.getStatusCode());
                System.out.println(clientHttpResponse.getBody());

            }
        });

        ResponseEntity<Object> res = restTemplate.postForEntity(crowdUrl, user.getPassWord(), Object.class);
        System.out.println("getStatusCode() : " + res.getStatusCode());
        System.out.println("getBody() : " + res.getBody());

	}
	
}
