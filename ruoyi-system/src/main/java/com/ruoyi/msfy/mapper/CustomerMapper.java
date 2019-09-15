package com.ruoyi.msfy.mapper;

import com.ruoyi.msfy.domain.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;	

/**
 * 会员 数据层
 * 
 * @author wk
 * @date 2019-05-19
 */
public interface CustomerMapper 
{
	/**
     * 查询会员信息
     * 
     * @param id 会员ID
     * @return 会员信息
     */
	public Customer selectCustomerById(Integer id);
	
	/**
     * 查询会员列表
     * 
     * @param customer 会员信息
     * @return 会员集合
     */
	public List<Customer> selectCustomerList(Customer customer);
	
	/**
     * 新增会员
     * 
     * @param customer 会员信息
     * @return 结果
     */
	public int insertCustomer(Customer customer);
	
	/**
     * 修改会员
     * 
     * @param customer 会员信息
     * @return 结果
     */
	public int updateCustomer(Customer customer);
	
	/**
     * 删除会员
     * 
     * @param id 会员ID
     * @return 结果
     */
	public int deleteCustomerById(Integer id);
	
	/**
     * 批量删除会员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCustomerByIds(String[] ids);

    int importList(List<Customer> list);

	int spendTime(@Param("id")Integer id,@Param("spend") Integer spend);
}