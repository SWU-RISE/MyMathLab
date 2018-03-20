package org.china.dailiyun.chaogao.func;
import java.lang.Math;
public class FuncAsin extends Function {
	public FuncAsin(){
		super("arcsin");
	}
	public double eval(double val){
		return  Math.asin(val);
	}
	

}
