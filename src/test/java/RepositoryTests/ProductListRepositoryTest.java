package RepositoryTests;

import com.example.Entities.Product;
import com.example.Entities.ProductList;
import com.example.Entities.Shop;
import com.example.Main;
import com.example.Services.ProductListService;
import com.example.Services.ProductServiceInterface;
import com.example.Services.ShopServiceInterface;
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
@TestPropertySource(locations="classpath:application.properties")
public class ProductListRepositoryTest {

    @Autowired
    ProductListService productList;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByShop() {
        assertEquals(new ProductList(new Product(), new Shop(), 100, 20, null, null),
                productList.findProductListByShop(1));
    }

    @Test
    public void findByProduct() {
        assertEquals(new ProductList(new Product(), new Shop(), 100, 20, null, null),
                productList.findProductListByProduct(1));
    }

    @Test
    public void getAllProductList() {
        assertNotNull(productList.getAllProductList());
    }

    @Test
    public void getProductListById() {
        assertNotNull(productList.getProductListById(1, 1));
    }
}