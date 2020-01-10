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

            Product product = new Product("16 GB DDR4 Ram", 12, new BigDecimal(570));
            Product product2 = new Product("Logitech M325 Fare", 27, new BigDecimal(140));

//            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
//            Basket basket = new Basket(customer, date, date, "pending", new BigDecimal(1280));
//            List<Item> items = new ArrayList<>();
//            items.add(new Item(basket, product, 2, new BigDecimal(570)));
//            items.add(new Item(basket, product, 1, new BigDecimal(140)));
//            basket.setItems(items);

            session.beginTransaction();
            session.save(customer);
            session.save(address);
//            session.save(product);
//            session.save(product2);
//            session.save(basket);
//            session.save(items);
            session.getTransaction().commit();
            System.out.println("Address Id: " + address.getId());
            System.out.println("Customer Id: " + customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

}
