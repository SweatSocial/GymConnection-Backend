package com.GymconnectionAPI.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Obtener el código de estado del error
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Lógica para manejar diferentes códigos de estado de error
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            // Manejar el error 404 (Not Found)
            return "error404"; // Aquí puedes redirigir a una vista personalizada para el error 404
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            // Manejar el error 500 (Internal Server Error)
            return "error500"; // Aquí puedes redirigir a una vista personalizada para el error 500
        }

        // Si el código de estado no se maneja explícitamente, puedes redirigir a una página de error genérica
        return "error";
    }

}
