package org.china.dailiyun.chaogao.func;
import java.lang.Math;
public class FuncAcos extends Function{
	public FuncAcos(){
		super("arccos");
	}
	public double eval(double val){
		return Math.acos(val);
		
	}
}
