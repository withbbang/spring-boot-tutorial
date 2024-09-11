package com.tutorial.spring_boot_tutorial.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.spring_boot_tutorial.common.CodeMessage;
import com.tutorial.spring_boot_tutorial.common.Result;
import com.tutorial.spring_boot_tutorial.common.SingleResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j(topic = "FORBIDDEN_EXCEPTION_HANDLER")
@AllArgsConstructor
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info(
                "\n############################################################################## START ##############################################################################");
        log.error("No Authorities", accessDeniedException);
        log.info(
                "\n############################################################################### END ###############################################################################");

        SingleResponse errorResponse = new SingleResponse();
        errorResponse.setResult(new Result(CodeMessage.ER0001));

        String responseBody = objectMapper.writeValueAsString(errorResponse);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseBody);
    }
}
