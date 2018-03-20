package org.china.dailiyun.chaogao.func;
import java.lang.Math;

public class FuncCos extends Function{
	public FuncCos(){
		super("cos");
	}
	public double eval(double val){ 
		return Math.cos(val);
	}

}
