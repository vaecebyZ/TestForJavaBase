package com.sybinal.shop.controller.api;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sybinal.shop.common.ApiResponseEnum;
import com.sybinal.shop.common.ApiResponseObject;
import com.sybinal.shop.model.User;
import com.sybinal.shop.service.ewallet.EwalletService;

@RestController
@RequestMapping("api/v1")
public class EwalletApiController extends AbstractApiController{

	@Autowired
	EwalletService ewalletService;
	/**
	 * 密码找回
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "ewallet/defalutValue", method = RequestMethod.GET, headers = "Accept=application/json")
	public ApiResponseObject recoveryPassword(User user)
			throws IllegalAccessException, InvocationTargetException {
		return reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(), ewalletService.getDefalutValueOfAccount());
	}
}
