package com.skyland.timesheetBackend.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyland.timesheetBackend.api.responseModel.ErrorInfo;
import com.skyland.timesheetBackend.filter.responseModel.LoginFailResponse;
import com.skyland.timesheetBackend.filter.responseModel.LoginSuccessResponse;
import com.skyland.timesheetBackend.filter.responseModel.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.stream.Collectors;


import static com.skyland.timesheetBackend.constants.K.ErrorMessageInfo.*;
import static com.skyland.timesheetBackend.constants.K.ErrorMessageType.*;
import static com.skyland.timesheetBackend.constants.K.ResponseStatusUtilities.STATUS_FAILED;
import static com.skyland.timesheetBackend.constants.K.ResponseStatusUtilities.STATUS_LOGIN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is: {}", username);
        log.info("Password is: {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        // LoginResponse when success login
        LoginSuccessResponse loginResponse =
                new LoginSuccessResponse(
                        true,
                        STATUS_LOGIN,
                        null,
                        createTokens(user, request),
                        user.getUsername()
                );

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), loginResponse);
    }

    // When username and password authentication failed called this method.
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        LoginFailResponse loginResponse;

        if(failed.getMessage() == USER_NOT_FOUND) {
            ErrorInfo errorInfo = new ErrorInfo(USER_NOT_FOUND, USER_NOT_FOUND_INFO);
            loginResponse = createLoginFailResponse(errorInfo);

        } else if(failed.getMessage() == USER_NOT_VERIFIED){
            ErrorInfo errorInfo = new ErrorInfo(USER_NOT_VERIFIED, USER_NOT_VERIFIED_INFO);
            loginResponse = createLoginFailResponse(errorInfo);
        } else {
            ErrorInfo errorInfo = new ErrorInfo(USERNAME_OR_PASSWORD_WRONG, USERNAME_OR_PASSWORD_WRONG_INFO);
            loginResponse = createLoginFailResponse(errorInfo);
        }
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.setContentType(APPLICATION_JSON_VALUE);

        try {
            new ObjectMapper().writeValue(response.getOutputStream(), loginResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LoginFailResponse createLoginFailResponse(ErrorInfo error) {
        return  new LoginFailResponse(
                false,
                STATUS_FAILED,
                error
        );
    }

    private Token createTokens(User user, HttpServletRequest request ) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);


        return new Token(access_token,refresh_token);
    }
}
