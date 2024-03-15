/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package biblio;

import Barcode.Barcode_Scanner;
import Entities.Author;
import Entities.Book;
import Entities.Booklending;
import Services.AccountService;
import Services.AuthorService;
import Services.BookItemService;
import Services.BookLendingService;
import Services.BookService;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class newBook extends javax.swing.JFrame {

    /**
     * Creates new form newBook
     */
    public newBook() {
        initComponents();
        
    }
     
    private boolean itemBarcodeExists(String searchString) {
        DefaultTableModel model = (DefaultTableModel) itemsBARCODES.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            Object rowData = model.getValueAt(row, 0); // Assuming the string is in the first column
            if (rowData != null && rowData.toString().equals(searchString)) {
                return true; // String already exists
            }
        }
        return false; // String does not exist
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        authorInput = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        languageInput = new javax.swing.JTextField();
        pagesInput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        titleInput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        priceInput = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemsBARCODES = new javax.swing.JTable();
        scanBUTTON = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/biblio/assets/newbook.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Author");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Title");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Language");

        pagesInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pagesInputKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Pages");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Items");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Price");

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/biblio/assets/add.png"))); // NOI18N
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/biblio/assets/addAuthor.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/biblio/assets/back.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/biblio/assets/close.png"))); // NOI18N
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        itemsBARCODES.setBackground(new java.awt.Color(204, 255, 204));
        itemsBARCODES.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BARCODES"
            }
        ));
        jScrollPane1.setViewportView(itemsBARCODES);

        scanBUTTON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/biblio/assets/barcode-scan.png"))); // NOI18N
        scanBUTTON.setText("SCAN ITEM");
        scanBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanBUTTONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(70, 70, 70))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(titleInput)
                            .addComponent(languageInput, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(pagesInput, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(authorInput, 0, 223, Short.MAX_VALUE)
                            .addComponent(priceInput))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scanBUTTON))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(authorInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(titleInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(languageInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pagesInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(priceInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scanBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        AuthorService aus = new AuthorService();
        ArrayList <Author> authors = aus.getAllAuthors();
        String[] items = {};
        for(int i=0; i<authors.size();i++) {
            String item = authors.get(i).getId()+"."+authors.get(i).getFullname();
            authorInput.addItem(item);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String author = (String)authorInput.getSelectedItem();       
        String title = titleInput.getText();
        int author_id = Integer.parseInt(author.split("\\.")[0]);
        String language = languageInput.getText();
        int pages = Integer.parseInt(pagesInput.getText());
        DefaultTableModel model = (DefaultTableModel) itemsBARCODES.getModel();
        int nbItems = model.getRowCount();
        String[] itemsArray = new String[model.getRowCount()];
        for (int row = 0; row <model.getRowCount(); row++) {
            itemsArray[row] = model.getValueAt(row, 0).toString();
        }
        double price = Double.parseDouble(priceInput.getText());
        Book b = new Book(title, author_id, language, pages, nbItems,itemsArray, price);
        BookService BS = new BookService();
        BS.addBook(b);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        this.setVisible(false);
        newAuthor obj = new newAuthor();
        obj.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void pagesInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pagesInputKeyTyped
        char keyChar = evt.getKeyChar();
        // Check if the key is a number (0-9) or backspace
        if (Character.isDigit(keyChar) || keyChar == KeyEvent.VK_BACK_SPACE) {
            // Allow the key to be processed normally
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_pagesInputKeyTyped

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        this.setVisible(false);
        LibrarianMenu obj = new LibrarianMenu();
        obj.setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void scanBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanBUTTONActionPerformed
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
                        if (!itemBarcodeExists(result)) {
                            DefaultTableModel model = (DefaultTableModel) itemsBARCODES.getModel();
                            model.addRow(new Object[]{result});
                        } else {
                            JOptionPane.showMessageDialog(null, "THIS BARCODE IS ALREADY SCANNED");
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
    }//GEN-LAST:event_scanBUTTONActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> authorInput;
    private javax.swing.JTable itemsBARCODES;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField languageInput;
    private javax.swing.JTextField pagesInput;
    private javax.swing.JTextField priceInput;
    private javax.swing.JButton scanBUTTON;
    private javax.swing.JTextField titleInput;
    // End of variables declaration//GEN-END:variables
}
