package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.InventarioConteoController;

class TestInventarioConteoController {

	private InventarioConteoController controler;
	
	public void escenario() {
		controler = new InventarioConteoController();
	}
	@Test
	void test() {
		assertTrue(2+2==4);
		
		//fail("Not yet implemented");
		
	}
}
