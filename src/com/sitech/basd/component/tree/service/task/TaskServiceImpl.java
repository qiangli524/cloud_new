package com.sitech.basd.component.tree.service.task;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.tree.dao.task.TaskDao;
import com.sitech.basd.component.tree.domain.task.DeployExampleObj;
import com.sitech.basd.component.tree.domain.task.TaskLogObj;
import com.sitech.basd.component.tree.domain.task.TaskObj;
import com.sitech.basd.component.tree.domain.task.TaskRelationObj;

@Service("taskServiceDeploy")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Override
	public List<TaskObj> queryTaskInfoList(TaskObj obj) {
		return taskDao.queryTaskInfoList(obj);
	}

	@Override
	public int insertByObj(TaskObj obj) {
		return taskDao.insertByObj(obj);
	}

	@Override
	public int delByObj(TaskObj obj) {
		return taskDao.delByObj(obj);
	}

	@Override
	public int updateTaskByObj(TaskObj obj) {
		return taskDao.updateTaskByObj(obj);
	}

	@Override
	public int insertRelationByObj(TaskRelationObj obj) {
		return taskDao.insertRelationByObj(obj);
	}

	@Override
	public List<TaskRelationObj> queryRelationTaskInfoList(TaskRelationObj obj) {
		return taskDao.queryRelationTaskInfoList(obj);
	}

	@Override
	public int delRelationByObj(TaskRelationObj obj) {
		return taskDao.delRelationByObj(obj);
	}

	@Override
	public int updateRelationByObj(TaskRelationObj obj) {
		return taskDao.updateRelationByObj(obj);
	}

	@Override
	public List<TaskObj> queryTaskRealtiontOrderList(TaskObj obj) {
		return taskDao.queryTaskRealtiontOrderList(obj);
	}

	@Override
	public int deleteTaskRelationOrderByObj(TaskObj obj) {
		return taskDao.deleteTaskRelationOrderByObj(obj);
	}

	@Override
	public List<TaskObj> queryForListBaseNotInOrderRelation(TaskObj obj) {
		return taskDao.queryForListBaseNotInOrderRelation(obj);
	}

	@Override
	public List<DeployExampleObj> queryExampleAndUserNameByTreeIdList(DeployExampleObj obj) {
		return taskDao.queryExampleAndUserNameByTreeIdList(obj);
	}

	@Override
	public TaskObj queryTaskStatus(TaskObj obj) {
		return taskDao.queryTaskStatus(obj);
	}

	@Override
	public int countTaskNum(TaskObj taskObj) {
		return taskDao.countTaskNum(taskObj);
	}

	@Override
	public List<TaskObj> queryAllMappingsByObj(TaskObj taskObj) {
		// TODO Auto-generated method stub
		return taskDao.queryAllMappingsByObj(taskObj);
	}

	/**
	 * @Title: queryLog
	 * @param obj
	 * @author siweichao
	 * @return int
	 * @version 1.0
	 */
	@Override
	public List<TaskLogObj> queryLog(TaskLogObj obj) {
		return taskDao.queryLog(obj);
	}

	@Override
	public List<DeployExampleObj> queryExampleListNotInTask(DeployExampleObj obj) {
		return taskDao.queryExampleListNotInTask(obj);
	}

	@Override
	public List<DeployExampleObj> queryExampleListInTaskAndComplete(DeployExampleObj obj) {
		return taskDao.queryExampleListInTaskAndComplete(obj);
	}

	/**
	 * 
	 * @Title: queryTaskReportList
	 * @Description: 查询任务报告
	 * @param
	 * @return List<TaskLogObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-20 下午11:03:01
	 */
	@Override
	public List<TaskLogObj> queryTaskReportList(TaskLogObj obj) {
		return taskDao.queryTaskReportList(obj);
	}

	// 导出Excel
	@Override
	public boolean exportExcel(HttpServletResponse response, TaskLogObj obj) {
		List<TaskLogObj> list = taskDao.exportTaskReportList(obj);// 查询导出的数据
		try {
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename=task_report.xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型

			WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
			String tmptitle = "任务报告明显"; // 标题
			WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称
			// 设置excel标题
			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wcfFC = new WritableCellFormat(wfont);
			wcfFC.setBackground(Colour.AQUA);
			wsheet.addCell(new Label(1, 0, tmptitle, wcfFC));
			wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			wcfFC = new WritableCellFormat(wfont);
			// 开始生成主体内容
			wsheet.addCell(new Label(0, 2, "任务编号"));
			wsheet.addCell(new Label(1, 2, "订单编号"));
			wsheet.addCell(new Label(2, 2, "实例编号"));
			wsheet.addCell(new Label(3, 2, "IP地址"));
			wsheet.addCell(new Label(4, 2, "实例名称"));
			wsheet.addCell(new Label(5, 2, "是否成功"));
			wsheet.addCell(new Label(6, 2, "是否完成"));
			wsheet.addCell(new Label(7, 2, "描述"));
			wsheet.addCell(new Label(8, 2, "插入时间"));

			for (int i = 0; i < list.size(); i++) {
				wsheet.addCell(new Label(0, i + 3, list.get(i).getTask_id()));
				wsheet.addCell(new Label(1, i + 3, list.get(i).getOrder_id()));
				wsheet.addCell(new Label(2, i + 3, list.get(i).getExampleID()));
				wsheet.addCell(new Label(3, i + 3, list.get(i).getIP()));
				wsheet.addCell(new Label(4, i + 3, list.get(i).getExampleName()));
				wsheet.addCell(new Label(5, i + 3, list.get(i).getIs_success()));
				wsheet.addCell(new Label(6, i + 3, list.get(i).getIs_complete()));
				wsheet.addCell(new Label(7, i + 3, list.get(i).getExample_log()));
				wsheet.addCell(new Label(8, i + 3, list.get(i).getInsertTime()));
			}
			// 主体内容生成结束
			wbook.write(); // 写入文件
			wbook.close();
			os.close(); // 关闭流
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public TaskLogObj querySuccessCount(TaskLogObj obj) {
		return taskDao.querySuccessCount(obj);
	}

	@Override
	public TaskLogObj queryFailCount(TaskLogObj obj) {
		return taskDao.queryFailCount(obj);
	}

	@Override
	public List<TaskLogObj> querySuccessTaskLog(TaskLogObj obj) {
		// TODO Auto-generated method stub
		return taskDao.querySuccessTaskLog(obj);
	}

	@Override
	public List<TaskLogObj> queryFailTaskLog(TaskLogObj obj) {
		// TODO Auto-generated method stub
		return taskDao.queryFailTaskLog(obj);
	}

	@Override
	public String queryBackupType(TaskObj obj) {
		// TODO Auto-generated method stub
		return taskDao.queryBackupType(obj);
	}
}
