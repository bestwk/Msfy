package com.ruoyi.web.controller.msfy;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.msfy.domain.Record;
import com.ruoyi.msfy.service.IRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消费记录 信息操作处理
 * 
 * @author wk
 * @date 2019-06-02
 */
@Controller
@RequestMapping("/msfy/record")
public class RecordController extends BaseController
{
    private String prefix = "msfy/record";
	
	@Autowired
	private IRecordService recordService;
	
	@RequiresPermissions("msfy:record:view")
	@GetMapping()
	public String record()
	{
	    return prefix + "/record";
	}
	
	/**
	 * 查询消费记录列表
	 */
	@RequiresPermissions("msfy:record:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Record record)
	{
		startPage();
        List<Record> list = recordService.selectRecordList(record);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出消费记录列表
	 */
	@RequiresPermissions("msfy:record:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Record record)
    {
    	List<Record> list = recordService.selectRecordList(record);
        ExcelUtil<Record> util = new ExcelUtil<Record>(Record.class);
        return util.exportExcel(list, "record");
    }
	
	/**
	 * 新增消费记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存消费记录
	 */
	@RequiresPermissions("msfy:record:add")
	@Log(title = "消费记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Record record)
	{		
		return toAjax(recordService.insertRecord(record));
	}

	/**
	 * 修改消费记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Record record = recordService.selectRecordById(id);
		mmap.put("record", record);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存消费记录
	 */
	@RequiresPermissions("msfy:record:edit")
	@Log(title = "消费记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Record record)
	{		
		return toAjax(recordService.updateRecord(record));
	}
	
	/**
	 * 删除消费记录
	 */
	@RequiresPermissions("msfy:record:remove")
	@Log(title = "消费记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(recordService.deleteRecordByIds(ids));
	}
	
}
