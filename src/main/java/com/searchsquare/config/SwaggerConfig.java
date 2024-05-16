package com.searchsquare.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "SearchSquare API 명세서",
                description = "<h3>SearchSquare API 명세서</h3>",
                version = "v1",
                contact = @Contact(
                        name = "심종한, 윤정섭",
                        email = "simhani1@gmail.com, yjs9714@gmail.com")
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder().group("all").pathsToMatch("/**").build();
    }

    @Bean
    public GroupedOpenApi memberApi() {
        return GroupedOpenApi.builder().group("member").pathsToMatch("/member/**").build();
    }
}
