package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.enums.UserRole;
import com.revature.models.Credentials;
import com.revature.models.User;

@Repository
public class AuthenticationRepo {


    DataSource ds;
    SessionFactory sf;
    
    @Autowired
	public AuthenticationRepo(DataSource ds, SessionFactory sf) {
		super();
		this.ds = ds;
		this.sf = sf;
	}

	
	public User login(Credentials credentials) {
		User user = new User();
    	try (Connection conn = ds.getConnection()){
    		String query = "SELECT * FROM login_user(?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,  credentials.getUsername());
			ps.setString(2, credentials.getPassword());
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
				return user;
			} 
    	} catch (SQLException e) {
			e.printStackTrace();
		} 
    	return null;
	}
	



	public User authenticate(String userId, String session) {
		User user = new User();
    	try (Connection conn = ds.getConnection()){
    		String query = "SELECT * FROM authenticate_cookie(?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,  Integer.parseInt(userId));
			ps.setString(2, session);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
				return user;
			} 
    	} catch (SQLException e) {
			e.printStackTrace();
		} 
    	return null;
		
	}
	
	private static User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		if (rs.getInt("user_role") == 1) {
			user.setRole(UserRole.DOCTOR);
		} else {
			user.setRole(UserRole.PATIENT);
		}
		user.setUsername(rs.getString("username"));
		user.setSession(rs.getString("session_id"));
		
		return user;

	}


}
