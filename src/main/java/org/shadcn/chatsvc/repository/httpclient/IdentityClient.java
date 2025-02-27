package org.shadcn.chatsvc.repository.httpclient;

import org.shadcn.chatsvc.config.AuthenticationRequestInterceptor;
import org.shadcn.chatsvc.dto.ApiResponse;
import org.shadcn.chatsvc.dto.response.UserProfileResponse;
import org.shadcn.chatsvc.exception.RetreiveMessageErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "identity-service",
        url = "${app.services.identity}",
        configuration = {AuthenticationRequestInterceptor.class, RetreiveMessageErrorDecoder.class})
public interface IdentityClient {
    @GetMapping(value = "/api/v1/users/me", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<UserProfileResponse> getCurrentUserProfile();
}
