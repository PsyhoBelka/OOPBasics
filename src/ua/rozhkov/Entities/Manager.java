package ua.rozhkov.Entities;

public class Manager extends Employee {
	public Manager() {
	}
	
	public Manager(long id, String name, int age, float salary, String gender) {
		super(id, name, age, salary, gender);
		this.rate=salary;
		this.salary=this.rate;
	}
	
	public Manager(String name, int age, float salary, String gender) {
		super(name, age, salary, gender);
		this.salary=this.rate;
	}
	
	@Override
	public void print() {
		super.print();
		System.out.println("|------------------|");
	}
}
