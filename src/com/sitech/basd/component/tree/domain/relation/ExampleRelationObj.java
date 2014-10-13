package com.sitech.basd.component.tree.domain.relation;

public class ExampleRelationObj {
	private String example_id;
	private String entity_id;
	private String type;
	private int PAGESIZE;

	public int getPAGESIZE() {
		return PAGESIZE;
	}

	public void setPAGESIZE(int pagesize) {
		PAGESIZE = pagesize;
	}

	public String getExample_id() {
		return example_id;
	}

	public void setExample_id(String example_id) {
		this.example_id = example_id;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
