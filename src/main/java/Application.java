import model.Address;
import model.Basket;
import model.Category;
import model.Customer;
import model.Item;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            LocalDateTime date = LocalDateTime.now();

            Customer customer = new Customer("Murat Kutluer", "muratkutluer@yahoo.com");
            Address address = new Address("Eski Londro Asfaltı", "İstanbul", "Turkey", customer);
            customer.setAddress(address);

            List<Category> categories = new ArrayList<>();
            Category category = new Category("Elektronik", "ELKTNRK");
            Category category2 = new Category("Bilgisayar", "BLGSYR");
            Product product = new Product("16 GB DDR4 Ram", 12, new BigDecimal(570), date);
            Product product2 = new Product("Logitech M325 Fare", 27, new BigDecimal(140), date.minusDays(12));
            product.setCategories(Arrays.asList(category, category2));
            product2.setCategories(Arrays.asList(category, category2));
            category.setProducts(Arrays.asList(product, product2));
            category2.setProducts(Arrays.asList(product, product2));

            Basket basket = new Basket(customer, date, date, "pending", new BigDecimal(1280));
            List<Item> items = new ArrayList<>();
            items.add(new Item(basket, product, 4, new BigDecimal(570)));
            items.add(new Item(basket, product2, 3, new BigDecimal(140)));
            basket.setItems(items);

            Transaction transaction = session.beginTransaction();
            session.save(customer);
            session.save(address);
            session.save(product);
            session.save(product2);
            session.save(category);
            session.save(category2);
            session.save(basket);
            session.save(items.get(0));
            session.save(items.get(1));
            transaction.commit();
            System.out.println("Address: " + address);
            System.out.println("Customer: " + customer);
            System.out.println("Product: " + product);
            System.out.println("Product2: " + product2);
            System.out.println("Basket: " + customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

}
