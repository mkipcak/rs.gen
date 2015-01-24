package com.mycompany.app;


public class Main {

	public static void main(String[] args) {

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

		State ground = new State() {
			{
				text = "ground";
			}
		};

		State first_ = new State() {
			{
				text = "first";
			}
		};

		final View myView = new View();

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

		Controller fsm = new Controller();

		fsm.whenIn(ground, down).thenOut(ground, redOn, greenOff);
		fsm.whenIn(ground, up__).thenOut(first_, redOff, greenOn);
		fsm.whenIn(first_, down).thenOut(ground, redOff, greenOn);
		fsm.whenIn(first_, up__).thenOut(first_, redOn, greenOff);

		fsm.setCurrent(ground);
		fsm.when(down);
		fsm.when(up__);
		fsm.when(up__);
		fsm.when(down);
		System.out.println(fsm.getCurrent());
	}

}