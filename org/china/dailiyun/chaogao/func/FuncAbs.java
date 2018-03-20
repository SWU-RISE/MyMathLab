package org.china.dailiyun.chaogao.func;
import java.lang.Math;

public class FuncAbs extends Function{
	public FuncAbs(){
		super("abs");
	}
	public double eval(double val){
		return Math.abs(val);
	}

}
