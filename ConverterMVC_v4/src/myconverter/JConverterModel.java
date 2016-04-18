/*
 This model class is reponsible for the logic of the converter. It performs the
 conversion calculations for display on the views and also writes the converted
 value to a file.
 
 @author David Pyle 041110777
 @version 1.0
 @since 4/4/2016
  
 Methods:
    - int getUnitCategory()
    - void setUnitCategory(int whichUnits)   
    - void setUnitFromIndex(int fromIndex)
    - int getUnitFromIndex()
    - void setUnitToIndex(int toIndex)
    - int getUnitToIndex()
    - void setToBox(boolean val)
    - String formatValue(double value)
    - void doConversion(double valueToConvert)
    - String txtFileHeaders()
    - String writeToFile(String content, String path)
    - String convertFile(String importPath, String savePath)
    - double getConversionResult()
    - String[] getConversionItems()
    - String[] getUnitMenuItems()

 Classes this class requires    
    java.io.BufferedReader;
    java.io.FileReader;
    java.io.FileWriter;
    java.io.IOException;
    java.math.BigDecimal;
    java.text.DecimalFormat;
 */

package myconverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;


public class JConverterModel {

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

    //the result of the converted value
    private double conversionResult;    
    //index number for the Unit From comboBox 
    private int unitFromIndex = 0;
    //index number for the Unit To comboBox 
    private int unitToIndex = 0;
    //index number for the Unit Category comboBox 
    private int unitCategory;
    //flag for if the vlaue to convert is entered into the To input box
    private boolean toBoxUsed;

    //formula conversion matrix used to select the correct formula based on the
    //index numbers in the Unit From and Unit To comboBoxes
    private final double[][] distanceMatrix = {
        {cmToCm, cmToM, cmToKm, cmToIn, cmToFt, cmToMi},
        {mToCm, mToM, mToKm, mToIn, mToFt, mToMi},
        {kmToCm, kmTom, kmToKm, kmToIn, kmToFt, kmToMi},
        {inToCm, inTom, inToKm, inToIn, inToFt, inToMi},
        {ftToCm, ftTom, ftToKm, ftToIn, ftToFt, ftToMi},
        {miToCm, miTom, miToKm, miToIn, miToFt, miToMi}
    };

    //menu items for the unit categories
    private final String[] unitCat
            = {
                "Distance",
                "Temperature"
            };
    
    //menu items for the distance units
    private final String[] distConversions
            = {
                "Centimetre",
                "Metre",
                "Kilometre",
                "Inch",
                "Foot",
                "Mile"
            };

    //menut items for the temperatur units
    private final String[] tempConversions
            = {
                "Celsius",
                "Fahrenheit"
            };

    /**
    * Gets the selected Unit Category index
    * @return  the index number of Unit Category comboBox  
    */
    public int getUnitCategory() {
        
        return unitCategory;
    }

    /**
    * Sets the selected Unit Category index
    * @param  whichUnits the index number of the Unit Category menu item  
    */
    public void setUnitCategory(int whichUnits) {
        
        unitCategory = whichUnits;
    }
    
    /**
    * Gets the selected Unit From index
    * @return  the index number of Unit From comboBox  
    */
    public int getUnitFromIndex() {
        
        return unitFromIndex;
    }
    
    /**
    * Sets the selected Unit From index
    * @param  fromIndex the index number of Unit From menu item  
    */
    public void setUnitFromIndex(int fromIndex) {
        
        unitFromIndex = fromIndex;
    }
    
     /**
    * Gets the selected Unit To index
    * @return  the index number of Unit To comboBox  
    */
    public int getUnitToIndex() {
        
        return unitToIndex;
    }
    
    /**
    * Sets the selected Unit To index
    * @param  toIndex the index number of Unit To menu item  
    */
    public void setUnitToIndex(int toIndex) {
        
        unitToIndex = toIndex;
    }
    
    /**
    * Sets flag of toBoxUsed 
    * @param  val boolean value to set  
    */
    public void setToBox(boolean val) {
        
        toBoxUsed = val;
    }
    
     /**
    * Gets the menu items for the unit category 
    * @return  string array of menu items  
    */
    public String[] getUnitMenuItems() {

        return unitCat;
    }
    
     /**
    * Gets the menu items for the conversion units
    * @return  string array of menu items  
    */
    public String[] getConversionItems() {

        //if the first menu item selected (Distance)
        if (unitCategory == 0) {
            return distConversions;
        //else temperature unit selected
        } else {
            return tempConversions;
        }
    }
    
    /**
    * Gets the conversion result
    * @return  the converted result  
    */
    public double getConversionResult() {
        
        return conversionResult;
    }
    
    /**
    * Formats a number to 5 decimal places
    * @param    value the number to format
    * @return   string representation 5 decimal placed number
    */
    private String formatValue(double value) {
        
        //number of decimal places to set
        int decimalPlaces = 5;
        //create a new BigDecimal object with value rounded up
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);

