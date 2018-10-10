package it.tonicminds.authservice.client;

import it.tonicminds.authservice.model.DTO.incoming.IdDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Alfonso Lentini
 */
@FeignClient(name = "account-service")
public interface AccountFeignClientCredentials {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/id")
    IdDTO getUserId(@RequestParam(value = "username") String username);

}
