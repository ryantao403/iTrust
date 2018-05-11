package edu.ncsu.csc.itrust.action;

import java.util.List;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.PatientVisitBean;
import edu.ncsu.csc.itrust.beans.PersonnelBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

/**
 * ViewPatientOfficeVisitHistoryActionTest
 */
public class ViewPatientOfficeVisitHistoryActionTest extends TestCase{

	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private TestDataGenerator gen = new TestDataGenerator();
	private ViewPatientOfficeVisitHistoryAction action;
	
	@Override
	protected void setUp() throws Exception{
		action = new ViewPatientOfficeVisitHistoryAction(factory, 9000000000L);
		gen.clearAllTables();
		gen.standardData();
	}
	
	/**
	 * testGetPersonnel
	 * @throws Exception
	 */
	public void testGetPersonnel() throws Exception {
		PersonnelBean hcp = action.getPersonnel();
		assertNotNull(hcp.getFirstName(), "Kelly");
	}
	
	/**
	 * testGetPatients
	 * @throws Exception
	 */
	public void testGetPatients() throws Exception {
		List<PatientVisitBean> list = action.getPatients();
		assertEquals(49, list.size());
		assertEquals("31", list.get(17).getLastOVDateD());
		assertEquals("03", list.get(17).getLastOVDateM());
	}
}
