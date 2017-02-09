package nut.cc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration    
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * здесь мы указывает, что представления будут браться из ресурсов приложения.
     * Поиск представлений с расширением .jspx будет вестись относительно директории /WEB-INF/views
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        registry.jsp("WEB-INF/views/site", ".jspx");

    }

}
