/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myconverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author davidpyle
 */
public class JConverterModel {

    private double conversionResult;

    //centimetre conversion formulas
    private final double cmToCm = 1;
    private final double cmToM = 0.01;
    private final double cmToKm = 1e-5;
    private final double cmToIn = 0.393701;
    private final double cmToFt = 0.0328084;
    private final double cmToMi = 6.2137e-6;

    //metre conversion formulas
    private final double mToCm = 100;
    private final double mToM = 1;
    private final double mToKm = 0.001;
    private final double mToIn = 39.3701;
    private final double mToFt = 3.28084;
    private final double mToMi = 0.000621371;

    //kilometre conversion formulas
    private final double kmToCm = 100000;
    private final double kmTom = 1000;
    private final double kmToKm = 1;
    private final double kmToIn = 39370.1;
    private final double kmToFt = 3280.84;
    private final double kmToMi = 0.621371;

    //inch conversion formulas
    private final double inToCm = 2.54;
    private final double inTom = 0.0254;
    private final double inToKm = 2.54e-5;
    private final double inToIn = 1;
    private final double inToFt = 0.0833333;
    private final double inToMi = 1.5783e-5;

    //foot conversion formulas
    private final double ftToCm = 30.48;
    private final double ftTom = 0.3048;
    private final double ftToKm = 0.0003048;
    private final double ftToIn = 12;
    private final double ftToFt = 1;
    private final double ftToMi = 0.000189394;

    //mile conversion formulas
    private final double miToCm = 160934;
    private final double miTom = 1609.34;
    private final double miToKm = 1.60934;
    private final double miToIn = 63360;
    private final double miToFt = 5280;
    private final double miToMi = 1;

    private int unitFromIndex = 0;
    private int unitToIndex = 0;

    private int unitCategory;

    private boolean toBoxUsed;

    private final double[][] distanceMatrix = {
        {cmToCm, cmToM, cmToKm, cmToIn, cmToFt, cmToMi},
        {mToCm, mToM, mToKm, mToIn, mToFt, mToMi},
        {kmToCm, kmTom, kmToKm, kmToIn, kmToFt, kmToMi},
        {inToCm, inTom, inToKm, inToIn, inToFt, inToMi},
        {ftToCm, ftTom, ftToKm, ftToIn, ftToFt, ftToMi},
        {miToCm, miTom, miToKm, miToIn, miToFt, miToMi}
    };

    private String[] unitCat
            = {
                "Distance",
                "Temperature"
            };

    private String[] distConversions
            = {
                "Centimetre",
                "Metre",
                "Kilometre",
                "Inch",
                "Foot",
                "Mile"
            };

    private String[] tempConversions
            = {
                "Celsius",
                "Fahrenheit"
            };

    public int getUnitCategory() {
        return unitCategory;
    }

    public void setUnitCategory(int whichUnits) {
        unitCategory = whichUnits;
    }

    public void setUnitFromIndex(int fromIndex) {
        unitFromIndex = fromIndex;
    }

    public int getUnitFromIndex() {
        return unitFromIndex;
    }

    public void setUnitToIndex(int toIndex) {
        unitToIndex = toIndex;
    }

    public int getUnitToIndex() {
        return unitToIndex;
    }

    public void setToBox(boolean val) {
        toBoxUsed = val;
    }

    private String formatValue(double value) {

        int decimalPlaces = 5;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(5);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);

        String result = df.format(bd);

        return result;
    }

    public void doConversion(double valueToConvert) {

        if (unitCategory == 0) {
            if (toBoxUsed) {
                conversionResult = valueToConvert * distanceMatrix[getUnitToIndex()][getUnitFromIndex()];
            } else {
                conversionResult = valueToConvert * distanceMatrix[getUnitFromIndex()][getUnitToIndex()];
            }

        } else if (getUnitFromIndex() == 0 && getUnitToIndex() == 1) {
            if (toBoxUsed) {
                conversionResult = (valueToConvert - 32) / 1.8;
            } else {
                conversionResult = (valueToConvert * 9 / 5) + 32;
            }
        } else if (getUnitFromIndex() == 1 && getUnitToIndex() == 0) {
            if (toBoxUsed) {
                conversionResult = (valueToConvert * 9 / 5) + 32;
            } else {
                conversionResult = (valueToConvert - 32) / 1.8;
            }
        } else if ((getUnitFromIndex() == 0 && getUnitToIndex() == 0) || (getUnitFromIndex() == 1 && getUnitToIndex() == 1)) {
            conversionResult = valueToConvert;
        }
    }

    public String txtFileHeaders() {

        String uCat = unitCat[unitCategory];
        String headers = "";

        switch (uCat) {

            case "Distance":
                headers += String.format("%1$-20s", distConversions[unitFromIndex]);
                headers += distConversions[unitToIndex];
                headers += "\n\n";
                break;
            case "Temperature":
                headers += String.format("%1$-20s", tempConversions[unitFromIndex]);
                headers += tempConversions[unitToIndex];
                headers += "\n\n";
                break;
        }
        return headers;

    }

    public String writeToFile(String content, String path) {
        try (FileWriter writer = new FileWriter(path);) {
            writer.write(txtFileHeaders());
            writer.write(content);  
            return "OK";
        } catch (IOException e) {           
            return "Error writing file\n" + e.getMessage();
        } 
    }
    
    public String convertFile(String importPath, String savePath) {

        String status;
        String contentToWrite = "";
        
        try (
                FileReader reader = new FileReader(importPath);
                BufferedReader bReader = new BufferedReader(reader);
                ) {
            //check if file empty
            if (bReader.readLine() == null) {
                 throw new Exception("File is empty. Nothing to convert!");
            } else {  
                while (true) {
                    String value = bReader.readLine();
                    if (value == null) {
                        break;
                    } else {
                        //writer.write(String.format("%1$-20s", value));
                        contentToWrite += String.format("%1$-20s", value);
                        doConversion(Double.parseDouble(value));
                        //writer.write(formatValue(conversionResult) + "\n");
                        contentToWrite += formatValue(conversionResult) + "\n";
                    }
                }
                status = writeToFile(contentToWrite, savePath);                
            }
            
        } catch (IOException e) {
            status = "Error reading file\n" + e.getMessage();

        } catch (Exception ex) {
            status = "Error encountered while processing file.\n" + ex.getMessage();
        }
        return status;
    }

    public double getConversionResult() {
        return conversionResult;
    }

    public String[] getConversionItems() {

        if (unitCategory == 0) {
            return distConversions;
        } else {
            return tempConversions;
        }
    }

    public String[] getUnitMenuItems() {

        return unitCat;
    }

}
