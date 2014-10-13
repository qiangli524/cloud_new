package com.sitech.basd.yicloud.web.graph.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.sitech.basd.yicloud.service.graph.MxGraphService;
import com.sitech.basd.yicloud.web.graph.form.MxGraphForm;

/**
 * 
 * @author liujhc <liujhc@si-tech.com.cn>
 */
public class MxGraphAction extends ActionSupport {

	private MxGraphService mxGraphService;
	private MxGraphForm mxGraph;
	private List<String> graphIds;
	private List<MxGraphForm> mxGraphList;

	public String saveMxGraph() {
		if (mxGraph.getGraphId() == null || mxGraph.getGraphId().isEmpty()) {
			mxGraph = mxGraphService.insertMxGraph(mxGraph);
		} else {
			mxGraphService.updateMxGraph(mxGraph);
		}
		return SUCCESS;
	}

	public String showMxGraph() {
		mxGraph = mxGraphService.findMxGraph(mxGraph.getGraphId());
		return SUCCESS;
	}

	public String delMxGraph() {
		mxGraphService.deleteMxGraph(graphIds);
		return SUCCESS;
	}

	public String mxGraphEditor() {
		// 如果指定了mxGraph.graphId，编辑器进入修改模式
		if (mxGraph != null && mxGraph.getGraphId() != null
				&& !mxGraph.getGraphId().isEmpty()) {
			mxGraph = mxGraphService.findMxGraph(mxGraph.getGraphId());
		}
		return SUCCESS;
	}

	public String findAllMxGraph() {
		mxGraphList = mxGraphService.findAllMxGraph();
		return SUCCESS;
	}

	public void setMxGraphService(MxGraphService mxGraphService) {
		this.mxGraphService = mxGraphService;
	}

	public MxGraphForm getMxGraph() {
		if (mxGraph == null) {
			mxGraph = new MxGraphForm();
		}
		return mxGraph;
	}

	public List<String> getGraphIds() {
		return graphIds;
	}

	public void setGraphIds(List<String> graphIds) {
		this.graphIds = graphIds;
	}

	public void setG(String graphId) {
		this.getMxGraph().setGraphId(graphId);
	}

	public List<MxGraphForm> getMxGraphList() {
		return mxGraphList;
	}
}
