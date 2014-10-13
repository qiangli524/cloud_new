package domain.clusterKpi;

public class ClusterKpiObj {

	
	/*
	 * 集群id
	 */
	private String id;

	/*
	 * 集群名称
	 */
	private String name;

	/*
	 * kpi标识
	 */
	private String kpiid;

	/*
	 * kpi名称
	 */
	private String kpivalue;	
	
	/*
	 * 归属域
	 */
	private String domain;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
		
	public String getName() {
			return name;
	 }
		
	public void setName(String name) {
	  this.name = name;
	}
	
	
	public String getKpiid() {
		return kpiid;
    }
	
   public void setKpiid(String kpiid) {
     this.kpiid = kpiid;
    }
   
   public String getKpiName() {
		return kpivalue;
   }
	
  public void setKpiName(String kpivalue) {
    this.kpivalue= kpivalue;
   }
  
  public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
}
