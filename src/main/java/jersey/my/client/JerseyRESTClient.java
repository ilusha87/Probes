package jersey.my.client;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Illia Krysenko on 10.04.2017.
 */
public class JerseyRESTClient {
    /*public static void main(String[] args) {
        WebResource webResource = getWebResource(httpHost, CLMRestURI.LOGIN);
        ClientResponse response = webResource
                .accept(PageAttributes.MediaType.TEXT_PLAIN)
                .header("user", user) //user login from the server
                .header("password", password) //user password from the srver
                .get(ClientResponse.class);

        String session_id = response.getEntity(String.class);

        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get("Test.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String exportedRoles = new String(encoded);
        logger.info("The roles were successfully read from the file");
        WebResource webResource = getWebResource(httpHost, CLMRestURI.IMPORT_ROLES);

        ClientResponse response = webResource
                .header("session_id", session_id)
                .accept(PageAttributes.MediaType.APPLICATION_XML)
                .entity(exportedRoles, PageAttributes.MediaType.APPLICATION_XML)
                .post(ClientResponse.class);
    }*/
}
