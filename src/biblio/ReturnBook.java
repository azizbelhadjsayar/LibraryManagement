/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package biblio;

import Barcode.Barcode_Scanner;
import Entities.BookItem;
import Entities.Booklending;
import Services.AccountService;
import Services.BookItemService;
import Services.BookLendingService;
import Services.BookLendingService.Row;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class ReturnBook extends javax.swing.JFrame {

    /**
     * Creates new form ReturnBook
     */
    public ReturnBook() {
        initComponents();
        BookLendingService BLS = new BookLendingService();
        BookLendingsTable(BLS.getAllBookLendings());
    }

    
    public void BookLendingsTable(ArrayList<Row> liste){
        try{
            
            DefaultTableModel model = (DefaultTableModel)lendingsTABLE.getModel();
            model.setRowCount(0);
            Object rowData[] = new Object[6];
            for(Row r : liste){
                rowData[0] = r.id();
                rowData[1] = r.bookitem_barcode();
                rowData[2] = r.account_id();
                rowData[3] = r.creation_date();
                rowData[4] = r.due_date();
                rowData[5] = r.return_date();
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

        activeComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lendingsTABLE = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        activeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Returned", "Not returned" }));
        activeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeComboBoxActionPerformed(evt);
            }
        });

        lendingsTABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "BOOK ITEM BARCODE", "ACCOUNT ID", "CREATION DATE", "DUE DATE", "RETURN DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(lendingsTABLE);
        if (lendingsTABLE.getColumnModel().getColumnCount() > 0) {
            lendingsTABLE.getColumnModel().getColumn(0).setPreferredWidth(15);
            lendingsTABLE.getColumnModel().getColumn(2).setPreferredWidth(40);
        }

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("PROCEED TO RETURN CONFIRMATION ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("SCAN BARCODE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(activeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(activeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void activeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeComboBoxActionPerformed
        // TODO add your handling code here:
        String activeState = (String)activeComboBox.getSelectedItem();
        BookLendingService BLS = new BookLendingService();
        switch(activeState){
            case "Returned" -> BookLendingsTable(BLS.getReturnedBookLendings());
            case "Not returned" -> BookLendingsTable(BLS.getNotReturnedBookLendings());
            case "All" -> BookLendingsTable(BLS.getAllBookLendings());
            default -> BookLendingsTable(BLS.getAllBookLendings());      
        }
    }//GEN-LAST:event_activeComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AccountService AS = new AccountService();
        BookItemService BIS = new BookItemService();
        BookLendingService BLS = new BookLendingService();
        DefaultTableModel model = (DefaultTableModel)lendingsTABLE.getModel();
        int row = lendingsTABLE.getSelectedRow();
        int account_id = (int) model.getValueAt(row, 2);
        String barcode_item = (String) model.getValueAt(row, 1);
        int booklending_id = (int) model.getValueAt(row, 0);
        this.setVisible(false);
        ConfirmReturn obj = new ConfirmReturn(AS.getAccountbyId(account_id),BIS.getBookItembyBarcode(barcode_item), BLS.getNotReturnedBookLendingbyID(booklending_id));
        obj.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running asynchronous task
            try {
                Barcode_Scanner.barcodeResult =null;
                Barcode_Scanner.startScanning();
                while(true) {
                    String result = Barcode_Scanner.barcodeResult;
                    sleep(100);
                    if (result!=null) {
                        //traitement ................................
                        AccountService AS = new AccountService();
                        BookItemService BIS = new BookItemService();
                        BookLendingService BLS = new BookLendingService();
                        Booklending bl = BLS.BookLendingbyBarcode(result);
                        if(bl!=null) {
                            this.setVisible(false);
                            ConfirmReturn obj = new ConfirmReturn(AS.getAccountbyId(bl.getAccount_id()),BIS.getBookItembyBarcode(bl.getBookItem_barcode()), bl);
                            obj.setVisible(true);
                            Barcode_Scanner.scanningThread.interrupt();
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"NO BOOK LENDING FOUND WITH THIS BARCODE");
                            Barcode_Scanner.scanningThread.interrupt();
                        }
                        //...........................................
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        });
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> activeComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lendingsTABLE;
    // End of variables declaration//GEN-END:variables
}
