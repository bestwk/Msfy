package com.ruoyi.web.controller.msfy;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.msfy.domain.Card;
import com.ruoyi.msfy.service.ICardService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 办卡记录 信息操作处理
 * 
 * @author wk
 * @date 2019-06-02
 */
@Controller
@RequestMapping("/msfy/card")
public class CardController extends BaseController
{
    private String prefix = "msfy/card";
	
	@Autowired
	private ICardService cardService;
	
	@RequiresPermissions("msfy:card:view")
	@GetMapping()
	public String card()
	{
	    return prefix + "/card";
	}
	
	/**
	 * 查询办卡记录列表
	 */
	@RequiresPermissions("msfy:card:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Card card)
	{
		startPage();
        List<Card> list = cardService.selectCardList(card);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出办卡记录列表
	 */
	@RequiresPermissions("msfy:card:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Card card)
    {
    	List<Card> list = cardService.selectCardList(card);
        ExcelUtil<Card> util = new ExcelUtil<Card>(Card.class);
        return util.exportExcel(list, "card");
    }
	
	/**
	 * 新增办卡记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存办卡记录
	 */
	@RequiresPermissions("msfy:card:add")
	@Log(title = "办卡记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Card card)
	{		
		return toAjax(cardService.insertCard(card));
	}

	/**
	 * 修改办卡记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Card card = cardService.selectCardById(id);
		mmap.put("card", card);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存办卡记录
	 */
	@RequiresPermissions("msfy:card:edit")
	@Log(title = "办卡记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Card card)
	{		
		return toAjax(cardService.updateCard(card));
	}
	
	/**
	 * 删除办卡记录
	 */
	@RequiresPermissions("msfy:card:remove")
	@Log(title = "办卡记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(cardService.deleteCardByIds(ids));
	}
	
}
