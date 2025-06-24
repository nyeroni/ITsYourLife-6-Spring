package org.scoula.security.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import org.springframework.http.HttpStatus;

public class JsonResponse {
    public static <T> void send(HttpServletResponse response, T result) throws IOException, IOException {
        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        Writer out = response.getWriter();
        out.write(om.writeValueAsString(result));
        out.flush();
    }

    public static void sendError(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json;charset=UTF-8");
        Writer out = response.getWriter();
        out.write(message);
        out.flush();
    }
}