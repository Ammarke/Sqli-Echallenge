
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DevTeam {
    public ArrayList<Member> team = new ArrayList<>();

    public  DevTeam(String info){
        String[] data = info.split(",");
        for(String elt : data){
            String[] memberString = elt.split(":");
            Member m = new Member(memberString[0],memberString[1]);
            team.add(m);
        }
    }
    public void add(String elt){
        String[] memberString = elt.split(":");
        Member m = new Member(memberString[0],memberString[1]);
        team.add(m);
    }

    public String assignTask(String data){
        String[] info = data.split(":");
        String profile = info[1];
        for(Member m : team){
            if(m.isDisponible() &&  m.getProfile().equals(profile) )
            {
                m.setTask(new Task(info[0],"open"));
                m.setDisponible(false);
                return "task assigned";
            }
        }
        return "there is no available profile to work on this task";
    }

    public String tasksStatus(){
        String status = "";
        status += team.stream().filter(team -> team.getTask() != null).map(Member::printInfo).collect(Collectors.joining(","));
        return status;
    }

    public void startTask(String task){
        for(Member m : team){
           if(m.getTask()!=null){
               if(m.getTask().getName().equals(task))
               {
                   m.getTask().setStatus("in progress");
               }
           }
        }

    }

    public void finishTask(String task){
        for(Member m : team){
            if(m.getTask()!=null){
                if(m.getTask().getName().equals(task))
                {
                    m.getTask().setStatus("done");
                    m.setDisponible(true);
                }
            }
        }
    }

    public String members(){
        String data = team.stream().map(Member::toString).collect(Collectors.joining(","));
        return data;

    }
}
