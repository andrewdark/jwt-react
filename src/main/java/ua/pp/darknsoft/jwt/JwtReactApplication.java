package ua.pp.darknsoft.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class JwtReactApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(JwtReactApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/", "classpath:/build/")
// To solve the 404 problem of browser refresh
// Customize the ClassPathResource implementation class to force the use of the **index.html** resource when the address requested by the front-end does not match the corresponding path
// you can check the implementation from {@link ResourceHttpRequestHandler#resolveResourceLocations}
                .addResourceLocations(new ClassPathResource("/build/index.html") {
                    @Override
                    public Resource createRelative(String relativePath) {
                        return this;
                    }
                });
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

}
