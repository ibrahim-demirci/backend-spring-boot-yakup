package com.skyland.timesheetBackend.api;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyland.timesheetBackend.dto.UserDto;
import com.skyland.timesheetBackend.manager.ResponseManager;
import com.skyland.timesheetBackend.manager.responseModel.BaseResponse;
import com.skyland.timesheetBackend.manager.responseModel.Token;
import com.skyland.timesheetBackend.model.Role;
import com.skyland.timesheetBackend.model.User;
import com.skyland.timesheetBackend.service.user.BaseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final BaseUserService userService;

//    @PostMapping("/user/save")
//    public ResponseEntity<User> saveUser(@RequestBody User user) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
//        return ResponseEntity.created(uri).body(userService.saveUser(user));
//    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String email = decodedJWT.getSubject();
                User user = userService.getUserByEmail(email);
                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Token token = new Token(accessToken, refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), token);

            } catch (Exception e) {
                response.setStatus(FORBIDDEN.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                BaseResponse baseResponse = ResponseManager.getInstance().get_error_response_with_custom_message(e.getMessage());

                try {
                    new ObjectMapper().writeValue(response.getOutputStream(), baseResponse);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @GetMapping("/user/{code}")
    public ResponseEntity<?> getUserByCode(@PathVariable("code") String code) {
        BaseResponse response = null;
        try {
            UserDto user = userService.getUserDtoByCode(code);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            response = ResponseManager.getInstance().get_error_response_with_custom_message(e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/user/delete/{code}")
    public ResponseEntity<?> deleteUserByCode(@PathVariable("code") String code) {
        BaseResponse response = null;
        try {
            userService.deleteUserByCode(code);
            response = ResponseManager.getInstance().get_base_response(ResponseManager.STATUS.deleted);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = ResponseManager.getInstance().get_error_response_with_custom_message(e.getMessage());
            return ResponseEntity.ok(response);
        }
    }


}
