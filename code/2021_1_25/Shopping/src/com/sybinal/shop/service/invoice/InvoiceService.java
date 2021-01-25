package com.sybinal.shop.service.invoice;

import java.util.List;
import java.util.Map;


import com.sybinal.shop.model.Invoice;

public interface InvoiceService {

	public List<Invoice> getInvoice();

	public int addInvoice(Invoice invoice);

	public int editInvoice(Invoice invoice);

	public int delInvoice(Invoice invoice);
	

	
	
}
