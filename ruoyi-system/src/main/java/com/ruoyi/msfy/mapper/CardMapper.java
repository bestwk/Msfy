package com.ruoyi.msfy.mapper;

import com.ruoyi.msfy.domain.Card;
import java.util.List;	

/**
 * 办卡记录 数据层
 * 
 * @author wk
 * @date 2019-06-02
 */
public interface CardMapper 
{
	/**
     * 查询办卡记录信息
     * 
     * @param id 办卡记录ID
     * @return 办卡记录信息
     */
	public Card selectCardById(Integer id);
	
	/**
     * 查询办卡记录列表
     * 
     * @param card 办卡记录信息
     * @return 办卡记录集合
     */
	public List<Card> selectCardList(Card card);
	
	/**
     * 新增办卡记录
     * 
     * @param card 办卡记录信息
     * @return 结果
     */
	public int insertCard(Card card);
	
	/**
     * 修改办卡记录
     * 
     * @param card 办卡记录信息
     * @return 结果
     */
	public int updateCard(Card card);
	
	/**
     * 删除办卡记录
     * 
     * @param id 办卡记录ID
     * @return 结果
     */
	public int deleteCardById(Integer id);
	
	/**
     * 批量删除办卡记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCardByIds(String[] ids);
	
}