package com.ruoyi.msfy.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.msfy.domain.Card;
import com.ruoyi.msfy.domain.Customer;
import com.ruoyi.msfy.domain.Record;
import com.ruoyi.msfy.mapper.CardMapper;
import com.ruoyi.msfy.mapper.CustomerMapper;
import com.ruoyi.msfy.mapper.RecordMapper;
import com.ruoyi.msfy.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 会员 服务层实现
 * 
 * @author wk
 * @date 2019-05-19
 */
@Service
public class CustomerServiceImpl implements ICustomerService 
{
	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private CardMapper cardMapper;

	@Autowired
	private RecordMapper recordMapper;

	/**
     * 查询会员信息
     * 
     * @param id 会员ID
     * @return 会员信息
     */
    @Override
	public Customer selectCustomerById(Integer id)
	{
	    return customerMapper.selectCustomerById(id);
	}
	
	/**
     * 查询会员列表
     * 
     * @param customer 会员信息
     * @return 会员集合
     */
	@Override
	public List<Customer> selectCustomerList(Customer customer)
	{
		List<Customer> customers = customerMapper.selectCustomerList(customer);
		for (Customer ct : customers) {
			ct.setCardTime(ct.getCardTime().substring(0,10));
		}

		return customers;
	}
	
    /**
     * 新增会员
     * 
     * @param customer 会员信息
     * @return 结果
     */
	@Override
	public int insertCustomer(Customer customer)
	{

		//生成八位随机数
		int random=(int) ((Math.random()+1)*10000000);
		customer.setCardId(random+"");
		//获取当前时间戳
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String temp = sf.format(new Date());
		customer.setCardTime(temp);
		int i = customerMapper.insertCustomer(customer);
		if( i > 0){
			Card card = new Card();
			BeanUtils.copyBeanProp(card,customer);

			return cardMapper.insertCard(card);
		}
		return 0;
	}

	@Override
	@Transactional
	public String importCustomer(List<Customer> list) {
		if (StringUtils.isNull(list) || list.size() == 0){
			throw new BusinessException("导入用户数据不能为空！");
		}
		ArrayList<Customer> list1  = new ArrayList<>();
		for (Customer customer : list) {
			if(customer.getName() == "" || customer.getName().isEmpty() || customer.getName() == null){
				continue;
			}
			if( customer.getCardId() == null || customer.getCardId().length() == 0){
				//生成八位随机数
				int random=(int) ((Math.random()+1)*10000000);
				customer.setCardId(random+"");
			}
			//获取当前时间戳
			if( customer.getCardTime() != null && customer.getCardTime().length() != 0){
				String x = customer.getCardTime();
				SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
				try
				{
					Date date=sdf1.parse(x);
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					String sDate=sdf.format(date);
					customer.setCardTime(sDate);
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}
			}else{
				//获取当前时间戳
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String temp = sf.format(new Date());
				customer.setCardTime(temp);
			}
			list1.add(customer);
		}
		int i = customerMapper.importList(list1);
		if(i > 0 ){
			return "导入成功";
		}

		return "导入失败";
	}

	/**
     * 修改会员
     * 
     * @param customer 会员信息
     * @return 结果
     */
	@Override
	public int updateCustomer(Customer customer)
	{
	    return customerMapper.updateCustomer(customer);
	}

	/**
     * 删除会员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCustomerByIds(String ids)
	{
		return customerMapper.deleteCustomerByIds(Convert.toStrArray(ids));
	}
	/**
	 * 会员消费
	 * @return 结果
	 */
	@Override
	public int spendTime(Integer id, Integer spend) {
		return customerMapper.spendTime(id,spend);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public AjaxResult xiaofei(Integer id, Integer spend, Integer remark) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			AjaxResult ajaxResult = null;
			int i = customerMapper.spendTime(id,spend);
//		if(i > 0 ){
			String remak = "";
			switch(remark){
				case 0:remak = "剪发";break;
				case 1:remak = "染发";break;
				case 2:remak = "烫发";break;
				case 3:remak = "洗头";break;
			}
			Customer customer = customerMapper.selectCustomerById(id);
			Record record = new Record();
			record.setCardId(customer.getCardId());
			record.setName(customer.getName());
			record.setPhone(customer.getPhone());
			record.setType(remak);
			record.setTime(simpleDateFormat.format(new Date()));
			if(spend > 1){
				record.setRemark("金额卡消费"+spend+"元"+"("+remak+")");
			}else{
				record.setRemark("次卡消费一次"+"("+remak+")");
			}
			record.setTimeMoney(customer.getTimeMoney());
			int i1 = recordMapper.insertRecord(record);

			if(i1 > 0 && i > 0){
				ajaxResult = new AjaxResult(AjaxResult.Type.SUCCESS, "操作成功");
				ajaxResult.setCode(0);
			}else{
				ajaxResult = new AjaxResult(AjaxResult.Type.SUCCESS, "消费记录插入失败");
				ajaxResult.setCode(500);
			}
			return ajaxResult;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new AjaxResult(AjaxResult.Type.ERROR, "操作失败");
		}
	}
}
