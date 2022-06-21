package ca.sheridancollege.karakow.beans;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Plant implements Serializable{

	
	private String plantName;
	private String plantType;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfProduction;
	private String nameOfSupplier;
	private Long quantity;
	private double amountPaid;

}
