package ca.sheridancollege.makonnen.beans;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
	
	private Long EMPLOYEE_ID;
	@NonNull
	private String FIRST_NAME;
	@NonNull
	private String LAST_NAME;
	@NonNull
	private String EMAIL;
	@NonNull
	private String PHONE_NUMBER;
	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date HIRE_DATE;
	@NonNull
	private String JOB_ID;
	
	private double SALARY;
	
	private double COMMISSION_PCT;
	
	@NonNull
	private Long MANAGER_ID;
	@NonNull
	private Long DEPARTMENT_ID;

}
