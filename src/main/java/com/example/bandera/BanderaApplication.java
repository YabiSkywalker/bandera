package com.example.bandera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class BanderaApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(BanderaApplication.class, args);

		/* ------------------CRUD FORM --------------- */
		customers d = new customers();


		//int i = 0;//vehicle primary key
		//int j = 0;//customer primary key


		//j = d.getCustomerPrimaryKey();
		//d.profileProcessor(j);



		/*
		//Test form to add entries
		System.out.println("Enter first name ");
		Scanner sc = new Scanner(System.in);
		String firstName = sc.nextLine();
		System.out.println("Enter last name ");
		String lastName = sc.nextLine();
		System.out.println("Enter phone number");
		String phoneNumber = sc.nextLine();
		System.out.println("Enter email");
		String email = sc.nextLine();
		*/


		/*
		//Test form to add cars
		String year = sc.nextLine();
		String make = sc.nextLine();
		String model = sc.nextLine();
		String trim = sc.nextLine();
		int zipCode = sc.nextInt();
		String bodyStyle = sc.nextLine();
		String exteriorColor = sc.nextLine();
		String interiorColor = sc.nextLine();
		String transmission = sc.nextLine();
		String drivetrain = sc.nextLine();
		String cylinders = sc.nextLine();
		 */

		/*
		i = d.getInventoryPrimaryKey();
		d.addVehicle(i, year, make, model, trim, zipCode, bodyStyle, exteriorColor, interiorColor, transmission, drivetrain, cylinders);

		d.getResults();
		d.deleteFromCustomers();
		*/
	}

}
