package com.kml.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.media.MediaLocator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sf.fmj.ui.application.CaptureDeviceBrowser;
import net.sf.fmj.ui.application.ContainerPlayer;
import net.sf.fmj.ui.application.PlayerPanelPrefs;
   
public class CameraFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel cameraPanel=null;//摄像头面板
	private static int num=0;
	
	public CameraFrame() throws Exception{
		this.setTitle("摄像头截图工具");
		this.setSize(480, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.cameraPanel=new JPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(this.cameraPanel, BorderLayout.CENTER);
		ContainerPlayer containerPlayer=new ContainerPlayer(this.cameraPanel);
		MediaLocator locator=CaptureDeviceBrowser.run(null);//弹出摄像头设备选项
		PlayerPanelPrefs prefs=new PlayerPanelPrefs();
		containerPlayer.setMediaLocation(locator.toExternalForm(), prefs.autoPlay);
		
		JPanel btnPanel=new JPanel(new BorderLayout());
		final JTextField path=new JTextField("E:/java");
		path.setColumns(30);
		btnPanel.add(path, BorderLayout.WEST);
		
		JButton okBtn=new JButton("截图");
		okBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Dimension imageSize=cameraPanel.getSize();
				BufferedImage image=new BufferedImage(imageSize.width,imageSize.height,BufferedImage.TYPE_INT_ARGB);
				Graphics2D g=image.createGraphics();
				cameraPanel.paint(g);
				g.dispose();
				try {
					String filePath=path.getText();
					File file=new File(filePath);
					if(!file.exists()) file.mkdirs();
					ImageIO.write(image, "png", new File(file.getAbsoluteFile()+"/"+num+".png"));
					num++;
				} catch (Exception e2) {
					e.paramString();
				}
			}
		});
		btnPanel.add(okBtn, BorderLayout.EAST);
		this.getContentPane().add(btnPanel, BorderLayout.SOUTH);
	}
	
	public void start(){
		
	}
	
	public static void main(String[] args) {
		try {
			CameraFrame c=new CameraFrame();
			c.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
