package com.example.demo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
@Controller
public class DemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private HSSFWorkbook wb;

	@Override
	public void run(ApplicationArguments args) {

		wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("test");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("Some loooooooooooooooooooooooong text");
		sheet.autoSizeColumn(0);
	}

	@GetMapping
	public void file(HttpServletResponse response) throws IOException {

		response.setHeader("Content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=\"test.xls\";");
		wb.write(response.getOutputStream());
		response.getOutputStream().close();
		response.flushBuffer();
	}
}
