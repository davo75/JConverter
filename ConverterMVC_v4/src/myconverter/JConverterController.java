/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myconverter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

/**
 *
 * @author davidpyle
 */
public class JConverterController {

    private final JConverterView theView;
    private final JConverterModel theModel;
    private final JImporterView theImportView;

    public JConverterController(JConverterView theView, JConverterModel theModel, JImporterView theImportView) {

        this.theView = theView;
        this.theModel = theModel;
        this.theImportView = theImportView;

        //setup listeners
        setupListeners();
        //set the initial state of the main view
        setInitialState();
    }

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

        this.theImportView.addUnitCatListener(new ImportUnitCatListener());
        this.theImportView.addUnitFromListener(new ImportUnitFromListener());
        this.theImportView.addUnitToListener(new ImportUnitToListener());
        this.theImportView.addCancelListener(new CancelListener());
        this.theImportView.addImportListener(new ImportListener());

    }

    class InputFromListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            try {
		theView.clearMsg();
                theModel.setToBox(false);
                theModel.doConversion(theView.getFromValue());
                theView.setToValue(theModel.getConversionResult());

            } catch (NumberFormatException ex) {
		//if (theModel.getUnitCategory() ==0 && e.getKeyChar() == '-') {
		    //System.out.println("negative entered for distance");
		
                theView.displayMsg("Enter a proper number.", "Input Warning", "warning");
		//}
            }
        }
    }

    class InputToListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            try {

                theModel.setToBox(true);
                theModel.doConversion(theView.getToValue());
                theView.setFromValue(theModel.getConversionResult());

            } catch (NumberFormatException ex) {

                theView.displayMsg("Enter a proper number.", "Input Warning", "warning");
            }
        }
    }

    class ImportUnitCatListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                theModel.setUnitCategory(cb.getSelectedIndex());

                theImportView.setUnitFromItems(theModel.getConversionItems());
                theImportView.setUnitToItems(theModel.getConversionItems());
                theImportView.setToCategory(1);
                theModel.setUnitToIndex(1);
            }
        }
    }

    class UnitCatListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                theModel.setUnitCategory(cb.getSelectedIndex());

                theView.setFromValue(1);
                theView.setUnitFromItems(theModel.getConversionItems());
                theView.setUnitToItems(theModel.getConversionItems());
                theView.setToCategory(1);
                theModel.setUnitToIndex(1);
            }
        }
    }

    class ImportUnitFromListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                theModel.setUnitFromIndex(cb.getSelectedIndex());
            }
        }
    }

    class UnitFromListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                theModel.setUnitFromIndex(cb.getSelectedIndex());
                theModel.setToBox(false);
                theModel.doConversion(theView.getFromValue());
                theView.setToValue(theModel.getConversionResult());
            }
        }
    }

    class ImportUnitToListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                theModel.setUnitToIndex(cb.getSelectedIndex());
            }
        }
    }

    class UnitToListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                theModel.setUnitToIndex(cb.getSelectedIndex());
                theModel.setToBox(false);
                theModel.doConversion(theView.getFromValue());
                theView.setToValue(theModel.getConversionResult());
            }
        }
    }

    class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            closeImportDialog();
        }
    }

    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.exit(0);

        }
    }

    private void setInitialStateImporter() {
        
        theImportView.setUnitCat(theModel.getUnitMenuItems());
        theImportView.setUnitFromItems(theModel.getConversionItems());
        theImportView.setUnitToItems(theModel.getConversionItems());
        theImportView.setToCategory(1);
    }

    private void setInitialState() {

        //set initial value on main view
        theView.setFromValue(1);        
        theView.setUnitFromItems(theModel.getConversionItems());
        theView.setUnitToItems(theModel.getConversionItems());
        theView.setUnitCat(theModel.getUnitMenuItems());
    }

    class ImportMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            setInitialStateImporter();
            theImportView.setVisible(true);
        }
    }

    public void closeImportDialog() {

        theImportView.setVisible(false);
        theImportView.clearForm();

        theModel.setUnitFromIndex(0);
        theModel.setUnitToIndex(1);
        theImportView.dispose();
        setInitialState();
    }

    class ImportListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String result;

            try {
                if (theImportView.getImportLocation() == null || theImportView.getSaveLocation() == null) {
                    throw new NullPointerException();
                }

                result = theModel.convertFile(theImportView.getImportLocation(), theImportView.getSaveLocation());

                if (result.equals("OK")) {
                    theImportView.displayMsg("Conversion successful!", "File Conversion", "information");

                } else {
                    theImportView.displayMsg("Conversion unsuccessful.\n" + result, "File Conversion Error", "error");
                }
                closeImportDialog();

            } catch (NullPointerException ex) {

                theImportView.displayMsg("Ensure import file path and save path are set.", "File Path Missing", "warning");
            }
        }
    }
}
