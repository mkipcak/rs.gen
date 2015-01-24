package com.mycompany.app;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Unit test for simple finite state machine.
 */

public class ControllerTest 
{ 
	Trans up__ = new Trans() {
		{
			text = "up";
		}
	};

	Trans down = new Trans() {
		{
			text = "down";
		}
	};

	State grndFloor = new State() {
		{
			text = "ground";
		}
	};

	State frstFloor = new State() {
		{
			text = "first";
		}
	};

	View myView = new View();

	Output redOff = new Output("r0") {
		{
			view = myView;
		}

		void tell() {
			view.red = 0;
		}
	};

	Output redOn = new Output("r1") {
		{
			view = myView;
		}

		void tell() {
			view.red = 1;
		}
	};

	Output greenOff = new Output("g0") {
		{
			view = myView;
		}

		void tell() {
			view.green = 0;
		}
	};

	Output greenOn = new Output("g1") {
		{
			view = myView;
		}

		void tell() {
			view.green = 1;
		}
	};
	
	private Controller createController(){
		Controller fsm = new Controller();
    
		fsm.whenIn(grndFloor, down).thenOut(grndFloor, redOn, greenOff);
		fsm.whenIn(grndFloor, up__).thenOut(frstFloor, redOff, greenOn);
		fsm.whenIn(frstFloor, down).thenOut(grndFloor, redOff, greenOn);
		fsm.whenIn(frstFloor, up__).thenOut(frstFloor, redOn, greenOff);
		
        return fsm;
	}
	    
    @Test
    public void test1()
    { 
		Controller fsm = createController();
		fsm.setCurrent(grndFloor);
		fsm.when(down); 
		
        assertThat((State)fsm.getCurrent(), is(equalTo(grndFloor)));
    }
    
    @Test
    public void test2()
    { 
    	// Arrange
		Controller fsm = createController();
		fsm.setCurrent(grndFloor);
		fsm.when(down);
		
		// Act
		fsm.when(up__); 
		
		// Assert
        assertTrue( fsm.getCurrent() == frstFloor);
    }
    
    @Test
    public void test3()
    { 
		Controller fsm = createController();
		fsm.setCurrent(grndFloor);
		fsm.when(down);
		fsm.when(up__);
		fsm.when(up__); 
		
        assertTrue( fsm.getCurrent() == frstFloor);
    }
    
    @Test
    public void test4()
    { 
		Controller fsm = createController();
		fsm.setCurrent(grndFloor);
		fsm.when(down);
		fsm.when(up__);
		fsm.when(up__);
		fsm.when(down); 
		
        assertTrue( fsm.getCurrent() == grndFloor);
    }
}
