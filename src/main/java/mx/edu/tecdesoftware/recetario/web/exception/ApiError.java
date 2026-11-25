package mx.edu.tecdesoftware.recetario.web.exception;

import java.util.Map;

// Cuerpo JSON de un error: código, mensaje general y el error de cada campo
// (un record genera automáticamente el constructor, los getters, equals y toString)
public record ApiError(int status, String message, Map<String, String> errors) {
}
