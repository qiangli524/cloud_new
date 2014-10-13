package action.directoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import service.directoryType.DirectoryTypeService;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.util.systemlog.MethodLog;

import domain.directoryType.DirectoryTypeObj;

/**
 * @author chenyu
 * @description 数据字典类型维护action
 * @date 2014-5-14
 */
@Component("directoryTypeAction")
@Scope("prototype")
public class DirectoryTypeAction extends BaseAction {
	
	private static final Logger log = LoggerFactory.getLogger(DirectoryTypeAction.class);

	@Autowired
	private DirectoryTypeService directoryTypeService;
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4489649922203249713L;
	
	/**
	 * 
	 * @Title: list
	 * @Description: 列表查询
	 * @param
	 * @return String
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-5-20 下午5:30:46
	 */
	@MethodLog(remark="DirectoryTypeAction-list",type=4,message="查询数据字典类型")
	public String list(){
		List<DirectoryTypeObj> directoryTypeObjs;
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			directoryTypeObjs = directoryTypeService.queryForList(new DirectoryTypeObj());
		} catch (SQLException e) {
			directoryTypeObjs = new ArrayList<DirectoryTypeObj>();
			request.setAttribute("errorMsg",e.getMessage());
		}
		request.setAttribute("directoryTypeList", directoryTypeObjs);
		return "directoryTypeList";
	}
	
	public String toEiit() throws SQLException{
		String id = request.getParameter("id");
		DirectoryTypeObj typeObj = new DirectoryTypeObj();
		if ("edit".equals(request.getParameter("operation"))) {
			typeObj.setId(id);
			List<DirectoryTypeObj> list = directoryTypeService.queryForList(typeObj);
			if (null != list && list.size() > 0) {
				typeObj = list.get(0);
			}
		}
		request.setAttribute("typeObj", typeObj);
		return "directoryTypeToEdit";
	}
	
	public String save() throws SQLException {
		String id = getParameter("id");
		String code = getParameter("code");
		String name = getParameter("name");
		String description = getParameter("description");
		DirectoryTypeObj typeObj = new DirectoryTypeObj();
		typeObj.setCode(code);
		typeObj.setName(name);
		typeObj.setDescription(description);
		if(null==id||"".equals(id.trim())){
			directoryTypeService.insertByObj(typeObj);
		} else {
			typeObj.setId(id);
			directoryTypeService.updateByObj(typeObj);
		}
		return "directoryTypeSaved";
	}
	
	public String delete() throws SQLException{
		String id = getParameter("id");
		if(null==id||"".equals(id.trim())){
			DirectoryTypeObj typeObj = new DirectoryTypeObj();
			typeObj.setId(id);
			directoryTypeService.deleteByObj(typeObj);
		}
		return list();
	}
	
	private String getParameter(String fieldName){
		return request.getParameter(fieldName);
	}
	

}
