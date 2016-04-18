/*
 This controller class communicates with the model and view objects.
 It is responsible for listening to input events on the view and updating
 the state of the model. It also updates the view as necessary for model state
 changes.
 
 @author David Pyle 041110777
 @version 1.0
 @since 4/4/2016
  
 Methods:
    - void setupListeners()
    - void setInitialStateImporter()   
    - void closeImportDialog()
    - void setInitialState()
    - void setInitialStateImporter()

 Classes this class requires    
    java.awt.event.ActionEvent;
    java.awt.event.ActionListener;
    java.awt.event.ItemEvent;
    java.awt.event.ItemListener;
    java.awt.event.KeyAdapter;
    java.awt.event.KeyEvent;
    javax.swing.JComboBox;
 */
package myconverter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;


public class JConverterController {

    //class instance variables for the main view
    private final JConverterView theView;
    //class instance variables for the moel
    private final JConverterModel theModel;
    //class instance variables for the import dialog view
    private final JImporterView theImportView;
    
    /**
    * Constructor
    * 
    * @param theView the main view of the application
    * @param theModel contains the logic for the application
    * @param theImportView dialog view for import functionality
    */
    public JConverterController(JConverterView theView, JConverterModel theModel, JImporterView theImportView) {
        
        //initialise the views and model
        this.theView = theView;
        this.theModel = theModel;
        this.theImportView = theImportView;

        //setup listeners
        setupListeners();
        //set the initial state of the main view
        setInitialState();
    }

    /**
     * This method adds all the event listeners for the main view and 
     * import dialog view     
     */
    private void setupListeners() {

        //add listeners for menu item events
        this.theView.addImportMenuListener(new ImportMenuListener());
        this.theView.addExitListener(new ExitListener());

        //add listeners for comboBox events
        this.theView.addUnitFromListener(new UnitFromListener());
        this.theView.addUnitToListener(new UnitToListener());
        this.theView.addUnitCatListener(new UnitCatListener());

        //add listeners for textField events
        this.theView.addInputFromListener(new InputFromListener());
        this.theView.addInputToListener(new InputToListener());

        //add listenrs for the inport dialog box
        this.theImportView.addUnitCatListener(new ImportUnitCatListener());
        this.theImportView.addUnitFromListener(new ImportUnitFromListener());
        this.theImportView.addUnitToListener(new ImportUnitToListener());
        this.theImportView.addCancelListener(new CancelListener());
        this.theImportView.addImportListener(new ImportListener());

    }
    
    /*
     Handles keyReleased events for the 'from' input textbox on the main view.

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void keyReleased(KeyEvent e)

     Classes this class requires    
        KeyAdapter       
     */
    class InputFromListener extends KeyAdapter {
        
        /**
        * Validates the input then passes the From value for conversion and displays
        * the result
        * @param  e the generated KeyEvent   * 
        */
        @Override
        public void keyReleased(KeyEvent e) {
            try {                             
                //clear any error messages
		theView.clearMsg();
                //not inputting to the To input so set to false
                theModel.setToBox(false);
                
                //validate input - check not using negative numbers
                if (theView.getFromValue() < 0 && theModel.getUnitCategory() == 0) {                    
                    throw new NumberFormatException("No negative numbers.");
                } else { 
                    //perform the conversion
                    theModel.doConversion(theView.getFromValue());
                    //display the result
                    theView.setToValue(theModel.getConversionResult());
                }

            } catch (NumberFormatException ex) {
		
		//System.out.println(ex.getMessage());
                theView.displayMsg("Invalid input. " + ex.getMessage(), "warning");		
            }
        }
    }
    
    /*
     Handles keyReleased events for the input 'To' textbox on the main view.

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void keyReleased(KeyEvent e)

     Classes this class requires    
        KeyAdapter       
     */
    class InputToListener extends KeyAdapter {
        
        /**
        * Validates the input then passes the To value for conversion and displays
        * the result
        * @param  e the generated KeyEvent    
        */
        @Override
        public void keyReleased(KeyEvent e) {
            try {
                //clear any error messages
		theView.clearMsg();    
                //using the To input so set to true
                theModel.setToBox(true);
                
                //validate input - check not using negative numbers
                if (theView.getToValue() < 0 && theModel.getUnitCategory() == 0) {                    
                    throw new NumberFormatException("No negative numbers.");
                } else { 
                    //perform the conversion
                    theModel.doConversion(theView.getToValue());
                    //display the result
                    theView.setFromValue(theModel.getConversionResult());
                }                

            } catch (NumberFormatException ex) {

                theView.displayMsg("Invalid input. " + ex.getMessage(), "error");
            }
        }
    }

