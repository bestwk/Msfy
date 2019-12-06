package com.ruoyi.web.controller.msfy;

import com.ruoyi.common.core.domain.ResponseData;
import com.ruoyi.msfy.domain.EchartsVo;
import com.ruoyi.msfy.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created By wk
 * Date: 2019/12/5
 */
@RequestMapping("/msfy/main")
@RestController
public class MainController {

    private String prefix = "msfy/main";

    @Autowired
    private IRecordService recordService;

    @GetMapping("/today/getPie")
    @ResponseBody
    public ResponseData getPie() {
        List<EchartsVo> list = recordService.getDayPie();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(new Date());

        return new ResponseData(true,list);
    }

    @GetMapping("/mouth/getPie")
    @ResponseBody
    public ResponseData getMouthPie() {
        List<EchartsVo> list = recordService.getMouthPie();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(new Date());

        return new ResponseData(true,list);
    }



}
