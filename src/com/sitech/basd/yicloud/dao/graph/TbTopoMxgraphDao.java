package com.sitech.basd.yicloud.dao.graph;

import com.sitech.basd.yicloud.domain.graph.TbTopoMxgraph;
import java.util.List;

/**
 *
 * @author liujhc <liujhc@si-tech.com.cn>
 */
public interface TbTopoMxgraphDao {

    void insertTopoMxGraph(TbTopoMxgraph mxgraph);

    int updateTopoMxGraph(TbTopoMxgraph mxgraph);

    int deleteTopoMxGraph(List<String> graphIds);

    TbTopoMxgraph findTooMxGraph(String graphId);

    List<TbTopoMxgraph> findAllTooMxGraph();

    List<TbTopoMxgraph> findAllTooMxGraphByName(String graphName);
}
