package it.tonicminds.authservice;


import feign.RequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@EnableConfigurationProperties
public class FeignClientCredentialsConfig {


    private ClientCredentialsResourceDetails resource;

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        this.resource = new ClientCredentialsResourceDetails();
        return this.resource;
    }


    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        if(this.resource == null) {
            return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
        } else{
            return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), this.resource);
        }
    }


    @Bean
    public OAuth2RestTemplate clientCredentialsRestTemplate() {
        if(this.resource == null) {
            return new OAuth2RestTemplate(clientCredentialsResourceDetails());
        } else{
            return new OAuth2RestTemplate(this.resource);
        }
    }
}
