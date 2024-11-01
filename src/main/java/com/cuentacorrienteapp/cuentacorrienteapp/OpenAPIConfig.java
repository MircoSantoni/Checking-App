package com.cuentacorrienteapp.cuentacorrienteapp;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Cuentas Corrientes")
                .version("1.0")
                .description("Sistema de gestión de cuentas corrientes para proveedores ")
                .contact(new Contact()
                    .name("Mirco")
                    .email("santonimircoariel@gamil.com")
                    .url("https://localgost:8080"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.apache.org/licenses/LICENSE-2.0.html")))
            .servers(List.of(
                new Server()
                    .url("http://localhost:8080")
                    .description("Servidor de Desarrollo")
            ));
    }
}