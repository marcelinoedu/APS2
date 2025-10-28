// src/main/java/com/aps2ArqObj/APS2/Config/OpenApiConfig.java
package com.aps2ArqObj.APS2.Config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("APS4 - API Bancária")
                        .version("1.0.0")
                        .description("API demo para gerenciamento de clientes, contas e cartões")
                        .contact(new Contact().name("Seu Nome").email("suporte@exemplo.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação adicional")
                        .url("https://seu-docs.exemplo.com"));
    }
}
