package org.muki.pets.web.argresolver;

import org.muki.pets.web.config.ObjectMappingConfig;
import org.muki.pets.config.TestPropertiesConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

@Configuration
@Import({TestPropertiesConfig.class, ObjectMappingConfig.class})
public class CreatePetWebRequestArgumentResolverTestConfig {

    @Bean
    public HandlerMethodArgumentResolver createPetWebRequestArgumentResolver() {
        return new CreatePetWebRequestArgumentResolver();
    }
}
