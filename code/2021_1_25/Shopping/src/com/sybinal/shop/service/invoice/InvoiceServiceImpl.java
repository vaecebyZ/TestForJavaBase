package com.sybinal.shop.service.invoice;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.InvoiceMapper;

import com.sybinal.shop.model.Invoice;


@Service
public class InvoiceServiceImpl  implements InvoiceService  {
	@Autowired
	InvoiceMapper invoiceMapper;
	
	
	@Override
	public List<Invoice> getInvoice() {
		
		List<Invoice> lists =  invoiceMapper.selectInvoice();
		
		
		System.out.println("进入方法");
		
		return lists;
	}


	@Override
	public int addInvoice(Invoice invoice) {
		
		System.out.println(invoice);
		
		return invoiceMapper.insertInvoice(invoice);
	}



	@Override
	public int editInvoice(Invoice invoice) {
		
		return invoiceMapper.editInvoice(invoice);
	}



	@Override
	public int delInvoice(Invoice invoice) {
		
		return invoiceMapper.delInvoice( invoice);
	}

}
