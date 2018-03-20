package org.china.dailiyun.chaogao.func;
import java.lang.Math;

public class FuncSin extends Function {
	
	public FuncSin(){
		super("sin");
	}
	public double eval(double var){
		return Math.sin(var);
	}


}
