package org.china.dailiyun.chaogao.func;
import java.lang.Math;
public class FuncExp extends Function {
	public FuncExp(){
		super("exp");
	}
	public double eval(double val){
		return Math.exp(val);
	}

}
