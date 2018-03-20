package org.china.dailiyun.lpsolve;

import java.awt.event.*;

public class ResultFrame extends LPSolve {
	public ResultFrame() {

		this.setTitle("最优结果");
		this.getJMENU().remove(this.getJSolveMenu());
		this.getPopup().remove(0);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e){
				ResultFrame.this.setVisible(false);
			}

		});

	}

	public static void main(String[] args) {
		new ResultFrame();
	}

}
