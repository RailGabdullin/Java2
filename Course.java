public class Course {

    Let lets[] = new Let[4];

    public Course(){
        lets[0] = new Let(1, 2);
        lets[1] = new Let(1, 2);
        lets[2] = new Let(2, 2);
        lets[3] = new Let(2, 2);

    }

    public void doIt (Team _team){

        boolean result = true;
        for (int i = 0; i < _team.teammates.length; i++) {
            for (int j = 0; j < lets.length; j++) {
                if ((_team.teammates[i].maxJumpLength >= lets[j].letLength) && (_team.teammates[i].maxJumpHeight >= lets[j].letHeight)){
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
            _team.teammates[i].setResult(result);
        }
    }

}
