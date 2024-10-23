package com.ashcoopeer.discord.utils;

import com.ashcoopeer.discord.constants.ReportConstants;
import com.ashcoopeer.discord.dto.TextPosition;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class UserReport extends Report<Map<String, String>> implements ReportConstants {
    private Workbook workbook;
    private Sheet sheet;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
    private CellStyle defaultCellStyle;
    private int totalColumns;
    @Setter
    private String[] colLabelArray;

    public void generateReport() {
        File outputFile;
        FileOutputStream fileOutputStream = null;
        String outputFileLocation;
        Font defaultFont;
        try {

            if(Objects.isNull(colLabelArray)) {
                throw new RuntimeException("Columns is not defined");
            }

            this.totalColumns = colLabelArray.length;
            workbook = new XSSFWorkbook();

            //Create sheet Report
            sheet = workbook.createSheet("Report");

            //create default cell style
            defaultCellStyle = workbook.createCellStyle();
            //add border to cell style
            defaultCellStyle.setBorderBottom(BorderStyle.THIN);
            defaultCellStyle.setBorderTop(BorderStyle.THIN);
            defaultCellStyle.setBorderLeft(BorderStyle.THIN);
            defaultCellStyle.setBorderRight(BorderStyle.THIN);

            //default font
            defaultFont = workbook.createFont();
            defaultFont.setFontName(DEFAULT_FONT_NAME);
            defaultFont.setFontHeight(DEFAULT_FONT_SIZE);
            defaultCellStyle.setFont(defaultFont);

            //createColorWhite
            XSSFColor whiteColor = new XSSFColor(new Color(255, 255,255), null);

            //set white to border color
            ((XSSFCellStyle) defaultCellStyle).setBottomBorderColor(whiteColor);
            ((XSSFCellStyle) defaultCellStyle).setTopBorderColor(whiteColor);
            ((XSSFCellStyle) defaultCellStyle).setLeftBorderColor(whiteColor);
            ((XSSFCellStyle) defaultCellStyle).setRightBorderColor(whiteColor);

            //set default style to all cell
            CTCol ctCol = ((XSSFSheet) sheet).getCTWorksheet().getColsArray(0).addNewCol();
            ctCol.setMin(1);
            ctCol.setMax(16384);
            ctCol.setWidth(12.71);
            ctCol.setStyle(defaultCellStyle.getIndex());

            addHeader();

            outputFile = new File("../");

            outputFileLocation = outputFile.getAbsolutePath() + File.separator + "report.xlsx";

            //save excel
            fileOutputStream = new FileOutputStream(outputFileLocation);

            workbook.write(fileOutputStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

                sheet = null;
                workbook = null;
            } catch (Exception ignored){}
        }
    }

    private void addHeader() throws Exception {
        if (Objects.isNull(workbook)) {
            throw new RuntimeException("workbook is not created");
        }

        if (Objects.isNull(sheet)) {
            throw new RuntimeException("sheet is not created");
        }

        //Create row and cell
        //Report 12 cols
        //first column user
        mergeRegionWithValue(0, "User: Junior", TextPosition.builder()
                .horizontalAlignment(HorizontalAlignment.RIGHT)
                .build());
        //second column blank
        mergeRegionWithValue(1, "Junior Report", TextPosition.builder()
                .verticalAlignment(VerticalAlignment.CENTER)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .fontSize((short) 13)
                .bold(true)
                .build());
        //second column date
        mergeRegionWithValue(2, simpleDateFormat.format(new Date()), TextPosition.builder()
                .horizontalAlignment(HorizontalAlignment.LEFT)
                .build());

        mergeRegionWithValue(3, "");
    }

    private void createTable() {

    }

    private void mergeRegionWithValue(int rowIndex, String value, TextPosition textPosition) {
        mergeRegionWithValue(rowIndex, DEFAULT_INITIAL_COLUMN_INDEX, value, textPosition);
    }

    private void mergeRegionWithValue(int rowIndex, String value) {
        mergeRegionWithValue(rowIndex, DEFAULT_INITIAL_COLUMN_INDEX, value, null);
    }

    private void mergeRegionWithValue(int rowIndex, int initialCol, String value, TextPosition textPosition) {
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(1);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.cloneStyleFrom(defaultCellStyle);

        if (!Objects.isNull(textPosition)) {
            if (!Objects.isNull(textPosition.getHorizontalAlignment())) {
                cellStyle.setAlignment(textPosition.getHorizontalAlignment());
            }

            if (!Objects.isNull(textPosition.getVerticalAlignment())) {
                cellStyle.setVerticalAlignment(textPosition.getVerticalAlignment());
            }

            //Copy default font styles
            Font font = workbook.createFont();
            font.setFontName(DEFAULT_FONT_NAME);
            font.setFontHeight(DEFAULT_FONT_SIZE);

            if (textPosition.getFontSize() > 0) {
                font.setFontHeight(textPosition.getRealFontSize());
            }

            font.setBold(textPosition.isBold());

            cellStyle.setFont(font);
        }

        cell.setCellStyle(cellStyle);

        cell.setCellValue(value);

        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, initialCol, totalColumns));
    }
}
