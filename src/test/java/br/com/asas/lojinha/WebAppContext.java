package br.com.asas.lojinha;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "br.com.asas.lojinha.controller",
        "br.com.asas.lojinha.service"
})
public class WebAppContext extends WebMvcConfigurerAdapter {

}
