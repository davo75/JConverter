/*
 This view class is reponsible for import file view. It allows the user
 to choose a file of values to import, set a path to an output file and select
 between several units of measurements to for conversion.
 
 @author David Pyle 041110777
 @version 1.0
 @since 4/4/2016
  
 Methods:
    + void addUnitCatListener(ItemListener listenUnitCat)
    + void addUnitFromListener(ItemListener listenUnitFrom)
    + void addUnitToListener(ItemListener listenUnitTo)
    + void addImportListener(ActionListener listenForImportBtn)
    + void addCancelListener(ActionListener listenForCancelBtn)
    + void addBrowseListener(ActionListener listenForBrowse)
    + void addSaveListener(ActionListener listenForSave)
    + void setImportLocation(String location)
    + String getImportLocation()
    + void setSaveLocation(String location)
    + String getSaveLocation()
    + void setUnitCategory(int unitCat)
    + int getUnitCategory()
    + void setUnitFromItems(String[] unitFromCat)
    + void setUnitCat(String[] unitCat)
    + void setUnitToItems(String[] unitToCat)
    + void setToCategory(int index)
    + void displayMsg(String msg, String msgTitle, String msgType)
    + void clearForm()
    - void btnBrowseActionPerformed(java.awt.event.ActionEvent evt)
    - void btnSaveActionPerformed(java.awt.event.ActionEvent evt)


 Classes this class requires    
    java.awt.event.ActionListener;
    java.awt.event.ItemListener;
    java.io.File;
    javax.swing.JFileChooser;
    javax.swing.JOptionPane;
    javax.swing.filechooser.FileNameExtensionFilter;
 */

package myconverter;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author davidpyle
 */
public class JImporterView extends javax.swing.JDialog {
    
    
    private String fileImportLocation;
    private String fileSaveLocation;
    private final JFileChooser fc;
    
    /**
    * Constructor
    * 
    * @param parent the parent form
    * @param modal sets the modality of the dialog window
    */
    public JImporterView(java.awt.Frame parent, boolean modal) {
        //set the parnt form and modality
        super(parent, modal);
        //initialise interface components
        initComponents();
        //create a file chooser dialog
        fc = new JFileChooser();
    }

    /**
     * Adds a listener to the Unit Category comboBox
     *
     * @param listenUnitCat the ItemListener that listens for a change to the 
     * Unit Category comboBox
     */
    public void addUnitCatListener(ItemListener listenUnitCat) {
        cbUnitCat.addItemListener(listenUnitCat);
    }
    
    /**
     * Adds a listener to the Unit From comboBox
     *
     * @param listenUnitFrom the ItemListener that listens for a change to the 
     * Unit From comboBox
     */
    public void addUnitFromListener(ItemListener listenUnitFrom) {
        cbConvertFrom.addItemListener(listenUnitFrom);
    }
    
    public void addUnitToListener(ItemListener listenUnitTo) {
        cbConvertTo.addItemListener(listenUnitTo);
    }
    
    /**
     * Adds a listener to the import button
     *
     * @param listenForImportBtn the ActionListener that listens for an import 
     * button click
     */
    public void addImportListener(ActionListener listenForImportBtn) {
        btnImport.addActionListener(listenForImportBtn);
    }
    
    /**
     * Adds a listener to the cancel button
     *
     * @param listenForCancelBtn the ActionListener that listens for a cancel 
     * button click
     */
    public void addCancelListener(ActionListener listenForCancelBtn) {
        btnImportCancel.addActionListener(listenForCancelBtn);
    }
    
    /**
     * Adds a listener to the browse button
     *
     * @param listenForBrowse the ActionListener that listens for a browse 
     * button click
     */
    public void addBrowseListener(ActionListener listenForBrowse) {
        btnBrowse.addActionListener(listenForBrowse);
    }
    
    /**
     * Adds a listener to the save button
     *
     * @param listenForSave the ActionListener that listens for a save 
     * button click
     */
    public void addSaveListener(ActionListener listenForSave) {
        btnSave.addActionListener(listenForSave);
    }
    
     /**
     * Sets the location for the import file
     *
     * @param location the path to the input file 
     */
    public void setImportLocation(String location) {
        fileImportLocation = location;
    }
    
     /**
     * Gets the location for the import file
     *
     * @return  the path to the input file 
     */
    public String getImportLocation() {
        return fileImportLocation;
    }
    
     /**
     * Sets the save location for the output file
     *
     * @param location the path to the output file 
     */
    public void setSaveLocation(String location) {
        fileSaveLocation = location;
    }
    
    /**
     * Gets the location for the output file
     *
     * @return  the path to the output file 
     */
    public String getSaveLocation() {
        return fileSaveLocation;
    }
    
    /**
     * Sets the index of a Unit Category menu item
     *
     * @param unitCat the index of the menu item
     */
    public void setUnitCategory(int unitCat) {
        cbUnitCat.setSelectedIndex(unitCat);
    }
    
    public int getUnitCategory() {
        return cbUnitCat.getSelectedIndex();
    }
    
    /**
     * Populates the Unit From comboxBox
     *
     * @param unitFromCat the menu items to add
     */
    public void setUnitFromItems(String[] unitFromCat) {
        
        //clear any items from the comboBox
        cbConvertFrom.removeAllItems();       
        //add the items
        for (String conversion : unitFromCat) {
            cbConvertFrom.addItem(conversion);
        }  	
    }
    
