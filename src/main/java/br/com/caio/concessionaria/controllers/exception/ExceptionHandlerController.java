package br.com.caio.concessionaria.controllers.exception;

import br.com.caio.concessionaria.service.exception.ObjectExistException;
import br.com.caio.concessionaria.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErroPadrao> clienteNaoEncontrado(ObjectNotFoundException exception,
                                                           HttpServletRequest request) {

        ErroPadrao erroPadrao = new ErroPadrao(HttpStatus.NOT_FOUND.value(), LocalTime.now(),
                System.currentTimeMillis(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroPadrao);

    }

    @ExceptionHandler(ObjectExistException.class)
    public ResponseEntity<ErroPadrao> clienteExistenteParaNovoCadastroException(ObjectExistException exception,
                                                                                HttpServletRequest request){

        ErroPadrao erroPadrao = new ErroPadrao(HttpStatus.BAD_REQUEST.value(), LocalTime.now(),
                System.currentTimeMillis(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> erroValidacaoException(MethodArgumentNotValidException exception,
                                                                      HttpServletRequest request) {

        ErroValidacao erroValidacao = new ErroValidacao(HttpStatus.BAD_REQUEST.value(),
                LocalTime.now(), System.currentTimeMillis(), exception.getMessage());

        exception.getBindingResult().getFieldErrors().forEach(fieldError ->
                erroValidacao.adicionarErro(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroValidacao);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErroPadrao> nullPointerException(NullPointerException exception,
                                                           HttpServletRequest request) {

        ErroPadrao erroPadrao = new ErroPadrao(HttpStatus.BAD_REQUEST.value(), LocalTime.now(),
                System.currentTimeMillis(), (exception.getMessage() + ". Objeto:" +
                exception.getClass().getName()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }
}
