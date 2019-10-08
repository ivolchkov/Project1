package project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.configuration.ApplicationConfiguration;
import project.view.Menu;


public class ConsoleApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Menu menu = context.getBean(Menu.class);
        menu.run();
    }
}
