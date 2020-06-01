package upf.edu.gephi.plugin.columnsmerge.ui;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.gephi.datalab.spi.DialogControls;
import org.gephi.datalab.spi.Manipulator;
import org.gephi.datalab.spi.ManipulatorUI;
import org.gephi.graph.api.Table;
import org.openide.util.NbPreferences;
import org.gephi.graph.api.Column;
import upf.edu.gephi.plugin.columnsmerge.ColumnCalculator;

/**
 *
 * @author XGG3
 * @editor oscarfont
 */
public class ColumnCalculatorUI extends javax.swing.JPanel implements ManipulatorUI {

    private ColumnCalculator manipulator;
    private DialogControls dialogControls;
    private Table table;
    
    /**
     * Creates new form ColumnCalculatorUI
     */
    public ColumnCalculatorUI() {
        initComponents();
        formulaTextField.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                refreshOkButton();
            }

            public void removeUpdate(DocumentEvent e) {
                refreshOkButton();
            }

            public void changedUpdate(DocumentEvent e) {
                refreshOkButton();
            }
            
            private void refreshOkButton() {
                String formulaText = formulaTextField.getText();
                String columnText = titleTextField.getText();
                // Formula not empty, title not empty, table not empty and table does not have a column with the new title titleTextField
                dialogControls.setOkButtonEnabled((formulaText != null && columnText != null) && (!formulaText.isEmpty() && !columnText.isEmpty()) && table != null && !table.hasColumn(columnText));
            }
        });
    }
    
    public String generateSelectedColumnsLabel(ColumnCalculator mani){
        
        String outputLabel = selectedColumns.getText();
        
        Column[] columnas = manipulator.getColumns();
        Integer columnasLength = columnas.length;
        for( int i = 0; i < columnasLength; ++i ){
            String columnTitle = columnas[i].getTitle();
            String columnIndex = String.valueOf(i);
            outputLabel += "<tr><td style='width:150px'>" + columnTitle + "</td><td style='width:150px'>$" + columnIndex + "</td></tr>"; 
        }
        
        outputLabel += "</table></html>";
                
        return outputLabel;
    }
    
    @Override
    public void setup(Manipulator m, DialogControls dialogControls) {
        //Receive our manipulator instance:
        this.manipulator = (ColumnCalculator) m; //We know the type of manipulator we are going to receive so cast is safe
        //An object to control the dialog if necessary 
        this.table = this.manipulator.getTable();
        //Enable/disable the Ok button of the dialog for validation purposes
        this.dialogControls = dialogControls;
        
        String columnsTable = generateSelectedColumnsLabel(this.manipulator);
        selectedColumns.setText(columnsTable);
    }

    @Override
    public void unSetup() {
        //Called when the dialog is closed, canceled or accepted. Pass necessary data to the manipulator:
        //Add a title to the new Columns and a formula to the manipulator
        manipulator.setColumnTitle(titleTextField.getText());
        manipulator.setCustomFormula(formulaTextField.getText());
        //Send to class ColumnCalculator column's title and the input formula
        NbPreferences.forModule(ColumnCalculator.class).put(ColumnCalculator.COLUMN_TITLE_SAVED_PREFERENCES, manipulator.getColumnTitle());
        NbPreferences.forModule(ColumnCalculator.class).put(ColumnCalculator.CUSTOM_FORMULA_SAVED_PREFERENCES, manipulator.getCustomFormula());
    }

    @Override
    public String getDisplayName() {
        //Provide title for the dialog:
        return manipulator.getName();//For example, the manipulator name
    }
    
    @Override
    public JPanel getSettingsPanel() {
        //Provide the JPanel to create the UI dialog
        //A good practice is to extend JPanel and just return this object
        return this;
    }
    
    @Override
    public boolean isModal() {
        return true;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formulaLabel = new javax.swing.JLabel();
        formulaTextField = new javax.swing.JTextField();
        titleDescriptionLabel = new javax.swing.JLabel();
        selectedColumnsLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        selectedColumns = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(formulaLabel, org.openide.util.NbBundle.getMessage(ColumnCalculatorUI.class, "ColumnCalculatorUI.formulaLabel.text")); // NOI18N

        formulaTextField.setText(org.openide.util.NbBundle.getMessage(ColumnCalculatorUI.class, "ColumnCalculatorUI.formulaTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(titleDescriptionLabel, org.openide.util.NbBundle.getMessage(ColumnCalculatorUI.class, "ColumnCalculatorUI.titleDescriptionLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(selectedColumnsLabel, org.openide.util.NbBundle.getMessage(ColumnCalculatorUI.class, "ColumnCalculatorUI.selectedColumnsLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(titleLabel, org.openide.util.NbBundle.getMessage(ColumnCalculatorUI.class, "ColumnCalculatorUI.titleLabel.text")); // NOI18N

        titleTextField.setText(org.openide.util.NbBundle.getMessage(ColumnCalculatorUI.class, "ColumnCalculatorUI.titleTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(selectedColumns, org.openide.util.NbBundle.getMessage(ColumnCalculatorUI.class, "ColumnCalculatorUI.selectedColumns.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectedColumnsLabel)
                            .addComponent(titleDescriptionLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(titleLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(formulaLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(formulaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectedColumns)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleDescriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(selectedColumnsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedColumns)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(formulaLabel)
                    .addComponent(formulaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );
    }// </editor-fold>//GEN-END:initComponents

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel formulaLabel;
    private javax.swing.JTextField formulaTextField;
    private javax.swing.JLabel selectedColumns;
    private javax.swing.JLabel selectedColumnsLabel;
    private javax.swing.JLabel titleDescriptionLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTextField;
    // End of variables declaration//GEN-END:variables


}