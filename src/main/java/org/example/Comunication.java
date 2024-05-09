package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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
    private String sessionId="";
    private String answer="";


    public List<User> getAllUser() throws JsonProcessingException {
        ResponseEntity<String> responseEntity;
        ObjectMapper objectMapper = new ObjectMapper();
        if (sessionId.isEmpty()) {  responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {});
        }else {
            HttpHeaders headers1 = new HttpHeaders();
            headers1.set("Cookie",sessionId);
            HttpEntity<User> http = new HttpEntity<User>(headers1);
            responseEntity = restTemplate.exchange(URL, HttpMethod.GET, http,
                new ParameterizedTypeReference<String>() {});}

        List<User>  allUser= objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<User>>(){});
        HttpHeaders headers = responseEntity.getHeaders();
        HttpHeaders headers1 = responseEntity.getHeaders();
//        headers1.get("Set-Cookie").stream().forEach(System.out::println);
        if (sessionId.isEmpty()) sessionId= headers.getFirst("Set-Cookie");
        System.out.println("? "+sessionId);
        return allUser;
    }
    public User getUser(Long id){

        return null;
    }
    public void saveUser(User user){
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Cookie",sessionId);
        HttpEntity<User> http = new HttpEntity<User>(user,headers1);
        System.out.println("!!!! "+http);
        if (user.getId()<4){

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL,http,String.class);

        System.out.println("! " +responseEntity);
            System.out.println("! " +responseEntity.getHeaders());
        System.out.println( "1 "+responseEntity.getBody());
        answer+=responseEntity.getBody();}
        else  {
           restTemplate.put(URL,user);

        }

    }
    public void updateUser(User user){
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Cookie",sessionId);
        HttpEntity<User> http = new HttpEntity<User>(user,headers1);
        System.out.println("!!!!up "+http);
        if (user.getId()<1){

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL,http,String.class);

            System.out.println("up " +responseEntity);
            System.out.println("up " +responseEntity.getHeaders());
            System.out.println( "up "+responseEntity.getBody());}
        else  {
            ResponseEntity<String> responseEntity = restTemplate.exchange(URL,HttpMethod.PUT,http,String.class, new ParameterizedTypeReference<String>() {});
            restTemplate.put(URL,http,String.class);
            System.out.println("up " +responseEntity);
            System.out.println("up " +responseEntity.getHeaders());
            System.out.println( "up "+responseEntity.getBody());
            answer+=responseEntity.getBody();
        }

    }


    public void deleteUser(Long id){
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Cookie",sessionId);
        HttpEntity<User> http = new HttpEntity<User>(headers1);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/3", HttpMethod.DELETE, http, String.class, new ParameterizedTypeReference<String>() {
        });
        System.out.println("del " +responseEntity);
        System.out.println("del " +responseEntity.getHeaders());
        System.out.println( "del "+responseEntity.getBody());
        answer+=responseEntity.getBody();
        System.out.println("");
        System.out.println(answer);
    }

}
