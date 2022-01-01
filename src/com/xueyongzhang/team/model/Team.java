package com.xueyongzhang.team.model;

import com.xueyongzhang.team.domain.Architect;
import com.xueyongzhang.team.domain.Designer;
import com.xueyongzhang.team.domain.Employee;
import com.xueyongzhang.team.domain.Programmer;
import com.xueyongzhang.team.persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Team implements Iterable<Programmer>, Writable {
    private String name;
    private List<Programmer> programmers;
    private final Integer MAX_MEMBER = 5;//The maximum of team members
    private final Integer MAX_PROGRAMMER_MEMBER = 3;
    private final Integer MAX_DESIGNER_MEMBER = 2;
    private final Integer MAX_ARCHITECT_MEMBER = 1;


    public Team() {
        name = "new team";
        programmers = new LinkedList<>();
    }

    public Team(String name) {
        this.name = name;
        programmers = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    private boolean beyondMaxNumber() {
        return programmers.size() >= MAX_MEMBER;
    }

    private boolean programmerBeyondMaxNumber() {
        Integer num = 0;
        for (Programmer programmer : programmers) {
            if (programmer.getClass().equals(Programmer.class)) {
                num++;
            }
        }
        return num >= MAX_PROGRAMMER_MEMBER;
    }

    private boolean designerBeyondMaxNumber() {
        Integer num = 0;
        for (Programmer programmer : programmers) {
            if (programmer.getClass().equals(Designer.class)) {
                num++;
            }
        }
        return num >= MAX_DESIGNER_MEMBER;
    }

    private boolean architectBeyondMaxNumber() {
        Integer num = 0;
        for (Programmer programmer : programmers) {
            if (programmer.getClass().equals(Architect.class)) {
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
            throw new TeamException("The selected one is already in this team.");
        if (!p.getStatus().equals("FREE")) {
            throw new TeamException("The selected one is " + p.getStatus() + " right now.");
        }
        if (p.getClass().equals(Architect.class)) {
            if (architectBeyondMaxNumber()) throw new TeamException("Only one architect is allowed in the team.");
        } else if (p.getClass().equals(Designer.class)) {
            if (designerBeyondMaxNumber()) throw new TeamException("Only two designers are allowed in the team.");
        } else if (p.getClass().equals(Programmer.class)) {
            if (programmerBeyondMaxNumber())
                throw new TeamException("Only three programmers are allowed in the team.");
        }
        p.setMemberId(programmers.size() + 1);
        programmers.add(p);
        p.setTeam(this);
        p.setStatus();
    }

    private boolean isExist(Programmer programmer) {
        for (Programmer p : programmers) {
            if (p.equals(programmer)) {
                return true;
            }
        }
        return false;
    }

    public void removeMember(Programmer p) throws TeamException {
        if (isExist(p)) {
            programmers.remove(p);
            p.setTeam(null);
            p.setStatus();
            giveNewMemberId();
        }
    }

    public void removeMember(Integer memberId) throws TeamException {
        boolean flag = false;
        Iterator<Programmer> programmerIterator = programmers.iterator();
        while (programmerIterator.hasNext() && !flag) {
            Programmer p= programmerIterator.next();
            if (p.getMemberId().equals(memberId)) {
                programmers.remove(p);
                p.setTeam(null);
                p.setStatus();
                giveNewMemberId();
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

    private void giveNewMemberId() {
        Iterator<Programmer> programmerIterator = programmers.iterator();
        int i = 0;
        while (programmerIterator.hasNext()) {
            programmerIterator.next().setMemberId(++i);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Iterator<Programmer> iterator() {
        return programmers.iterator();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Employee e : programmers) {
            jsonArray.put(e.toJson());
        }

        jsonObject.put("name", name);
        jsonObject.put("team", jsonArray);
        return jsonObject;
    }
}
