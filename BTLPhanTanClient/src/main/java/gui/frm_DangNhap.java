
package gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.NhanVien;
import entity.TaiKhoan;
import form.HinhNen;
import form.QuenMatKhauDialog;

public class frm_DangNhap extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates new form frm_DangNhap
	 */
	private Image backgroundImage;
	private Socket socket;
	DataOutputStream outData;
	ObjectInputStream in;

	@SuppressWarnings("serial")
	public frm_DangNhap() {
		
		try {
			socket = new Socket("LAPTOP-NU04B44F", 7878);
			outData = new DataOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setContentPane(new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(new ImageIcon("img/background.jpg").getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		});
		initComponents();
	}


	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g); // Generated from
									// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
		g.drawImage(backgroundImage, 0, 0, this);
	}

	/**
	 *
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	private void initComponents() {

		lblTaiKhoan = new javax.swing.JLabel();
		txtTaiKhoan = new javax.swing.JTextField();
		lblMatKhau = new javax.swing.JLabel();
		btnDangNhap = new javax.swing.JButton();
		pwdMatKhau = new javax.swing.JPasswordField();
		lblQuenMatKhau = new javax.swing.JLabel();
		lblTieuDe = new javax.swing.JLabel();
		lblLoiTenDangNhap = new javax.swing.JLabel();
		lblLoiMatKhau = new javax.swing.JLabel();
		pnHinhNen = new HinhNen();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Đăng Nhập");
		setBounds(new java.awt.Rectangle(600, 300, 0, 0));
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		lblTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblTaiKhoan.setText("Tên Tài Khoản");

		txtTaiKhoan.setText("Tên Tài Khoản");
		txtTaiKhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
		txtTaiKhoan.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtTaiKhoanFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtTaiKhoanFocusLost(evt);
			}
		});
		txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtTaiKhoanActionPerformed(evt);
			}
		});

		lblMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblMatKhau.setText("Mật Khẩu");

		btnDangNhap.setText("Đăng Nhập");
		btnDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					btnDangNhapMouseClicked(evt);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDangNhapActionPerformed(evt);
			}
		});

		pwdMatKhau.setText("Mật Khẩu");
		pwdMatKhau.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
		pwdMatKhau.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				pwdMatKhauFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				pwdMatKhauFocusLost(evt);
			}
		});

		lblQuenMatKhau.setText("Quên mật khẩu");
		lblQuenMatKhau.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		lblQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblQuenMatKhauMouseClicked(evt);
			}
		});

		lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lblTieuDe.setText("Đăng Nhập");

		lblLoiTenDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblLoiTenDangNhap.setForeground(new Color(255, 51, 0));

		lblLoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblLoiMatKhau.setForeground(new Color(255, 51, 0));

		pnHinhNen.setNewBackground("img\\background_Login.jpg");

		javax.swing.GroupLayout hinhNen1Layout = new javax.swing.GroupLayout(pnHinhNen);
		pnHinhNen.setLayout(hinhNen1Layout);
		hinhNen1Layout.setHorizontalGroup(hinhNen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 273, Short.MAX_VALUE));
		hinhNen1Layout.setVerticalGroup(hinhNen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addComponent(pnHinhNen, javax.swing.GroupLayout.PREFERRED_SIZE, 273,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup().addGap(132, 132, 132)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup().addComponent(btnDangNhap).addGap(135, 135, 135))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup().addComponent(lblQuenMatKhau).addGap(32, 32,
												32))))
						.addGroup(layout.createSequentialGroup().addGap(112, 112, 112).addComponent(lblTieuDe))
						.addGroup(layout.createSequentialGroup().addGap(17, 17, 17)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 98,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(
												lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 103,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lblLoiTenDangNhap, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createSequentialGroup().addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(pwdMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 194,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 195,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblLoiMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addContainerGap()))))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(19, 19, 19).addComponent(lblTieuDe).addGap(39, 39, 39)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(lblLoiTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 16,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(12, 12, 12)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(pwdMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(lblLoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 16,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20).addComponent(lblQuenMatKhau).addGap(4, 4, 4).addComponent(btnDangNhap)
						.addContainerGap(18, Short.MAX_VALUE))
				.addComponent(pnHinhNen, javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE));
		txtTaiKhoan.setText("NV0001");
		pwdMatKhau.setText("123456");

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDangNhapActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnDangNhapActionPerformed

	private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtTaiKhoanActionPerformed
		// TODO add your handling code here:

	}// GEN-LAST:event_txtTaiKhoanActionPerformed

	private void txtTaiKhoanFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtTaiKhoanFocusLost
		// TODO add your handling code here:
		if (txtTaiKhoan.getText().isEmpty()) {
			txtTaiKhoan.setText("Tên Tài Khoản");
		}
	}// GEN-LAST:event_txtTaiKhoanFocusLost

	private void txtTaiKhoanFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtTaiKhoanFocusGained
		// TODO add your handling code here:
		if (txtTaiKhoan.getText().equals("Tên Tài Khoản")) {
			txtTaiKhoan.setText("");
			txtTaiKhoan.setForeground(Color.BLACK);
		}
	}// GEN-LAST:event_txtTaiKhoanFocusGained

	private void pwdMatKhauFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_pwdMatKhauFocusGained
		// TODO add your handling code here:
		String passWord = new String(pwdMatKhau.getPassword());
		if (passWord.equals("Mật Khẩu")) {
			pwdMatKhau.setText("");
			pwdMatKhau.setForeground(Color.BLACK);
		}
	}// GEN-LAST:event_pwdMatKhauFocusGained

	private void pwdMatKhauFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_pwdMatKhauFocusLost
		// TODO add your handling code here
		String passWord = new String(pwdMatKhau.getPassword());
		if (passWord.isEmpty()) {
			pwdMatKhau.setText("Mật Khẩu");
		}
	}// GEN-LAST:event_pwdMatKhauFocusLost

	private void lblQuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblQuenMatKhauMouseClicked
		// TODO add your handling code here:
		String tieuDe = lblQuenMatKhau.getText();
		if (tieuDe.equals("Quên mật khẩu")) {
			xoaLoi();
			lblTieuDe.setText("Quên mật khẩu");
			lblQuenMatKhau.setText("Quaý lại Đăng Nhập");
			btnDangNhap.setText("Xác nhận");
			lblMatKhau.setVisible(false);
			pwdMatKhau.setVisible(false);
		} else {
			xoaLoi();
			lblTieuDe.setText("Đăng nhập");
			lblQuenMatKhau.setText("Quên mật khẩu");
			btnDangNhap.setText("Đăng Nhập");
			lblMatKhau.setVisible(true);
			pwdMatKhau.setVisible(true);
		}
	}// GEN-LAST:event_lblQuenMatKhauMouseClicked

	private void btnDangNhapMouseClicked(java.awt.event.MouseEvent evt) throws ClassNotFoundException, IOException {// GEN-FIRST:event_btnDangNhapMouseClicked
		// TODO add your handling code here:
		String nhiemVu = btnDangNhap.getText();
		if (nhiemVu.equals("Đăng Nhập")) {
			try {
				String taiKhoan = txtTaiKhoan.getText();
				String matKhau = new String(pwdMatKhau.getPassword());
				if (kiemTraTaiKhoan(taiKhoan) && kiemTraMatKhau(matKhau)) {
					outData.writeUTF("GD_DANGNHAP");
					outData.writeInt(1);
					outData.writeUTF(taiKhoan);
					outData.writeUTF(matKhau);
					outData.flush();
					TaiKhoan tk = (TaiKhoan) in.readObject();
					if (tk == null) {
						JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không chính xác");
					} else {
						JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
						NhanVien nv = (NhanVien) in.readObject();
						this.dispose();

						GUI_APP gui_APP = new GUI_APP(nv, outData, in, socket);
						gui_APP.setVisible(true);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else {
            String taiKhoan = txtTaiKhoan.getText();
            kiemTraTaiKhoan(taiKhoan);
            if(lblLoiTenDangNhap.getText().isEmpty() || lblLoiTenDangNhap.getText().trim().equals("")){
    			outData.writeUTF("GD_DANGNHAP");
    			outData.writeInt(2);
            	outData.writeUTF(taiKhoan);
            	outData.flush();
            	String maXacNhan = (String) in.readObject();
            	if(maXacNhan == null) {
                	JOptionPane.showMessageDialog(null, "Tài khoản không hợp lệ");
                	return;
            	}
                String giaTri = JOptionPane.showInputDialog(this, "Nhập vào mã xác nhận được gởi tới Email của bạn", "Xác nhận", JOptionPane.INFORMATION_MESSAGE);
                if(giaTri.equals(maXacNhan)) {
                	QuenMatKhauDialog dialog = new QuenMatKhauDialog(new Frame(), true, taiKhoan, outData, in, socket);
                	dialog.setVisible(true);
                	dialog.setLocationRelativeTo(null);
                }else {
                	JOptionPane.showMessageDialog(null, "Mã xác nhận không hợp lệ");
                }
            }
		}
	}// GEN-LAST:event_btnDangNhapMouseClicked

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(frm_DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(frm_DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(frm_DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(frm_DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				frm_DangNhap dangNhap = new frm_DangNhap();
				dangNhap.setLocationRelativeTo(null);
				dangNhap.setVisible(true);
				
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnDangNhap;
	private HinhNen pnHinhNen;
	private javax.swing.JLabel lblLoiMatKhau;
	private javax.swing.JLabel lblLoiTenDangNhap;
	private javax.swing.JLabel lblMatKhau;
	private javax.swing.JLabel lblQuenMatKhau;
	private javax.swing.JLabel lblTaiKhoan;
	private javax.swing.JLabel lblTieuDe;
	private javax.swing.JPasswordField pwdMatKhau;
	private javax.swing.JTextField txtTaiKhoan;
	// End of variables declaration//GEN-END:variables

	private boolean kiemTraTaiKhoan(String taiKhoan) {
		if (taiKhoan.isEmpty() || taiKhoan.equalsIgnoreCase("Tên Tài Khoản")) {
			lblLoiTenDangNhap.setText("Tên đăng nhập không được rỗng");
			return false;
		} else if (taiKhoan.length() < 6) {
			lblLoiTenDangNhap.setText("Độ dài kí tự phải từ 6 trở lên");
			return false;
		} else
			lblLoiTenDangNhap.setText("");
		return true;
	}

	private boolean kiemTraMatKhau(String matKhau) {
		if (matKhau.isEmpty() || matKhau.equals("Mật Khẩu")) {
			lblLoiMatKhau.setText("Mật khẩu không được để trống");
			return false;
		} else if (matKhau.length() < 4) {
			lblLoiMatKhau.setText("Độ dài kí tự phải từ 4 trở lên");
			return false;
		} else if (!matKhau.matches("[a-zA-Z0-9@$!%*&]{4,}")) {
			lblLoiMatKhau.setText("Mật khẩu gồm chữ số và kí tự đặ biết");
			return false;
		} else
			lblLoiMatKhau.setText("");
		return true;
	}

	private void xoaLoi() {
		lblLoiMatKhau.setText("");
		lblLoiTenDangNhap.setText("");
	}
}
