package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactoryXmlMapping;

    private static SessionFactory sessionFactoryAnnotation;

    private static SessionFactory sessionFactoryJavaConfig;

    private static SessionFactory buildSessionFactoryXmlMapping() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate-xmlMapping.cfg.xml");
            System.out.println("Hibernate configuration file loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.err.println("Session factory create failed. Error: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactoryXmlMapping() {
        if (sessionFactoryXmlMapping == null) {
            sessionFactoryXmlMapping = buildSessionFactoryXmlMapping();
        }
        return sessionFactoryXmlMapping;
    }

    private static SessionFactory buildSessionFactoryAnnotation() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate-annotation.cfg.xml");
            System.out.println("Hibernate annotation configuration loaded");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.err.println("Initial Session factroy create failed. Error: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactoryAnnotation() {
        if (sessionFactoryAnnotation == null) {
            sessionFactoryAnnotation = buildSessionFactoryXmlMapping();
        }
        return sessionFactoryAnnotation;
    }


}
