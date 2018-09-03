package RepositoryTests;

import com.example.Entities.Account;
import com.example.Entities.ProductList;
import com.example.Entities.QueryHistory;
import com.example.Main;
import com.example.Services.QueryHistoryServiceInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application.properties")
public class QueryHistoryRepositoryTest {

    @Autowired
    QueryHistoryServiceInterface queryHistoryService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByAccountId() {
        assertEquals(new QueryHistory(new Account(false), new ProductList()),
                queryHistoryService.getQueryHistoryById(1, 1));
    }

    @Test
    public void getAllQueryHistory() {
        assertNotNull(queryHistoryService.getAllQueryHistory());
    }

    @Test
    public void getQueryHistoryById() {
        assertEquals(new QueryHistory(new Account(false), new ProductList()),
                queryHistoryService.getQueryHistoryById(1, 1));
    }
}