package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class Util {

    public static final String DataBaseDriver = "com.mysql.cj.jdbc.Driver";
    public static final String DataBaseUrl = "jdbc:mysql://localhost:3306";
    public static final String DataBaseUsername = "root";
    public static final String DataBasePassword = "whiteshark";
    public static SessionFactory sessionFactory = null;

    public static SessionFactory getConnection() {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class",DataBaseDriver)
                    .setProperty("hibernate.connection.url",DataBaseUrl)
                    .setProperty("hibernate.connection.username",DataBaseUsername)
                    .setProperty("hibernate.connection.password",DataBasePassword)
                    .setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
                    .addAnnotatedClass(User.class);

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Connection succeed");
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return sessionFactory;
    }
}