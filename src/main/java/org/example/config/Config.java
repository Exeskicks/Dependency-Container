package org.example.config;

import org.example.service.RandomIdMessageProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.interfaces.MessageProvider;
import org.example.service.HelloWorldMessageProviderImpl;
import java.util.UUID;

/*Аннотация @Configuration над классом указывает на то,
 что в данном классе будут формироваться объекты,
  которые в последующем должны оказаться в контейнере.
*/
// наличие классов помеченных аннотацией @Component
@Configuration
public class Config {
    //Для создания бина необходимо реализовать
    // соответствующий метод и пометить его аннотацией @Bean
    @Bean
    public MessageProvider provider(){
        String id = UUID.randomUUID().toString();
        System.out.println(id);
        System.out.println("-------bean created----------");
        return new RandomIdMessageProviderImpl();
    }

    @Bean(name = "second")
    public MessageProvider secondProvider(){
        String id = UUID.randomUUID().toString();
        System.out.println(id);
        System.out.println("-------bean created----------");
        return new RandomIdMessageProviderImpl();
    }

}
