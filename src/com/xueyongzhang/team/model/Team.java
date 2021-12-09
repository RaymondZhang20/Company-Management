package com.xueyongzhang.team.model;

import com.xueyongzhang.team.domain.Architect;
import com.xueyongzhang.team.domain.Designer;
import com.xueyongzhang.team.domain.Employee;
import com.xueyongzhang.team.domain.Programmer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Team implements Iterable<Programmer> {
    private List<Programmer> programmers;
    private static Integer counter = 1;//Automatically form team Id(TID)
    private final Integer MAX_MEMBER = 5;//The maximum of team members
    private final Integer MAX_PROGRAMMER_MEMBER = 3;
    private final Integer MAX_DESIGNER_MEMBER = 2;
    private final Integer MAX_ARCHITECT_MEMBER = 1;


    public Team() {
        programmers = new LinkedList<>();
    }

    private boolean beyondMaxNumber() {
        return programmers.size() >= MAX_MEMBER;
    }

    private boolean programmerBeyondMaxNumber() {
        Integer num = 0;
        for (Programmer programmer : programmers) {
            if (programmer instanceof Programmer) {
                num++;
            }
        }
        return num >= MAX_PROGRAMMER_MEMBER;
    }

    private boolean designerBeyondMaxNumber() {
        Integer num = 0;
        for (Programmer programmer : programmers) {
            if (programmer instanceof Designer) {
                num++;
            }
        }
        return num >= MAX_DESIGNER_MEMBER;
    }

    private boolean architectBeyondMaxNumber() {
        Integer num = 0;
        for (Programmer programmer : programmers) {
            if (programmer instanceof Architect) {
                num++;
            }
        }
        return num >= MAX_ARCHITECT_MEMBER;
    }

    public void addMember(Employee e) throws TeamException {
        if (beyondMaxNumber())
            throw new TeamException("Members are full.");
        if (!(e instanceof Programmer))
            throw new TeamException("The selected one isn't a programmer or superior.");
        Programmer p = (Programmer) e;
        if (isExist(p))
            throw new TeamException("The selected one is already in the team.");
        if (p.getStatus().equals("BUSY")) {
            throw new TeamException("The selected one is busy right now.");
        } else if (p.getStatus().equals("VOCATION")) {
            throw new TeamException("The selected one is in vocation right now.");
        }
        if (p instanceof Architect) {
            if (architectBeyondMaxNumber()) throw new TeamException("Only one architect is allowed in the team.");
        } else if (p instanceof Designer) {
            if (designerBeyondMaxNumber()) throw new TeamException("Only two designer is allowed in the team.");
        } else if (p instanceof Programmer) {
            if (programmerBeyondMaxNumber()) throw new TeamException("Only three programmer is allowed in the team.");
        }
        programmers.add(p);
        p.setStatus("BUSY");
        p.setMemberId(counter++);
    }

    private boolean isExist(Programmer programmer) {
        for (Programmer p : programmers) {
            if (p.equals(programmer)) {
                return true;
            }
        }
        return false;
    }

    public void removeMember(Integer memberId) throws TeamException {
        boolean flag = false;
        for (Programmer p : programmers) {
            if (p.getMemberId().equals(memberId)) {
                programmers.remove(p);
                p.setStatus("FREE");
                flag = true;
            }
        }
        if (!flag) {
            throw new TeamException("The selected one cannot be found.");
        }
    }

    public Integer size() {
        return programmers.size();
    }

    @Override
    public Iterator<Programmer> iterator() {
        return programmers.iterator();
    }
}
