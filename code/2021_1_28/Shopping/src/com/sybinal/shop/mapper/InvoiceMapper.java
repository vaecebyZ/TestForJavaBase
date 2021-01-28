package com.sybinal.shop.mapper;

import java.util.List;

import com.sybinal.shop.model.Invoice;

public interface InvoiceMapper {

int insertInvoice(Invoice invoice);

List<Invoice> selectInvoice(Invoice invoice);

int editInvoice(Invoice invoice);

int delInvoice(Invoice invoice);

}
