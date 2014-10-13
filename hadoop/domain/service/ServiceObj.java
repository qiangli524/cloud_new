package domain.service;

public class ServiceObj {

	private String id;//id
	private String service_type;//服务类型
	private Integer example_count;//实例个数
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public Integer getExample_count() {
		return example_count;
	}
	public void setExample_count(Integer example_count) {
		this.example_count = example_count;
	}
	
}
