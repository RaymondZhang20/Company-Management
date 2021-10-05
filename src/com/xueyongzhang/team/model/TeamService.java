package com.xueyongzhang.team.model;

import com.xueyongzhang.team.domain.Architect;
import com.xueyongzhang.team.domain.Designer;
import com.xueyongzhang.team.domain.Employee;
import com.xueyongzhang.team.domain.Programmer;
import com.xueyongzhang.team.model.TeamException;

public class TeamService {
    private static int counter = 1;//Automatically form memberId
    private final int MAX_MEMBER = 5;//The maximum of team members
    private Programmer[] team = new Programmer[MAX_MEMBER];//Preserve new member
    private int total = 0;//Actual team member

    public TeamService() {
    }
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];

        for (int i = 0; i < total; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    public void addMember(Employee e) throws TeamException {
        if (total >= MAX_MEMBER)
            throw new TeamException("Members are full.");
        if (!(e instanceof Programmer))
            throw new TeamException("The selected one isn't a programmer or superior.");

        Programmer p = (Programmer)e;
        
        if (isExist(p))
        	throw new TeamException("The selected one is already in the team.");
        
        if(p.getStatus().equals("BUSY")) {
        	throw new TeamException("The selected one is busy right now.");
        }else if(p.getStatus().equals("VOCATION")) {
        	throw new TeamException("The selected one is in vocation right now.");
        }

        
        int numOfArch = 0, numOfDsgn = 0, numOfPrg = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect) numOfArch++;
            else if (team[i] instanceof Designer) numOfDsgn++;
            else if (team[i] instanceof Programmer) numOfPrg++;
        }

        if (p instanceof Architect) {
            if (numOfArch >= 1) throw new TeamException("Only one architect is allowed in the team.");
        } else if (p instanceof Designer) {
            if (numOfDsgn >= 2) throw new TeamException("Only two designer is allowed in the team.");
        } else if (p instanceof Programmer) {
            if (numOfPrg >= 3) throw new TeamException("Only three programmer is allowed in the team.");
        }
        p.setStatus("BUSY");
        p.setMemberId(counter++);
        team[total++] = p;
    }

    private boolean isExist(Programmer p) {
        for (int i = 0; i < total; i++) {
            if (team[i].getId() == p.getId()) return true;
        }

        return false;
    }

    public void removeMember(int memberId) throws TeamException {
        int n = 0;
        for (; n < total; n++) {
            if (team[n].getMemberId() == memberId) {
                team[n].setStatus("FREE");
                break;
            }
        }
        if (n == total)
            throw new TeamException("The selected one cannot be found.");
        for (int i = n + 1; i < total; i++) {
            team[i - 1] = team[i];
        }
        team[--total] = null;
    }
}
