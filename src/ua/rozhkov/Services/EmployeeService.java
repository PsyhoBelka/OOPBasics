package ua.rozhkov.Services;

import ua.rozhkov.Entities.Developer;
import ua.rozhkov.Entities.Employee;
import ua.rozhkov.Entities.Manager;

import java.util.Arrays;
import java.util.Random;

public class EmployeeService {
	long id = 0;
	public Employee[] employees;
	//Names [0] - male names, Name[1] - female names
	public String[][] names = {{"Анфим", "Аполлон", "Арам", "Ардалион", "Арий", "Аристарх", "Богдан", "Боголюб", "Болеслав", "Венедикт", "Вениамин", "Викентий", "Виктор", "Динасий", "Дитмар", "Дмитрий", "Доброслав", "Добрыня", "Дональд", "Донат", "Иннокентий", "Ион", "Ионос", "Иосиф", "Ипат"}, {"Августа", "Аврора", "Агата", "Агнесса", "Агния", "Бабетта", "Багдагуль", "Барбара", "Баянсулу", "Беата", "Беатриса", "Валентина", "Валерия", "Валида", "Валия", "Ванда", "Габриэлла", "Газама", "Галима", "Галина", "Гаянэ", "Дайна", "Далия", "Дамира", "Дана", "Даниэла", "Данута", "Дариа"}};
	//Gender: 	true - male,
	// 			false - female
	
	public EmployeeService() {
	}
	
	//	генерирует случайным образом заданное количество сотрудников, и присваивает их внутреннему массиву
	public void generateEmployees(int size) {
		Random random = new Random();
		employees = new Employee[size];
		String name;
		String gender;
		for (int i = 0; i < employees.length; i++) {
			if (random.nextBoolean()) {
				gender = "male";
				name = names[0][random.nextInt(names[0].length)];
			}
			else {
				gender = "female";
				name = names[1][random.nextInt(names[1].length)];
			}
			int age = new Random().nextInt(101);
			float salary = random.nextInt(25000) + random.nextFloat();
			int bugs = new Random().nextInt(1000);
			
			switch (random.nextInt(2)) {
				case 0: {
					employees[i] = new Developer(id, name, age, salary, gender, bugs);
					id++;
					break;
				}
				case 1: {
					employees[i] = new Manager(id, name, age, salary, gender);
					id++;
					break;
				}
			}
		}
	}
	
	//	вывод на экран информации о сотрудниках
	public void printEmployees() {
		for (Employee emp : employees) {
			emp.print();
		}
	}
	
	//	возвращает количество денег необходимое для выплаты зарплат для всех сотрудников в этом месяце
	// Developer	(ставка + fixedBugs * 1.5) * (randomBoolean ? 2 : 0)
	//	Manager - salary
	public double calculateSalaryAndBonus() {
		double wholeSalary = 0;
		for (Employee emp : employees) {
			wholeSalary+=emp.salary;
		}
		return wholeSalary;
	}
	
	//	возвращает сотрудника по заданному id
	public Employee getById(long id) {
		Employee tmp = new Employee();
		for (Employee emp : employees) {
			if (emp.id == id) {
				tmp = emp;
				break;
			}
		}
		return tmp;
	}
	
	//	возвращает первого сотрудника по заданному имени
	public Employee getByName(String name) {
		Employee tmp = new Employee();
		for (Employee emp : employees) {
			if (emp.name == name) {
				tmp = emp;
				break;
			}
		}
		return tmp;
	}
	
	//	возвращают отсортированный массив с сотрудниками по критерию
	public Employee[] sortByName() {
		Employee[] tmpArr = employees;
		Arrays.sort(tmpArr);
		return tmpArr;
	}
	
	//	возвращают отсортированный массив с сотрудниками по критерию
	public Employee[] sortByNameAndSalary() {
		Employee[] tmpArr = employees;
		Arrays.sort(tmpArr, new Employee.compareByNameAndSalary());
		return tmpArr;
	}
	
	//	находит сотрудника по id, и подменяет информацию о нем на новую. Метод возвращает старую версию сотрудника
	public Employee edit(Employee newEmp, long id) {
		Employee old = getById(id);
		for (Employee emp : employees) {
			if (old.id == emp.id) {
				emp.id = old.id;
				emp.name = newEmp.name;
				emp.rate=newEmp.rate;
				emp.salary = newEmp.salary;
				emp.age = newEmp.age;
				emp.gender = newEmp.gender;
				break;
			}
		}
		return old;
	}
}
