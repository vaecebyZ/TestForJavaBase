package com.sybinal.shop.mapper;

import java.util.List;

import com.sybinal.shop.model.Invoice;

public interface InvoiceMapper {

int insertInvoice(Invoice invoice);

List<Invoice> selectInvoice();

int editInvoice(Invoice invoice);

int delInvoice(Invoice invoice);

}
