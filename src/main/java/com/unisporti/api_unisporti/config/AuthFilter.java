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
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AuthFilter implements Filter {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        final String authorizationHeader = httpRequest.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token de autenticação não fornecido");
            return;
        }

        final String token = authorizationHeader.substring(7);

        try {
            if (jwtUtil.isTokenExpired(token)) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expirado");
                return;
            }

            final Claims claims = jwtUtil.getClaimsFromToken(token);

            final String cpf = claims.getSubject();
            final Integer idUser = claims.get("idUser", Integer.class);
            final char role = claims.get("role", String.class).charAt(0);

            if (validateUser(idUser, cpf, role)) {
                if (isAccessAllowed(httpRequest.getRequestURI(), role)) {
                    final UserContext userContext = new UserContext();
                    userContext.setCpf(cpf);
                    userContext.setUserId(idUser);
                    userContext.setRole(role);

                    UserContext.setCurrentUser(userContext);

                    chain.doFilter(request, response);
                } else {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Endereço não permitido para o usuário");
                }
            } else {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login inválido");
            }
        } catch (Exception e) {
            e.printStackTrace();

            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
        } finally {
            UserContext.clear();
        }
    }

    private boolean isAccessAllowed(String requestURI, char role) {
        if (requestURI.startsWith("/api/secure/admin") && Role.getHierarchyByRole(role) >= Role.ROLE_ADMIN.getHierarchy()) {
            return true;
        } else if (requestURI.startsWith("/api/secure/manager") && Role.getHierarchyByRole(role) >= Role.ROLE_MANAGER.getHierarchy()) {
            return true;
        } else if (requestURI.startsWith("/api/secure/instructor") && Role.getHierarchyByRole(role) >= Role.ROLE_INSTRUCTOR.getHierarchy()) {
            return true;
        } else
            return requestURI.startsWith("/api/secure/user") && Role.getHierarchyByRole(role) >= Role.ROLE_USER.getHierarchy();
    }

    private boolean validateUser(Integer idUser, String cpf, char role) {
        final String query = "SELECT 1 FROM users WHERE id_user = ? AND cpf = ? AND role = ? AND active = true";

        try (final Connection connection = JDBC.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, cpf);
            preparedStatement.setString(3, String.valueOf(role));

            return preparedStatement.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }
}
