import com.example.CronSchedule;
import com.example.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application.properties")
public class CronSheduleTests {

    private CronSchedule cronSchedule;

    @Before
    public void setUp() throws Exception {
        cronSchedule = new CronSchedule();
    }

    @After
    public void tearDown() throws Exception {
        cronSchedule = null;
    }

    @Test
    public void checkUpdate() throws IOException, ParseException {
        cronSchedule.checkUpdate();
        assertNotNull(cronSchedule.getProductList());
        assertNotNull(cronSchedule.getProductListList());
    }
}
