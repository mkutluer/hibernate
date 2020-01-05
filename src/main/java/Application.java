import model.Customer;
import model.Employee;
import model.Transaction;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Date;

public class Application {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Employee employee = new Employee();
            employee.setName("Murat");
            employee.setRole("USG");
            employee.setInsertTime(new Date());

            Customer customer = new Customer();
            customer.setName("murat");
            customer.setEmail("muratkutluer@yahoo.com");

            Transaction transaction = new Transaction();
            transaction.setTotal(250);
            transaction.setDate(new Date());
            customer.setTransaction(transaction);

            session.beginTransaction();
            session.save(employee);
            session.save(transaction);
            session.save(customer);
            session.getTransaction().commit();
            System.out.println("Employee Id: " + employee.getId());
            System.out.println("Transaction Id: " + transaction.getId());
            System.out.println("Customer Id: " + customer.getId());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

}
