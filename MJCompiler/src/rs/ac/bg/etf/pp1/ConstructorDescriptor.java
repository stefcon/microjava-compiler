package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class ConstructorDescriptor {

	int formalParamCount;
	Obj constructorObj;
	
	ConstructorDescriptor(Obj obj, int count) {
		constructorObj = obj;
		formalParamCount = count;
	}
	
	public Obj getConstructorObj() {
		return constructorObj;
	}
	
	public int getFormalParamCount() {
		return formalParamCount;
	}
}
