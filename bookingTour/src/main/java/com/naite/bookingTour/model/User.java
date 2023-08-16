package com.naite.bookingTour.model;

import java.io.Serializable;

import jakarta.persistence.*;


@Entity
@Table(name = "users")

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "repeatpassword")
	private String repeatPassword;

	@Column(name = "email")
	private String email;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    
    public enum Role {
    	ADMIN,
    	USER
    }


	public User() {
		super();
	}

	

	public User(Long id, String username, String password, String repeatPassword, String email, String fullname,
			String phone, String address, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.email = email;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}



	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatpassword) {
		this.repeatPassword = repeatpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	public boolean isAdmin() {
		if (role.equals("ADMIN")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPasswordMatching() {
		if (password.equals(repeatPassword)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPasswordEnoughLength() {
		if (password.length() >= 6) {
			return true;
		} else {
			return false;
		}
	}

}
