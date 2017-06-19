package jtp.c.dendai.ac.jp.adventure.scene;
public enum GameState {
    first(new First()),
    second(new Second()),

    ben1(new Ben1()),
    ben2(new Ben2()),
    ben3(new Ben3()),
    ben4(new Ben4()),

    fre1(new Fre1()),
    freX1(new FreX1()),
    freX2(new FreX2()),
    freY1(new FreY1()),
    freY2(new FreY2()),
    freY3(new FreY3()),
    freY4(new FreY4()),
    freY5(new FreY5()),
    freY6(new FreY6()),
    freZ1(new FreZ1()),
    freZ2(new FreZ2()),
    freZ3(new FreZ3()),

    god1(new God1()),
    god2(new God2()),
    god3(new God3()),
    god4(new God4()),
    god5(new God5()),

    badend(new BadEnd()),
    deadend(new DeadEnd());

    private Scene scene;
    public Scene getScene(){
        return scene;
    }
    private GameState(Scene s){
        scene = s;
    }
    public static Scene getInitialScene(){
        return first.getScene();
    }
}