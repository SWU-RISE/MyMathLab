package org.china.dailiyun.chaogao.func;
import java.lang.Math;
public class FuncLog10 extends Function {
	public  FuncLog10(){
		super("log10");
	}
	public double eval(double val){
		return Math.log10(val);
	}

}
