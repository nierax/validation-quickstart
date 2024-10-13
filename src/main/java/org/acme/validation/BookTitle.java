/**
 * 
 */
package org.acme.validation;

import de.lexasoft.common.model.SimpleType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 
 */
public class BookTitle extends SimpleType<String> {

	@NotBlank(message = "Book title must not be empty, anyway")
	public BookTitle(String value) {
		super(value);
	}

	@Override
	@Size(min = 8, max = 32, message = "Book title must have min. 8, max. 32 characters")
	@Valid
	public String value() {
		return super.value();
	}

	public static BookTitle of(String title) {
		return new BookTitle(title);
	}

}