    /**
     * Populates the Unit Category comboxBox
     *
     * @param unitCat the menu items to add
     */
     public void setUnitCat(String[] unitCat) {
        //clear any items from the comboBox
        cbUnitCat.removeAllItems();       
         //add the items
        for (String conversion : unitCat) {
            cbUnitCat.addItem(conversion);
        }  	
    }
    
     /**
     * Populates the Unit To comboxBox
     *
     * @param unitToCat the menu items to add
     */
     public void setUnitToItems(String[] unitToCat) {
        //clear any items from the comboBox
        cbConvertTo.removeAllItems();       
        //add the items
        for (String conversion : unitToCat) {
            cbConvertTo.addItem(conversion);
        }  	
    }
    
     /**
     * Sets the index of a Unit To menu item
     *
     * @param index the index of the menu item
     */
     public void setToCategory(int index) {
        cbConvertTo.setSelectedIndex(index);
    }
     
    /**
     * Displays error/warning messages
     *
     * @param msg the message to display
     * @param msgTitle the message title
     * @param msgType the message type (error or warning)
     */
    public void displayMsg(String msg, String msgTitle, String msgType) {
        switch(msgType) {
            //show error message
            case "error":
                JOptionPane.showMessageDialog(this, msg, msgTitle, JOptionPane.ERROR_MESSAGE);
                break;
                //show warning message
            case "warning":
                JOptionPane.showMessageDialog(this, msg, msgTitle, JOptionPane.WARNING_MESSAGE);
                break;
                //show other messages
            default:
                JOptionPane.showMessageDialog(this, msg);                        
        }        
    }
    
    /**
     * Reset the import dialog file input and output fields
     *
     */
    public void clearForm() {
        //set import file path text box to null
        this.tfImportFilePath.setText(null);
        //set save location path text box to null
        this.tfSaveLocPath.setText(null);
        //set save location to null
        setSaveLocation(null);
        //set import locaton to null
        setImportLocation(null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblFileToImport = new javax.swing.JLabel();
        lblConvertFrom = new javax.swing.JLabel();
        lblConvertTo = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        cbConvertFrom = new javax.swing.JComboBox<String>();
        cbConvertTo = new javax.swing.JComboBox<String>();
        btnImport = new javax.swing.JButton();
        btnImportCancel = new javax.swing.JButton();
        lblFileSaveLoc = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        lblUnitCat = new javax.swing.JLabel();
        cbUnitCat = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        tfImportFilePath = new javax.swing.JTextField();
        tfSaveLocPath = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("JConverter->Import");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "File Import", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 13))); // NOI18N

        lblFileToImport.setText("Select file to import");

        lblConvertFrom.setText("Convert From");

        lblConvertTo.setText("Convert To");

        btnBrowse.setText("Browse Files");
        btnBrowse.setPreferredSize(new java.awt.Dimension(90, 23));
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        btnImport.setLabel("Convert");

        btnImportCancel.setText("Cancel");

        lblFileSaveLoc.setText("Choose save location");

        btnSave.setText("Save Location");
        btnSave.setPreferredSize(new java.awt.Dimension(90, 23));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblUnitCat.setText("Unit Category");

        cbUnitCat.setName("cbImportUnitCat"); // NOI18N

        tfImportFilePath.setEditable(false);

        tfSaveLocPath.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblUnitCat)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbUnitCat, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblConvertFrom)
                                            .addComponent(lblConvertTo))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnImportCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(cbConvertTo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbConvertFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblFileToImport)
                                            .addComponent(lblFileSaveLoc))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfImportFilePath, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                                    .addComponent(tfSaveLocPath, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFileToImport)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfImportFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFileSaveLoc)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSaveLocPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUnitCat)
                    .addComponent(cbUnitCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConvertFrom)
                    .addComponent(cbConvertFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConvertTo)
                    .addComponent(cbConvertTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImport)
                    .addComponent(btnImportCancel))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Displays a file chooser dialog when the browse button is clicked
     *
     * @param evt the browse button click event
     */
    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        
        //set the filter for choosing files to text files only
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt", "text");
        fc.setFileFilter(filter);
        //open the file chooser dialog
        int returnVal = fc.showOpenDialog(null);
        //get the input file
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();           
            setImportLocation(file.getAbsolutePath());
	    tfImportFilePath.setText(getImportLocation());
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    /**
     * Displays a file chooser dialog when the save button is clicked
     *
     * @param evt the save button click event
     */
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        //set the filter for choosing files to text files only
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt", "text");
        fc.setFileFilter(filter);
        //open the file chooser dialog
        int returnVal = fc.showSaveDialog(null);
        //set the output file location
        if (returnVal == JFileChooser.APPROVE_OPTION) {            
            File file = fc.getSelectedFile();            
            String filePath = file.getAbsolutePath();
            if (!filePath.endsWith(".txt")) {
                filePath = filePath + ".txt";
            }
            setSaveLocation(filePath);
            tfSaveLocPath.setText(getSaveLocation());
            
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnImportCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbConvertFrom;
    private javax.swing.JComboBox<String> cbConvertTo;
    private javax.swing.JComboBox cbUnitCat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblConvertFrom;
    private javax.swing.JLabel lblConvertTo;
    private javax.swing.JLabel lblFileSaveLoc;
    private javax.swing.JLabel lblFileToImport;
    private javax.swing.JLabel lblUnitCat;
    private javax.swing.JTextField tfImportFilePath;
    private javax.swing.JTextField tfSaveLocPath;
    // End of variables declaration//GEN-END:variables
}
