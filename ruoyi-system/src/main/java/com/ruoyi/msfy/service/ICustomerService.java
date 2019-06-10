package com.ruoyi.msfy.service;


import com.ruoyi.msfy.domain.Customer;

import java.util.List;

/**
 * 会员 服务层
 * 
 * @author wk
 * @date 2019-05-19
 */
public interface ICustomerService 
{
	/**
     * 查询会员信息
     * 
     * @param id 会员ID
     * @return 会员信息
     */
	 Customer selectCustomerById(Integer id);
	
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
     * 删除会员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCustomerByIds(String ids);

	/**
	 * 导入客户
	 *
	 *
	 * @return 结果
	 */
	String importCustomer(List<Customer> list);
}
