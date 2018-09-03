package com.example;

import com.example.Entities.Product;
import com.example.Entities.ProductList;
import com.example.Services.ProductListServiceInterface;
import com.example.Services.ProductServiceInterface;
import com.example.Services.ShopServiceInterface;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Component
public class CronSchedule {

    private List<Product> productList;
    private List<ProductList> productListList;

    @Autowired
    private ProductServiceInterface productService;

    @Autowired
    private ShopServiceInterface shopService;

    @Autowired
    private ProductListServiceInterface productListService;

    //cron = second minute hour dayOfMonth month day(s)OfWeek
    //0 0 */12 * * *", zone = "Europe/Moscow
    @Scheduled(cron = "0 59 08 * * *", zone = "Europe/Moscow")
    public void checkUpdate() throws IOException, ParseException {
        System.out.println("Do some thing");

        if(true){
            updateDixyProduct();
            productService.updateProducts(productList);
            productListService.clearProductList();
            updateDixyProductList();
            productListService.updateProductLists(productListList);
        }
    }

    private void updateDixyProduct() throws ParseException, IOException {
        String name = "";
        String image;
        productList =  new ArrayList<>();

        Document doc  = Jsoup.connect("https://dixy.ru/akcii/skidki-nedeli/?PAGEN_1=1").post();
        Elements numbers = doc.select(".elem-pagination__btn.js-page-number");

        System.out.println(numbers.size());

        int tmpInt;
        if(numbers.size() > 0)
            tmpInt = numbers.size() - 1;
        else
            tmpInt = numbers.size();
        System.out.println(numbers.get(tmpInt).text());
        for(int i = 1; i <= numbers.get(tmpInt).text().split("PAGEN_1=")[0].toCharArray()[0] - '0'; i++) {
            doc = Jsoup.connect("https://dixy.ru/akcii/skidki-nedeli/?PAGEN_1=" + i).post();

            String tmpstr[] = doc.html().split("<div class=\"elem-product__image\">");
            String images[] = new String[tmpstr.length];
            for(int j = 1; j < tmpstr.length; j++)
            {
                images[j - 1] = tmpstr[j].split("</div>")[0].split(" alt")[0].split("<img src=\"/")[1].split("\"")[0];
            }

            int elementCount = 0;
            Elements nameElements = doc.select(".elem-product");
            for (Element element : nameElements) {
                name = element.select(".product-name.js-ellipsis").text();

                image = "https://dixy.ru/" + images[elementCount];
                elementCount++;

                Product product = new Product(image, name);

                productList.add(product);
            }
        }
    }

    private void updateDixyProductList() throws ParseException, IOException {
        double discount = 0;
        double price = 0;
        String name = "";
        String date = "";
        Date discount_begin;
        Date discount_end;
        productListList = new ArrayList<>();

        Document doc  = Jsoup.connect("https://dixy.ru/akcii/skidki-nedeli/?PAGEN_1=1").post();
        Elements numbers = doc.select(".elem-pagination__btn.js-page-number");

        int tmpInt;
        if(numbers.size() > 0)
            tmpInt = numbers.size() - 1;
        else
            tmpInt = numbers.size();
        for(int i = 1; i <= numbers.get(tmpInt).text().split("PAGEN_1=")[0].toCharArray()[0] - '0'; i++) {
            doc = Jsoup.connect("https://dixy.ru/akcii/skidki-nedeli/?PAGEN_1=" + i).post();

            Elements nameElements = doc.select(".elem-product");
            for (Element element : nameElements) {
                name = element.select(".product-name.js-ellipsis").text();

                String discountElement = element.select(".discount").text();
                if (discountElement.split("%")[0].equals("1+1"))
                    discount = 116611;
                else if (discountElement.split("%")[0].equals("2+1"))
                    discount = 226622;
                else if (discountElement.split("%")[0].equals("3+1"))
                    discount = 336633;
                else if (discountElement.equals(""))
                    discount = 116611;
                else {
                    try {
                        discount = Double.valueOf(discountElement.split("%")[0]);
                    } catch (NumberFormatException ex) {
                        discount = 66666;
                    }
                }

                String priceLeftElement = element.select(".price-left").text();
                price = Double.valueOf(priceLeftElement);
                String priceRightElement = element.select(".price-discount__float").text();
                price = price + (Double.valueOf(priceRightElement) / 100);

                date = element.select(".elem-badge-cornered").text();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                String testDate = new Date().toString();
                String mmDD = getMMdd(date);
                String dateString = "";
                if (!mmDD.contains("_")) {
                    dateString = testDate.split(" ")[5] + "-" + mmDD + "T00:00:00.945158Z";
                    discount_begin = simpleDateFormat.parse(dateString);
                    discount_end = discount_begin;
                } else {
                    String first = testDate.split(" ")[5] + "-" + mmDD.split("_")[0] + "T00:00:00.945158Z";
                    discount_begin = simpleDateFormat.parse(first);
                    String seconf = testDate.split(" ")[5] + "-" + mmDD.split("_")[1] + "T00:00:00.945158Z";
                    discount_end = simpleDateFormat.parse(seconf);
                }
                productListList.add(new ProductList(productService.findProductByName(name), shopService.getShopById(1),
                        price, discount, discount_begin, discount_end));
            }
        }
    }

    private String getMMdd(String date){
        String res = "";
        String firstDD = date.split("/")[1];
        res += firstDD.toCharArray()[0];
        res += firstDD.toCharArray()[1] + "-";
        String firstMM = date.split("/")[0];
        res += firstMM.toCharArray()[firstMM.toCharArray().length - 2];
        res += firstMM.toCharArray()[firstMM.toCharArray().length - 1];

        try {
            String secondDD = date.split("/")[2];
            res += "_";
            res += secondDD.toCharArray()[0];
            res += secondDD.toCharArray()[1];
            String secondMM = date.split("/")[1].split("—")[1];
            res += "-" + secondMM;
        }
        catch (ArrayIndexOutOfBoundsException ex){
        }

        return res;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<ProductList> getProductListList() {
        return productListList;
    }
}
