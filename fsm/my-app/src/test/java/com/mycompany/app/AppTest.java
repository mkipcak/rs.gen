package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple finite state machine.
 */
public class AppTest 
    extends TestCase
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
	    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void test1()
    { 
		Controller fsm = createController();
		fsm.setCurrent(grndFloor);
		fsm.when(down); 
		
        assertTrue( fsm.getCurrent() == grndFloor);
    }
    public void test2()
    { 
		Controller fsm = createController();
		fsm.setCurrent(grndFloor);
		fsm.when(down);
		fsm.when(up__); 
		
        assertTrue( fsm.getCurrent() == frstFloor);
    }
    public void test3()
    { 
		Controller fsm = createController();
		fsm.setCurrent(grndFloor);
		fsm.when(down);
		fsm.when(up__);
		fsm.when(up__); 
		
        assertTrue( fsm.getCurrent() == frstFloor);
    }
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
