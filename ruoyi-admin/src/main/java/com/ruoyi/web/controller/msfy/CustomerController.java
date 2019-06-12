package com.ruoyi.web.controller.msfy;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.msfy.domain.Card;
import com.ruoyi.msfy.domain.Customer;
import com.ruoyi.msfy.domain.Record;
import com.ruoyi.msfy.service.ICardService;
import com.ruoyi.msfy.service.ICustomerService;
import com.ruoyi.msfy.service.IRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 会员 信息操作处理
 * 
 * @author wk
 * @date 2019-05-19
 */
@Controller
@RequestMapping("/msfy/customer")
public class CustomerController extends BaseController
{
    private String prefix = "msfy/customer";
	
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IRecordService recordService;

	@Autowired
	private ICardService cardService;
	
	@RequiresPermissions("msfy:customer:view")
	@GetMapping()
	public String customer()
	{
	    return prefix + "/customer";
	}
	
	/**
	 * 查询会员列表
	 */
	@RequiresPermissions("msfy:customer:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Customer customer)
	{
		startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
		return getDataTable(list);
	}

	@RequiresPermissions("msfy:customer:pay")
	@Log(title = "会员续费", businessType = BusinessType.UPDATE)
	@GetMapping("/pay/{id}")
	public String pay(@PathVariable("id") Integer Id, ModelMap mmap)
	{
		mmap.put("customer", customerService.selectCustomerById(Id));
		return prefix + "/pay";
	}


	@Log(title = "查看记录", businessType = BusinessType.UPDATE)
	@GetMapping("/lookDetail/{cardId}")
	public String lookDetail(@PathVariable("cardId") String cardId, ModelMap mmap)
	{
		Record record = new Record();
		record.setCardId(cardId);
		mmap.put("record", record);
		return "msfy/record/record";
	}
	@Transactional
	@RequiresPermissions("msfy:customer:pay")
	@Log(title = "会员续费", businessType = BusinessType.UPDATE)
	@GetMapping("/pay")
	@ResponseBody
	public AjaxResult paySave(@RequestParam("id") Integer id,
							  @RequestParam("rechargeAmount") Integer rechargeAmount,
							  @RequestParam("timeMoney") Integer timeMoney)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		AjaxResult ajaxResult = null;
		Customer customer = customerService.selectCustomerById(id);
		customer.setRechargeAmount(rechargeAmount);
		customer.setTimeMoney(customer.getTimeMoney() + timeMoney);
		int i = customerService.updateCustomer(customer);
		if(i > 0 ){
			Card card = new Card();
			BeanUtils.copyBeanProp(card,customer);
			card.setCardTime(simpleDateFormat.format(new Date()));
			int i1 = cardService.insertCard(card);
			if(i1 > 0 ){
				ajaxResult = new AjaxResult(AjaxResult.Type.SUCCESS, "操作成功");
				ajaxResult.setCode(0);
			}else{
				ajaxResult = new AjaxResult(AjaxResult.Type.SUCCESS, "插入记录失败");
				ajaxResult.setCode(500);
			}

		}else{
			ajaxResult.setType(AjaxResult.Type.ERROR);
			ajaxResult.setCode(500);
			ajaxResult.setMsg("操作失败");
		}
		return ajaxResult;
	}

	public static void main(String[] args) {
		Customer customer = new Customer();
		customer.setRechargeAmount(100);
		customer.setType(0);
		customer.setTimeMoney(8);
		customer.setCardTime("2200-12-33");
		customer.setCardId("234623742");
		customer.setId(1);
		customer.setPhone("1273612");
		customer.setName("111");
		customer.setRemark("000");
		Card card = new Card();
		BeanUtils.copyBeanProp(card,customer);
		System.out.println(card.toString());
	}


	@RequiresPermissions("msfy:customer:spend")
	@Log(title = "会员消费", businessType = BusinessType.UPDATE)
	@GetMapping("/spend/{id}")
	public String spend(@PathVariable("id") Integer Id, ModelMap mmap)
	{
		mmap.put("customer", customerService.selectCustomerById(Id));
		return prefix + "/spend";
	}


	@Log(title = "会员消费", businessType = BusinessType.UPDATE)
	@GetMapping("/spend1/{id}")
	public String spend1(@PathVariable("id") Integer Id, ModelMap mmap)
	{
		mmap.put("customer", customerService.selectCustomerById(Id));
		return prefix + "/spend1";
	}
	@Transactional
	@RequiresPermissions("msfy:customer:spend")
	@Log(title = "会员消费", businessType = BusinessType.UPDATE)
	@GetMapping("/spend")
	@ResponseBody
	public AjaxResult spendSave(@RequestParam("id") Integer id,@RequestParam("spend") Integer spend,@RequestParam("remark")Integer remark)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		AjaxResult ajaxResult = null;
		Customer customer = customerService.selectCustomerById(id);
		customer.setTimeMoney(customer.getTimeMoney() - spend);
		int i = customerService.updateCustomer(customer);
		String remak = "";
		switch(remark){
			case 0:remak = "剪发";break;
			case 1:remak = "染发";break;
			case 2:remak = "烫发";break;
			case 3:remak = "洗头";break;
		}
		if(i > 0 ){
			Record record = new Record();
			record.setCardId(customer.getCardId());
			record.setName(customer.getName());
			record.setPhone(customer.getPhone());
			record.setTime(simpleDateFormat.format(new Date()));
			if(spend > 1){
				record.setRemark("金额卡消费"+spend+"元"+"("+remak+")");
			}else{
				record.setRemark("次卡消费一次"+"("+remak+")");
			}
			int i1 = recordService.insertRecord(record);

			if(i1 > 0 ){
				ajaxResult = new AjaxResult(AjaxResult.Type.SUCCESS, "操作成功");
				ajaxResult.setCode(0);
			}else{
				ajaxResult = new AjaxResult(AjaxResult.Type.SUCCESS, "消费记录插入失败");
				ajaxResult.setCode(500);
			}

		}else{
		ajaxResult.setType(AjaxResult.Type.ERROR);
		ajaxResult.setCode(500);
		ajaxResult.setMsg("操作失败");
		}
		return ajaxResult;
	}
	
	
	/**
	 * 导出会员列表
	 */
	@RequiresPermissions("msfy:customer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Customer customer)
    {
    	List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        return util.exportExcel(list, "customer");
    }

	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception
	{
		ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
		List<Customer> list = util.importExcel(file.getInputStream());

		String message = customerService.importCustomer(list);
		return AjaxResult.success(message);
	}
	
	/**
	 * 新增会员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员
	 */
	@RequiresPermissions("msfy:customer:add")
	@Log(title = "会员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Customer customer)
	{		

		return toAjax(customerService.insertCustomer(customer));
	}

	/**
	 * 修改会员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Customer customer = customerService.selectCustomerById(id);
		mmap.put("customer", customer);

	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员
	 */
	@RequiresPermissions("msfy:customer:edit")
	@Log(title = "会员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Customer customer)
	{		
		return toAjax(customerService.updateCustomer(customer));
	}
	
	/**
	 * 删除会员
	 */
	@RequiresPermissions("msfy:customer:remove")
	@Log(title = "会员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(customerService.deleteCustomerByIds(ids));
	}
	
}
