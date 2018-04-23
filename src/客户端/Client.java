package 客户端;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Client {
	         JFrame frame=new JFrame("客户端");
	         JTextArea txt1=new JTextArea();
	         JTextArea txt2=new JTextArea();
	         JScrollPane jsp1=new JScrollPane(txt1);
		       JScrollPane jsp2=new JScrollPane(txt2);
	       	 JButton bu=new JButton("发送");

	public Client() {
		frame.setLocation(100, 100);
		frame.setSize(400, 600);
		frame.setLayout(null);
		jsp1.setBounds(0, 0, 400, 290);
		jsp2.setBounds(0, 300, 400, 200);
		txt1.setLineWrap(true);
		txt2.setLineWrap(true);
		txt1.setEditable(false);
		frame.add(jsp1);
		frame.add(jsp2);
		bu.setBounds(290, 500, 100, 80);
		frame.add(bu);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(3);

	}
	    public void connect() throws IOException {
              InetAddress thisIP=InetAddress.getLocalHost() ;
              Socket soc=new Socket(thisIP,12306);
              OutputStream output=soc.getOutputStream();
              InputStream input=soc.getInputStream();
              OutputStreamWriter osw=new OutputStreamWriter(output);
              InputStreamReader isr=new InputStreamReader(input);
              bu.addActionListener(new ActionListener() {
  	    		public void actionPerformed(ActionEvent e) {
  	    			String str=txt2.getText();
  	    			try {
						osw.write(str);
						osw.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  	    			txt2.setText(null);
  	    			txt1.append("服务端： "+"\r\n"+str+"\r\n");
  	    		}
  	    	
  	    	});
	 
              txt2.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
							String str=txt2.getText();
	  	    			try {
							osw.write(str);
							osw.flush();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	  	    			txt2.setText(null);
	  	    			txt1.append("服务端： "+"\r\n"+str+"\r\n");
					}
				}
            	  
              });
              
              while(true) {
	          char[] ch=new char[2048];
	        int len=  isr.read(ch);
	        String str2=new String(ch,0,len);
	        txt1.append("服务端 ： "+"\r\n"+str2+"\r\n");
              
	   }
	    }
	    public static void main(String[] agrs) throws IOException {
	    	new Client().connect();
	    }
	   
}


































