package cosc442.spring2018.project3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineItemTest {
	
	public final static VendingMachineItem VMI = new VendingMachineItem("Chips", 2.5);
	public final static VendingMachineItem VMI2 = new VendingMachineItem("Cookie", -1);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void getName() {
		//VendingMachineItem VMI = new VendingMachineItem("Chips", 1.0);
		assertEquals("Chips", VMI.getName());
	}
	
	@Test
	public void getPrice() {
		assertEquals(2.5, VMI.getPrice(), .01);
	}
	
	
	//Constructor Test 
	@Test
	public void VendingMachineItem() {
		assertEquals("Chips", VMI.getName());
		 if(VMI.getPrice() >= 0) {
			assertEquals(2.5, VMI.getPrice(), .01);
		}
	}
	

}
