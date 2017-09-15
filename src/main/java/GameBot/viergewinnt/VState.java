package GameBot.viergewinnt;

import GameBot.basicclass.State;
import GameBot.basicclass.Statevar;

public class VState extends State {
    public VState() {
        Statevar tmp = new Statevar();
        tmp.board = new int[VierGewinntFactory.length][VierGewinntFactory.hight];
        this.var = tmp;
    }

    public String toString() {
        String str = "";

        for(int i = 0; i < VierGewinntFactory.hight; ++i) {
            for(int j = 0; j < VierGewinntFactory.length; ++j) {
                str = str + this.var.board[j][i];
            }

            str = str + "\n";
        }

        return str;
    }
}
