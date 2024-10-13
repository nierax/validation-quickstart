package org.acme.validation;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/books")
public class BookResource {

	public static class Result {

		Result(String message) {
			this.success = true;
			this.message = message;
		}

		Result(Set<? extends ConstraintViolation<?>> violations) {
			this.success = false;
			this.message = violations.stream().map(cv -> cv.getMessage()).collect(Collectors.joining(", "));
		}

		private String message;
		private boolean success;

		public String getMessage() {
			return message;
		}

		public boolean isSuccess() {
			return success;
		}

	}

	@Inject
	Validator validator;

	@Path("/manual-validation")
	@POST
	public Result tryMeManualValidation(Book book) {
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		if (violations.isEmpty()) {
			return new Result("Book is valid! It was validated by manual validation.");
		} else {
			return new Result(violations);
		}
	}

	@Path("/end-point-method-validation")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Result tryMeEndPointMethodValidation(@Valid Book book) {
		return new Result("Book is valid! It was validated by end point method validation.");
	}

	@Inject
	BookService bookService;

	@Path("/service-method-validation")
	@POST
	public Result tryMeServiceMethodValidation(Book book) {
		try {
			bookService.validateBook(book);
			return new Result("Book is valid! It was validated by service method validation.");
		} catch (ConstraintViolationException e) {
			return new Result(e.getConstraintViolations());
		}
	}
}