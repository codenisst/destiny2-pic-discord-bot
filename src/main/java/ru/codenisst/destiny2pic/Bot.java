package ru.codenisst.destiny2pic;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.config.SpringConfig;

public class Bot {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        Configurator configurator = context.getBean("configurator", Configurator.class);
        configurator.start();
    }
}
