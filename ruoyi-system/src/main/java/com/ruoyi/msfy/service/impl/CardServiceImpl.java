package com.ruoyi.msfy.service.impl;


import com.ruoyi.common.core.text.Convert;
import com.ruoyi.msfy.domain.Card;
import com.ruoyi.msfy.mapper.CardMapper;
import com.ruoyi.msfy.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 办卡记录 服务层实现
 * 
 * @author wk
 * @date 2019-06-02
 */
@Service
public class CardServiceImpl implements ICardService
{
	@Autowired
	private CardMapper cardMapper;

	/**
     * 查询办卡记录信息
     * 
     * @param id 办卡记录ID
     * @return 办卡记录信息
     */
    @Override
	public Card selectCardById(Integer id)
	{
	    return cardMapper.selectCardById(id);
	}
	
	/**
     * 查询办卡记录列表
     * 
     * @param card 办卡记录信息
     * @return 办卡记录集合
     */
	@Override
	public List<Card> selectCardList(Card card)
	{
		List<Card> cards = cardMapper.selectCardList(card);
		for (Card ct : cards) {
			ct.setCardTime(ct.getCardTime().substring(0,10));
		}
	    return cards;
	}
	
    /**
     * 新增办卡记录
     * 
     * @param card 办卡记录信息
     * @return 结果
     */
	@Override
	public int insertCard(Card card)
	{
	    return cardMapper.insertCard(card);
	}
	
	/**
     * 修改办卡记录
     * 
     * @param card 办卡记录信息
     * @return 结果
     */
	@Override
	public int updateCard(Card card)
	{
	    return cardMapper.updateCard(card);
	}

	/**
     * 删除办卡记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCardByIds(String ids)
	{
		return cardMapper.deleteCardByIds(Convert.toStrArray(ids));
	}
	
}
