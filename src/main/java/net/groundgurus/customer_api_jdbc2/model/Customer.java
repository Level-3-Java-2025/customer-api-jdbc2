package net.groundgurus.customer_api_jdbc2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
	@Id
	private long id;
	private String firstName;
	private String lastName;
}
