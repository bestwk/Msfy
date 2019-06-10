package com.ruoyi.msfy.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会员表 ms_customer
 * 
 * @author wk
 * @date 2019-05-19
 */
public class Customer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 客户id */

	private Integer id;
	/** 姓名 */
	@Excel(name = "姓名")
	private String name;
	/** 卡号 */
	@Excel(name = "卡号")
	private String cardId;
	/** 电话 */
	@Excel(name = "电话")
	private String phone;
	/** 充值金额 */
	@Excel(name = "充值金额")
	private Integer rechargeAmount;
	/** 充值卡类型(0:次数卡;1:金额卡) */
	@Excel(name = "类型", readConverterExp = "0=次数,1=金额")
	private Integer type;
	/**办卡时间  */
	@Excel(name = "办卡时间", dateFormat = "yyyy-MM-dd")
	private String cardTime;
	/** 剩余次数/剩余金额 */
	@Excel(name = "余次/余额")
	private Integer timeMoney;
	/** 备注说明*/
	@Excel(name = "备注",defaultValue = "无")
	private String remark;

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

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
	public void setTimeMoney(Integer timeMoney) 
	{
		this.timeMoney = timeMoney;
	}

	public Integer getTimeMoney() 
	{
		return timeMoney;
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
            .append("timeMoney", getTimeMoney())
            .append("remark", getRemark())
            .toString();
    }
}
