package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class Comunication {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL="http://94.198.50.185:7081/api/users";
    private String sessionId;


    public List<User> getAllUser() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {});
        List<User>  allUser= objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<User>>(){});
        HttpHeaders headers = responseEntity.getHeaders();
        sessionId= headers.getFirst("Set-Cookie");
        System.out.println(headers.toString());
        return allUser;
    }
    public User getUser(Long id){
        return null;
    }
    public void saveUser(User user){

    }
    public void deleteUser(Long id){}
}
