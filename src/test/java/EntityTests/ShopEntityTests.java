package EntityTests;

import com.example.Entities.Product;
import com.example.Main;
import com.example.Services.ProductServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application.properties")
public class ShopEntityTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetShopById() throws Exception {
        mvc.perform(get("/shop/1"))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                /*.andExpect((ResultMatcher) jsonPath("$.shop_name", is("Дикси")))*/;
    }

    @Test
    public void testGetAllShop() throws Exception {
        mvc.perform(get("/shop/get_all"))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddShop() throws Exception {
        mvc.perform(post("/shop/add_shop"))
                .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateShop() throws Exception {
        mvc.perform(put("/shop/update"))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteShop() throws Exception {
        mvc.perform(delete("/shop/delete/0"))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
