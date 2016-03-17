/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myconverter;

/**
 *
 * @author davidpyle
 */
public class JConverter {

    public static void main(String[] args) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //creat main view
        JConverterView theView = new JConverterView();
	//create the Importer Dialog view
        JImporterView theImportView = new JImporterView(theView, true);
	//create the model
        JConverterModel theModel = new JConverterModel();	
	//create the contoller
        JConverterController theController = new JConverterController(theView, theModel, theImportView);
	//set the main form visible
        theView.setVisible(true);
    }

}
