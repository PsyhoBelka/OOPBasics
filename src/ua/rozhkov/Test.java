package ua.rozhkov;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import ua.rozhkov.Entities.Developer;
import ua.rozhkov.Entities.Employee;
import ua.rozhkov.Entities.Manager;
import ua.rozhkov.Services.EmployeeParser;
import ua.rozhkov.Services.EmployeeService;

public class Test {
	public static void main(String[] args) {
		Random random = new Random();
		EmployeeService employeeService = new EmployeeService();
		employeeService.generateEmployees(5);
		employeeService.printEmployees();
		
		System.out.printf("Salaries and bonuses: %1$4.2f%n----%n", employeeService.calculateSalaryAndBonus());
		
		System.out.println("Employee card by id: ");
		Employee employee1 = employeeService.getById(3);
		employee1.print();
		
		employee1 = employeeService.getByName("Агата");
		System.out.println("Employee card by name");
		employee1.print();
		
		System.out.println("Sort employees by name:");
		Employee[] employees1 = employeeService.sortByName();
		for (Employee e : employees1) {
			e.print();
		}
		
		System.out.println("Sort employees by name and salary:");
		Employee[] employees2 = employeeService.sortByNameAndSalary();
		for (Employee e : employees2) {
			e.print();
		}
		Employee newEmp = null;
		String name;
		String gender;
		if (random.nextBoolean()) {
			gender = "male";
			name = employeeService.names[0][random.nextInt(employeeService.names[0].length)];
		}
		else {
			gender = "female";
			name = employeeService.names[1][random.nextInt(employeeService.names[1].length)];
		}
		int age = random.nextInt(101);
		float salary = random.nextInt(25000) + random.nextFloat();
		int bugs = random.nextInt(1000);
		switch (random.nextInt(2)) {
			case 0: {
				newEmp = new Developer(name, age, salary, gender, bugs);
				break;
			}
			case 1: {
				newEmp = new Manager(name, age, salary, gender);
				break;
			}
		}
		System.out.println("Old employee:");
		Employee oldEmp = employeeService.getById(3);
		oldEmp.print();
		employeeService.edit(newEmp, 3);
		System.out.println("New employee:");
		Employee newEmployee2 = employeeService.getById(3);
		newEmployee2.print();
		
		System.out.println("%n--------------Trying JSON-------------%n");
		EmployeeParser employeeParser = new EmployeeParser();
		employeeParser.toJson(employeeService.employees);
		System.out.println("--------------Writing to JSON sucesfully-------------");
		
		System.out.println("--------------Try reading JSON-------------");
		try {
			employeeService.employees = employeeParser.fromJson(new FileReader(employeeParser.filename));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("--------------Printing Employees from JSON-------------");
		employeeService.printEmployees();
		
	}
}


