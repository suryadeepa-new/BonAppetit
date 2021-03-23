package com.bonAppetit.demo.service;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bonAppetit.demo.bean.Menu;
import com.bonAppetit.demo.dao.RestaurantDAO;



@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	
	@Autowired
	private RestaurantDAO restaurantDao;

	
	@Override
	public int uploadMenu(MultipartFile fileUploaded, String name) {
		String filePath = "";
		int savedRecords = 0;

		List<Menu> data = new ArrayList<>();
		try {
			InputStream in = fileUploaded.getInputStream();
		
			File currentDir = new File("/MenuExcel");
			filePath = currentDir + File.separator + fileUploaded.getOriginalFilename();
			FileOutputStream savingFile = new FileOutputStream(filePath);
			int ch = 0;
			while ((ch = in.read()) != -1) {
				savingFile.write(ch);
			}
			savingFile.flush();
			savingFile.close();

			PropertyDescriptor methodCall = null;
			FileInputStream file = new FileInputStream(new File(filePath));
			Workbook workbook = new HSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			List<String> sortProperties = new ArrayList<String>();
			BeanInfo info = Introspector.getBeanInfo(Menu.class);
			PropertyDescriptor[] pds = info.getPropertyDescriptors();
			for (PropertyDescriptor p : pds) {
				sortProperties.add(p.getName());
			}
			sortProperties.remove("class");
			Collections.sort(sortProperties);
		
			int i = 0;Cell c = null;
			Row firstRow = sheet.getRow(0);
			for (int j = 1; j <= sheet.getLastRowNum(); j++) {
				Row row = sheet.getRow(j);
				Menu menuEntry = new Menu();
				
				for (int index = 0; index <firstRow.getPhysicalNumberOfCells(); index++) {
					String columnName = firstRow.getCell(index).getStringCellValue();
					if (sortProperties.contains(columnName) && sortProperties.get(index).equalsIgnoreCase(columnName)) {
						methodCall = new PropertyDescriptor(columnName, Menu.class);
						if(row.getCell(index)!=null) {
							row.getCell(index).setCellType(CellType.STRING);		
						methodCall.getWriteMethod().invoke(menuEntry, row.getCell(index).getStringCellValue());
						}
						else {
							methodCall.getWriteMethod().invoke(menuEntry, "");
						}
					}else {
						
					}

				}
				data.add(menuEntry);
			}
	
		
	
	}catch(Exception e) {
		e.printStackTrace();
	}
		savedRecords = restaurantDao.bulkInsertMenuItems(data);
		return savedRecords;

	}
	
}
