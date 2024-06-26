/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ACER
 */
public class HinhNen extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1965346027329553048L;
	private BufferedImage image;
	private String hinhNen = null;

	public HinhNen(String hinhNen) {
		try {
			image = ImageIO.read(new File(hinhNen));
			this.hinhNen = hinhNen;
		} catch (IOException ex) {
			// handle exception...
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // see javadoc for more info on the
																			// parameters
	}

	public HinhNen() {

		initComponents();
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setNewBackground(String hinhNen) {
		try {
			image = ImageIO.read(new File(hinhNen));
			this.hinhNen = hinhNen;
			repaint(); // Vẽ lại JPanel để hiển thị hình nền mới
		} catch (IOException ex) {
			// Xử lý ngoại lệ...
		}

	}

	public String getHinhNen() {
		return hinhNen;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		setPreferredSize(new java.awt.Dimension(160, 160));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 219, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 170, Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
