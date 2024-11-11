package com.unisporti.api_unisporti.config;

import com.unisporti.api_unisporti.model.UserContext;
import io.jsonwebtoken.Claims;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class AuthFilter implements Filter {

    private final JwtUtil jwtUtil = new JwtUtil();

    private static final Map<String, Integer> roleHierarchy = new HashMap<>() {
        {
            put("A", 4);
            put("M", 3);
            put("I", 2);
            put("U", 1);
        }
    };

    public AuthFilter() throws NoSuchAlgorithmException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        final String authorizationHeader = httpRequest.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token de autenticação não fornecido");
            return;
        }

        System.out.println("Authorization header: " + authorizationHeader);

        final String token = authorizationHeader.substring(7);

        System.out.println("Token: " + token);

        try {
            if (jwtUtil.isTokenExpired(token)) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expirado");
                return;
            }

            final Claims claims = jwtUtil.getClaimsFromToken(token);

            final String cpf = claims.getSubject();
            final Long idUser = claims.get("idUser", Long.class);
            final String password = claims.get("password", String.class);
            final String role = claims.get("role", String.class);

            if (isAccessAllowed(httpRequest.getRequestURI(), role)) {
                if (validateUser(idUser, cpf, password)) {
                    UserContext userContext = new UserContext();
                    userContext.setCpf(cpf);
                    userContext.setUserId(idUser);
                    userContext.setPassword(password);

                    UserContext.setCurrentUser(userContext);

                    chain.doFilter(request, response);
                } else {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login inválido");
                }
            } else {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Endereço não permitido para o usuário");
            }
        } catch (Exception e) {
            e.printStackTrace();
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
        } finally {
            UserContext.clear();
        }
    }

    private boolean isAccessAllowed(String requestURI, String role) {
        if (requestURI.startsWith("/api/secure/admin") && roleHierarchy.get(role) >= roleHierarchy.get("A")) {
            return true;
        } else if (requestURI.startsWith("/api/secure/manager") && roleHierarchy.get(role) >= roleHierarchy.get("M")) {
            return true;
        } else if (requestURI.startsWith("/api/secure/instructor") && roleHierarchy.get(role) >= roleHierarchy.get("I")) {
            return true;
        } else if (requestURI.startsWith("/api/secure/user") && roleHierarchy.get(role) >= roleHierarchy.get("U")) {
            return true;
        }
        return false;
    }

    private boolean validateUser(Long idUser, String cpf, String password) {

        final String query = "SELECT COUNT(*) FROM users WHERE id_user = ? AND cpf = ? AND password = ? AND active = true";

        try (final Connection connection = JDBC.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setLong(1, idUser);
            preparedStatement.setString(2, cpf);
            preparedStatement.setString(3, password);

            final ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;

        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }
}
