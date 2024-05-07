package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    }
}
