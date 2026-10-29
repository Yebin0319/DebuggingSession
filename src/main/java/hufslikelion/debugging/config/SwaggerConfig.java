package hufslikelion.debugging.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("수강신청 API")
                .description("멋사 14기 BE 디버깅 세션용 수강신청 API 문서입니다.")
                .version("v1"));
    }
}
