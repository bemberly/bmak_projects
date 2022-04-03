package ca.sheridancollege.makonnen.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.makonnen.beans.Employee;
import ca.sheridancollege.makonnen.beans.User;

@Repository
public class DatabaseAccess {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	//Insert method for adding form employee object into the database
	public void insertEmployee(Employee employee) {
		
		String query = "INSERT into EMPLOYEES(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, "
				+ "JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID) VALUES(:FIRST_NAME, :LAST_NAME, "
				+ ":EMAIL, :PHONE_NUMBER, :HIRE_DATE, :JOB_ID, :SALARY, :COMMISSION_PCT, :MANAGER_ID, :DEPARTMENT_ID)";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		namedParameters.addValue("FIRST_NAME", employee.getFIRST_NAME());
		namedParameters.addValue("LAST_NAME", employee.getLAST_NAME());
		namedParameters.addValue("EMAIL", employee.getEMAIL());
		namedParameters.addValue("PHONE_NUMBER", employee.getPHONE_NUMBER());
		namedParameters.addValue("HIRE_DATE", employee.getHIRE_DATE());
		namedParameters.addValue("JOB_ID", employee.getJOB_ID());
		namedParameters.addValue("SALARY", employee.getSALARY());
		namedParameters.addValue("COMMISSION_PCT", employee.getCOMMISSION_PCT());
		namedParameters.addValue("MANAGER_ID", employee.getMANAGER_ID());
		namedParameters.addValue("DEPARTMENT_ID", employee.getDEPARTMENT_ID());

		
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if(rowsAffected > 0)
			System.out.println("Employee has been inserted into database.");
		
	}
	
	//Update method for changing COMMISSION_PCT null value to zero to allow successful SELECT method from database
	public void updateNull() {
	
		String query = "UPDATE EMPLOYEES SET COMMISSION_PCT=00.00 WHERE COMMISSION_PCT IS NULL";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if(rowsAffected > 0)
			System.out.println("NULL values have been removed from EMPLOYEES table in database.");
	}
	
	//Method retrieves all employee records from database
	public List<Employee> getEmployees(){
		
		String query = "SELECT * FROM EMPLOYEES";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Employee>(Employee.class));
		
	}
	
	//Method retrieves only EMPLOYEE_ID from all records in employee table in database
	public List<Long> getEmployeeIds(){
		
		String query = "SELECT EMPLOYEE_ID FROM EMPLOYEES";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		return jdbc.queryForList(query, namedParameters, Long.class);
		
	}
	
	//Method retrieves only DEPARTMENT_ID from all records in departments table in database
	public List<Long> getDepartmentIds(){
		
		String query = "SELECT DEPARTMENT_ID FROM DEPARTMENTS";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		return jdbc.queryForList(query, namedParameters, Long.class);
		
	}
	
	//Method updates the salary of a specific employee id from the employee table in database
	public void updateEmployee(Long EMPLOYEE_ID, double SALARY) {
		
		String query = "UPDATE EMPLOYEES SET SALARY=:SALARY WHERE EMPLOYEE_ID=:EMPLOYEE_ID";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		namedParameters.addValue("EMPLOYEE_ID", EMPLOYEE_ID);
		namedParameters.addValue("SALARY", SALARY);


		int rowsAffected = jdbc.update(query, namedParameters);
		
		if(rowsAffected > 0)
			System.out.println("Salary has been updated for Employee with id " + EMPLOYEE_ID);

	}
	
	//Method deletes a sepcific employee record from employee table in database using an employee id
	public void deleteEmployee(Long EMPLOYEE_ID) {
		String query = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID=:EMPLOYEE_ID";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		namedParameters.addValue("EMPLOYEE_ID", EMPLOYEE_ID);

		int rowsAffected = jdbc.update(query, namedParameters);
		
		if(rowsAffected > 0)
			System.out.println("Employee with id " + EMPLOYEE_ID + " has been deleted from database.");
		
	}
	
	//Method retrieves a user from the database using an email
	public User findUserAccount(String email) {
		
		String query = "SELECT * FROM SEC_USER WHERE email=:email";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		parameters.addValue("email", email);
		
		ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));
		
		if(users.size() > 0)
			
			return users.get(0);
		
		else return null;
	}
	
	//Method retrieves roles assigned to a specific user associated with userId parameter from database
	public List<String> getRolesById(Long userId){
		
		ArrayList<String> roles = new ArrayList<String>();
		
		//MAKE SURE THERE IS A SPACE BEFORE KEY WORDS
		String query = "SELECT USER_ROLE.userId, SEC_ROLE.roleName "
				
				+ "FROM USER_ROLE,SEC_ROLE "
				+ "WHERE USER_ROLE.roleId=SEC_ROLE.roleId "
				+ "AND userId=:userId";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		parameters.addValue("userId", userId);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		
		for(Map<String, Object> row :rows) {
			
			roles.add((String)row.get("roleName"));
		}
		
		return roles;
	}
	
}
