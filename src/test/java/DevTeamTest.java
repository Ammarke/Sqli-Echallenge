import org.junit.Assert;
import org.junit.Test;

/**
 * A team is composed of a backend developer, a frontend developer and a tester
 * A task assigned to a team can be either backend or frontend task
 * The main purpose of this exercise is to create a team and assign tasks to the developers
 *
 * business rules:
 * 1- A team can work on a backend/frontend task if there is an available backend/frontend developer in the team
 * 2- A status of a task can be one of those status: Open, in progress, done
 * 3- A developer can work on a task if she/he has no task assigned in open or in progress status
 */
public class DevTeamTest {

    @Test
    public void create_dev_team(){
        DevTeam devTeam = new DevTeam("Rida:backend,Ahmed:frontend");
        devTeam.add("Nora:tester");
        Assert.assertEquals("backend:Rida,frontend:Ahmed,tester:Nora", devTeam.members());
    }

    @Test
    public void assign_task_to_a_team(){
        DevTeam devTeam = new DevTeam("Ahmed:frontend,Rida:backend,Nora:tester");
        Assert.assertEquals("task assigned", devTeam.assignTask("task:frontend"));
    }

    @Test
    public void team_can_not_work_on_task_due_to_a_missing_profile(){
        DevTeam devTeam = new DevTeam("Ahmed:frontend, Nora:tester");
        Assert.assertEquals("there is no available profile to work on this task", devTeam.assignTask("task:backend"));
    }

    @Test
    public void tasks_status_all_tasks_are_open() {
        DevTeam devTeam = new DevTeam("Ahmed:frontend,Rida:backend,Nora:tester");

        devTeam.assignTask("task1:frontend");
        devTeam.assignTask("task2:backend");

        Assert.assertEquals("task1:frontend->open,task2:backend->open", devTeam.tasksStatus());

    }

    @Test
    public void all_developpers_have_an_assigned_task() {
        DevTeam devTeam = new DevTeam("Ahmed:frontend,Rida:backend,Nora:tester");

        devTeam.assignTask("task1:frontend");
        devTeam.assignTask("task2:backend");

        devTeam.startTask("task1");
        Assert.assertEquals("task1:frontend->in progress,task2:backend->open",devTeam.tasksStatus());
        Assert.assertEquals("there is no available profile to work on this task", devTeam.assignTask("task3:backend"));
    }

    @Test
    public void once_a_task_is_done_a_team_can_receive_a_new_task() {
        DevTeam devTeam = new DevTeam("Ahmed:frontend,Rida:backend,Nora:tester");

        devTeam.assignTask("task1:frontend");
        devTeam.assignTask("task2:backend");

        devTeam.startTask("task1");
        devTeam.finishTask("task1");
        Assert.assertEquals("task1:frontend->done,task2:backend->open",devTeam.tasksStatus());
        Assert.assertEquals("task assigned", devTeam.assignTask("task3:frontend"));
    }

}
