import model.Address;
import model.Basket;
import model.Customer;
import model.Item;
import model.Product;
import org.hibernate.Session;
import util.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Customer customer = new Customer("Murat Kutluer", "muratkutluer@yahoo.com");
            Address address = new Address("Eski Londro Asfaltı", "İstanbul", "Turkey", customer);
            customer.setAddress(address);

            LocalDateTime date = LocalDateTime.now();
            Product product = new Product("16 GB DDR4 Ram", 12, new BigDecimal(570), date);
            Product product2 = new Product("Logitech M325 Fare", 27, new BigDecimal(140), date.minusDays(12));


            Basket basket = new Basket(customer, date, date, "pending", new BigDecimal(1280));
            List<Item> items = new ArrayList<>();
            items.add(new Item(basket, product, 4, new BigDecimal(570)));
            items.add(new Item(basket, product2, 3, new BigDecimal(140)));
            basket.setItems(items);

            session.beginTransaction();
            session.save(customer);
            session.save(address);
            session.save(product);
            session.save(product2);
            session.save(basket);
            session.save(items.get(0));
            session.save(items.get(1));
            session.getTransaction().commit();
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
