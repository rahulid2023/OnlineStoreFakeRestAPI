package payloads;


import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Address;
import pojo.Geolocation;
import pojo.Name;
import pojo.Product;
import pojo.Users;


public class Payload {

	private static final Faker faker=new Faker();
	private static final String categories[]= {"electronics", "furniture", "clothing", "books", "beauty"};
	
	private static final Random random=new Random();
	
		
	//Product
	public static Product productPayload()
	{
		String name=faker.commerce().productName();
		double price=Double.parseDouble(faker.commerce().price());
		String description=faker.lorem().sentence();
		String imageUrl="https://i.pravatar.cc/100";
		String category=categories[random.nextInt(categories.length)];
		
		new Product(name, price, description, imageUrl, category);
		return new Product(name, price, description, imageUrl, category);
	}
	
	
	//Cart
	
	
	
   // Users

	public static Users userPayload()
	{
		
		Geolocation geolocation = new Geolocation (faker.address().latitude(),faker.address().longitude());	

        String city = faker.address().city();
        String street = faker.address().streetAddress();
        int number = random.nextInt(100);
        String zipcode = faker.address().zipCode();
				
		String email = faker.internet().emailAddress();
		String username =faker.name().username();
		String password = faker.internet().password(6, 10, true);
		Name name = new Name(faker.name().firstName(),faker.name().lastName());
		Address address = new Address(city, street, number, zipcode, geolocation) ;
		String phone =  faker.phoneNumber().phoneNumber();
		
		Users user = new Users(email, username, password, name, address, phone);
		return user;
	}	  
	
}

//Login




