package domain.metries;

import java.util.List;

public class HadoopMetriesObj {

	private String entity_id;//
	private String type;//
	private String metries_id;//
	private String metries_value;//
	private String last_update_time;//
	
	private List<String> entityIdList;//
	
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
	public String getMetries_id() {
		return metries_id;
	}
	public void setMetries_id(String metries_id) {
		this.metries_id = metries_id;
	}
	public String getMetries_value() {
		return metries_value;
	}
	public void setMetries_value(String metries_value) {
		this.metries_value = metries_value;
	}
	public String getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}
	public List<String> getEntityIdList() {
		return entityIdList;
	}
	public void setEntityIdList(List<String> entityIdList) {
		this.entityIdList = entityIdList;
	}
	
}
