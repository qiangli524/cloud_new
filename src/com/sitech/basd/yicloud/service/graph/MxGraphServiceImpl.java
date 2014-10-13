package com.sitech.basd.yicloud.service.graph;

import com.sitech.basd.yicloud.dao.graph.TbTopoMxgraphDao;
import com.sitech.basd.yicloud.domain.graph.TbTopoMxgraph;
import com.sitech.basd.yicloud.util.Base62Util;
import com.sitech.basd.yicloud.web.graph.form.MxGraphForm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liujhc <liujhc@si-tech.com.cn>
 */
public class MxGraphServiceImpl implements MxGraphService {

    private TbTopoMxgraphDao mxgraphDao;

    @Override
    public MxGraphForm insertMxGraph(MxGraphForm graph) {
        String graphId = graph.getGraphId();
        if (graphId == null || graphId.isEmpty()) {//如果没有指定graphId，此处生成
            graphId = Base62Util.randomUUID();
            graph.setGraphId(graphId);
        }
        mxgraphDao.insertTopoMxGraph(new TbTopoMxgraph(graph));
        return graph;
    }

    @Override
    public int updateMxGraph(MxGraphForm graph) {
        return mxgraphDao.updateTopoMxGraph(new TbTopoMxgraph(graph));
    }

    @Override
    public int deleteMxGraph(List<String> graphIds) {
        return mxgraphDao.deleteTopoMxGraph(graphIds);
    }

    @Override
    public MxGraphForm findMxGraph(String graphId) {
        return new MxGraphForm(mxgraphDao.findTooMxGraph(graphId));
    }

    @Override
    public List<MxGraphForm> findAllMxGraph() {
        List<TbTopoMxgraph> list = mxgraphDao.findAllTooMxGraph();
        List<MxGraphForm> result = new ArrayList<MxGraphForm>();
        for (TbTopoMxgraph graph : list) {
            result.add(new MxGraphForm(graph));
        }
        return result;
    }

    public void setMxgraphDao(TbTopoMxgraphDao mxgraphDao) {
        this.mxgraphDao = mxgraphDao;
    }
}
