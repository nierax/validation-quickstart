/**
 * 
 */
package org.acme.validation;

import de.lexasoft.common.model.SimpleType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 */
@NotBlank(message = "Book title must not be empty, anyway")
public class BookTitle extends SimpleType<String> {

	public BookTitle(@Valid @NotBlank(message = "Book title must not be empty, anyway") String value) {
		super(value);
	}

	@Override
//	@Size(min = 8, max = 32, message = "Book title must have min. 8, max. 32 characters")
	@NotBlank(message = "Book title must not be empty, anyway")
	@Valid
	public String value() {
		return super.value();
	}

//	public static BookTitle of(String title) {
//		return new BookTitle(title);
//	}

}
