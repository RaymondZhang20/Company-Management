package com.xueyongzhang.team.domain;

import com.xueyongzhang.team.domain.Equipment;
import com.xueyongzhang.team.persistence.Writable;
import org.json.JSONObject;

public class PC implements Equipment {

	private String model;
	private String display;
	
	

	public PC() {
		super();
	}

	public PC(String model, String display) {
		super();
		this.model = model;
		this.display = display;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
	@Override
	public String getDescription() {
		return model + "(" + display + ")";
	}

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "PC");
        jsonObject.put("model", model);
        jsonObject.put("display", display);
        return jsonObject;
    }
}
