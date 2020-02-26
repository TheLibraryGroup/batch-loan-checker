package org.thibaut.thelibrary.loanchecker.dto;

import lombok.*;
import org.joda.time.DateTime;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookDTO {

	private Long id;

	private String title;


	private String language;

	private Long isbn;

	private DateTime publicationDate;

	private Integer numberOfPages;

}
