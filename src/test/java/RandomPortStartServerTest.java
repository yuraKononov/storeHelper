import com.example.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import static org.assertj.core.api.Assertions.assertThat;


@TestPropertySource(locations="classpath:application.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Main.class)
public class RandomPortStartServerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void startServerTest() {
        String body = this.restTemplate.getForObject("/", String.class);
        //assertThat(body).isEqualTo("[{\"account\":{\"user_id\":2,\"active\":true},\"productList\":{\"product\":{\"product_id\":2,\"image\":null,\"product_name\":\"test2\"},\"shop\":{\"shop_id\":1,\"shop_name\":\"test_shop\",\"shop_address\":\"test_address\"},\"price\":150.0,\"discont\":0.0,\"discont_begin\":null,\"discont_end\":null}}]");
        assertNotNull(body);
    }
}
