package org.example;

import org.example.config.Config;
import org.example.service.RandomIdMessageProviderImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;
import org.example.interfaces.MessageProvider;
// Spring извлекать бины из контейнера в методе main воспользуемся одной из реализаций интерфейса BeanFactory:
public class Main {
    public static void main(String[] args) {

        //Указав в конструкторе AnnotationConfigApplicationContext название класса конфигурации
        // мы тем самым предоставляем фабрике возможность зарегистрировать формирующиеся в конфигурационном классе бины
        BeanFactory context = new AnnotationConfigApplicationContext(Config.class);
        MessageProvider firstProvider = context.getBean("provider", MessageProvider.class);
        System.out.println(firstProvider.provide());
        System.out.println(firstProvider.getProviderId());
        System.out.println("---------------");
        MessageProvider secondProvider = context.getBean("second", MessageProvider.class);
        System.out.println(secondProvider.provide());
        System.out.println(secondProvider.getProviderId());
        System.out.println("---------------");

        GenericApplicationContext thirdFactory = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader otherReader = new AnnotatedBeanDefinitionReader(thirdFactory);
        otherReader.register(Config.class);
        thirdFactory.refresh();
        MessageProvider secondProviderCopy = thirdFactory.getBean("second", MessageProvider.class);
        System.out.println(secondProviderCopy.provide());
        System.out.println(secondProviderCopy.getProviderId());
        System.out.println("---------------");


        GenericApplicationContext firstFactory = new GenericApplicationContext();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(firstFactory);
        scanner.scan("org.example.service");
        firstFactory.refresh();
        MessageProvider thirdProvider = firstFactory.getBean(MessageProvider.class);
        System.out.println(thirdProvider.provide());
        System.out.println(thirdProvider.getProviderId());
        System.out.println("---------------");


        GenericApplicationContext secondFactory = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(secondFactory);
        reader.register(RandomIdMessageProviderImpl.class);
        secondFactory.refresh();
        MessageProvider thirdProviderCopy = secondFactory.getBean(MessageProvider.class);
        System.out.println(thirdProviderCopy.provide());
        System.out.println(thirdProviderCopy.getProviderId());
        System.out.println("---------------");

        /*
        * Вывод консоли красноречиво говорит о том, что у каждого контейнера сформирован свой уникальный набо
        * р объектов. В данном утверждении достаточно легко укрепиться с помощью следующего участка кода:*/
        String template = "%s - %s\n";
        System.out.printf(template, thirdProvider.equals(thirdProviderCopy), thirdProvider.getProviderId().equals(thirdProviderCopy.getProviderId()));
        System.out.printf(template, secondProvider.equals(secondProviderCopy), secondProvider.getProviderId().equals(secondProviderCopy.getProviderId()));
        MessageProvider oneMoreSecondProviderCopy = context.getBean("second", MessageProvider.class);
        System.out.printf(template, secondProvider == oneMoreSecondProviderCopy, secondProvider.getProviderId() == oneMoreSecondProviderCopy.getProviderId());
    }
}