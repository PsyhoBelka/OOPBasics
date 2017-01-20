package ua.rozhkov.Entities;

import java.util.Comparator;

/**
 * Created by Win7 on 18.01.2017.
 */
public class Employee implements Comparable {
	public long id;
	public String name;
	public int age;
	public float rate;
	public float salary;
	public String gender;
	
	public Employee() {
		
	}
	
	public Employee(long id, String name, int age, float salary, String gender) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.rate = salary;
		this.gender = gender;
	}
	
	public Employee(String name, int age, float salary, String gender) {
		this.id = -1;
		this.name = name;
		this.age = age;
		this.rate = salary;
		this.gender = gender;
	}
	
	public void print() {
		System.out.println("Id: " + this.id);
		System.out.println("Name: " + this.name);
		System.out.println("Age: " + this.age);
		System.out.printf("Salary: %1$.2f%n", this.salary);
	}
	
	@Override
	public int compareTo(Object o) {
		return name.compareTo(((Employee) o).name);
	}
	
	public static class compareByNameAndSalary implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			if (((Employee) o1).name.compareTo(((Employee) o2).name) != 0) {
				return ((Employee) o1).name.compareTo(((Employee) o2).name);
			}
			else {
				return (int) (((Employee) o1).salary - ((Employee) o2).salary);
			}
		}
	}
}
