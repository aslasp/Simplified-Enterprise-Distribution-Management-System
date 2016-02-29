package program;

import java.io.IOException;

import Frames.AccountantFrame;
import Frames.AdminFrame;
import Frames.SalesmanFrame;
import Frames.StorekeeperFrame;
import Others.LoginFrame;

public class Client {
	public static void ClientDistinguish(Users user)
			throws ClassNotFoundException, IOException {
		switch (user.role) {
		case Administrator:
			AdminFrame af = new AdminFrame(user);
			break;
		case Accountant:
			AccountantFrame accf = new AccountantFrame(user);
			break;
		case Salesman:
			SalesmanFrame sf = new SalesmanFrame(user);
			break;
		case Storekeeper:
			StorekeeperFrame stof = new StorekeeperFrame(user);
			break;
		}
	}

	public static void main(String[] args) throws ClassNotFoundException,
			IOException {
		LoginFrame lf = new LoginFrame();
	}


}
