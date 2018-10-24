public class Teammate {

    String name;
    int maxJumpHeight;
    int maxJumpLength;
    boolean myResult;

    public Teammate(String _name, int _maxJumpHeight, int _maxJumpLength){
        this.name = _name;
        this.maxJumpHeight = _maxJumpHeight;
        this.maxJumpLength = _maxJumpLength;
    }

    public void getTeammateInfo(){
        System.out.println("Привет! Меня зовут " + name + ". Я прыгаю в длину на " + maxJumpLength + ", а в высоту на " + maxJumpHeight);
    }

    public void setResult(boolean _result){
        this.myResult = _result;
    }

    public void doYouPass(){
        if (myResult){ System.out.println(name + " прошел дистацию! Несите приз!");
        } else {
            System.out.println(name + " слишком слаб, он не прошел(");
        }
    }

}
