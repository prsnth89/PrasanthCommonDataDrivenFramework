package com.prasanth.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadExcel {
public static String excelFilePath;
	
/**
 * @param args
 */
//public static String excelFilePath;

private  static Map<Integer, List<String>> valuesMap;

private static Map<Integer, String> headerList = new HashMap();

private List<String> valueList = new ArrayList();

private FileInputStream inputStream;

/*public static void main(String[] args) throws IOException {
        
  // Fetch the values based on input
        ReadExcel readData=        new ReadExcel();
        
int a=        readData.getLastRowIterationId("manualtransactions", "MAN001");
System.out.println("Iteration Id----"+a);

for(int j=1;j<=a;j++) {
        String searchText = readData.getTestData("manualtransactions", "MAN001", j, "SearchText");
        System.out.println("Search Text---"+searchText);
        String ibanNumbers = readData.getTestData("manualtransactions", "MAN001", j, "IBANNo");
        System.out.println("IbanNo--"+ibanNumbers);
        String chargeCodes = readData.getTestData("manualtransactions", "MAN001", j, "ChargeCode");
        System.out.println("ChargeCode--"+chargeCodes);
        String qtys= readData.getTestData("manualtransactions", "MAN001", j, "Quantity");
        System.out.println("Quantity--"+qtys);
        String rates = readData.getTestData("manualtransactions", "MAN001", j, "RateInAdjustment");
        System.out.println("Rates--"+rates);
        String dates= readData.getTestData("manualtransactions", "MAN001", j, "Date");
        System.out.println("Dates--"+dates);
}
        

}*/

public ReadExcel(){
        valuesMap = new HashMap();
}
public ReadExcel(String excelFileName, String sheetName) {
        try {
                valuesMap = new HashMap();
                readExcel(excelFileName, sheetName);
        }catch(Exception e) {
                System.out.println("Exception in reading excel file---"+e.getMessage());
        }
        
}

public synchronized String getTestData(String excelFileName, String sheetName,int iterationID, String colName) throws Exception {
        String data=null;
        try {
                readExcel(excelFileName, sheetName);
        
        for (Entry<Integer, List<String>> keyEntry : valuesMap.entrySet()) {
                if (keyEntry.getValue().get(0).equals(String.valueOf(iterationID))) {
                        Integer indexKey = keyEntry.getKey();
                        for (Entry<Integer, String> valueEntry : headerList.entrySet()) {
                                if (valueEntry.getValue().equals(colName)) {
                                        Integer columnIndex = valueEntry.getKey();
                                        
                                        data=valuesMap.get(indexKey).get(columnIndex);
                                        break;
                                        //System.out.println("values test :" + valuesMap.get(indexKey).get(columnIndex));
                                }
                        }
                }
        }
        } catch (IOException e) {
                System.out.println("Please input correct file");
                e.printStackTrace();
                throw new Exception();
                
        }
        
        return data;
}

public void readExcel(String excelFileName, String sheetName) throws IOException {
        int i = 0;
        int k = 0;
        String filePath;
        try {
        	
                filePath=System.getProperty("user.dir")+File.separator+"data"+File.separator+excelFileName+".xls";
                System.out.println("DataPath--"+filePath);
                inputStream = new FileInputStream(filePath);
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheet(sheetName);
        for (Row s : firstSheet) {
                
                
                valueList = new ArrayList();
                for (Cell row : s) {
                        if (i == 0) {
                                headerList.put(k, row.toString());
                                k++;
                        } else {
                                
                        valueList.add(row.getStringCellValue());
                                
                                
                        }
                }
                if (i != 0) {
                        valuesMap.put(i, valueList);
                }
                i++;
        }
        for (Integer key : valuesMap.keySet()) {
                //System.out.println(valuesMap.get(key)); --print row values
        }

        for (Integer header : headerList.keySet()) {
                //System.out.println(headerList.get(header)); --print col names
        }
        
        }catch(Exception e) {
                System.out.println("Please check the Input data file--"+e.getMessage());
                
        }
}



public  int getLastRowIterationId(String excelFileName, String sheetName) {
        String data=null;
        try {
                readExcel(excelFileName, sheetName);
        
        for (Entry<Integer, List<String>> keyEntry : valuesMap.entrySet()) {
                        System.out.println("Key----"+keyEntry.getValue().get(0));
                        
                        data=keyEntry.getValue().get(0);
                        
                }
        
        } catch (IOException e) {
                System.out.println("Please input correct file");
                e.printStackTrace();
        }
        
        return Integer.parseInt(data);
}






}
