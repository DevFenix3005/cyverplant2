package com.cyver.plant.data.web.rest;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Map;

import com.cyver.plant.commons.dto.LogoutDto;
import com.cyver.plant.commons.dto.UserDto;

@RestController
public class UserResource {
    private final ClientRegistration registration;

    public UserResource(ClientRegistrationRepository registrations) {
        this.registration = registrations.findByRegistrationId("okta");
    }

    @GetMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@AuthenticationPrincipal OAuth2User user) {

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        final Map<String, Object> attributes = user.getAttributes();
        final String email = (String) attributes.get("email");
        final String name = (String) attributes.get("name");
        final String picture = (String) attributes.get("picture");
        final String nickname = (String) attributes.get("nickname");

        return ResponseEntity.ok(new UserDto(name, email, picture, nickname));
    }

    @PostMapping(path = "/api/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogoutDto> logout(HttpServletRequest request) {
        // send logout URL to client so they can initiate logout
        var issuerUri = registration.getProviderDetails().getIssuerUri();
        var originUrl = request.getHeader(HttpHeaders.ORIGIN);
        Object[] params = { issuerUri, registration.getClientId(), originUrl };
        // Yes! We @ Auth0 should have an end_session_endpoint in our OIDC metadata.
        // It's not included at the time of this writing, but will be coming soon!
        var logoutUrl = MessageFormat.format("{0}v2/logout?client_id={1}&returnTo={2}", params);
        request.getSession().invalidate();
        return ResponseEntity.ok().body(new LogoutDto(logoutUrl));
    }
}
