package com.aps2ArqObj.APS2.Security;

import com.aps2ArqObj.APS2.Services.TokenService;
import com.aps2ArqObj.APS2.Services.ClienteService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;
    private final ClienteService clienteService;

    public AuthInterceptor(TokenService tokenService, ClienteService clienteService) {
        this.tokenService = tokenService;
        this.clienteService = clienteService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();

            if (method.isAnnotationPresent(Autenticado.class)) {
                String authHeader = request.getHeader("Authorization");

                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token ausente ou inválido");
                    return false;
                }

                String token = authHeader.substring(7);
                try {
                    String cpf = tokenService.validarToken(token);

                    if (clienteService.buscarPorCpf(cpf).isEmpty()) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("Usuário não encontrado");
                        return false;
                    }


                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token inválido ou expirado");
                    return false;
                }
            }
        }
        return true;
    }
}
