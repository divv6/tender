package com.example.tenderhak.handler;

import com.example.tenderhak.model.TypeEnum;
import com.example.tenderhak.repository.Error;
import com.example.tenderhak.repository.ErrorRepository;
import com.example.tenderhak.repository.Type;
import com.example.tenderhak.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ParseXMLHandler {

    private final TypeRepository typeRepository;
    private final ErrorRepository errorRepository;


    public ResponseEntity  handle() {

        List<Type> typeError = typeRepository.findAll();
        Type unknown = typeRepository.findByType(TypeEnum.UNKNOWN);

        String filePath = "C:\\logs1.xlsx";
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis);) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cellName = row.getCell(0);
                Cell cellDate = row.getCell(1);
                Cell cellError = row.getCell(2);
                if (cellName != null) {
                    String cellKeyValue = cellName.toString();
                    String cellDateValue = cellDate.toString();
                    String cellErrorValue = cellError.toString().replaceAll("\n", "")
                            .replaceAll("\r", "")
                            .replaceAll("\n\r", "")
                            .replaceAll("\r\n", "");
                    Error error = new Error();
                    error.setErrorKey(cellKeyValue);
                    error.setDate(LocalDateTime.parse(cellDateValue, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    error.setError(cellErrorValue);
                    error.setErrorType(unknown);
                    for (Type type : typeError) {
                        if (cellErrorValue.startsWith(type.getKey())) {
                            error.setErrorType(type);
                        }
                    }
                    Error error1 = errorRepository.save(error);
                }
            }
        } catch (IOException | NullPointerException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
