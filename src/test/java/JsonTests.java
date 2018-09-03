import com.example.Main;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.json.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.json.*;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.*;


import javax.persistence.Entity;
import java.io.Serializable;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Main.class)
@TestPropertySource(locations="classpath:application.properties")
@JsonTest
public class JsonTests {

    @Autowired
    private JacksonTester<TestJsonClass> json;

    @Test
    public void testSerialize() throws Exception {
        TestJsonClass details = new TestJsonClass("Honda", "Civic");
        assertThat(this.json.write(details)).isEqualToJson("expected.json");
        assertThat(this.json.write(details)).hasJsonPathStringValue("@.make");
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make")
                .isEqualTo("Honda");
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"make\":\"Ford\",\"model\":\"Focus\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(new TestJsonClass("Ford", "Focus"));
        assertThat(this.json.parseObject(content).getA()).isEqualTo("Ford");
    }
}

class TestJsonClass implements Serializable {

    @Autowired
    private String a;

    @Autowired
    private String b;

    public TestJsonClass() {
    }

    public TestJsonClass(String a, String b){
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }
}