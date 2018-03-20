package org.china.dailiyun.chaogao.func;
import java.lang.Math;
public class FuncAtan extends Function{
	public FuncAtan(){
		super("arctan");
	}
	public double eval(double val){
		return Math.atan(val);
	}

}
