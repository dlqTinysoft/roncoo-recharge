/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.recharge.boss.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.recharge.boss.bean.qo.MerchantInfoQO;
import com.roncoo.recharge.boss.service.MerchantInfoService;
import com.roncoo.recharge.util.base.BaseController;
import com.roncoo.recharge.util.enums.ApiVerifyTypeEnum;
import com.roncoo.recharge.util.enums.PriceLevelEnum;
import com.roncoo.recharge.util.enums.StatusIdEnum;
import com.roncoo.recharge.util.enums.TxVerifyTypeEnum;

/**
 * 商户信息
 *
 * @author wujing
 * @since 2017-12-22
 */
@Controller
@RequestMapping(value = "/admin/merchantInfo")
public class MerchantInfoController extends BaseController {

	private final static String TARGETID = "admin-merchantInfo";

	@Autowired
	private MerchantInfoService service;

	@RequestMapping(value = "/list")
	public void list(@RequestParam(value = "pageCurrent", defaultValue = "1") int pageCurrent, @RequestParam(value = "pageSize", defaultValue = "20") int pageSize, @ModelAttribute MerchantInfoQO qo, ModelMap modelMap) {
		modelMap.put("page", service.listForPage(pageCurrent, pageSize, qo));
		modelMap.put("pageCurrent", pageCurrent);
		modelMap.put("pageSize", pageSize);
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add() {

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute MerchantInfoQO qo) {
		if (service.save(qo) > 0) {
			return success(TARGETID);
		}
		return error("添加失败");
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id") Long id) {
		if (service.deleteById(id) > 0) {
			return delete(TARGETID);
		}
		return error("删除失败");
	}

	@RequestMapping(value = "/edit")
	public void edit(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", service.getById(id));
	}

	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute MerchantInfoQO qo) {
		if (service.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", service.getById(id));
	}

	@ModelAttribute
	public void enums(ModelMap modelMap) {
		modelMap.put("txVerifyTypeEnums", TxVerifyTypeEnum.values());
		modelMap.put("apiVerifyTypeEnums", ApiVerifyTypeEnum.values());
		modelMap.put("statusIdEnums", StatusIdEnum.values());
		modelMap.put("priceLevelEnums", PriceLevelEnum.values());
	}

}
