package com.xueyongzhang.team.ui;

import com.xueyongzhang.team.domain.Employee;
import com.xueyongzhang.team.domain.Programmer;
import com.xueyongzhang.team.model.NameListService;
import com.xueyongzhang.team.model.TeamException;
import com.xueyongzhang.team.model.TeamService;

import static com.xueyongzhang.team.ui.TSUtility.*;

public class TeamView {
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();


	public void enterMainMenu() {
		boolean loopFlag = true;
		char key = 0;

		do {
			if (key != '1') {
				listAllEmployees();
			}
			System.out.print("1-Team View   2-Add New Member   3-Remove Member   4-Quit    Please select(1-4):");
			key = readMenuSelection();
			System.out.println();
			switch (key) {
			case '1':
				listTeam();
				break;
			case '2':
				addMember();
				break;
			case '3':
				deleteMember();
				break;
			case '4':
				System.out.print("Please conform(Y/N)：");
				char yn = readConfirmSelection();
				if (yn == 'Y')
					loopFlag = false;
				break;
			}
		} while (loopFlag);
	}

	private void listAllEmployees() {
		System.out
				.println("\n-------------------------------Team Assignment Project--------------------------------\n");
		Employee[] employees = listSvc.getAllEmployees();
			System.out.println("ID\t\tname\t\tage\t\tsalary\t\tposition\t\tstatus\t\tbonus\t\tstock\t\tequipment");
		for (Employee e : employees) {
			System.out.println(" " + e.toString());
		}
		System.out
				.println("-------------------------------------------------------------------------------");
	}

	private void listTeam() {
		System.out
				.println("\n--------------------Team View---------------------\n");
		Programmer[] team = teamSvc.getTeam();
		if (team.length == 0) {
			System.out.println("None in the team for now.");
		} else {
			System.out.println("TID/ID\t\tname\t\tage\t\tsalary\t\tposition\t\tbonus\t\tstock");
		}

		for (Programmer p : team) {
			System.out.println(" " + p.getDetailsForTeam());
		}
		System.out
				.println("-----------------------------------------------------");
	}

	private void addMember() {
		System.out.println("---------------------Add New Number---------------------");
		System.out.print("Please type ID:");
		int id = readInt();

		try {
			Employee e = listSvc.getEmployee(id);
			teamSvc.addMember(e);
			System.out.println("Successfully added");
		} catch (TeamException e) {
			System.out.println("Failure,reason:" + e.getMessage());
		}
		readReturn();
	}

	private void deleteMember() {
		System.out.println("---------------------Remove Member---------------------");
		System.out.print("Please type TID：");
		int id = readInt();
		System.out.print("Please conform(Y/N)：");
		char yn = readConfirmSelection();
		if (yn == 'N')
			return;

		try {
			teamSvc.removeMember(id);
			System.out.println("Successfully removed");
		} catch (TeamException e) {
			System.out.println("Failure,reason:" + e.getMessage());
		}
		TSUtility.readReturn();
	}

	public static void main(String[] args) {
		TeamView view = new TeamView();
		view.enterMainMenu();
	}
}
