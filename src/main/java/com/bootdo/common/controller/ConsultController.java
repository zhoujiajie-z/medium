package com.bootdo.common.controller;

import com.bootdo.common.domain.ConsultDO;
import com.bootdo.common.service.ConsultService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common/consult")
public class ConsultController extends BaseController {

    @Autowired
    private ConsultService consultService;


    @GetMapping()
    String consult(Model model) {
        Map<String, Object> params = new HashMap<>(16);
        return "common/consult/consult";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<ConsultDO> consultList = consultService.list(query);
        int total = consultService.count(query);
        PageUtils pageUtils = new PageUtils(consultList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "common/consult/add";
    }

    @GetMapping("/edit")
    String edit(Long id, Model model) {
        ConsultDO consult = consultService.get(id);
        model.addAttribute("consult", consult);
        return "common/consult/edit";
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        ConsultDO consult = consultService.get(id);
        if (consult != null) {
        }
        consult.setReaded(consult.getReaded() + 1);
        if (consultService.update(consult) > 1) {
            return R.ok().put("consult", consult);
        }
        return R.error("点赞失败");
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(ConsultDO consult) throws Exception {
        if (StringUtils.isEmpty(consult.getUserId())) {
            if (StringUtils.isEmpty(getUserId()))
                throw new Exception("获取用户ID为空");
            consult.setUserId(getUserId());
        }
        if (consultService.save(consult) > 0) {
            return R.ok();
        }
        return R.error();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ConsultDO consult) {
        if (consultService.update(consult) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 点赞
     */
    @GetMapping("/good/{id}")
    public R update(@PathVariable Long id) {
        ConsultDO consultDO = consultService.get(id);
        if (consultDO != null) {
            consultDO.setGooded(consultDO.getGooded() + 1);
            if (consultService.update(consultDO) > 0) {
                return R.ok();
            }
        }
        return R.error();
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (consultService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] ids) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        consultService.batchRemove(ids);
        return R.ok();
    }
}