package com.sitech.basd.yicloud.dao.graph;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.yicloud.domain.graph.TbTopoMxgraph;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author liujhc <liujhc@si-tech.com.cn>
 */
public class TbTopoMxgraphDaoImpl extends BaseDao implements TbTopoMxgraphDao {

    @Override
    public void insertTopoMxGraph(TbTopoMxgraph mxgraph) {
        try {
			getSqlMap().insert("TB_TOPO_MXGRAPH.insertTopoMxGraph", mxgraph);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public int updateTopoMxGraph(TbTopoMxgraph mxgraph) {
    	int ret = 0;
        try {
			ret=getSqlMap().update("TB_TOPO_MXGRAPH.updateTopoMxGraph", mxgraph);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
    }

    @Override
    public int deleteTopoMxGraph(List<String> graphIds) {
    	int ret = 0;
        try {
			ret=getSqlMap().delete("TB_TOPO_MXGRAPH.deleteTopoMxGraph", graphIds.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
    }

    @Override
    public TbTopoMxgraph findTooMxGraph(String graphId) {
    	TbTopoMxgraph tbt = new TbTopoMxgraph();
        try {
        	tbt=(TbTopoMxgraph)getSqlMap().queryForObject("TB_TOPO_MXGRAPH.findTooMxGraph", graphId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tbt;
    }

    @Override
    public List<TbTopoMxgraph> findAllTooMxGraph() {
    	List<TbTopoMxgraph> lst = null;
        try {
			lst=getSqlMap().queryForList("TB_TOPO_MXGRAPH.findAllTooMxGraph");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
    }

    @Override
    public List<TbTopoMxgraph> findAllTooMxGraphByName(String graphName) {
    	List<TbTopoMxgraph> lst = null;
        try {
			lst=getSqlMap().queryForList("TB_TOPO_MXGRAPH.findAllTooMxGraphByName", graphName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
    }
}
