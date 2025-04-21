/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package tampilan;
import java.awt.Dimension; 
import java.awt.Toolkit; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.Statement; 
import java.util.Vector;
import javax.swing.JOptionPane; 
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author mrssy
 */
public class FormDataRules1 extends javax.swing.JInternalFrame {
        String driver = "org.mariadb.jdbc.Driver"; 
        String db = "jdbc:mariadb://localhost:3306/bumil"; 
        String user = "root"; 
        String password = "";
        DefaultTableModel model;
        DefaultTableModel model2;
    /**
     * Creates new form FormDataPenyakit
     */
    public FormDataRules1() {
        initComponents();
        TampilDataGejala();
        TampilDataPenyakit();
    }
    
    public void HapusTeks() { 
    
    txtNamaPenyakit.setText(""); 
    

    }
    
    public void TampilDataPenyakit() { 
	try{ Class.forName(driver); 
	Connection con = DriverManager.getConnection(db, user, password); 
	Statement stmt=con.createStatement(); 
	String queri="SELECT * FROM penyakit ORDER BY kodep"; 
	ResultSet rs=stmt.executeQuery(queri); 
	int index=0; 

	comboKodePenyakit.addItem(" "); 
	while(rs.next()) 
	{ 
	
	comboKodePenyakit.addItem(rs.getString("kodep")); 
	
	} 
	
	rs.close(); 
	con.close(); 
	
	}catch(Exception ex) 
	{ 
	javax.swing.JOptionPane.showMessageDialog(rootPane, ex.getMessage()); 
        } 
    }
    
    public void SimpanData(String kodegejala, String kodepenyakit) 
    { 
	try { 
	Class.forName(driver); 
	Connection con = DriverManager.getConnection(db, user, password); 
        Statement stmt=con.createStatement(); 
	String queri="INSERT INTO gejalapenyakit (kodeg,kodep) VALUES ('"+kodegejala+"','"+kodepenyakit+"')"; 

	stmt.executeUpdate(queri); 
	javax.swing.JOptionPane.showMessageDialog(rootPane, "Data tersimpan"); 
	
	}catch(Exception ex) 
	{ 
	javax.swing.JOptionPane.showMessageDialog(rootPane, ex.getMessage()); 
	}
    } 
    
    void UpdateData(String kodegejala, String kodepenyakit) 
    { 
	try { 
	Class.forName(driver); 
	Connection con = DriverManager.getConnection(db, user, password); 
	Statement stmt=con.createStatement();
	String queri="UPDATE gejalapenyakit SET kodeg='"+kodegejala+"'"+
                "WHERE kodep='"+kodepenyakit+"'";
	stmt.executeUpdate(queri);
	javax.swing.JOptionPane.showMessageDialog(rootPane, "Data terupdate");
	
	}catch(Exception ex)
        {
	javax.swing.JOptionPane.showMessageDialog(rootPane, ex.getMessage()); 
      }	
    }  
    
    public boolean isDataEksis(String kodegejala,String kodepenyakit) 
    { 
	boolean hasil=false; 
	int ndata=0 ; 
	try{ 
	Class.forName(driver); 
	Connection con = DriverManager.getConnection(db, user, password); 
	Statement stmt=con.createStatement(); 
	String queri="SELECT * FROM gejalapenyakit WHERE kodep='"+kodepenyakit+"'"; 
	ResultSet rs=stmt.executeQuery(queri); 
	while(rs.next()) 
	{ 
		ndata++; 
	} 
	rs.close(); 
	con.close(); 
	}catch(Exception ex) 
	{ 
	javax.swing.JOptionPane.showMessageDialog(rootPane, ex.getMessage()); 
	} 
	
	if(ndata>0) 
		hasil=true; 
	else 
	hasil=false; 
	return hasil; 
    }
    
