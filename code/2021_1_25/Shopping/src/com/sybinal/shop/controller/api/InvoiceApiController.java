package com.sybinal.shop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sybinal.shop.common.ApiResponseEnum;
import com.sybinal.shop.common.ApiResponseObject;
import com.sybinal.shop.model.Invoice;
import com.sybinal.shop.service.invoice.InvoiceService;

@RestController
@RequestMapping("api/v1")
public class InvoiceApiController  extends AbstractApiController {
	@Autowired
	InvoiceService invoiceService;
	

	/**
	 * 测试
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "invoice/hello",method = RequestMethod.POST )
	public ApiResponseObject sayHello() {
		System.out.println("hello");
		
		return null;
	}
	
	/**
	 * 获取发票
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "invoice/getinvoice",method = RequestMethod.POST )
	public ApiResponseObject getInvoice() {
		
		System.out.println("获取发票");
		
		
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),invoiceService.getInvoice());
	}
	
	/**
	 * 增加发票
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "invoice/addinvoice",method = RequestMethod.POST )
	public ApiResponseObject addInvoice(@RequestBody Invoice invoice) {
		
		System.out.println("增加发票");
		
		
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),invoiceService.addInvoice(invoice));
	}
	
	/**
	 * 编辑发票
	 * 
	 * @param reqMap
	 * @return
	 */
	
	@RequestMapping(value = "invoice/editinvoice",method = RequestMethod.POST )
	public ApiResponseObject editInvoice(@RequestBody Invoice invoice) {
		
		System.out.println("修改发票");
		
		System.out.println(invoice);
		
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),invoiceService.editInvoice(invoice));
	}
	
	
	/**
	 * 删除发票
	 * 
	 * @param reqMap
	 * @return
	 */
	
	@RequestMapping(value = "invoice/delinvoice",method = RequestMethod.POST )
	public ApiResponseObject delInvoice(@RequestBody Invoice invoice) {
		
		System.out.println("删除发票");
		
		System.out.println(invoice);
		
		
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),invoiceService.delInvoice(invoice));
	}
}
