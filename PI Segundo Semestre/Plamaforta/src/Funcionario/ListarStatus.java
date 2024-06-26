/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Funcionario;

import Dados.AppDao;
import Dados.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author enzod
 */
public class ListarStatus extends javax.swing.JFrame {
    /**
     * Creates new form ListarStatus
     */
    public ListarStatus() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMensagem = new javax.swing.JTable();
        asdas = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCnpj = new javax.swing.JTextPane();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMensagem = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        tblMensagem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CNPJ", "Nome", "Visualizações", "Mensagem"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMensagem.setRowHeight(30);
        tblMensagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMensagemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMensagem);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 100, 480, 180);

        asdas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        asdas.setText("CNPJ:");
        getContentPane().add(asdas);
        asdas.setBounds(60, 30, 50, 40);

        jScrollPane3.setViewportView(txtCnpj);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(110, 30, 130, 40);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(290, 30, 100, 40);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mensagem Selecionada: ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(500, 50, 150, 30);

        txtMensagem.setColumns(20);
        txtMensagem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMensagem.setLineWrap(true);
        txtMensagem.setRows(5);
        txtMensagem.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtMensagem);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(490, 90, 340, 180);

        setSize(new java.awt.Dimension(856, 317));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {            
            String u = txtCnpj.getText();
            ResultSet usuario = new AppDao().carregarDadosStatus(u);
            DefaultTableModel tblModelo = (DefaultTableModel) tblMensagem.getModel();
            
            //Nesse comando ele limpa a tebela
            tblModelo.setRowCount(0);
            
            //Percorrendo toda os usuários presentes no BD, para depois colocarmos num modelo e 
            while(usuario.next()){    
                if(!usuario.getString("mensagem_publi").equals(" ")){
                        Object linha[] = {
                        usuario.getString("cnpj"),
                        usuario.getString("nome"),
                        usuario.getInt("visualisacoes"),
                        usuario.getString("mensagem_publi")
                    };
                    tblModelo.addRow(linha);
                }else if(!u.equals("")){
                    JOptionPane.showMessageDialog(null, "Não possúi uma publicação cadastrada");
                    break;
                }else{
                    continue;
                }
            }
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "Entre em contato com o suporte e informe o erro: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListarStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblMensagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMensagemMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblMensagem.getModel();
        int row = tblMensagem.getSelectedRow();
        
        txtMensagem.setText(model.getValueAt(row, 3).toString());
        
    }//GEN-LAST:event_tblMensagemMouseClicked

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
            java.util.logging.Logger.getLogger(ListarStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarStatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asdas;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblMensagem;
    private javax.swing.JTextPane txtCnpj;
    private javax.swing.JTextArea txtMensagem;
    // End of variables declaration//GEN-END:variables
}
