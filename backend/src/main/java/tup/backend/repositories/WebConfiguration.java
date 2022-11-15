package tup.backend.repositories;

// ----- Imports ----- //
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}

/*Necesitamos esta clase por las restricciones CORS.
El backend percibe los requests del frontend como proviniendo de otro dominio. */