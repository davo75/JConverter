/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
     * Creates new form NewJDialog
     * @param parent
     * @param modal
     */
    public JImporterView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fc = new JFileChooser();
    }

    void addUnitCatListener(ItemListener listenUnitCat) {
        cbUnitCat.addItemListener(listenUnitCat);
    }
    
    void addUnitFromListener(ItemListener listenUnitFrom) {
        cbConvertFrom.addItemListener(listenUnitFrom);
    }
    
    void addUnitToListener(ItemListener listenUnitTo) {
        cbConvertTo.addItemListener(listenUnitTo);
    }
    
    void addImportListener(ActionListener listenForImportBtn) {
        btnImport.addActionListener(listenForImportBtn);
    }
    
 
    
    void addCancelListener(ActionListener listenForCancelBtn) {
        btnImportCancel.addActionListener(listenForCancelBtn);
    }
    
    void addBrowseListener(ActionListener listenForBrowse) {
        btnBrowse.addActionListener(listenForBrowse);
    }
    
    void addSaveListener(ActionListener listenForSave) {
        btnSave.addActionListener(listenForSave);
    }
    
    public void setImportLocation(String location) {
        fileImportLocation = location;
    }
    
    public String getImportLocation() {
        return fileImportLocation;
    }
    
    public void setSaveLocation(String location) {
        fileSaveLocation = location;
    }
    
    public String getSaveLocation() {
        return fileSaveLocation;
    }
    
    public void setUnitCategory(int unitCat) {
        cbUnitCat.setSelectedIndex(unitCat);
    }
    
    public int getUnitCategory() {
        return cbUnitCat.getSelectedIndex();
    }
    
    public void setUnitFromItems(String[] unitFromCat) {
        
        cbConvertFrom.removeAllItems();       
        
        for (String conversion : unitFromCat) {
            cbConvertFrom.addItem(conversion);
        }  	
    }
    
     public void setUnitCat(String[] unitCat) {
        
        cbUnitCat.removeAllItems();       
        
        for (String conversion : unitCat) {
            cbUnitCat.addItem(conversion);
        }  	
    }
    
     public void setUnitToItems(String[] unitToCat) {
        
        cbConvertTo.removeAllItems();       
        
        for (String conversion : unitToCat) {
            cbConvertTo.addItem(conversion);
        }  	
    }
    
     public void setToCategory(int index) {
        cbConvertTo.setSelectedIndex(index);
    }
     
   
    void displayMsg(String msg, String msgTitle, String msgType) {
        switch(msgType) {
            case "error":
                JOptionPane.showMessageDialog(this, msg, msgTitle, JOptionPane.ERROR_MESSAGE);
                break;
            case "warning":
                JOptionPane.showMessageDialog(this, msg, msgTitle, JOptionPane.WARNING_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(this, msg);                        
        }        
    }
    
    
    public void clearForm() {
        this.tfImportFilePath.setText(null);
        this.tfSaveLocPath.setText(null);
        setSaveLocation(null);
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

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        //JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt", "text");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();           
            setImportLocation(file.getAbsolutePath());
	    tfImportFilePath.setText(getImportLocation());
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        //JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt", "text");
        fc.setFileFilter(filter);
        int returnVal = fc.showSaveDialog(null);
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