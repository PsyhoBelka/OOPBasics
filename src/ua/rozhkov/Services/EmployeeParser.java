package ua.rozhkov.Services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import ua.rozhkov.Entities.Developer;
import ua.rozhkov.Entities.Employee;
import ua.rozhkov.Entities.Manager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class EmployeeParser {
	public final String filename = "src\\ua\\rozhkov\\Resources\\file.json";
	
	public Employee[] fromJson(FileReader filename) {
		org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
		JSONObject jsonObject;
		JSONArray jsonArray;
		Employee[] employees = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(filename);
			jsonArray = (JSONArray) jsonObject.get("employees");
			JSONObject jsonObject1;
			employees = new Employee[(int) (long) jsonObject.get("employees_size")];
			Iterator <JSONObject> employeeIterator = jsonArray.iterator();
			int i = 0;
			while (employeeIterator.hasNext()) {
				jsonObject1 = employeeIterator.next();
				if (jsonObject1.get("instanceOf").equals("Manager")) {
					Employee employeeManager = new Manager();
					employeeManager.id = (long) jsonObject1.get("id");
					employeeManager.name = (String) jsonObject1.get("name");
					employeeManager.age = (int) (long) jsonObject1.get("age");
					employeeManager.gender = (String) jsonObject1.get("gender");
					employeeManager.rate = (float) (double)jsonObject1.get("rate");
					employeeManager.salary = (float) (double) jsonObject1.get("salary");
					employees[i] = employeeManager;
				}
				else {
					if (jsonObject1.get("instanceOf").equals("Developer")) {
						Employee employeeDeveloper = new Developer();
						employeeDeveloper.id = (long) jsonObject1.get("id");
						employeeDeveloper.name = (String) jsonObject1.get("name");
						employeeDeveloper.age = (int) (long) jsonObject1.get("age");
						employeeDeveloper.gender = (String) jsonObject1.get("gender");
						employeeDeveloper.rate = (float) (double)jsonObject1.get("rate");
						employeeDeveloper.salary = (float) (double) jsonObject1.get("salary");
						((Developer) employeeDeveloper).fixedBugs = (int) (long) jsonObject1.get("fixedBugs");
						employees[i] = employeeDeveloper;
					}
				}
				i++;
			}
		}
		catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	public String toJson(Employee[] employee) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < employee.length; i++) {
			//			формируем набор из полей объекта-елемента массива
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put("id", employee[i].id);
			jsonObject1.put("name", employee[i].name);
			jsonObject1.put("age", employee[i].age);
			jsonObject1.put("rate", employee[i].rate);
			jsonObject1.put("salary", employee[i].salary);
			jsonObject1.put("gender", employee[i].gender);
			//			записываем какому инстансу принадлежит объект
			if (employee[i] instanceof Developer) {
				jsonObject1.put("instanceOf", "Developer");
				jsonObject1.put("fixedBugs", ((Developer) employee[i]).fixedBugs);
			}
			else {
				if (employee[i] instanceof Manager) {
					jsonObject1.put("instanceOf", "Manager");
				}
			}
			jsonArray.add(jsonObject1);
		}
		jsonObject.put("employees_size", employee.length);
		jsonObject.put("employees", jsonArray);
		try (FileWriter fileWriter = new FileWriter(filename)) {
			fileWriter.write(jsonObject.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		}
		catch (IOException ex) {
			System.out.print(ex.getMessage().toString());
		}
		return jsonObject.toJSONString();
	}
}
