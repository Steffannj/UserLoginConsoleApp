package userLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDAOImplementation implements UserDAOInterface {

	Connection connection = ConnectionManager.getInstance().getConnection();
	private Scanner input;

	@Override
	public void registerUser(String firstname, String lastname, String email, String username, String password)
			throws SQLException {
		String query = "INSERT INTO info (firstname, lastname, email, username, password) VALUES (?,?,?,?,?)";

		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, email);
			statement.setString(4, username);
			statement.setString(5, password);

			statement.execute();

		}

	}

	@Override
	public User loginUser(String username, String password) throws SQLException {
		User user = new User();
		String query = "SELECT * FROM info WHERE username = ? AND password = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				System.out.println("You've been successfully loged in.");
				user.setUserId(rs.getInt("userId"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				rs.close();
			} else {
				System.out.println("Wrong username or password.");
			}
		}
		return user;

	}

	@Override
	public void userOptions(User user) throws SQLException {
		System.out.println("Choose one option: ");
		input = new Scanner(System.in);

		int choice = 0;

		while (choice != 5) {
			try {
				System.out.println(
						"1. Account info\n2. Change firstname\n3. Change lastname\n4. Change password"
								+ "\n5. Log out");

				choice = input.nextInt();

				switch (choice) {
				case 1:
					System.out.println(user.toString());
					break;
				case 2:
					System.out.println("Enter new firstname: ");
					input.nextLine();
					changeFirstname(user.getUserId(), input.nextLine());
					break;
				case 3:
					System.out.println("Enter new lastname: ");
					input.nextLine();
					changeLastname(user.getUserId(), input.nextLine());
					break;
				case 4:
					System.out.println("Enter new password: ");
					input.nextLine();
					changePassword(user.getUserId(), input.nextLine());
					break;
				case 5:
					break;
				default:
					System.out.println("Wrong input! Choose one option(1-5).");
				}
			} catch (Exception e) {
				System.out.println("Wrong input! Input must be integer(1-5).");
				input.next();
			}
		}

	}

	@Override
	public void changeFirstname(int userId, String newFirstname) throws SQLException {
		String query = "UPDATE info SET firstname = ? WHERE userId = ?";

		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, newFirstname);
			statement.setInt(2, userId);

			statement.executeUpdate();

			System.out.println("Firstname successfully changed.");
		}
	}

	@Override
	public void changeLastname(int userId, String newLastname) throws SQLException {
		String query = "UPDATE info SET lastname = ? WHERE userId = ?";

		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, newLastname);
			statement.setInt(2, userId);

			statement.executeUpdate();

			System.out.println("Lastname successfully changed.");
		}
	}

	@Override
	public void changePassword(int userId, String password) throws SQLException {
		String query = "UPDATE info SET password = ? WHERE userId = ?";

		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, password);
			statement.setInt(2, userId);

			statement.executeUpdate();

			System.out.println("Password successfully changed.");
		}
	}

}
