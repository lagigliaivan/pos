package ar.com.terminal.service;

import ar.com.terminal.model.Catalog;
import ar.com.terminal.Controller;
import ar.com.terminal.DBMock;
import ar.com.terminal.model.NullProduct;
import ar.com.terminal.db.Database;
import ar.com.terminal.dto.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ControllerTest {

    private Database database;
    private Controller controller;

    @Before
    public void setup(){
        database = new DBMock();
        controller = new Controller(new Catalog(database));
    }


    @Test
    public void return_a_product_if_it_exits(){

        String id = "7798133540489";
        Product product = new Product();
        product.setId(id);
        product.setPrice(10F);
        product.setDescription("AVE Malbec 750M ml");


        controller.addProduct(product);

        product = controller.getProduct(id);

        assertThat(product, is(notNullValue()));
        assertThat(product.getId(), is(id));

    }

    @Test
    public void return_an_empty_product_if_it_does_not_exits(){

        String id = "7798133540481";
        Product product = controller.getProduct(id);

        assertThat(product.getId(), is(NullProduct.name));
        assertThat(product.getPrice(), is(NullProduct.price));
        assertThat(product.getDescription(), is(NullProduct.desc));
    }

    @Test
    public void return_a_list_of_products_when_id_is_not_passed(){

        Integer page = 1;
        List<Product> products = controller.getAll(page);
        assertThat(products, is(notNullValue()));
    }
}