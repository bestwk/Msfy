package com.ruoyi.msfy.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.msfy.domain.Record;
import com.ruoyi.msfy.mapper.RecordMapper;
import com.ruoyi.msfy.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消费记录 服务层实现
 * 
 * @author wk
 * @date 2019-06-02
 */
@Service
public class RecordServiceImpl implements IRecordService 
{
	@Autowired
	private RecordMapper recordMapper;

	/**
     * 查询消费记录信息
     * 
     * @param id 消费记录ID
     * @return 消费记录信息
     */
    @Override
	public Record selectRecordById(Integer id)
	{
	    return recordMapper.selectRecordById(id);
	}
	
	/**
     * 查询消费记录列表
     * 
     * @param record 消费记录信息
     * @return 消费记录集合
     */
	@Override
	public List<Record> selectRecordList(Record record)
	{
	    return recordMapper.selectRecordList(record);
	}
	
    /**
     * 新增消费记录
     * 
     * @param record 消费记录信息
     * @return 结果
     */
	@Override
	public int insertRecord(Record record)
	{
	    return recordMapper.insertRecord(record);
	}
	
	/**
     * 修改消费记录
     * 
     * @param record 消费记录信息
     * @return 结果
     */
	@Override
	public int updateRecord(Record record)
	{
	    return recordMapper.updateRecord(record);
	}

	/**
     * 删除消费记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRecordByIds(String ids)
	{
		return recordMapper.deleteRecordByIds(Convert.toStrArray(ids));
	}
	
}
