import model.Employee;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Date;

public class HibernateApp {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("Murat");
        employee.setRole("USG");
        employee.setInsertTime(new Date());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        System.out.println("Employee Id: " + employee.getId());;
        HibernateUtil.getSessionFactory().close();

    }

}
