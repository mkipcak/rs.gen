package com.mycompany.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Controller {

	Map<Inputs<?>, Object[]> map = new HashMap<Inputs<?>, Object[]>();
 
	<T1> Inputs<T1> whenIn(T1... inputs) {
		return new Inputs<T1>(this, inputs);
	}

	public Object[] define(Inputs<?> in, Object[] out) {
		return map.put(in, out);
	}
	Object currstate = null;
	public Object getCurrent(){return currstate;}
	public void setCurrent(Object state){
		this.currstate = state;
	}
	public Object[] when(Object... in0) {
		StringBuffer mess = new StringBuffer();
		mess.append("curr="+this.getCurrent() + " input=" + Arrays.toString(in0));
		Inputs<Object> in = new Inputs<Object>(this,new Object[in0.length + 1]);
		System.arraycopy(in0, 0, in.inputs, 1, in0.length);
		in.inputs[0] = this.currstate;
		Object[] outs = this.map.get(in);
		setCurrent(outs[0]);
		mess.append(" -> output=" + Arrays.toString(outs));
		System.out.println(mess);
		return outs;
	}
	@Override
	public String toString() { 
		return super.toString()  + "currstate=" + this.currstate;
	}
}