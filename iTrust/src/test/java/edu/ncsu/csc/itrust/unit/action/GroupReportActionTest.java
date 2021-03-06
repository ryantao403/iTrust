package edu.ncsu.csc.itrust.unit.action;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.action.GroupReportAction;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.old.beans.GroupReportBean;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.report.DemographicReportFilter;
import edu.ncsu.csc.itrust.report.MedicalReportFilter.MedicalReportFilterType;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.report.ReportFilter;
import edu.ncsu.csc.itrust.report.DemographicReportFilter.DemographicReportFilterType;
import junit.framework.TestCase;

public class GroupReportActionTest extends TestCase {

	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private TestDataGenerator gen = new TestDataGenerator();
	private GroupReportAction action;

	@Override
	protected void setUp() throws Exception {
		gen.clearAllTables();
		gen.standardData();
		action = new GroupReportAction(factory, 1l);
	}

	public void testGenerateReport() {
		List<ReportFilter> f = new ArrayList<ReportFilter>();
		f.add(new DemographicReportFilter(DemographicReportFilterType.LAST_NAME, "Person", factory));
		GroupReportBean res = action.generateReport(f);
		assertTrue(res.getPatients().size() == 1);
		assertEquals(1L, res.getPatients().get(0).getMID());
	}

	public void testGetComprehensiveDemographicInfo() throws DBException {
		PatientBean b = factory.getPatientDAO().getPatient(2L);
		for (DemographicReportFilterType filterType : DemographicReportFilterType.values()) {
			String res = action.getComprehensiveDemographicInfo(b, filterType);
			switch (filterType) {
			case GENDER:
				assertEquals("Male", res);
				break;
			case LAST_NAME:
				assertEquals("Programmer", res);
				break;
			case FIRST_NAME:
				assertEquals("Andy", res);
				break;
			case CONTACT_EMAIL:
				assertEquals("andy.programmer@gmail.com", res);
				break;
			case STREET_ADDR:
				assertEquals("344 Bob Street ", res);
				break;
			case CITY:
				assertEquals("Raleigh", res);
				break;
			case STATE:
				assertEquals("NC", res);
				break;
			case ZIP:
				assertEquals("27607", res);
				break;
			case PHONE:
				assertEquals("555-555-5555", res);
				break;
			case EMER_CONTACT_NAME:
				assertEquals("Mr Emergency", res);
				break;
			case EMER_CONTACT_PHONE:
				assertEquals("555-555-5551", res);
				break;
			case INSURE_NAME:
				assertEquals("IC", res);
				break;
			case INSURE_ADDR:
				assertEquals("Street1 Street2", res);
				break;
			case INSURE_CITY:
				assertEquals("City", res);
				break;
			case INSURE_STATE:
				assertEquals("PA", res);
				break;
			case INSURE_ZIP:
				assertEquals("19003-2715", res);
				break;
			case INSURE_PHONE:
				assertEquals("555-555-5555", res);
				break;
			case INSURE_ID:
				assertEquals("1", res);
				break;
			case PARENT_FIRST_NAME:
				assertEquals("Random\n", res);
				break;
			case PARENT_LAST_NAME:
				assertEquals("Person\n", res);
				break;
			case CHILD_FIRST_NAME:
				assertEquals("Baby\nBaby\nBaby\nBaby\n", res);
				break;
			case CHILD_LAST_NAME:
				assertEquals("Programmer\nA\nB\nC\n", res);
				break;
			case SIBLING_FIRST_NAME:
				assertEquals("Care\nNoRecords\nBowser\nPrincess\n", res);
				break;
			case SIBLING_LAST_NAME:
				assertEquals("Needs\nHas\nKoopa\nPeach\n", res);
				break;
			default:
				break;
			}
		}
	}

	public void testGetComprehensiveMedicalInfo() throws DBException {
		PatientBean b = factory.getPatientDAO().getPatient(2L);
		for (MedicalReportFilterType filterType : MedicalReportFilterType.values()) {
			String res = action.getComprehensiveMedicalInfo(b, filterType);
			switch (filterType) {
			case ALLERGY:
				assertEquals("\n664662530\n", res);
				break;
			default:
				break;
			}
		}
	}

}
