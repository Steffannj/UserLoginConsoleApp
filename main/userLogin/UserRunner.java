package userLogin;

import java.util.Scanner;

public class UserRunner {
	
	public static void main(String[] args) {
		UserDAOImplementation userDAO = new UserDAOImplementation();
		
		Scanner input = new Scanner(System.in);

		int choice = -1;

		while (choice != 3) {

			try {
				System.out.println("1. Sign up\n2. Log in\n3. Exit");
				choice = input.nextInt();

				switch (choice) {
				case 1:
					System.out.println("Enter your firstname: ");
					String firstname = input.next();

					System.out.println("Enter your lastname: ");
					String lastname = input.next();

					System.out.println("Enter your email: ");
					String email = input.next();

					System.out.println("Enter username: ");
					String username = input.next();

					System.out.println("Enter password: ");
					String password = input.next();
					
					userDAO.registerUser(firstname, lastname, email, username, password);
					break;
				case 2:
					System.out.println("Enter username: ");
					username = input.next();

					System.out.println("Enter password: ");
					password = input.next();

					User logedUser = userDAO.loginUser(username, password);

					if (logedUser.getFirstname() != null) {
						userDAO.userOptions(logedUser);
					}

					break;
				case 3:
					ConnectionManager.getInstance().close();
					input.close();
					break;
				default:
					System.out.println("Wrong input! Choose integer(1-3): ");
				}
			} catch (Exception e) {
				System.out.println("Wrong input! Input must be integer number(1-3)");
				input.next();
			}
		}
	
	}
}
