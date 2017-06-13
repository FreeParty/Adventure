package jtp.c.dendai.ac.jp.adventure.scene;
public enum GameState {
    first(new First()),
    second(new Second()),
    ending(new Ending()),
    badend(new BadEnd());

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