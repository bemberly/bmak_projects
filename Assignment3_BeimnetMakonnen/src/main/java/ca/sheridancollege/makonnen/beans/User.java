package ca.sheridancollege.makonnen.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private Long userId;
	@NonNull 
	private String email;
	@NonNull
	private String encryptedPassword;
	@NonNull
	private Boolean enabled;
}
