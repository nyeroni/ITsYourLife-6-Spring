package org.scoula.main;

import org.scoula.beans.Parrot;
import org.scoula.beans.Person;
import org.scoula.beans.Person2;
import org.scoula.config.ProjectConfig;
import org.scoula.config.ProjectConfig2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig2.class);
        Person2 person = context.getBean(Person2.class);

        System.out.println("Person's name: " + person.getName());
        System.out.println("Person's parrot: " + person.getParrot());
    }
}