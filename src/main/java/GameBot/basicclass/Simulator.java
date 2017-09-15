//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameBot.basicclass;

import java.util.ArrayList;

public abstract class Simulator {
    public Simulator() {
    }

    public abstract State change_state(Action var1, State var2, int var3);

    public abstract ArrayList<Action> get_all_actions(State var1);

    public abstract int check_state(State var1);

    public abstract State undo_change(Action var1, State var2);
}
