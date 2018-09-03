package RepositoryTests;

import com.example.Entities.Account;
import com.example.Entities.ProductList;
import com.example.Entities.Query;
import com.example.Entities.Shop;
import com.example.Main;
import com.example.Services.QueryServiceInterface;
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
public class QueryRepositoryTest {

    @Autowired
    QueryServiceInterface queryService;

    @Test
    public void findByAccountId() {
        assertEquals(new Query(new Account(false), new ProductList(), null),
                queryService.getQueryById(1, 1));
    }

    @Test
    public void getAllQuery() {
        assertNotNull(queryService.getAllQuery());
    }

    @Test
    public void getQueryById() {
        assertEquals(new Query(new Account(false), new ProductList(), null),
                queryService.getQueryById(1, 1));
    }
}