package com.sitech.basd.yicloud.web.graph.form;

import com.sitech.basd.yicloud.domain.graph.TbTopoMxgraph;
import com.sitech.basd.yicloud.util.JSONUtil;

/**
 *
 * @author liujhc <liujhc@si-tech.com.cn>
 */
public class MxGraphForm {

    private String graphId;
    private String graphName;
    private String graphType;
    private String graphDesc;
    private String graphContent;
    private String dbTime;

    public MxGraphForm() {
    }

    public MxGraphForm(TbTopoMxgraph mxgraph) {
        this.dbTime = mxgraph.getDB_TIME();
        this.graphContent = mxgraph.getGRAPH_CONTENT();
        this.graphDesc = mxgraph.getGRAPH_DESC();
        this.graphId = mxgraph.getGRAPH_ID();
        this.graphName = mxgraph.getGRAPH_NAME();
        this.graphType = mxgraph.getGRAPH_TYPE();
    }

    @Override
    public String toString() {
        return JSONUtil.toJSON(this);
    }

    public String getDbTime() {
        return dbTime;
    }

    public void setDbTime(String dbTime) {
        this.dbTime = dbTime;
    }

    public String getGraphContent() {
        return graphContent;
    }

    public void setGraphContent(String graphContent) {
        this.graphContent = graphContent;
    }

    public String getGraphDesc() {
        return graphDesc;
    }

    public void setGraphDesc(String graphDesc) {
        this.graphDesc = graphDesc;
    }

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }

    public String getGraphName() {
        return graphName;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }

    public String getGraphType() {
        return graphType;
    }

    public void setGraphType(String graphType) {
        this.graphType = graphType;
    }
}
