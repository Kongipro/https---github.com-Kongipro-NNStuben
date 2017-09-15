//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameBot.basicclass;

import java.util.ArrayList;

public class AlphaBetta {
    public AlphaBetta() {
    }

    public float perform_alphabetta(Game game, Evaloator ev, int depth, float alpha, float beta, int player) {
    	
        if(depth == 0) {
            return ev.evaluate(game.state);
        } else {
            ArrayList<Action> ls = game.simulator.get_all_actions(game.state);
            if(ls.size() == 0) {
                return ev.evaluate(game.state);
            } else {
               

                for(Action act : ls) {
                    
                   
                    game.state = game.simulator.change_state(act, game.state, player);
                    if(player == 1) {
                        alpha = Math.max(alpha, this.perform_alphabetta(game, ev, depth - 1, alpha, beta, 2));
                        if(alpha >= beta) {
                        	 game.state = game.simulator.undo_change(act, game.state);
                            return alpha;
                        }
                    } else {
                        beta = Math.min(beta, this.perform_alphabetta(game, ev, depth - 1, alpha, beta, 1));
                        if(alpha >= beta) {
                        	 game.state = game.simulator.undo_change(act, game.state);
                            return beta;
                        }
                    }
                    game.state = game.simulator.undo_change(act, game.state);
                   
                    }

                    
                }

                if(player == 1) {
                    return alpha;
                } else {
                    return beta;
                }
            }
        }
    }

