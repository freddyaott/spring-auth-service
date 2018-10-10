package it.tonicminds.authservice;

import it.tonicminds.authservice.client.AccountFeignClientCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Alfonso Lentini
 */
public class JWTTokenEnhancer implements TokenEnhancer {


    @Autowired
    private AccountFeignClientCredentials accountFeignClientCredentials;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        String userId;
        if(authentication.isClientOnly()){
            userId = "server";
        }else {
            userId = accountFeignClientCredentials.getUserId(authentication.getName()).getId();
        }
        additionalInfo.put("userId", userId);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
