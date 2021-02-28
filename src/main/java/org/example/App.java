package org.example;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class App {
    static final String URL_USERS = "http://91.241.64.178:7081/api/users/";

    public static void main(String[] args) {
        //----------1-------------//
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity<>(headers);
        HttpEntity<String> responseFirst = template.exchange(URL_USERS, HttpMethod.GET, request, String.class);
        HttpHeaders headers1 = responseFirst.getHeaders();
        String set_cookie = headers1.getFirst(headers.SET_COOKIE);

        //----------2-------------//
        Long id = 3L;
        User user = new User(id, "James", "Brown", (byte) 1);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", set_cookie);
        HttpEntity entityOne = new HttpEntity(user, requestHeaders);
        ResponseEntity responseTwo = template.exchange(URL_USERS, HttpMethod.POST, entityOne, String.class);
        String firstWord = (String) responseTwo.getBody();

        //----------3-------------//
        User userNew = new User(id, "Thomas", "Shelby", (byte) 1);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entityTwo = new HttpEntity(userNew, requestHeaders);
        ResponseEntity responseThree = template.exchange(URL_USERS, HttpMethod.PUT, entityTwo, String.class);
        String secondWord = (String) responseThree.getBody();

        //----------4-------------//

        ResponseEntity responseFour = template.exchange(URL_USERS + id, HttpMethod.DELETE, entityOne,String.class);
        String threeWord = (String) responseFour.getBody();
        System.out.println(firstWord+secondWord+threeWord);
    }
}
