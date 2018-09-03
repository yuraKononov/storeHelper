package com.example.Controllers;

import com.example.Entities.Shop;
import com.example.Services.ShopServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopServiceInterface shopService;

    @PostMapping("/add")
    public ResponseEntity<Void> addShop(@RequestBody Shop shop, UriComponentsBuilder builder){
        boolean flag = shopService.addsShop(shop);
        if(!flag)
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/shop/{id}").buildAndExpand(shop.getShop_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Shop> updateShop(@RequestBody Shop shop) {
        shopService.updateShop(shop);
        return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable("id") Integer shop_id) {
        shopService.deleteShop(shop_id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable("id") Integer shop_id) {
        Shop shop = shopService.getShopById(shop_id);
        return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Shop>> getAllShop() {
        List<Shop> list = shopService.getAllShop();
        return new ResponseEntity<List<Shop>>(list, HttpStatus.OK);
    }

    @RequestMapping("/test")
    public void test(){
        Shop shop = new Shop();
        shop.setShop_name("Test shop");
        shop.setShop_address("Test address");
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080");
        addShop(shop, urlBuilder);
    }
}