    void TampilDataGejala() 
    { 
	try { 
	Class.forName(driver); 
	Connection con = DriverManager.getConnection(db, user, password); 
	Statement stmt=con.createStatement(); 
	String queri="SELECT * FROM gejala ORDER BY kodeg"; 
	ResultSet rs=stmt.executeQuery(queri);
	rs.last(); 
	
	int jumbaris=rs.getRow(); 
	String []titleKolom={"Kode Gejala","Nama Gejala"};
	String [][]datagejala=new String[jumbaris][2]; 
	int index=0; 
	
	model=new DefaultTableModel() { 
		public boolean isCellEditable(int rowIndex, int mColIndex) { 
		return false; 
		} 
		}; 
	
        model2=new DefaultTableModel() { 
		public boolean isCellEditable(int rowIndex, int mColIndex) { 
		return false; 
		} 
		}; 
         model2.setNumRows(jumbaris);
         model2.addColumn("Kode Gejala");
         model2.addColumn("Nama Gejala");
         tabelGejalaPenyakit.setModel(model2);
        
	rs=stmt.executeQuery(queri); 
	while(rs.next()) 
	{ 
            datagejala[index][0]=rs.getString("kodeg"); 
            datagejala[index][1]=rs.getString("namag"); 
            index++; 
	} 
	model.setDataVector(datagejala,titleKolom);
	tabelDataGejala.setModel(model);
        
        tabelDataGejala.getColumnModel().getColumn(0).setPreferredWidth(100); 	
        tabelDataGejala.getColumnModel().getColumn(1).setPreferredWidth(150);         
	
        rs.close(); 
	con.close(); 
    }catch(Exception ex) 
    { 
    javax.swing.JOptionPane.showMessageDialog(rootPane,ex.getMessage()); 
    } 
   }
    
    public String getNamaGejala(String kode) { 
    String namaGejala = ""; 
    try { 
        Class.forName(driver); 
        Connection con = DriverManager.getConnection(db, user, password); 
        Statement stmt = con.createStatement(); 
        String queri = "SELECT namag FROM gejala WHERE kodeg='" + kode + "'"; 
        ResultSet rs = stmt.executeQuery(queri); 

        if (rs.next()) { 
            namaGejala = rs.getString("namag"); // Menggunakan kolom namag
        } 
        rs.close(); 
        con.close(); 
    } catch (Exception ex) { 
        javax.swing.JOptionPane.showMessageDialog(rootPane, ex.getMessage()); 
    } 
    return namaGejala; 
}
    
   void TampilDataGejalaPenyakit(String kodepenyakit) { 
    try { 
        Class.forName(driver); 
        Connection con = DriverManager.getConnection(db, user, password); 
        Statement stmt = con.createStatement(); 
        String queri = "SELECT * FROM gejalapenyakit WHERE kodep='" + kodepenyakit + "'"; 
        ResultSet rs = stmt.executeQuery(queri); 

        int jumbaris = tabelDataGejala.getRowCount(); 
        String[] titleKolom = {"Kode Gejala", "Nama Gejala"}; 
        String[][] datagejala = new String[jumbaris][2]; 
        int index = 0; 

        model2 = new DefaultTableModel() { 
            public boolean isCellEditable(int rowIndex, int mColIndex) { 
                return false; 
            } 
        }; 
        model2.setRowCount(jumbaris);

        while (rs.next()) { 
            String kodegejala = rs.getString("kodeg"); 
            int fromIndex = 0, pos1 = 0, pos2 = 0; 

            while ((fromIndex = kodegejala.indexOf(",", fromIndex)) != -1) { 
                pos2 = fromIndex; 
                String namaGejala = getNamaGejala(kodegejala.substring(pos1, pos2)); 
                datagejala[index][0] = kodegejala.substring(pos1, pos2); 
                datagejala[index][1] = namaGejala; 

                pos1 = pos2 + 1;
                fromIndex++; 
                index++; 
            } 
            String namaGejala = getNamaGejala(kodegejala.substring(pos1)); 
            datagejala[index][0] = kodegejala.substring(pos1); 
            datagejala[index][1] = namaGejala; 
            index++;
        } 
        model2.setDataVector(datagejala, titleKolom); 
        tabelGejalaPenyakit.setModel(model2); 

        tabelGejalaPenyakit.getColumnModel().getColumn(0).setPreferredWidth(100);     
        tabelGejalaPenyakit.getColumnModel().getColumn(1).setPreferredWidth(150);    
        rs.close(); 
        con.close(); 
    } catch (Exception ex) { 
        javax.swing.JOptionPane.showMessageDialog(rootPane, ex.getMessage()); 
    } 
}
    
