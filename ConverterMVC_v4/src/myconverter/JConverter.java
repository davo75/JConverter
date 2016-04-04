/*
 This program converts between metric and imperial measurements. 
 
 @author David Pyle 041110777
 @version 1.0
 @since 4/4/2016
  
 Methods:
    + void main(String[] args)  

 */
package myconverter;

/**
 *
 * @author davidpyle
 */
public class JConverter {
     /**
     * Main method that creates the model, views and controller objects and then
     * displays the view
     * @param args not used
     */
     
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
        //create main view
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
