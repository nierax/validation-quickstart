/**
 * 
 */
package org.acme.validation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 */
public class Book {

	// @NotBlank(message = "Title may not be blank")
	public BookTitle title;

	@NotBlank(message = "Author may not be blank")
	public String author;

	@Min(message = "Author has been very lazy", value = 1)
	public double pages;

}
