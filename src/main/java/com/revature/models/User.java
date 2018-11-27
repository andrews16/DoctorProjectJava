package com.revature.models;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.enums.UserRole;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
//@NamedNativeQuery(
//	    name = "find_person_name",
//	    query =
//	        " IF EXISTS (SELECT users.user_id\r\n" + 
//	        "	FROM users \r\n" + 
//	        "	WHERE users.username = input_username\r\n" + 
//	        "	AND password = crypt(input_password,password)) \r\n" + 
//	        "THEN \r\n" + 
//	        "	UPDATE users \r\n" + 
//	        "		SET session_id = crypt(input_username, gen_salt('bf',6))\r\n" + 
//	        "		WHERE users.username = input_username;\r\n" + 
//	        "	RETURN QUERY \r\n" + 
//	        "		SELECT *" + 
//	        "		FROM users \r\n" + 
//	        "		WHERE users.username = input_username;\r\n" + 
//	        "\r\n" + 
//	        "END IF;\r\n"
//	)
//@NamedStoredProcedureQuery(
//		name = "login", // name of stored procedure in the persistence unit
//		procedureName = "login_user", //name of  stored procedure in the database
//		parameters = //Parameters of the stored procedure
//		{ 
//			@StoredProcedureParameter(// A parameter,
//					name = "username", //Name of the parameter
//					mode = ParameterMode.IN, // Mode of the parameter
//					type = String.class),
//			@StoredProcedureParameter(// A parameter,
//					name = "password", //Name of the parameter
//					mode = ParameterMode.IN, // Mode of the parameter
//					type = String.class)// JDBC Type.		
//		}
//)
//@NamedStoredProcedureQuery(
//		name = "authenticate", // name of stored procedure in the persistence unit
//		procedureName = "authenticate_cookie", //name of  stored procedure in the database
//		parameters = //Parameters of the stored procedure
//		{ 
//			@StoredProcedureParameter(// A parameter,
//					name = "username", //Name of the parameter
//					mode = ParameterMode.IN, // Mode of the parameter
//					type = String.class),
//			@StoredProcedureParameter(// A parameter,
//					name = "session", //Name of the parameter
//					mode = ParameterMode.IN, // Mode of the parameter
//					type = String.class)// JDBC Type.		
//		}
//)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id", unique = true, nullable = false)
	@Access(AccessType.PROPERTY)
	private int id;
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "user_role")
	private UserRole role;
	@JsonIgnore
	@Column(name = "session_id")
	private String session;
	
	
	public User() {
		super();
	}
	public User(int id, String username, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", role=" + role + ", session=" + session + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}

}