    public int getJumlahGejala() 
    { 

	int jumlah=0; 
	for(int i=0;i<tabelGejalaPenyakit.getRowCount();i++) 
	{ 
		try { 
		     if(!tabelGejalaPenyakit.getModel().getValueAt
			( i,0).toString().isEmpty())

		{ 
			jumlah++; 
		} 
	}catch(Exception ex) 
	{ 
		continue; 
		} 
	} return jumlah; 
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboKodePenyakit = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNamaPenyakit = new javax.swing.JTextField();
        SIMPAN = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelDataGejala = new javax.swing.JTable();
        tombolAdd = new javax.swing.JButton();
        tombolRemove = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelGejalaPenyakit = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximumSize(new java.awt.Dimension(1250, 900));
        setMinimumSize(new java.awt.Dimension(1250, 900));
        setPreferredSize(new java.awt.Dimension(1250, 900));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATA RULES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(537, 537, 537)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Kode Penyakit");

        comboKodePenyakit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboKodePenyakitItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nama Gejala");

        SIMPAN.setText("SIMPAN");
        SIMPAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIMPANActionPerformed(evt);
            }
        });

        tabelDataGejala.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Gejala", "Nama Gejala"
            }
        ));
        tabelDataGejala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDataGejalaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelDataGejala);

        tombolAdd.setText("MASUKAN GEJALA");
        tombolAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolAddActionPerformed(evt);
            }
        });

        tombolRemove.setText("KELUARKAN GEJALA");
        tombolRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolRemoveActionPerformed(evt);
            }
        });

        tabelGejalaPenyakit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Gejala", "Nama Gejala"
            }
        ));
        tabelGejalaPenyakit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelGejalaPenyakitMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabelGejalaPenyakit);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNamaPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SIMPAN)
                            .addComponent(comboKodePenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(903, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tombolAdd)
                            .addComponent(tombolRemove))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(tombolAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tombolRemove)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboKodePenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SIMPAN)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 180, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelGejalaPenyakitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelGejalaPenyakitMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelGejalaPenyakitMouseClicked

    private void tombolRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolRemoveActionPerformed
        // TODO add your handling code here:
        int[] pos = tabelGejalaPenyakit.getSelectedRows();
        Vector kode = new Vector();
        Vector namagejala = new Vector();

        for (int i = 0; i < tabelGejalaPenyakit.getRowCount(); i++) {
            try {
                if (!tabelGejalaPenyakit.getModel().getValueAt(i, 0).toString().isEmpty()) {
                    kode.add(tabelGejalaPenyakit.getModel().getValueAt(i, 0).toString());
                    namagejala.add(tabelGejalaPenyakit.getModel().getValueAt(i, 1).toString());
                }
            } catch (Exception ex) {
                continue;
            }
        }

        Vector kodetmp = new Vector(kode);
        Vector namagejalatmp = new Vector(namagejala);

        for (int i = 0; i < pos.length; i++) {
            kodetmp.removeElement(kode.elementAt(pos[i]));
            namagejalatmp.removeElement(namagejala.elementAt(pos[i]));
            model2.removeRow(pos[i]);
        }

        model2.setRowCount(tabelDataGejala.getRowCount());

        for (int i = 0; i < kodetmp.size(); i++) {
            model2.setValueAt(kodetmp.elementAt(i), i, 0);
            model2.setValueAt(namagejalatmp.elementAt(i), i, 1);
        }
        tabelGejalaPenyakit.setModel(model2);
    }//GEN-LAST:event_tombolRemoveActionPerformed

    private void tombolAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolAddActionPerformed
        // TODO add your handling code here:
        int[] pos = tabelDataGejala.getSelectedRows();
        int baris = getJumlahGejala();

        for (int i = 0; i < pos.length; i++) {
            String kode = tabelDataGejala.getModel().getValueAt(pos[i], 0).toString();
            String namagejala = tabelDataGejala.getModel().getValueAt(pos[i], 1).toString();

            model2.setValueAt(kode, i + baris, 0);
            model2.setValueAt(namagejala, i + baris, 1);
        }
        tabelGejalaPenyakit.setModel(model2);
    }//GEN-LAST:event_tombolAddActionPerformed

    private void tabelDataGejalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataGejalaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelDataGejalaMouseClicked

    private void comboKodePenyakitItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboKodePenyakitItemStateChanged
        // TODO add your handling code here:
        int pos=comboKodePenyakit.getSelectedIndex();
        if(pos==1)
        {
            txtNamaPenyakit.setText("IUGR");
            TampilDataGejalaPenyakit(comboKodePenyakit.getSelectedItem().toString());
        }
        else if(pos==2)
        {
            txtNamaPenyakit.setText("GAKY");
            TampilDataGejalaPenyakit(comboKodePenyakit.getSelectedItem().toString());
        }
        else if(pos==3)
        {
            txtNamaPenyakit.setText("Anemia");
            TampilDataGejalaPenyakit(comboKodePenyakit.getSelectedItem().toString());
        }
        else if(pos==4)
        {
            txtNamaPenyakit.setText("KEK");
            TampilDataGejalaPenyakit(comboKodePenyakit.getSelectedItem().toString());
        }
        else if(pos==5)
        {
            txtNamaPenyakit.setText("Kehamilan Prematur");
            TampilDataGejalaPenyakit(comboKodePenyakit.getSelectedItem().toString());
        }
        else
        txtNamaPenyakit.setText("");
    }//GEN-LAST:event_comboKodePenyakitItemStateChanged

    private void SIMPANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIMPANActionPerformed
        // TODO add your handling code here:
        int jumgejala = getJumlahGejala();
        String kodegejala = "";
        String kodepenyakit;

        if (comboKodePenyakit.getSelectedIndex() != 0 && !txtNamaPenyakit.getText().isEmpty()) {
            if (jumgejala > 0) {
                for (int i = 0; i < tabelGejalaPenyakit.getRowCount(); i++) {
                    try {
                        if (!tabelGejalaPenyakit.getModel().getValueAt(i, 0).toString().isEmpty()) {
                            kodegejala += tabelGejalaPenyakit.getModel().getValueAt(i, 0).toString() + ",";
                        }
                    } catch (Exception ex) {
                        continue;
                    }
                }
                kodegejala = kodegejala.substring(0, kodegejala.length() - 1);
                kodepenyakit = comboKodePenyakit.getSelectedItem().toString();

                if (isDataEksis(kodegejala, kodepenyakit) == false)
                SimpanData(kodegejala, kodepenyakit);
                else
                UpdateData(kodegejala, kodepenyakit);
            } else {
                javax.swing.JOptionPane.showMessageDialog(rootPane, "Silahkan pilih gejala untuk penyakit yang dipilih");
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(rootPane, "Silahkan pilih kode penyakit terlebih dahulu");
        }
    }//GEN-LAST:event_SIMPANActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SIMPAN;
    private javax.swing.JComboBox<String> comboKodePenyakit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tabelDataGejala;
    private javax.swing.JTable tabelGejalaPenyakit;
    private javax.swing.JButton tombolAdd;
    private javax.swing.JButton tombolRemove;
    private javax.swing.JTextField txtNamaPenyakit;
    // End of variables declaration//GEN-END:variables
}
