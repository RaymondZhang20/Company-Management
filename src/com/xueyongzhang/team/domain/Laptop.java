package com.xueyongzhang.team.domain;

import com.xueyongzhang.team.domain.Equipment;
import org.json.JSONObject;

public class Laptop implements Equipment {
	private String model;
	private double price;
	
	
	public Laptop() {
		super();
	}


	public Laptop(String model, double price) {
		super();
		this.model = model;
		this.price = price;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public String getDescription() {
		return model + "(" + price + ")";
	}


    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "Laptop");
        jsonObject.put("model", model);
        jsonObject.put("price", price);
        return jsonObject;
    }
}
