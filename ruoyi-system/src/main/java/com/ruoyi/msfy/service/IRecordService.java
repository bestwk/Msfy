package com.ruoyi.msfy.service;


import com.ruoyi.msfy.domain.EchartsVo;
import com.ruoyi.msfy.domain.Record;

import java.util.List;

/**
 * 消费记录 服务层
 * 
 * @author wk
 * @date 2019-06-02
 */
public interface IRecordService 
{
	/**
     * 查询消费记录信息
     * 
     * @param id 消费记录ID
     * @return 消费记录信息
     */
	public Record selectRecordById(Integer id);
	
	/**
     * 查询消费记录列表
     * 
     * @param record 消费记录信息
     * @return 消费记录集合
     */
	public List<Record> selectRecordList(Record record);
	
	/**
     * 新增消费记录
     * 
     * @param record 消费记录信息
     * @return 结果
     */
	public int insertRecord(Record record);
	
	/**
     * 修改消费记录
     * 
     * @param record 消费记录信息
     * @return 结果
     */
	public int updateRecord(Record record);
		
	/**
     * 删除消费记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRecordByIds(String ids);
	/**
	 * @Author wk on 2019/12/6
	 * @Description 获取今日消费饼图
	 */
    List<EchartsVo> getDayPie();
	/**
	 * @Author wk on 2019/12/6
	 * @Description 获取当月消费饼图
	 */
	List<EchartsVo> getMouthPie();
}
