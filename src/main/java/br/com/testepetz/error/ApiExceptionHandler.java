package br.com.testepetz.error;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.testepetz.error.ErrorResponse.ApiError;
import br.com.testepetz.exception.EnderecoException;
import br.com.testepetz.exception.MensagemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

	private static final String ERRO_INESPERADO = "Erro inesperado";
	private static final String SEM_MENSAGEM_DEFINIDA = "Sem mensagem definida para este erro";

	private final MessageSource apiErrorMessageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleNotValidException(MethodArgumentNotValidException exception,
			Locale locale) {
		Stream<ObjectError> errors = exception.getBindingResult().getAllErrors().stream();

		List<ApiError> apiErrors = errors.map(ObjectError::getDefaultMessage).map(code -> toApiError(code, locale))
				.collect(toList());

		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrors);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, WebRequest request, Locale locale) {
		List<ApiError> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(toApiError(violation.getMessage(), locale));
		}

		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST,errors);
		
		return new ResponseEntity<ErrorResponse>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<ErrorResponse> handleInvalidFormatException(InvalidFormatException exception, Locale locale) {
		final String errorCode = "generic-1";
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		final ErrorResponse errorResponse = ErrorResponse.of(status,
				toApiError(errorCode, locale, exception.getValue()));

		return ResponseEntity.badRequest().body(errorResponse);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception exception, Locale locale) {
		log.error(ERRO_INESPERADO, exception);
		final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		final ErrorResponse errorResponse = ErrorResponse.of(status, toApiError("error-1", locale));
		return ResponseEntity.status(status).body(errorResponse);
	}

	@ExceptionHandler(MensagemException.class)
	public ResponseEntity<ErrorResponse> handleMensagemException(MensagemException exception, Locale locale) {
		final String errorCode = "";
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		final ErrorResponse errorResponse = ErrorResponse.of(status,
				toApiError(errorCode, locale, exception.getMensagem()));

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(EnderecoException.class)
	public ResponseEntity<ErrorResponse> handleMensagemException(EnderecoException exception, Locale locale) {
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		final ErrorResponse errorResponse = ErrorResponse.of(status,
				toApiError(exception.getMensagem(), locale));

		return ResponseEntity.badRequest().body(errorResponse);
	}

	private ApiError toApiError(String code, Locale locale, Object... args) {
		String message;

		try {
			message = this.apiErrorMessageSource.getMessage(code, args, locale);
		} catch (NoSuchMessageException e) {
			log.error("Não foi possível encontrar msg para {} código abaixo {} local", code, locale);
			message = SEM_MENSAGEM_DEFINIDA;
		}
		return new ApiError(code, message);
	}
}
