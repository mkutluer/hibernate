import model.Address;
import model.Basket;
import model.BasketStatus;
import model.Category;
import model.Customer;
import model.Item;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUp() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @AfterClass
    public static void tearDown() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }

    @Test
    public void test01_CreateCategoryAndProduct() {
        Session session = null;
        try {
            session = sessionFactory.openSession();

            LocalDateTime date = LocalDateTime.now();

            List<Category> categories = new ArrayList<>();
            Category category = new Category("Elektronik", "ELKTNRK");
            Category category2 = new Category("Bilgisayar", "BLGSYR");
            Product product = new Product("16 GB DDR4 Ram", 12, new BigDecimal(570), date);
            Product product2 = new Product("Logitech M325 Fare", 27, new BigDecimal(140), date.minusDays(12));
            product.setCategories(Arrays.asList(category, category2));
            product2.setCategories(Arrays.asList(category, category2));
            category.setProducts(Arrays.asList(product, product2));
            category2.setProducts(Arrays.asList(product, product2));

            Transaction transaction = session.beginTransaction();
            session.save(category);
            session.save(category2);
            session.save(product);
            session.save(product2);
            transaction.commit();
            System.out.println("Product: " + product);
            System.out.println("Product2: " + product2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Test
    public void test02_CreateCustomer() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            LocalDateTime date = LocalDateTime.now();

            Customer customer = new Customer("Murat Kutluer", "muratkutluer@yahoo.com");
            Address address = new Address("Eski Londro Asfaltı", "İstanbul", "Turkey", customer);
            customer.setAddress(address);

            Transaction transaction = session.beginTransaction();
            session.save(customer);
            session.save(address);
            transaction.commit();
            System.out.println("Address: " + address);
            System.out.println("Customer: " + customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    @Test
    public void test03_CreateBasket() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            LocalDateTime date = LocalDateTime.now();

            Product product = session.get(Product.class, 1L);
            Product product2 = session.get(Product.class, 2L);
            Customer customer = session.get(Customer.class, 1L);

            Basket basket = new Basket(customer, date, date, BasketStatus.PENDING, new BigDecimal(1280));
            List<Item> items = new ArrayList<>();
            items.add(new Item(basket, product, 4, new BigDecimal(570)));
            items.add(new Item(basket, product2, 3, new BigDecimal(140)));
            basket.setItems(items);

            Transaction transaction = session.beginTransaction();
            session.save(basket);
            session.save(items.get(0));
            session.save(items.get(1));
            transaction.commit();
            System.out.println("Basket: " + basket);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
