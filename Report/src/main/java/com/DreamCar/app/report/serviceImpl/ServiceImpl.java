package com.bitlogic.customerregister.app.serviceimpl;

import java.awt.Color;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bitlogic.customerregister.app.binding.Customer;
import com.bitlogic.customerregister.app.binding.MailSender;
import com.bitlogic.customerregister.app.model.CustomerDetail;
import com.bitlogic.customerregister.app.model.EmiCalculation;
import com.bitlogic.customerregister.app.repository.CustomerRepo;
import com.bitlogic.customerregister.app.repository.EmiRepo;
import com.bitlogic.customerregister.app.service.CustomerService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ServiceImpl implements CustomerService {
	@Autowired
	CustomerRepo cr;
	@Autowired
	EmiRepo er;
	@Autowired
	JavaMailSender jms;

	Document document;

	@Override
	public Integer saveData(Customer c) {
		// TODO Auto-generated method stub
		// EmiCalculation e=new EmiCalculation();
//		BeanUtils.copyProperties(c.getEmi(), e);
		CustomerDetail cd = new CustomerDetail();
		BeanUtils.copyProperties(c, cd);
		CustomerDetail save = cr.save(cd);
		EmiCalculation e = new EmiCalculation();
		double princi = (double) cd.getLoanAmount();
		double month = (double) cd.getTimePeriod();

		double interest = (double) (cd.getRateOfInterast() / 12) / 100;
		double emi = ((princi * interest) * Math.pow(1 + interest, month) / (Math.pow(1 + interest, month) - 1));

		for (int i = 1; i <= month; i++) {
			int k = 1;
			e.setBalance(princi);
			e.setCustomerId(cd.getId());
			LocalDate now = LocalDate.of(2022, 01, 22);
			e.setPaymentdate(now.plusMonths(i));
			e.setPaymentNumber(k);
			if (princi > emi) {
				e.setEmiPaid(emi);
			} else {
				e.setEmiPaid(princi);
			}
			double dd = princi - emi;
			// System.out.println(dd);
			e.setAtatus("Pending");

			princi = dd;
			k++;
			EmiCalculation save2 = er.save(e);

		}
		return save.getId();
	}

	@Override
	public void ganaratePdf(HttpServletResponse response, Integer cusId) throws Exception {
		List<EmiCalculation> all = er.findAllByCustomerId(cusId);

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.green);

		Paragraph p = new Paragraph("Ladger Report", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 2.5f, 3.5f, 3.0f, 1.5f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.white);
		cell.setPadding(5);

		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Number", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Payment Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Balance", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("EmiPaid", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Status", font));
		table.addCell(cell);
		int i = 1;
		for (EmiCalculation e : all) {
			table.addCell(String.valueOf(i));
			table.addCell(String.valueOf(e.getPaymentdate()));
			table.addCell(String.valueOf(e.getBalance()));
			table.addCell(String.valueOf(e.getEmiPaid()));
			table.addCell(e.getAtatus());
			i++;
		}
		document.add(table);
		document.close();
	}

	@Override
	public void ganarateExl(HttpServletResponse response, Integer cusId) throws Exception {
		List<EmiCalculation> all = er.findAllByCustomerId(cusId);

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("PaymentNumber");
		row.createCell(1).setCellValue("Paymentdate");
		row.createCell(2).setCellValue("Balance");
		row.createCell(3).setCellValue("EmiPaid");
		row.createCell(4).setCellValue("Status");
		int i = 1;
		for (EmiCalculation al : all) {

			HSSFRow data = sheet.createRow(i);
			data.createCell(0).setCellValue(String.valueOf(i));
			;
			data.createCell(1).setCellValue("" + al.getPaymentdate() + "");
			data.createCell(2).setCellValue(al.getBalance());
			data.createCell(3).setCellValue(al.getEmiPaid());
			data.createCell(4).setCellValue(al.getAtatus());
			i++;

		}
		ServletOutputStream stream = response.getOutputStream();
		workbook.write(stream);
		workbook.close();
		stream.close();
	}

	@Override
	public void sendMailWithAttachment(MailSender e) {
		try {
			MimeMessage message = jms.createMimeMessage();
			System.out.println("shagdxhygasx");
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(e.getFromEmail());
			helper.setTo(e.getToEmail());
			helper.setText(e.getTxtmsg());
			helper.setSubject(e.getSubject());

			helper.addAttachment("doc", (DataSource) document);
			System.out.println(e.getFromEmail());
			jms.send(message);
		} catch (Exception e2) {
			// System.out.println("Error Occer");
			e2.printStackTrace();
		}

	}

	@Override
	public void updateStatus(Integer id, LocalDate date) {
		List<EmiCalculation> all = er.findAllByCustomerId(id);
		// Stream<EmiCalculation> filter = all.stream().filter(p ->
		// p.getAtatus().equalsIgnoreCase("pending"));
		for (EmiCalculation e : all) {
			if (e.getPaymentdate().equals(date)) {
				e.setAtatus("Paid");
			} else if (e.getPaymentdate().isBefore(LocalDate.now()) &&e.getAtatus().equalsIgnoreCase("pending")) {
				e.setAtatus("Not Paid");
			}
			er.save(e);
		}

	}

	@Override
	public String findDefaulterCustomer(Integer id) {
		List<EmiCalculation> all = er.findAllByCustomerId(id);
		int s=0;
		String name="";
		int i = 0;
		for (EmiCalculation e : all) {
			
			if (e.getPaymentdate().isBefore(LocalDate.now())) {
				if (e.getAtatus().equalsIgnoreCase("Not Paid")) {
					i++;
				}else if(e.getAtatus().equalsIgnoreCase("paid")) {
					i=0;
				}
						
			}
			if(i>=3) {
				s++;
			}
		}
		if(s!=0) {
			Optional<CustomerDetail> ss = cr.findById(id);
			if(ss.isPresent()) {
				CustomerDetail customerDetail = ss.get();
				
				name = customerDetail.getName();
			}
		}
		

		return name;
	}

	@Override
	public String findCustomer(Integer id) {
		Optional<CustomerDetail> findById = cr.findById(id);
		String name="";
		if(findById.isPresent()) {
			CustomerDetail detail = findById.get();
		 name = detail.getName();
		}
		return name;
	}

}
