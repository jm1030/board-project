package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    // Auditing할 때마다 사람 이름은 uno로 넣게된다.
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("uno"); // TODO: 임의의 값 uno. 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정하자
    }
}
