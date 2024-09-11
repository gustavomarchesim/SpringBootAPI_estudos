package dev.gdam.voll.api.infra;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Void> tratarErro404() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<DadosErrosValidacao>> tratarError400(MethodArgumentNotValidException ex) {
    var error = ex.getFieldErrors();
    return ResponseEntity.badRequest().body(error.stream().map(DadosErrosValidacao::new).toList());
  }

  private record DadosErrosValidacao(String campo, String mensagem) {
    public DadosErrosValidacao(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }
}
