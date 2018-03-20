package org.china.dailiyun.lpsolve;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Solve {
	/**
	 * 
	 */
	public final static ResultFrame resultFrame=new ResultFrame();
	

	/**
	 * 
	 */
	public Solve() {
		
		
	}

	public  void run(final String filename) {

		Runnable task = new SwingWorkerTask() {
			public void init() {
				resultFrame.getJSaveButton().setEnabled(false);
				resultFrame.getJMENU().setEnabled(false);
				resultFrame.getPopup().setEnabled(false);

			}

			public void update() {
				/* statusLine.setText("" + lineNumber); */
			}

			public void finish() {
				resultFrame.getJSaveButton().setEnabled(true);
				resultFrame.getJMENU().setEnabled(true);
				resultFrame.getPopup().setEnabled(true);
				resultFrame.getJTextArea().setEditable(false);

			}

			public void work() {
				
				resultFrame.getJTextArea().setText("");
				try {
					FileInputStream fin=new FileInputStream(filename+".lp");
					byte[] bytes=new byte[50000];
					int length=fin.read(bytes);
					fin.close();
					FileOutputStream fout=new FileOutputStream("myrunrun.lp");
					fout.write(bytes, 0, length);
					
					fout.close();
					Thread.sleep(10);
					
					Process p = Runtime.getRuntime().exec("run.bat");
					BufferedInputStream bis = new BufferedInputStream(p.getInputStream());
					Scanner sin=new Scanner(bis);
					for(int i=0;i<2&&(!Thread.currentThread().isInterrupted()&&sin.hasNext());i++)sin.nextLine();
					
					while (!Thread.currentThread().isInterrupted()&&sin.hasNext()) {
						resultFrame.getJTextArea().append(sin.nextLine());
						resultFrame.getJTextArea().append("\n");
						
						doUpdate();
						
					}
					sin.close();
					

				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "" + e);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}

			private String line;
			private int lineNumber;
		};

		workerThread = new Thread(task);
		workerThread.start();

	}
	private static Thread workerThread;
	
}
