package com.sitech.basd.yicloud.service.graph;

import com.sitech.basd.yicloud.web.graph.form.MxGraphForm;
import java.util.List;

/**
 *
 * @author liujhc <liujhc@si-tech.com.cn>
 */
public interface MxGraphService {

    MxGraphForm insertMxGraph(MxGraphForm graph);

    int updateMxGraph(MxGraphForm graph);

    int deleteMxGraph(List<String> graphIds);

    MxGraphForm findMxGraph(String graphId);

    List<MxGraphForm> findAllMxGraph();
}
