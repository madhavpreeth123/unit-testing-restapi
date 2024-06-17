package in.makersharks.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "USERS_TBL")

public class User implements Serializable {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@NotBlank(message = "username is madatory")
	@Size(min = 3,max = 20)
	private String username;
	
	@Email
	@NotBlank(message = "email is madatory")
	private String email;
	
	@NotBlank(message = "Password is mandatory")
    @Size(min = 6)
	private String password;
}
