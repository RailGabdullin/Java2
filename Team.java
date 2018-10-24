public class Team {

    String teamName;
    Teammate teammates[] = new Teammate[4];

    public Team(){
        this.teamName = "VirtusPro";
        teammates[0] = new Teammate("Вася", 3, 2);
        teammates[1] = new Teammate("Петя",2, 2);
        teammates[2] = new Teammate("Заур",1, 2);
        teammates[3] = new Teammate("Снежана",2, 1);
    }

    public void getTeamInfo(){
        System.out.println(teamName);
        for (int i = 0; i < teammates.length; i++) {
            teammates[i].getTeammateInfo();
        }
    }

    public void showResults(){
        for (int i = 0; i < teammates.length; i++) {
            teammates[i].doYouPass();
        }
    }

}
