package com.sitech.basd.yicloud.domain.graph;

import com.sitech.basd.yicloud.web.graph.form.MxGraphForm;

/**
 *
 * @author liujhc <liujhc@si-tech.com.cn>
 */
public class TbTopoMxgraph {

    private String GRAPH_ID;
    private String GRAPH_NAME;
    private String GRAPH_TYPE;
    private String GRAPH_DESC;
    private String GRAPH_CONTENT;
    private String DB_TIME;

    public TbTopoMxgraph() {
    }

    public TbTopoMxgraph(MxGraphForm form) {
        this.DB_TIME = form.getDbTime();
        this.GRAPH_CONTENT = form.getGraphContent();
        this.GRAPH_DESC = form.getGraphDesc();
        this.GRAPH_ID = form.getGraphId();
        this.GRAPH_NAME = form.getGraphName();
        this.GRAPH_TYPE = form.getGraphType();
    }

    public String getDB_TIME() {
        return DB_TIME;
    }

    public void setDB_TIME(String DB_TIME) {
        this.DB_TIME = DB_TIME;
    }

    public String getGRAPH_CONTENT() {
        return GRAPH_CONTENT;
    }

    public void setGRAPH_CONTENT(String GRAPH_CONTENT) {
        this.GRAPH_CONTENT = GRAPH_CONTENT;
    }

    public String getGRAPH_DESC() {
        return GRAPH_DESC;
    }

    public void setGRAPH_DESC(String GRAPH_DESC) {
        this.GRAPH_DESC = GRAPH_DESC;
    }

    public String getGRAPH_ID() {
        return GRAPH_ID;
    }

    public void setGRAPH_ID(String GRAPH_ID) {
        this.GRAPH_ID = GRAPH_ID;
    }

    public String getGRAPH_NAME() {
        return GRAPH_NAME;
    }

    public void setGRAPH_NAME(String GRAPH_NAME) {
        this.GRAPH_NAME = GRAPH_NAME;
    }

    public String getGRAPH_TYPE() {
        return GRAPH_TYPE;
    }

    public void setGRAPH_TYPE(String GRAPH_TYPE) {
        this.GRAPH_TYPE = GRAPH_TYPE;
    }
}
