//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import GameBot.basicclass.Action;
import GameBot.basicclass.Actionvar;
import GameBot.basicclass.Game;
import GameBot.viergewinnt.VAction;
import GameBot.viergewinnt.VierGewinntFactory;
import java.util.ArrayList;
import java.util.Scanner;

public class Play {
    public Play() {
    }

    public static void main(String[] args) {
        Game game = VierGewinntFactory.createNewInstance(1);
       int i = 0;
        int it = 0;
        boolean var4 = false;

        while(true) {
            System.out.print(game.state.toString());
            ArrayList<Action> ls = game.simulator.get_all_actions(game.state);

           
            for(i = 0; i < ls.size(); ++i) {
                System.out.println("possible:" + ((Action)ls.get(i)).act.row);
            }

            Scanner input = new Scanner(System.in);
            i = input.nextInt();
            int j = input.nextInt();
            if(i == -1) {
                return;
            }

            System.out.println("input: " + i);
            game.simulator.change_state(new VAction(new Actionvar(i, j)), game.state, it % 2 + 1);
            ++it;
        }
    }
}
