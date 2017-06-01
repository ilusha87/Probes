package spring.my.rest.client;

import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Illia Krysenko on 10.04.2017.
 */
public class SpringRestClient {

    static Logger logger = Logger.getLogger(SpringRestClient.class);
    static String baseUrl = "http://ikrysenko.ngrok.io/services/rest/methods/";
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("user", "ikrysenko@determine.com");
        headers.set("password", "Sel12345_");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl+"login", HttpMethod.GET, entity, String.class);
        String sessionId = responseEntity.getBody();
        System.out.println(sessionId);
        System.out.println(responseEntity);


        byte[] encoded = new byte[0];
        try {
            encoded = IOUtils.toByteArray(SpringRestClient.class.getResourceAsStream("/Test.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String exportedRoles = new String(encoded);
        System.out.println("The roles were successfully read from the file: " + exportedRoles);

        HttpHeaders headers2 = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers2.set("session_id", sessionId);
//        headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        headers2.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<String>(exportedRoles, headers2);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl+"importRoles", request, String.class);
        System.out.println(response.getBody());
        System.out.println(response);



    }
}
