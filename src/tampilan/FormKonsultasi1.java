/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package tampilan;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension; 
import java.awt.Toolkit; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.Statement; 
import java.util.Vector;
import javax.swing.JOptionPane;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



/**
 *
 * @author mrssy
 */
public class FormKonsultasi1 extends javax.swing.JInternalFrame {
        String driver = "org.mariadb.jdbc.Driver"; 
        String db = "jdbc:mariadb://localhost:3306/bumil"; 
        String user = "root"; 
        String password = "";
        
    /**
     * Creates new form FormKonsultasi
     */
    public FormKonsultasi1() {
        initComponents();
        setTableAction();
        initializeTable();
        lebarkolom();
        
       
    }
    
    public void HapusTeks() { 
    
    txtIDKonsultasi.setText("");
    txtTanggalKonsul.setText(""); 
    txtIDPasien.setText("");
    txtNamaPasien.setText("");
    txtUmur.setText("");
    txtTelp.setText("");
    txtAlamat.setText("");
    txtKodePenyakit.setText("");
    txtNamaPenyakit.setText("");
    txtDeskripsi.setText("");
    txtSaran.setText("");
    }
    
    public void lebarkolom() {
        TableColumn column;
        tabelPertanyaan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column =  tabelPertanyaan.getColumnModel().getColumn(0);
        column.setPreferredWidth(80);
        column =  tabelPertanyaan.getColumnModel().getColumn(1);
        column.setPreferredWidth(680);
        column =  tabelPertanyaan.getColumnModel().getColumn(2);
        column.setPreferredWidth(50);
    }
    
