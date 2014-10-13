package com.sitech.shop.webservice.dao;


import com.sitech.shop.webservice.domain.TbOrderRelationInstance;
import com.sitech.shop.webservice.domain.TbOrderRelationInstanceExample;

import java.util.List;

public interface TbOrderRelationInstanceDAO {
    /**
     * 根据条件查询记录数
     *
     * @param example 条件对象
     * @return 记录数
     */
    int countByExample(TbOrderRelationInstanceExample example);

    /**
     * 根据条件删除记录
     *
     * @param example 条件对象
     * @return 删除记录数
     */
    int deleteByExample(TbOrderRelationInstanceExample example);

    /**
     * 根据主键删除记录
     *
     * @param uuid 主键
     * @return 删除记录数
     */
    int deleteByPrimaryKey(String uuid);


    /**
     * 选择性插入记录
     *
     * @param record 内容对象
     */
    void insertSelective(TbOrderRelationInstance record);

    /**
     * 根据条件查询记录
     *
     * @param example 条件对象
     * @return 结果集
     */
    List<TbOrderRelationInstance> selectByExample(TbOrderRelationInstanceExample example);
    
    /**
     * 分页查询记录
     * @param example 条件对象
     * @return
     */
    List<TbOrderRelationInstance> selectByExamplePage(TbOrderRelationInstanceExample example);
    
    /**
     * 根据主键查询对象
     *
     * @param uuid 主键
     * @return 结果对象
     */
    TbOrderRelationInstance selectByPrimaryKey(String uuid);

    /**
     * 根据条件更新记录
     *
     * @param record  欲更新的对象
     * @param example 条件对象
     * @return 更新记录数
     */
    int updateByExampleSelective(TbOrderRelationInstance record, TbOrderRelationInstanceExample example);

    /**
     * 根据主键更新记录
     *
     * @param record 欲更新的对象
     * @return 更新记录数
     */
    int updateByPrimaryKeySelective(TbOrderRelationInstance record);


}