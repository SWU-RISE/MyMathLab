package org.china.dailiyun.chaogao.func;
import java.lang.Math;
public class FuncLog extends Function{
	public FuncLog(){
		super("log");
	}
	public double eval(double val){
		return Math.log(val);
	}
}
