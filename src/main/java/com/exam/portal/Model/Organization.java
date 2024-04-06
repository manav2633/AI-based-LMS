package com.exam.portal.Model;

import java.util.Date;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "register_organization")
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organization_id")
	private int organizationId;

	// @NotBlank(message = " Organization Name cannot be empty")
	// @Pattern(regexp = "^[A-Za-z][A-Za-z-'. ][A-Za-z]*$", message = " Organization
	// Name has invalid characters")
	public String organization_name;

	// @Email
	// @NotBlank
	public String email_id;

	public Date registered_date;

	// @NotBlank(message = " Discription cannot be empty")
	// @Pattern(regexp = "^[A-Za-z][A-Za-z-'. ][A-Za-z]*$", message = " Discription
	// has invalid characters")
	private String description;

	// @NotBlank(message = "Password cannot be empty")
	// @Size(min = 8, message = "Password must be at least 8 characters long")
	// @Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=]).*$",
	// message = "Password must contain at least one digit, one lowercase, one
	// uppercase, and one special character")
	// private String password;
	private Boolean status;

	// @NotBlank(message = "Address cannot be empty")
	// @Pattern(regexp = "^[A-Za-z][A-Za-z-'. ][A-Za-z]*$", message = " Address has
	// invalid characters")
	private String Address;

	// @NotBlank(message = "city cannot be empty")
	// @Pattern(regexp = "^[A-Za-z][A-Za-z-'. ][A-Za-z]*$", message = " City has
	// invalid characters")
	private String city;
	//
	// @NotBlank(message = "State cannot be empty")
	// @Pattern(regexp = "^[A-Za-z][A-Za-z-'. ][A-Za-z]*$", message = " State has
	// invalid characters")
	private String state;
	// @NotBlank(message = "Country cannot be empty")
	// @Pattern(regexp = "^[A-Za-z][A-Za-z-'. ][A-Za-z]*$", message = " Country has
	// invalid characters")
	private String country;

	// @NotBlank
	// @Size(min = 10, max = 10)
	public String contact_no;

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public Date getRegistered_date() {
		return registered_date;
	}

	public void setRegistered_date(Date registered_date) {
		this.registered_date = registered_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}


}
