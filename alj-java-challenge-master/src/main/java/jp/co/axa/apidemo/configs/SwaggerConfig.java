package jp.co.axa.apidemo.configs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.co.axa.apidemo.constants.JWTConstant;
import jp.co.axa.apidemo.constants.SwaggerContant;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage(SwaggerContant.BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiKey apiKey() { 
		return new ApiKey(JWTConstant.API_KEY_NAME, 
				JWTConstant.AUTHORIZATION_HEADER, SwaggerContant.API_KEY_PASS_AS); 
	}

	private ApiInfo apiInfo(){
		return new ApiInfo(
				SwaggerContant.TITLE,
				SwaggerContant.DESCRIPTION,
				SwaggerContant.VERSION,
				SwaggerContant.TERMS_OF_SERVICE_URL,
				new Contact(SwaggerContant.AUTHER, "", SwaggerContant.AUTHER_EMAIL_ID),
				SwaggerContant.LICENSE,
				SwaggerContant.LICENSE_URL,
				Collections.emptyList()
				);
	}

	private SecurityContext securityContext(){
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}
	private List<SecurityReference> defaultAuth(){
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference(JWTConstant.API_KEY_NAME, authorizationScopes));
	}
}
