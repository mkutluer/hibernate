import model.Address;
import model.Customer;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Date;

public class Application {

    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Customer customer = new Customer();
            customer.setName("Murat Kutluer");
            customer.setEmail("muratkutluer@yahoo.com");

            Address address = new Address();
            address.setStreet("Eski Londro Asfaltı");
            address.setCity("İstanbul");
            address.setCountry("Turkey");

            customer.setAddress(address);
            address.setCustomer(customer);

            session.beginTransaction();
            session.save(customer);
            session.save(address);
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
