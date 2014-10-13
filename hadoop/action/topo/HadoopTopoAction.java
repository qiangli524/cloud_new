package action.topo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import service.tree.HadoopTreeService;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.servlet.PrintWriterOut;

import dao.tree.HadoopTreeDao;
import domain.tree.HadoopTreeObj;
import domain.tree.TopoObj;

@Component("hadoopTopoAction")
@Scope("prototype")
public class HadoopTopoAction extends BaseAction {
	private List resultList;
	@Autowired
	private HadoopTreeService hadoopTreeService;
	@Autowired
	private HadoopTreeDao hadoopTreeDao;
	private TopoObj obj;
	private HadoopTreeObj tree;

	/**
	 * 
	 * @Title: list
	 * @Description: 进入生成拓扑图页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-6 上午11:36:12
	 */
	public String list() {
		return "list";
	}

	/**
	 * 
	 * @Title: generateTopo
	 * @Description: 生成拓扑图
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-1-6 上午11:47:55
	 */
	public String generateTopo() throws SQLException {
		if (obj == null) {
			obj = new TopoObj();
		}
		if (tree == null) {
			tree = new HadoopTreeObj();
		}
		HadoopTreeObj ha = new HadoopTreeObj();
		ha = hadoopTreeService.recursiveTree(tree.getId(), hadoopTreeDao);
		if (obj.getList() == null) {
			obj.setList(new ArrayList<HadoopTreeObj>());
		}
		obj.getList().add(ha);
		String result = JacksonUtil.toJson(obj);
		printJson(result.replace("null", "\"\""));
		return null;
	}

	/**
	 * 
	 * @Title: printJson
	 * @Description: 将null转化为""
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-7 下午4:28:48
	 */
	private synchronized void printJson(String... params) {
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				System.out.println(params[i]);
				// out.println(params[i]);
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
		// out.close();
	}
	
	/**
	 * 
	 * @Title: newTopo
	 * @Description:跳转到拓扑页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 28, 2014 10:28:15 AM
	 */
	public String newTopo(){
		return "newTopo";
	}
	
	/**
	 * 
	 * @Title: getFlexData
	 * @Description: 获取flex拓扑图数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 28, 2014 10:27:22 AM
	 */
	public String getFlexData(){
		String currentNodeId = request.getParameter("currentNodeId");
		String showType = request.getParameter("showType");
		String data = hadoopTreeService.queryTopoData(currentNodeId,showType);
		PrintWriterOut.printWirter(response, data);
		return null;
	}

	public HadoopTreeObj getTree() {
		return tree;
	}

	public void setTree(HadoopTreeObj tree) {
		this.tree = tree;
	}

	public TopoObj getObj() {
		return obj;
	}

	public void setObj(TopoObj obj) {
		this.obj = obj;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

}
