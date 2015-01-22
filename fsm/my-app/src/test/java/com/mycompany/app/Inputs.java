package com.mycompany.app;

import java.util.Arrays;

public class Inputs<TINPUT> {
	Controller owner;
	TINPUT[] inputs;

	public Inputs(Controller owner, TINPUT[] oa) {
		this.inputs = oa;
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(inputs);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inputs<?> other = (Inputs<?>) obj;
		if (!Arrays.equals(inputs, other.inputs))
			return false;
		return true;
	}
	Controller thenOut(Object... outs){
		this.owner.define(this, outs);
		return this.owner;
	}
}
