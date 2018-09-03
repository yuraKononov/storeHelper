package RepositoryTests;

import com.example.Entities.Product;
import com.example.Main;
import com.example.Services.ProductServiceInterface;
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
public class ProductRepositoryTest {

    @Autowired
    ProductServiceInterface productService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByName() {
        assertEquals(new Product(null,"test"), productService.findProductByName("test"));
    }

    @Test
    public void getAllProduct() {
        assertNotNull(productService.getAllProduct());
    }

    @Test
    public void getProductById() {
        assertEquals(new Product(null,"test"), productService.getProductById(1));
    }
}