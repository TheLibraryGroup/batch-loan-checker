package org.thibaut.thelibrary.loanchecker.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.joda.time.DateTime;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIdentityInfo( generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = LoanDTO.class )
public class LoanDTO implements Serializable {

	private Long id;

	private DateTime startDate;
	private Integer durationInDay;
	private Boolean extended;

	private boolean returned = false;

	private Long bookId;

	private Long userId;

	private String email;

}
