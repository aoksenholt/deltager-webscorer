package no.aoksenholt.deltagerwebscorer.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemWriter;

import no.aoksenholt.deltagerwebscorer.model.Webscorer;

public class WebscorerWriter implements ItemWriter<Webscorer> {
    Log log = LogFactory.getLog(getClass());

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private String outfilename;

    private String[] headers = { "First name", "Last name", "Team name", "Gender", "Age", "Distance", "Category", "Info 1", "Info 2", "Bib", "Start time" };

    public WebscorerWriter(String outfilename) {
	this.outfilename = outfilename;
	this.workbook = new XSSFWorkbook();
	this.sheet = workbook.createSheet();

	initWorkbook();
    }

    @Override
    public void write(List<? extends Webscorer> webscorers) throws Exception {
	int rownum = 1;
	for (Webscorer webscorer : webscorers) {
	    Row row = sheet.createRow(rownum++);
	    Cell firstname = row.createCell(0);
	    Cell lastname = row.createCell(1);
	    Cell teamname = row.createCell(2);
	    Cell gender = row.createCell(3);
	    Cell age = row.createCell(4);
	    Cell distance = row.createCell(5);
	    Cell category = row.createCell(6);
	    Cell info1 = row.createCell(7);
	    Cell info2 = row.createCell(8);
	    Cell bib = row.createCell(9);
	    Cell starttime = row.createCell(10);

	    firstname.setCellValue(webscorer.getFirstname());
	    lastname.setCellValue(webscorer.getLastname());
	    teamname.setCellValue(webscorer.getTeamname());
	    gender.setCellValue(webscorer.getGender());
	    age.setCellValue(webscorer.getAge());
	    distance.setCellValue(webscorer.getDistance());
	    category.setCellValue(webscorer.getCategory());
	    info1.setCellValue(webscorer.getInfo1());
	    info2.setCellValue(webscorer.getInfo2());
	    bib.setCellValue(rownum - 1);
	    starttime.setCellValue("13:00:00");
	}
    }

    private void initWorkbook() {
	Row row = sheet.createRow(0);
	int cellnum = 0;

	for (String header : headers) {
	    Cell cell = row.createCell(cellnum++);
	    cell.setCellValue(header);
	}
    }

    public XSSFWorkbook getWorkbook() {
	return workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
	this.workbook = workbook;
    }

    public String getOutfilename() {
	return outfilename;
    }

    public void setOutfilename(String outfilename) {
	this.outfilename = outfilename;
    }

    public void writeFile() {
	try {
	    FileOutputStream fos = new FileOutputStream(new File(this.outfilename));
	    workbook.write(fos);
	    fos.close();
	    log.info("Fil " + outfilename + " opprettet");
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
