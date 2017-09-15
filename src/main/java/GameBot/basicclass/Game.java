//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameBot.basicclass;

public class Game {
    public int turn;
    public int player;
    public boolean finish;
    public int winner;
    public State state;
    public Simulator simulator;

    public Game(int starting, State state, Simulator simulator) {
        this.player = starting;
        this.state = state;
        this.simulator = simulator;
        this.turn = 0;
    }
}
