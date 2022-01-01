package com.xueyongzhang.team.model;

import com.xueyongzhang.team.domain.*;
import com.xueyongzhang.team.persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Staff implements Iterable<Employee>, Writable {
	private List<Employee> employees;

	public Staff() {
		employees = new LinkedList<>();
	}

	public Employee getEmployee(int id) throws TeamException {
		for (Employee e : employees) {
			if (e.getId() == id)
				return e;
		}
		throw new TeamException("The employee doesn't exist");
	}

    public void add(Employee e) {
        employees.add(e);
    }

    public void remove(Employee e) {
        employees.remove(e);
    }

    public Integer getMaxID() {
        Integer max = 0;
        for (Employee employee : employees) {
            if (employee.getId() > max) {
                max = employee.getId();
            }
        }
        return max;
    }

    @Override
    public Iterator<Employee> iterator() {
        return employees.iterator();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Employee e : employees) {
            jsonArray.put(e.toJson());
        }

        jsonObject.put("staff", jsonArray);
        return jsonObject;
    }
}
