package com.ada.meuPrimeiroProjeto.controller.configuration;

import com.ada.meuPrimeiroProjeto.controller.exception.PasswordValidationError;
import com.ada.meuPrimeiroProjeto.controller.exception.ValidationError;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

  private final MessageSource messageSource;

  @Autowired
  public ControllerAdvice(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public List<ValidationError> handler(MethodArgumentNotValidException exception) {
    List<ValidationError> errors = new ArrayList<>();
    List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();

    fieldErros.forEach(e -> {
      String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
      ValidationError validationError = new ValidationError(e.getField(), message);
      errors.add(validationError);
    });

    return errors;
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(PasswordValidationError.class)
  public String handlerPassword(PasswordValidationError exception){
    return exception.getDescription();
  }
}