        //format the value
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(5);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);

        String result = df.format(bd);
        //return the string version of the formatted value
        return result;
    }

    /**
    * Converts a value between the from and to selected units. Direction of
    * conversion depends on which input box was used on the user interface.
    * @param    valueToConvert the value to convert  
    */
    public void doConversion(double valueToConvert) {

        //if the first Unit Category menu item selected (Distance)
        if (unitCategory == 0) {
            //check if the value to convert was entered into the To Input Box
            if (toBoxUsed) {
                //convert the value in the reverse direction i.e. To input -> From input
                conversionResult = valueToConvert * distanceMatrix[getUnitToIndex()][getUnitFromIndex()];
            } else {
                //convert the value in the normal direction i.e. From input -> To input
                conversionResult = valueToConvert * distanceMatrix[getUnitFromIndex()][getUnitToIndex()];
            }
        //else handle the temperature conversion (Celsius to Fahrenheit)
        } else if (getUnitFromIndex() == 0 && getUnitToIndex() == 1) {
            //check if the value to convert was entered into the To Input Box
            if (toBoxUsed) {
                //convert the value in reverse i.e. Fahrenheit to Celsius
                conversionResult = (valueToConvert - 32) / 1.8;
            } else {
                //convert the value i.e. Celsius to Fahrenheit
                conversionResult = (valueToConvert * 9 / 5) + 32;
            }
        //else handle the temperature conversion (Fahrenheit to Celsius)
        } else if (getUnitFromIndex() == 1 && getUnitToIndex() == 0) {
            //check if the value to convert was entered into the To Input Box
            if (toBoxUsed) {
                //convert the value in reverse i.e. Celsius to Fahrenheit
                conversionResult = (valueToConvert * 9 / 5) + 32;
            } else {
                //convert the value i.e. Fahrenheit to Celsius
                conversionResult = (valueToConvert - 32) / 1.8;
            }
        //else if the units selected in the both the From and To are the same just return the value entered
        } else if ((getUnitFromIndex() == 0 && getUnitToIndex() == 0) || (getUnitFromIndex() == 1 && getUnitToIndex() == 1)) {
            conversionResult = valueToConvert;
        }
    }

    /**
    * Returns the distance or temperature headers for use on the output file when
    * performing a conversion of values in an input file.
    * 
    * @return    the distance or temperature headers based on selection on user interface
    */
    public String txtFileHeaders() {
        
        //get the current selected Unit Category
        String uCat = unitCat[unitCategory];
        String headers = "";

        switch (uCat) {
            //set headers for the selected Distance units
            case "Distance":
                headers += String.format("%1$-20s", distConversions[unitFromIndex]);
                headers += distConversions[unitToIndex];
                headers += "\n\n";
                break;
            //set headers for the selected Temprature units
            case "Temperature":
                headers += String.format("%1$-20s", tempConversions[unitFromIndex]);
                headers += tempConversions[unitToIndex];
                headers += "\n\n";
                break;
        }
        
        //return the headers
        return headers;

    }

    /**
    * Writes the contents to the output file
    * 
    * @param    content the content to write to the file
    * @param    path    the path to the output file
    * @return    status of the file write operation. 'OK' for no errors or the error message  
    */
    public String writeToFile(String content, String path) {
        //attempt to write to the output file
        try (FileWriter writer = new FileWriter(path);) {
            //first write the headers
            writer.write(txtFileHeaders());
            //write the converted values
            writer.write(content);  
            //set OK status to indicate a successful write
            return "OK";
        } catch (IOException e) {           
            //return error if unable to write to file
            return "Error writing file\n" + e.getMessage();
        } 
    }
    
    /**
    * Converts a file of values to the selected conversion units and saves to 
    * output file 
    * 
    * @param    importPath  the path to the input file to convert
    * @param    savePath    the path to the output file
    * @return    status of the file open operation 
    */
    public String convertFile(String importPath, String savePath) {

        //status of conversion
        String status;
        String contentToWrite = "";
        
        //attempto read input file of values
        try (
                FileReader reader = new FileReader(importPath);
                BufferedReader bReader = new BufferedReader(reader);
                ) {
            //check if file empty
            if (bReader.readLine() == null) {
                 throw new Exception("File is empty. Nothing to convert!");
            } else {  
                while (true) {
                    //read each line of the file
                    String value = bReader.readLine();
                    if (value == null) {
                        break;
                    } else {
                        //add some spaces in the output so its nice an neat
                        contentToWrite += String.format("%1$-20s", value);
                        //perform the conversion on the value
                        doConversion(Double.parseDouble(value));
                        //format the converted result and add it to the contents to be written
                        contentToWrite += formatValue(conversionResult) + "\n";
                    }
                }
                //write the converted values to the output file and get the status
                status = writeToFile(contentToWrite, savePath);                
            }
            
        //handle any file read or processing exceptions
        } catch (IOException e) {
            status = "Error reading file\n" + e.getMessage();

        } catch (Exception ex) {
            status = "Error encountered while processing file.\n" + ex.getMessage();
        }
        //return the status of the file read/write
        return status;
    }

}
