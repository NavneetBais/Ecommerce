import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;


public class Ecommerce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, Products> product = new HashMap<>();//Used hashmap coz it is much easier to get product object
														     // when a user select a product by ID .
		List<User> users = new ArrayList<>();
		List<Order> order = new ArrayList<>();
		// List of predefined products
		product.put(1, new Products(1, "Television", 20, 10000));
		product.put(2, new Products(2, "Radio", 20, 5000));
		product.put(3, new Products(3, "AC", 20, 30000));

		Scanner sc = new Scanner(System.in);
		System.out.println("New User [Y/N] ? "); // Not required since we don't have database connectivity
		String option = sc.nextLine();
		boolean isNewUser = option.equalsIgnoreCase("Y") ? true : false;
		if (option.equalsIgnoreCase("Y")){
			System.out.println("Enter First Name");
			String fname = sc.nextLine();
			System.out.println("Enter Last Name");
			String lname = sc.nextLine();
			System.out.println("Enter Your Address");
			String address = sc.nextLine();
			User newUser = new User(fname.charAt(0) + lname, fname, lname, address);
			System.out.println("Signed Up Successfully.");
			users.add(newUser); // Adding new user to users list
			disMenu(product, newUser, order);
		} else {
			boolean flag = false;
			System.out.println("Enter User Name : ");
			String uName = sc.nextLine();
			User u = null;
			for (User x : users) {
				if (x.getUserID().equalsIgnoreCase(uName)) {
					flag = true;
					u = x;
					break;

				}
			}
			if (flag) {
				disMenu(product, u, order);
			} else {
				System.out.println("User does not Exists.");
			}

		}

	}

	public static void disMenu(HashMap<Integer, Products> p, User user, List<Order> orderList) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1. Shop Products");
		System.out.println("2. Show Cart");
		System.out.println("3. Checkout");
		System.out.println("4. Quit");
		int option = Integer.parseInt(sc.nextLine());
		switch (option) {
		case 1: {
			// Loop to display product catalogue
			for (Map.Entry<Integer, Products> entry : p.entrySet()) {
				System.out.println("ID = " + entry.getKey() + ", Product = " + entry.getValue().getProductName()
						+ "Price(INR): " + entry.getValue().getUnitPrice());
			}

			System.out.println("Select Products : ");
			int op = Integer.parseInt(sc.nextLine()); // Product ID variable.
			System.out.println("Enter Quantity : ");
			int quantity = Integer.parseInt(sc.nextLine());
			// IF block checking if entered quantity is less than or equal to available
			// stock.
			if (quantity <= p.get(op).getQuantity()) {
				int q = p.get(op).getQuantity() - quantity; // Calculating remaining quantity.
			//Hashmap Benefit
				p.put(op, new Products(op, p.get(op).getProductName(), q, p.get(op).getUnitPrice()));// overriding
																										// product
																										// hashmap with
																										// updated
																										// quantity.
				// Asking details for order to add in OrderList.
				System.out.println("Enter you Phone Number: ");
				String pNum = sc.nextLine();
				System.out.println("Enter you Payment Mode: ");
				String paymentMode = sc.nextLine();
				String orderDate = formatDate(new Date()); // Formatting date as per "yyyy-mm-dd" format.
				String shippingDate = shippingDate(orderDate); // Incrementing date by 2 Days.

				orderList.add(new Order(p.get(op), shippingDate, orderDate, user.getUserID(), user.getSavedAddresses(),
						pNum, paymentMode, quantity)); // Adding order in order List array.
				System.out.println("Product added in Cart. Do you want to shop more? [Y/N]");
				String shopMore = sc.nextLine();
				if (shopMore.equalsIgnoreCase("Y")) {
					disMenu(p, user, orderList);
				} else {
					showCart(user, orderList);
					System.out.println("Continue to checkout [Y/N] ? ");
					String choice = sc.nextLine();
					if (choice.equalsIgnoreCase("Y")) {
						System.out.println(user);
						System.out.println("++++++++++Invoice++++++++++++");
						showCart(user, orderList);
						System.out.println("Thanks for Shopping with us.");
					} else {
						disMenu(p, user, orderList);
					}
				}

				// If entered quantity is greater than available stock.
			} else {
				System.out.println("Sorry, Only " + p.get(op).getQuantity() + " of " + p.get(op).getProductName()
						+ " is available.");
				System.out.println("Do you want to conitnue ? [Y/N]");
				String choice = sc.nextLine();
				if (choice.equalsIgnoreCase("Y")) {
					disMenu(p, user, orderList);
				} else {
					System.out.println("Thanks for visiting.");
				}
			}
			break;
		}
		// If User select Show Cart Option
		case 2: {
			showCart(user, orderList);
			System.out.println();
			System.out.println("Press 1 to return to Main Menu : ");
			int mainMenu = Integer.parseInt(sc.nextLine());
			switch (mainMenu) {
			case 1:
				disMenu(p, user, orderList);
				break;

			}
			break;
		}
		// If user select Checkout
		case 3: {
			System.out.println("++++++++++User Details++++++++++++");
			System.out.println(user);
			System.out.println();

			System.out.println("++++++++++Invoice++++++++++++");
			showCart(user, orderList);

			System.out.println("Thanks for Shopping with us.");

			break;
		}
		// If user chooses Quit Option
		case 4: {

			System.out.println("Thanks for Shopping with us.");
			break;
		}
		default: {
			System.out.println("Invalid Input.");
			disMenu(p, user, orderList);
		}
		}
	}

//ShowCart takes current user and show all those orders in orderList having same userId as current userID.
	public static void showCart(User u, List<Order> orderList) {
		double totalAmount = 0;
		int i = 1; // Just a for BuLLET No. of order
		for (Order x : orderList) {
			// If block is checking for all those orders having same userID as current
			// user's id.
			if (x.getUserId().equalsIgnoreCase(u.userID)) {
				Products p = x.getProduct(); // Storing product in orderList as we need properties of product to
												// calculate Bill and Price.
				double price = p.getUnitPrice();
				int quantity = x.getOrderQuantity();
				double bill = price * quantity;
				totalAmount += bill; // Calculating total amount by adding bill for each product.
				System.out
						.println(i + ". " + p.getProductName() + ", Quantity : " + quantity + ", Sub Total : " + bill);
				System.out.println(x);
				System.out.println();
				i++;// Bullet pointer just for formatting
			}

		}
		System.out.println("Your Total Bill : " + totalAmount);

	}

	// Formatting Date as per "yyyy-mm-dd"
	public static String formatDate(Date date) {

		// Specify format as "yyyy-MM-dd"
		SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Use format method on SimpleDateFormat
		String formattedDateStr = dmyFormat.format(date);
		return formattedDateStr;
	}

	// Incrementing Date
	public static String shippingDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 2); // number of days to add
		String d = sdf.format(c.getTime()); // d is now the new date
		return d;
	}

}
