package br.com.vsc.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Excel extends Setup {
    String pathExcel = prop.getProperty("prop.data.vsc-vendas");

    public String getData(String columnName) throws IOException {
        int row_ = -1, column = -1;

        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(pathExcel));
            Sheet sheet;
            sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if ( cell.getRichStringCellValue().toString().equals( getCTNumber() ) )
                        row_ = cell.getRowIndex();
                    if( cell.getRichStringCellValue().toString().equals(columnName) )
                        column = cell.getColumnIndex();
                }
            }
            if( row_ == -1 || column == -1 )
                throw new Exception("Nenhum dado encontrado para coluna ["+ columnName +"] e linha ["+ getCTNumber() +"]");
            else
                return sheet.getRow( row_ ).getCell( column ).getStringCellValue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
