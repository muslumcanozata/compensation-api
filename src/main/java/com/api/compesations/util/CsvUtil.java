package com.api.compesations.util;

import com.api.compesations.constants.HeaderNames;
import com.api.compesations.domain.entity.Compensation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    public static String TYPE = "text/csv";

    public static boolean hasCsvFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Compensation> csvToCompensationList(InputStream is) {
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(bReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<Compensation> compensations = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            csvRecords.forEach(csvRecord -> compensations.add(toCompensation(csvRecord)));
            return compensations;
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }
    }

    private static Compensation toCompensation(CSVRecord csvRecord) {
        Compensation compensation = new Compensation();
        compensation.setAge(csvRecord.get(HeaderNames.AGE));
        compensation.setCurrency(csvRecord.get(HeaderNames.CURRENCY));
        compensation.setAnnualSalary(new BigDecimal(csvRecord.get(HeaderNames.ANNUAL_SALARY)));
        compensation.setIndustry(csvRecord.get(HeaderNames.INDUSTRY));
        compensation.setJobTitle(csvRecord.get(HeaderNames.JOB_TITLE));
        compensation.setYearsExperience(csvRecord.get(HeaderNames.EXPERIENCE));
        compensation.setJobTitleContext(csvRecord.get(HeaderNames.JOB_TITLE_CONTEXT));
        compensation.setLocation(csvRecord.get(HeaderNames.LOCATION));
        compensation.setOtherCurrency(csvRecord.get(HeaderNames.OTHER_CURRENCY));
        compensation.setTimestamp(LocalDateTime.parse(csvRecord.get(HeaderNames.TIMESTAMP)));
        return compensation;
    }
}

