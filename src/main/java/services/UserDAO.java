package services;

import models.CustomUser;

import java.util.List;

public interface UserDAO {
 public List<CustomUser> load_user_by_username (String username);



}
