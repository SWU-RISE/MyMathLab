package org.china.dailiyun.chaogao.func;
import java.lang.Math;
public class FuncTan extends Function{
	public FuncTan(){
		super("tan");
	}
	public double eval(double val){
		return Math.tan(val);
	}

}
