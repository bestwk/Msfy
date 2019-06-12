package com.ruoyi.msfy.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 消费记录表 ms_record
 * 
 * @author wk
 * @date 2019-06-02
 */
public class Record extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/**  */
	private String name;
	/** 卡号 */
	private String cardId;
	/** 手机号 */
	private String phone;
	/** 消费时间 */
	private String time;
	/** 剩余次数 */
	private Integer timeMoney;
	/**消费说明*/
	private String remark;


	public Integer getTimeMoney() {
		return timeMoney;
	}

	public void setTimeMoney(Integer timeMoney) {
		this.timeMoney = timeMoney;
	}

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
	public void setTime(String time)
	{
		this.time = time;
	}

	public String getTime()
	{
		return time;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("cardId", getCardId())
            .append("phone", getPhone())
            .append("time", getTime())
            .append("remark", getRemark())
            .toString();
    }
}