     /*
     Handles itemStateChanged event for the Import Unit Category (Distance or Temperature) comboBox
     on the import dialog view.

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void itemStateChanged(ItemEvent e)

     Classes this class requires    
        ItemListener       
     */
    class ImportUnitCatListener implements ItemListener {
        
        /**
        * Updates the model with the Unit Category and updates the import dialog view
        * @param  e the generated ItemEvent   
        */
        @Override
        public void itemStateChanged(ItemEvent e) {
            
            //get the selected item form the comboBox
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                theModel.setUnitCategory(cb.getSelectedIndex());                
                //populate the To and From comboBoxes
                theImportView.setUnitFromItems(theModel.getConversionItems());
                theImportView.setUnitToItems(theModel.getConversionItems());
                //set the To measurement unit
                theImportView.setToCategory(1);
                //update the model
                theModel.setUnitToIndex(1);
            }
        }
    }

    /*
     Handles itemStateChanged event for the Unit Category (Distance or Temperature) 
     comboBox on the main view.

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void itemStateChanged(ItemEvent e)

     Classes this class requires    
        ItemListener       
     */
    class UnitCatListener implements ItemListener {
        
        /**
        * Updates the model with the Unit Category and updates the main view
        * @param  e the generated ItemEvent   
        */
        @Override
        public void itemStateChanged(ItemEvent e) {

            //get the selected item
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                theModel.setUnitCategory(cb.getSelectedIndex());
                //clear any error messages
                theView.clearMsg(); 
                //set the From measurement unit
                theView.setFromValue(1);
                //populate the To and From comboBoxes
                theView.setUnitFromItems(theModel.getConversionItems());
                theView.setUnitToItems(theModel.getConversionItems());
                //set the To measurement unit
                theView.setToCategory(1);
                //update the model
                theModel.setUnitToIndex(1);
            }
        }
    }

    /*
     Handles itemStateChanged event for the Import Unit From comboBox 
     on the import dialog view.

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void itemStateChanged(ItemEvent e)

     Classes this class requires    
        ItemListener       
     */
    class ImportUnitFromListener implements ItemListener {
        
        /**
        * Updates the model with the Import Unit From measurement
        * @param  e the generated ItemEvent   
        */
        @Override
        public void itemStateChanged(ItemEvent e) {
            
            //get the selected unit of measurement
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                //update the model
                theModel.setUnitFromIndex(cb.getSelectedIndex());
            }
        }
    }

    /*
     Handles itemStateChanged event for the Unit From comboBox 
     comboBox on the main view.

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void itemStateChanged(ItemEvent e)

     Classes this class requires    
        ItemListener       
     */
    class UnitFromListener implements ItemListener {
        
        /**
        * Updates the model with the From unit of measurement, passes the value to
        * convert and displays the result
        * @param  e the generated ItemEvent   
        */
        @Override
        public void itemStateChanged(ItemEvent e) {
            
            //get the selected value
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                //update the model with the selection
                theModel.setUnitFromIndex(cb.getSelectedIndex());
                theModel.setToBox(false);
                //perform and display the conversion
                theModel.doConversion(theView.getFromValue());
                theView.setToValue(theModel.getConversionResult());
            }
        }
    }
    
    /*
     Handles itemStateChanged event for the Import Unit To comboBox 
     on the import dialog view.

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void itemStateChanged(ItemEvent e)

     Classes this class requires    
        ItemListener       
     */
    class ImportUnitToListener implements ItemListener {
        
        /**
        * Updates the model with the To unit of measurement
        * @param  e the generated ItemEvent   
        */
        @Override
        public void itemStateChanged(ItemEvent e) {
            
            //get the selected unit of measurement
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                //update the model
                theModel.setUnitToIndex(cb.getSelectedIndex());
            }
        }
    }
    
     /*
     Handles itemStateChanged event for the Unit To comboBox 
     comboBox on the main view.

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void itemStateChanged(ItemEvent e)

     Classes this class requires    
        ItemListener   
    
     */
    class UnitToListener implements ItemListener {
        
        /**
        * Updates the model with the To unit of measurement, passes the value to
        * convert and displays the result
        * @param  e the generated ItemEvent   
        */
        @Override
        public void itemStateChanged(ItemEvent e) {
            
            //get the selected unit of measurement
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                //update the model with the selection
                theModel.setUnitToIndex(cb.getSelectedIndex());
                theModel.setToBox(false);
                //perform and display the model
                theModel.doConversion(theView.getFromValue());
                theView.setToValue(theModel.getConversionResult());
            }
        }
    }
    
    /*
     Handles actionPerformed event when the user clicks the Cancel button on the
     Import Dialog view.

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void actionPerformed(ActionEvent e)
    
     Classes this class requires    
        ActionListener   
    
     */
    class CancelListener implements ActionListener {
        
        /**
        * Closes the import dialog when the cancel button clicked 
        * @param  e the generated ActionEvent   
        */
        @Override
        public void actionPerformed(ActionEvent e) {
            //close the dialog box and return to the main view
            closeImportDialog();
        }
    }

     /*
     Handles actionPerformed event when the user clicks the Exit menu item

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void actionPerformed(ActionEvent e)
    
     Classes this class requires    
        ActionListener   
    
     */
    class ExitListener implements ActionListener {
        
        /**
        * Exits the application if the Exit menu item clicked 
        * @param  e the generated ActionEvent   
        */
        @Override
        public void actionPerformed(ActionEvent e) {
            //exit the application
            System.exit(0);

        }
    }
    
    /**
    * Sets the default initial state for the controls on the import dialog  
    */
    private void setInitialStateImporter() {
        
        //populate the unit category comboBox
        theImportView.setUnitCat(theModel.getUnitMenuItems());
        //populate the unit From measurement comboBox
        theImportView.setUnitFromItems(theModel.getConversionItems());
        //populate the unit To measurement comboBox
        theImportView.setUnitToItems(theModel.getConversionItems());
        //set the To selection to the second comboBox item
        theImportView.setToCategory(1);
    }

    /**
    * Sets the default initial state for the controls on the main view  
    */
    private void setInitialState() {

        //set the From selection to the second comboBox item
        theView.setFromValue(1);        
        //populate the unit From measurement comboBox
        theView.setUnitFromItems(theModel.getConversionItems());
        //populate the unit category comboBox
        theView.setUnitToItems(theModel.getConversionItems());
        //populate the unit category comboBox
        theView.setUnitCat(theModel.getUnitMenuItems());
    }

    /*
     Handles actionPerformed event when the user clicks the Import menu item

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void actionPerformed(ActionEvent e)
    
     Classes this class requires    
        ActionListener   
    
     */
    class ImportMenuListener implements ActionListener {

        /**
        * Sets the initial state of the import dialog box controls and then makes
        * it visible
        * @param  e the generated ActionEvent   
        */
        @Override
        public void actionPerformed(ActionEvent e) {

            setInitialStateImporter();
            theImportView.setVisible(true);
        }
    }

    /**
    * Closes the Import Dialog view and resets the values on the main view controls
    */
    public void closeImportDialog() {
        
        //hide the dialog and reset any values
        theImportView.setVisible(false);
        theImportView.clearForm();

        //set the intial state of the main view and close the dialog view
        theModel.setUnitFromIndex(0);
        theModel.setUnitToIndex(1);
        theImportView.dispose();
        setInitialState();
    }

    /*
     Validates that the import and save file path location when a users wants to 
     import a file of values to convert.

     @author David Pyle 041110777
     @version 1.0
     @since 4/4/2016

     Methods:
        + void actionPerformed(ActionEvent e)
    
     Classes this class requires    
        ActionListener   
    
     */
    class ImportListener implements ActionListener {

          /**
        * Validates file import and save locations and calls the model to convert
        * the file of values. Displays conversion result message.
        * @param  e the generated ActionEvent   
        */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            
            String result;
            //throw an exception if the either of the file path locations are not set
            try {
                if (theImportView.getImportLocation() == null || theImportView.getSaveLocation() == null) {
                    throw new NullPointerException();
                }
                //if set then try to convert the import file
                result = theModel.convertFile(theImportView.getImportLocation(), theImportView.getSaveLocation());
                   
                //show the success message
                if (result.equals("OK")) {
                    theImportView.displayMsg("Conversion successful!", "File Conversion", "information");

                } else {
                    //show the error message
                    theImportView.displayMsg("Conversion unsuccessful.\n" + result, "File Conversion Error", "error");
                }
                //close the dialog
                closeImportDialog();

            } catch (NullPointerException ex) {
                //show error message if both import and/or save lcoations not set
                theImportView.displayMsg("Ensure import file path and save path are set.", "File Path Missing", "warning");
            }
        }
    }
}
