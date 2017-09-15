//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameBot.viergewinnt;

import GameBot.basicclass.Game;
import GameBot.basicclass.Simulator;
import GameBot.basicclass.State;

public class VierGewinntFactory {
    static int length = 7;
    static int hight = 6;

    public VierGewinntFactory() {
    }

    public static Game createNewInstance(int start) {
        Simulator sim = new VSimulator();
        State state = new VState();
        return new Game(start, state, sim);
    }
}
