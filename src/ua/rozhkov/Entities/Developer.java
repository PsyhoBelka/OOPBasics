package ua.rozhkov.Entities;

import java.util.Random;

public class Developer extends Employee {
	public int fixedBugs;
	
	public Developer() {
	}
	
	public Developer(long id, String name, int age, float salary, String gender, int fixedBugs) {
		super(id, name, age, salary, gender);
		this.fixedBugs = fixedBugs;
		this.salary= (float) (this.rate+((this.fixedBugs * 1.5) * (new Random().nextBoolean() ? 2 : 0)));
	}
	
	public Developer(String name, int age, float salary, String gender, int fixedBugs) {
		super(name, age, salary, gender);
		this.fixedBugs = fixedBugs;
		this.rate=salary;
		this.salary= (float) (this.rate+((this.fixedBugs * 1.5) * (new Random().nextBoolean() ? 2 : 0)));
	}
	
	@Override
	public void print() {
		super.print();
		System.out.println("Fixed bugs: " + this.fixedBugs);
		System.out.println("|------------------|");
	}
}
