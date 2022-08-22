// The User class will have details such as userId, firstName, lastName, and savedAddresses. 

public class User {
	String userID ; 
	String firstName ; 
	String lastName ; 
	String savedAddresses ;
	
	public User(String userID, String firstName, String lastName, String savedAddresses) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.savedAddresses = savedAddresses;
	}
	public User( String firstName, String lastName, String savedAddresses) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.savedAddresses = savedAddresses;
	}

	public String getUserID() {
		return userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSavedAddresses() {
		return savedAddresses;
	}
	@Override
	public String toString() {
		return "User ID : " + userID +"\n" + "First Name : " + firstName +"\n" +  "Last Name : " + lastName +"\n" + "Billing Addresses : "
				+ savedAddresses ;
	}

	
	
	
	
}
