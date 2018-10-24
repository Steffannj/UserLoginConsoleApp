package userLogin;

import java.sql.SQLException;

public interface UserDAOInterface {
	public void registerUser(String firstname, String lastname, String email, String username, String password) throws SQLException;

	public User loginUser(String username, String password) throws SQLException;

	public void userOptions(User user) throws SQLException;

	public void changeFirstname(int userId, String newFirstname) throws SQLException;

	public void changeLastname(int userId, String newLastname) throws SQLException;

	public void changePassword(int userId, String password) throws SQLException;
}
