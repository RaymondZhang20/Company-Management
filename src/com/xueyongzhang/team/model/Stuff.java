package com.xueyongzhang.team.model;

import com.xueyongzhang.team.domain.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.xueyongzhang.team.model.Data.*;

public class Stuff implements Iterable<Employee>{
	private List<Employee> employees;

	public Stuff() {
		employees = new LinkedList<>();

		for (int i = 0; i < 12; i++) {
			// get normal field
			Integer type = Integer.parseInt(EMPLOYEES[i][0]);
			Integer id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			Integer age = Integer.parseInt(EMPLOYEES[i][3]);
			Double salary = Double.parseDouble(EMPLOYEES[i][4]);

			//get subclass field
			Equipment eq;
			Double bonus;
			Integer stock;

			switch (type) {
			case EMPLOYEE:
				employees.add(new Employee(id, name, age, salary));
				break;
			case PROGRAMMER:
				eq = createEquipment(i);
                employees.add(new Programmer(id, name, age, salary, eq));
				break;
			case DESIGNER:
				eq = createEquipment(i);
				bonus = Double.parseDouble(EMPLOYEES[i][5]);
                employees.add(new Designer(id, name, age, salary, eq, bonus));
				break;
			case ARCHITECT:
				eq = createEquipment(i);
				bonus = Double.parseDouble(EMPLOYEES[i][5]);
				stock = Integer.parseInt(EMPLOYEES[i][6]);
                employees.add(new Architect(id, name, age, salary, eq, bonus, stock));
				break;
			}
		}
	}

	private Equipment createEquipment(int index) {
		int type = Integer.parseInt(EQIPMENTS[index][0]);
		switch (type) {
		case PC:
			return new PC(EQIPMENTS[index][1], EQIPMENTS[index][2]);
		case LAPTOP:
			int price = Integer.parseInt(EQIPMENTS[index][2]);
			return new Laptop(EQIPMENTS[index][1], price);
		case PRINTER:
			return new Printer(EQIPMENTS[index][1], EQIPMENTS[index][2]);
		}
		return null;
	}

	public Employee getEmployee(int id) throws TeamException {
		for (Employee e : employees) {
			if (e.getId() == id)
				return e;
		}
		throw new TeamException("The employee doesn't exist");
	}

    @Override
    public Iterator<Employee> iterator() {
        return employees.iterator();
    }
}
