package unit.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import tcore.LHS;
import tcore.MetaModel;
import tcore.Model;
import tcore.Pattern;
import tcore.messages.MatchSet;
import tcore.messages.Packet;
import tcore.strategy.Matcher;
import tcore.messages.Match;

/**
 * Unit test for a case with an applicable NAC.
 *
 * @author Sebastien Ehouan
 * @author An Li
 * @since 2021-12-19
 */

class MatcherNACApplicable {

	@Before
	public void setUp() {
	}

	@Test
	public void isSuccess() throws Exception {
		utils.Utils.initialize();

		// Imports
		MetaModel OracleMM = new MetaModel("Oracle", "../Ramifier_New/Model/Oracle.ecore"); // Oracle MetaModel
		MetaModel Oracle_ramified = new MetaModel("OracleRoot", "../Ramifier_New/Model/Oracle_augmented.ecore"); // Ramified Oracle

		Model oracle = new Model("Oracle", "../Ramifier_New/Model/Oracle.xmi", OracleMM); // Dynamic Instance from Oracle

		Pattern SingleMatch_pre = new Pattern("SingleMatch_pre", "../Ramifier_New/Model/SingleMatch_pre.xmi", Oracle_ramified); // precondition same as single match
		Pattern Oracle_NAC = new Pattern("oracle_NAC", "../Ramifier_New/Model/NACApplicable.xmi", Oracle_ramified); // NAC
		ArrayList<Pattern> oracle_NACS = new ArrayList<>();
		oracle_NACS.add(Oracle_NAC);

		Packet p = new Packet(oracle);
		LHS lhs = new LHS(SingleMatch_pre, oracle_NACS);

		// Testing
		Matcher tester = new Matcher(lhs, 5, false); // max=1

		@SuppressWarnings("unused")
		Packet result = tester.packetIn(p);
		
		@SuppressWarnings("unused")
		Match expectedMatch = new Match();

		// Array of matches expected to be found
		// This example should not return any matches, as A -> B is part of both the NAC and the input model
		ArrayList<Match> expectedMatchArray = new ArrayList<Match>();

		// Expected MatchSet to find
		MatchSet ms = new MatchSet(expectedMatchArray, lhs);

		assertTrue(tester.isSuccess(), "Matcher failed");
		assertTrue(ms.equals(p.getCurrentMatchSet()), "Wrong match found");
	}
	
	@Test
	public void isSuccessVF2() throws Exception {
		utils.Utils.initialize();

		// Imports
		MetaModel OracleMM = new MetaModel("Oracle", "../Ramifier_New/Model/Oracle.ecore"); // Oracle MetaModel
		MetaModel Oracle_ramified = new MetaModel("OracleRoot", "../Ramifier_New/Model/Oracle_augmented.ecore"); // Ramified Oracle

		Model oracle = new Model("Oracle", "../Ramifier_New/Model/Oracle.xmi", OracleMM); // Dynamic Instance from Oracle

		Pattern SingleMatch_pre = new Pattern("SingleMatch_pre", "../Ramifier_New/Model/SingleMatch_pre.xmi", Oracle_ramified); // precondition same as single match
		Pattern Oracle_NAC = new Pattern("oracle_NAC", "../Ramifier_New/Model/NACApplicable.xmi", Oracle_ramified); // NAC
		ArrayList<Pattern> oracle_NACS = new ArrayList<>();
		oracle_NACS.add(Oracle_NAC);

		Packet p = new Packet(oracle);
		LHS lhs = new LHS(SingleMatch_pre, oracle_NACS);

		// Testing
		Matcher tester = new Matcher(lhs, 5, true); // max=1

		@SuppressWarnings("unused")
		Packet result = tester.packetIn(p);
				
		@SuppressWarnings("unused")
		Match expectedMatch = new Match();

		// Array of matches expected to be found
		// This example should not return any matches, as A -> B is part of both the NAC and the input model
		ArrayList<Match> expectedMatchArray = new ArrayList<Match>();

		// Expected MatchSet to find
		MatchSet ms = new MatchSet(expectedMatchArray, lhs);

		assertTrue(tester.isSuccess(), "Matcher failed");
		assertTrue(ms.equals(p.getCurrentMatchSet()), "Wrong match found");
	}
}
