import models.ContactDTO;
import models.ErrorResponse;
import models.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class TestCase {

    String BASE = "http://localhost:8080";
    String BASE_USERS = "http://localhost:8080/users";
    String CONTACTS1 = "http://localhost:8080/users/1/contacts";

    @Test
    public void negativePostUser() {
        RestTemplate restTemplate = new RestTemplate();
        UserDTO user = new UserDTO(4, "v", "chernookaya");
        try {
            UserDTO userVika = restTemplate.postForObject(BASE_USERS, user, UserDTO.class);
            Assert.fail("Expected Exception");
            restTemplate.delete(BASE_USERS + "/" + userVika.getId());
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    public void positivePostUser() {
        RestTemplate restTemplate = new RestTemplate();
        UserDTO ivan = new UserDTO(4, "ivan", "ivanov");
        UserDTO result = restTemplate.postForObject(BASE_USERS, ivan, UserDTO.class);
        assertThat(result, notNullValue());
        assertEquals(ivan.getFirstName(), result.getFirstName());
        assertEquals(ivan.getLastName(), result.getLastName());
        restTemplate.delete(BASE_USERS + "/" + result.getId());
    }

    @Test
    public void negativeDeleteContact() {
        RestTemplate restTemplate = new RestTemplate();
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(), 404, "Contact with id:4 not found");
        ContactDTO contact = new ContactDTO(4, "liza", "chernookaya", "9500418181", "test@mail.ru");
        try {
            restTemplate.delete(CONTACTS1 + "/" + contact.getId());
        } catch (HttpClientErrorException e) {
            assertEquals(response.getStatus(), e.getRawStatusCode());
        }
    }

    @Test
    public void positiveGetContact() {
        RestTemplate restTemplate = new RestTemplate();
        ContactDTO expected = new ContactDTO(1, "Brandon", "Stark", "3123456789", "brain_star@gmail.com");
        ContactDTO contact = restTemplate.getForObject("http://localhost:8080/users/1/contacts/1", ContactDTO.class);
        assertEquals(expected, contact);
    }


}
