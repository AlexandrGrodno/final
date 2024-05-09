package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.Entity.User;
import org.example.configuraition.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonProcessingException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Comunication comunication = context.getBean("comunication", Comunication.class);
        System.out.println(comunication.getAllUser());
        comunication.saveUser(new User(3L,"James","Brown",(byte)10));
        System.out.println("N "+comunication.getAllUser());
        comunication.updateUser(new User(3L,"Thomas","Shelby",(byte)10));
        System.out.println("u " +comunication.getAllUser());
        comunication.deleteUser(3L);
    }
}
