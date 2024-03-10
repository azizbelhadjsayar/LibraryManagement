/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package biblio;

import Services.LibraryCardService;
import Services.LibraryCardService.Row;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bchir
 */
public class LibraryCardStatistics extends javax.swing.JFrame {

    /**
     * Creates new form LibraryCardStatistics
     */
    public LibraryCardStatistics() {
        initComponents();
        libraryCardsTable();
    }
    public void libraryCardsTable(ArrayList<Row> liste){
                try{
            
            DefaultTableModel model = (DefaultTableModel)libraryCardsTable.getModel();
            model.setRowCount(0);
            Object rowData[] = new Object[6];
            for(Row r : liste){
                rowData[0] = r.id();
                rowData[1] = r.email();
                rowData[2] = r.username();
                rowData[3] = r.issuedAt();
                rowData[4] = r.dateEndSubscription();
                rowData[5] = r.active();
                model.addRow(rowData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void libraryCardsTable(){
        try{
            DefaultTableModel model = (DefaultTableModel)libraryCardsTable.getModel();
            model.setRowCount(0);
            LibraryCardService lCS = new LibraryCardService();
            ArrayList<Row> rows = lCS.getLibraryCards();
            Object rowData[] = new Object[6];
            for(Row r : rows){
                rowData[0] = r.id();
                rowData[1] = r.email();
                rowData[2] = r.username();
                rowData[3] = r.issuedAt();
                rowData[4] = r.dateEndSubscription();
                rowData[5] = r.active();
                model.addRow(rowData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        libraryCardsTable = new javax.swing.JTable();
        modifyCardButton = new javax.swing.JButton();
        idSearchButton = new javax.swing.JButton();
        activeComboBox = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        idSearchTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Library Cards", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(0, 153, 153))); // NOI18N

        libraryCardsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "account_id", "email", "username", "Issued At", "End of subscription", "Active"
            }
        ));
        jScrollPane1.setViewportView(libraryCardsTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        modifyCardButton.setText("Modify Card");
        modifyCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyCardButtonActionPerformed(evt);
            }
        });

        idSearchButton.setText("Search by ID");
        idSearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                idSearchButtonMouseClicked(evt);
            }
        });
        idSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idSearchButtonActionPerformed(evt);
            }
        });

        activeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Non Active", "AlI" }));
        activeComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activeComboBoxMouseClicked(evt);
            }
        });
        activeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeComboBoxActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/biblio/assets/back-arrow .png"))); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Generate new card");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(idSearchTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idSearchButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(activeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(modifyCardButton)
                        .addGap(23, 23, 23)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(idSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyCardButton)
                    .addComponent(idSearchButton)
                    .addComponent(activeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idSearchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idSearchButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void activeComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activeComboBoxMouseClicked
       
    }//GEN-LAST:event_activeComboBoxMouseClicked

    private void activeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeComboBoxActionPerformed
        String activeState = (String)activeComboBox.getSelectedItem();
        LibraryCardService lCS = new LibraryCardService();
        switch(activeState){
            case "Active" -> libraryCardsTable(lCS.getActiveLibraryCards());
            case "Non Active" -> libraryCardsTable(lCS.getNonActiveLibraryCards());
            case "All" -> libraryCardsTable(lCS.getLibraryCards());
            default -> libraryCardsTable(lCS.getLibraryCards());       
        }
    }//GEN-LAST:event_activeComboBoxActionPerformed

    private void idSearchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idSearchButtonMouseClicked
        try{
            Integer id = Integer.valueOf(idSearchTextField.getText());
            ArrayList<Row> liste = new ArrayList<>();
            LibraryCardService lCS = new LibraryCardService();
            liste.add(lCS.getInfoById(id));
            libraryCardsTable(liste);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_idSearchButtonMouseClicked

    private void modifyCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyCardButtonActionPerformed
        this.setVisible(false);
        new Subscription().setVisible(true);
    }//GEN-LAST:event_modifyCardButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibraryCardStatistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryCardStatistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryCardStatistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryCardStatistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibraryCardStatistics().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> activeComboBox;
    private javax.swing.JButton idSearchButton;
    private javax.swing.JTextField idSearchTextField;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable libraryCardsTable;
    private javax.swing.JButton modifyCardButton;
    // End of variables declaration//GEN-END:variables
}