    private void initializeTable() {
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 2) {
                return Boolean.class;
            }
            return String.class;
        }
    };
    model.addColumn("Kode Gejala");
    model.addColumn("Pertanyaan");
    model.addColumn("Pilih");

    try {
        Connection con = DriverManager.getConnection(db, user, password);
        Statement stmt = con.createStatement();
        String query = "SELECT kodeg, namag FROM gejala"; // Sesuaikan dengan struktur tabel Anda
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String kodeGejala = rs.getString("kodeg");
            String namaGejala = rs.getString("namag");
            String pertanyaan = "Apakah pasien mengalami " + namaGejala + "?";
            model.addRow(new Object[]{kodeGejala, pertanyaan, false}); // Set kolom Pilih defaultnya ke false
        }

        tabelPertanyaan.setModel(model);

        rs.close();
        stmt.close();
        con.close();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
    }
    }
    
    private void setTableAction() {
        TableColumnModel columnModel = tabelPertanyaan.getColumnModel();
        int checkboxColumnIndex = 1; // Index kolom checkbox (ubah sesuai dengan index kolom Anda)

        // Mengatur renderer untuk checkbox
        columnModel.getColumn(checkboxColumnIndex).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected((boolean) value);
                return checkBox;
            }
        });
        

        // Mengatur editor untuk checkbox
        columnModel.getColumn(checkboxColumnIndex).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // Menambahkan listener untuk menangani perubahan di checkbox
        tabelPertanyaan.getModel().addTableModelListener(e -> {
            if (e.getColumn() == checkboxColumnIndex) {
                int row = e.getFirstRow();
                boolean isChecked = (boolean) tabelPertanyaan.getValueAt(row, checkboxColumnIndex);

                if (isChecked) {
                    String kodeGejala = (String) tabelPertanyaan.getValueAt(row, 0); // Ambil kode gejala (asumsikan kode gejala ada di kolom 0)
                    getPenyakitInfo(kodeGejala);
                }
            }
        });
       }
    
    private void getDataPasien(String id) {
        try {
            Connection con = DriverManager.getConnection(db, user, password); 
            Statement stmt=con.createStatement(); 
            String query = "SELECT * FROM pasien WHERE id='" + id+ "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                txtNamaPasien.setText(rs.getString("nama"));
                txtUmur.setText(rs.getString("umur"));
                txtTelp.setText(rs.getString("telp"));
                txtAlamat.setText(rs.getString("alamat"));
            }
            rs.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }
    
    
    private void getPenyakitInfo(String kodePenyakit) {
    try {
        Connection con = DriverManager.getConnection(db, user, password);
        String query = "SELECT * FROM penyakit WHERE kodep = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, kodePenyakit);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            txtKodePenyakit.setText(rs.getString("kodep"));
            txtNamaPenyakit.setText(rs.getString("namap"));
            txtDeskripsi.setText(rs.getString("deskripp"));
            txtSaran.setText(rs.getString("saranp"));
        }

        rs.close();
        ps.close();
        con.close();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }
    }
     
    
    private void simpanDiagnosis() {
    try {
        Connection con = DriverManager.getConnection(db, user, password); 
        Statement stmt=con.createStatement(); 
        String query = "INSERT INTO diagnosa (idkonsultasi,tanggalkonsul, id, nama_pasien, umur_pasien, notelp, alamat, kodep, namap, deskripp, saranp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setString(1, txtIDKonsultasi.getText());
        ps.setString(2, txtTanggalKonsul.getText());
        ps.setString(3, txtIDPasien.getText());
        ps.setString(4, txtNamaPasien.getText());
        ps.setString(5, txtUmur.getText());
        ps.setString(6, txtTelp.getText());
        ps.setString(7, txtAlamat.getText());
        ps.setString(8, txtKodePenyakit.getText());
        ps.setString(9, txtNamaPenyakit.getText());
        ps.setString(10, txtDeskripsi.getText());
        ps.setString(11, txtSaran.getText());

        ps.executeUpdate();
        ps.close();
        con.close();

        JOptionPane.showMessageDialog(rootPane, "Data diagnosis berhasil disimpan.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIDKonsultasi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTanggalKonsul = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIDPasien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNamaPasien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtUmur = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        bcari = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtKodePenyakit = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNamaPenyakit = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDeskripsi = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSaran = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelPertanyaan = new javax.swing.JTable();
        SimpanDiagnosa = new javax.swing.JButton();
        CetakHasil = new javax.swing.JButton();
        bdiagnosa = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximumSize(new java.awt.Dimension(1250, 900));
        setMinimumSize(new java.awt.Dimension(1250, 900));
        setPreferredSize(new java.awt.Dimension(1250, 900));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KONSULTASI PASIEN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(465, 465, 465)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID Konsultasi");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tanggal Konsultasi");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("ID Pasien");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nama Pasien");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Umur Pasien");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Nomor Telepon");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Alamat Pasien");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        bcari.setText("CARI");
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Kode Penyakit");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("HASIL DIAGNOSA PASIEN MENDERITA");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nama Penyakit");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Deskripsi Penyakit");

        txtDeskripsi.setColumns(20);
        txtDeskripsi.setLineWrap(true);
        txtDeskripsi.setRows(5);
        jScrollPane2.setViewportView(txtDeskripsi);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Pencegahan dan Saran");

        txtSaran.setColumns(20);
        txtSaran.setLineWrap(true);
        txtSaran.setRows(5);
        jScrollPane3.setViewportView(txtSaran);

        tabelPertanyaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Apakah Berat Janin kurang dari 90% berat janin normal ?", null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Pertanyaan", "Jawaban  (Ya/Tidak)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelPertanyaan.setColumnSelectionAllowed(true);
        jScrollPane4.setViewportView(tabelPertanyaan);
        tabelPertanyaan.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        SimpanDiagnosa.setText("SIMPAN HASIL DIAGNOSA");
        SimpanDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanDiagnosaActionPerformed(evt);
            }
        });

        CetakHasil.setText("CETAK HASIL DIAGNOSA");

        bdiagnosa.setText("DIAGNOSA");
        bdiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdiagnosaActionPerformed(evt);
            }
        });

        jButton2.setText("BERSIHKAN TEXT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUmur, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDKonsultasi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggalKonsul, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtIDPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bcari))
                    .addComponent(txtNamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNamaPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(SimpanDiagnosa)
                                .addGap(18, 18, 18)
                                .addComponent(CetakHasil, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtKodePenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)
                                .addComponent(bdiagnosa, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDKonsultasi)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTanggalKonsul, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUmur, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtKodePenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNamaPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bdiagnosa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(SimpanDiagnosa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CetakHasil, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(625, 625, 625))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        // TODO add your handling code here:
        getDataPasien(txtIDPasien.getText());
        
    }//GEN-LAST:event_bcariActionPerformed

    private void SimpanDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanDiagnosaActionPerformed
        // TODO add your handling code here:
        
        simpanDiagnosis();
        
    }//GEN-LAST:event_SimpanDiagnosaActionPerformed

    private void bdiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdiagnosaActionPerformed
        // TODO add your handling code here:
     List<String> selectedGejala = getSelectedGejala();

    if (!selectedGejala.isEmpty()) {
        String kodePenyakit = getKodePenyakit(selectedGejala);
        if (kodePenyakit != null) {
            getPenyakitInfo(kodePenyakit);
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada penyakit yang cocok ditemukan, "
                    + "Disarankan agar pasien segera pergi ke rumah sakit "
                    + "untuk konsultasi lebih lanjut dengan dokter spesialis "
                    + "agar mendapatkan penanganan yang terbaik terkait kondisi pasien.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Tidak ada gejala yang dipilih.");
    }
        
    }//GEN-LAST:event_bdiagnosaActionPerformed

    private List<String> getSelectedGejala() {
    List<String> selectedGejala = new ArrayList<>();
    int rowCount = tabelPertanyaan.getRowCount();
    for (int i = 0; i < rowCount; i++) {
        Boolean isChecked = (Boolean) tabelPertanyaan.getValueAt(i, 2); // Asumsi kolom checkbox adalah kolom 2
        if (isChecked != null && isChecked) {
            String kodeGejala = (String) tabelPertanyaan.getValueAt(i, 0); // Asumsi kolom kode gejala adalah kolom 0
            selectedGejala.add(kodeGejala);
        }
    }
    return selectedGejala;
    }
    
   
 private String getKodePenyakit(List<String> gejalaList) {
    String kodePenyakit = null;
    try {
        Connection con = DriverManager.getConnection(db, user, password);
        
        // Query untuk mencari kode penyakit yang sesuai dengan kombinasi gejala yang dipilih
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT kodep FROM gejalapenyakit WHERE ");
        
        // Memeriksa setiap gejala yang dipilih
        for (int i = 0; i < gejalaList.size(); i++) {
            if (i > 0) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append("FIND_IN_SET(?, kodeg) > 0 ");
        }
        
        PreparedStatement ps = con.prepareStatement(queryBuilder.toString());
        
        // Mengatur parameter untuk setiap kode gejala yang dipilih
        for (int i = 0; i < gejalaList.size(); i++) {
            ps.setString(i + 1, gejalaList.get(i));
        }
        
        ResultSet rs = ps.executeQuery();

        // Memeriksa apakah ada hasil dari query
        if (rs.next()) {
            kodePenyakit = rs.getString("kodep");
        }

        rs.close();
        ps.close();
        con.close();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }
    return kodePenyakit;
    }
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        HapusTeks();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CetakHasil;
    private javax.swing.JButton SimpanDiagnosa;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bdiagnosa;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tabelPertanyaan;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextArea txtDeskripsi;
    private javax.swing.JTextField txtIDKonsultasi;
    private javax.swing.JTextField txtIDPasien;
    private javax.swing.JTextField txtKodePenyakit;
    private javax.swing.JTextField txtNamaPasien;
    private javax.swing.JTextField txtNamaPenyakit;
    private javax.swing.JTextArea txtSaran;
    private javax.swing.JTextField txtTanggalKonsul;
    private javax.swing.JTextField txtTelp;
    private javax.swing.JTextField txtUmur;
    // End of variables declaration//GEN-END:variables
}
