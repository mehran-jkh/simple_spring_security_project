package services;

import models.signupDTO;

public interface SignupDAO
{

	public void save_user(signupDTO signupDAO);
	public void delete_user(String username);
}
