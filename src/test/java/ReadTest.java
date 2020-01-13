import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import util.HibernateUtil;

public class ReadTest {
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
}
