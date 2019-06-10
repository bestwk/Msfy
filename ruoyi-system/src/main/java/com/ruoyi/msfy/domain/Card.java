package com.ruoyi.msfy.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 办卡记录表 ms_card
 * 
 * @author wk
 * @date 2019-06-02
 */
public class Card extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 客户id */
	private Integer id;
	/** 姓名 */
	private String name;
	/** 卡号 */
	private String cardId;
	/** 电话 */
	private String phone;
	/** 充值金额 */
	private Integer rechargeAmount;
	/** 充值卡类型(0:次数卡;1:金额卡) */
	private Integer type;
	/** 办卡时间 */
	private String  cardTime;



	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setCardId(String cardId) 
	{
		this.cardId = cardId;
	}

	public String getCardId() 
	{
		return cardId;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setRechargeAmount(Integer rechargeAmount) 
	{
		this.rechargeAmount = rechargeAmount;
	}

	public Integer getRechargeAmount() 
	{
		return rechargeAmount;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}
	public void setCardTime(String cardTime)
	{
		this.cardTime = cardTime;
	}

	public String getCardTime()
	{
		return cardTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("cardId", getCardId())
            .append("phone", getPhone())
            .append("rechargeAmount", getRechargeAmount())
            .append("type", getType())
            .append("cardTime", getCardTime())
            .append("remark", getRemark())
            .toString();
    }
}
