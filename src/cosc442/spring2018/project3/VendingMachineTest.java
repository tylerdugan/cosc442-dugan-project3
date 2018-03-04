package cosc442.spring2018.project3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class VendingMachineTest {
	
	public static VendingMachine VM;

	@Rule 
	public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		VM = new VendingMachine();
	}

	@After
	public void tearDown() throws Exception {
		VM = null;
	}

	/*
	 * Input = new VendingMachineItem inserted at index 0/slot A
	 * Assuming empty slot = successful add item
	 */
	@Test
	public void testAddItem_slot_empty() {
		 VendingMachineItem vmi = new VendingMachineItem("Chips", 1);
		 VM.addItem(vmi, "A");
		 assertEquals(vmi, VM.getItem("A")); // VendingMachine.getItem() returns the object, not item string
	}
	
	/*
	 * 2 Inputs - Fill slot A/index 0 with item. Attempt to add another unique object to the same slot
	 * Expected result = thrown exception 
	 */
	@Test (expected = Exception.class)
	public void testAddItem_slot_full() {
		VendingMachineItem vmi = new VendingMachineItem("Chips", 1);
		VendingMachineItem vmi2 = new VendingMachineItem("Cookies", 1);
		VM.addItem(vmi, "A"); VM.addItem(vmi2, "A");
		assertNotEquals(vmi2, VM.getItem("A"));
	}

	/*
	 * Add item to slot A, remove item, compare object in slot to initial item created 
	 * Expected outcome = empty slot, objects not the same 
	 */
	@Test
	public void testRemoveItem_slot_full() {
		VendingMachineItem vmi = new VendingMachineItem("Chips", 1); 
		VM.addItem(vmi, "A"); VM.removeItem("A");
		assertNotSame(vmi, VM.getItem("A"));
	}
	
	/*
	 * Attempt to remove item from empty slot
	 * Expected outcome = thrown exception
	 */
	@Test(expected = Exception.class)
	public void testRemoveItem_slot_empty() {
		VM.removeItem("A");
		thrown.expect(Exception.class);
	
	}

	/*
	 * Input = double > 0
	 * Expected outcome = match to current balance
	 */
	@Test
	public void testInsertMoney_positive() {
		VM.insertMoney(5.0);
		assertEquals(5.0, VM.getBalance(), 0);
	}

	/*
	 * Input = negative double
	 * Expected outcome = thrown exception
	 */
	@Test (expected = Exception.class)
	public void testInsertMoney_negative() {
		VM.insertMoney(-1.0);
	}

	/*
	 * Input = positive double
	 * Expected outcome = matching balance to VM 
	 */
	@Test
	public void testGetBalance() {
		VM.insertMoney(2.0);
		assertEquals(2.0, VM.getBalance(), 0);
	}

	/*
	 * Tested with an item in appropriate slot and balance > price of item
	 * Expected outcome = true
	 */
	@Test
	public void testMakePurchase_slotFull_posBalance() {
		VendingMachineItem vmi = new VendingMachineItem("Chips", 1.0);
		VM.addItem(vmi, "A");
		VM.balance = 2.0;
		assertTrue(VM.makePurchase("A"));
	}
	
	/*
	 * Tested with an empty slot
	 * Expected outcome = false
	 */
	@Test 
	public void testMakePurchase_slotEmpty_posBalance() {
		assertFalse(VM.makePurchase("A"));
	}
	
	/*
	 * Tested with an full slot // negative balance
	 * Expected outcome = false
	 */
	@Test 
	public void testMakePurchase_slotFull_negBalance() {
		VM.balance = -1;
		assertFalse(VM.makePurchase("A"));
	}
	

	/*
	 * Input = balance of 5
	 * Expected outcome - change matches balance added 
	 */
	@Test
	public void testReturnChange() {
		VM.balance = 5;
		assertEquals(5.0, VM.returnChange(), 0);
	}

}
