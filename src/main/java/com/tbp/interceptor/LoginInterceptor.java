package com.tbp.interceptor;

import com.tbp.repository.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);


    @Autowired
    UserSession userSession;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        LOGGER.info("Interceptando a requisicao {}", url);
        if(url.contains("/secure") && userSession.getLoggedUser() == null) {
            LOGGER.info("Redirecting to {}", url);
            String loginPage = httpServletRequest.getContextPath() + "/login/doLogin";
            httpServletResponse.sendRedirect(loginPage);
            return false;
        } else if(url.contains("carList") || url.contains("carCreate")) {
            // somente quem tem perfil Ordinary pode acessar as funcionalidade
            // para cadastrar ou listar carros
            User loggedUser = userSession.getLoggedUser();
            if(!"Ordinary".equals(loggedUser.getProfile())) {
                LOGGER.info("Perfil {} nao permitido o acesso a {}", loggedUser.getProfile(), url);
                String principalPage = httpServletRequest.getContextPath() + "/secure";
                httpServletResponse.sendRedirect(principalPage);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
