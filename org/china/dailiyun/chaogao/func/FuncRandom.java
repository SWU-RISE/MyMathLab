package org.china.dailiyun.chaogao.func;

import java.lang.Math;

public class FuncRandom extends Function {

	public FuncRandom() {
		super("random");
	}

	public double eval(double var) {
		// TODO �Զ����ɷ������
		return var * Math.random();
		
	}

}
