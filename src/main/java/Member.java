
public class Member {
    private String name;
    private String profile;
    private Task task;
    private boolean disponible;

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Member(String name, String profile) {
        this.name = name;
        this.profile = profile;
        this.disponible = true;
    }
    public Member(){
        //
        this.disponible = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String toString(){
        return profile+":"+name;
    }

    public String printInfo(){
        return task.getName()+":"+profile+"->"+task.getStatus();
    }

}
