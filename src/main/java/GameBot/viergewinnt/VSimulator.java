//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameBot.viergewinnt;

import GameBot.basicclass.Action;
import GameBot.basicclass.Actionvar;
import GameBot.basicclass.Simulator;
import GameBot.basicclass.State;
import GameBot.basicclass.Statevar;
import k.nnlib.GamePlayer.viergewinnt.VAction;

import java.util.ArrayList;

public class VSimulator extends Simulator {
	
	int xm;
	int ym;
    public VSimulator() {
    }

    public State change_state(Action action, State state, int player) throws Error {
        int move = this.move(state.var, action.act);
        if(move != -1 && action.act.column == move) {
            state.var.board[action.act.row][move] = player;
            boolean full = true;
            
            
            for(int j = 0; j < state.var.board.length; j++)
            {
            	for(int h = 0; h < state.var.board[0].length; h++)
            	{
            		if( state.var.board[j][h] == 0)
            		{
            			full = false;
            			break;
            		}
            		
            	}
            	if(!full)
            		break;
            }
            
            if(full)
            	state.gamestate = -1;
            
            state.gamestate = referee(state);
            
        
        
            
            
            return state;
        } else {
            throw new Error();
        }
    }
    
    
    
    private int referee(State state)
    {
    	int [][] board = state.var.board;
    	
            int winner=0;
            int i;
            int k;
            xm = board.length;
            ym = board[0].length;
            for(int j=board.length; j>0; --j){
                for(int jj=board[0].length; jj>0; --jj){
                    i = j-1; //da code von matlab übernommen und dort startet es bei 1 und nicht bei 0
                    k = jj-1;

                    //In Zeile gewonnen?
                    if(in_boundary(i+1,k)&&in_boundary(i+2,k)&&in_boundary(i+3,k)) { //nach rechts
                        if (board[i][k] == 1 && board[i + 1][k] == 1 && board[i + 2][k] == 1 && board[i + 3][k] == 1) {
                            winner = 1;
                            return winner;
                        } else if (board[i][k] == 2 && board[i + 1][k] == 2 && board[i + 2][k] == 2 && board[i + 3][k] == 2) {
                            winner = 2;
                            return winner;
                        }
                    }

                    if(in_boundary(i-1,k)&&in_boundary(i-2,k)&&in_boundary(i-3,k)) { //nach links
                        if (board[i][k] == 1 && board[i - 1][k] == 1 && board[i - 2][k] == 1 && board[i - 3][k] == 1) {
                            winner = 1;
                            return winner;
                        } else if (board[i][k] == 2 && board[i - 1][k] == 2 && board[i - 2][k] == 2 && board[i - 3][k] == 2) {
                            winner = 2;
                            return winner;
                        }
                    }

                    //In Spalte gewonnen?
                    if(in_boundary(i,k+1)&&in_boundary(i,k+2)&&in_boundary(i,k+3)) { //nach unten
                        if (board[i][k] == 1 && board[i][k+1] == 1 && board[i][k+2] == 1 && board[i][k+3] == 1) {
                            winner = 1;
                            return winner;
                        } else if (board[i][k] == 2 && board[i][k+1] == 2 && board[i][k+2] == 2 && board[i][k+3] == 2) {
                            winner = 2;
                            return winner;
                        }
                    }

                    if(in_boundary(i,k-1)&&in_boundary(i,k-2)&&in_boundary(i,k-3)) { //nach oben
                        if (board[i][k] == 1 && board[i][k-1] == 1 && board[i][k-2] == 1 && board[i][k-3] == 1) {
                            winner = 1;
                            return winner;
                        } else if (board[i][k] == 2 && board[i][k-1] == 2 && board[i][k-2] == 2 && board[i][k-3] == 2) {
                            winner = 2;
                            return winner;
                        }
                    }

                    //In Diagonale gewonnen?
                    if(in_boundary(i+1,k+1)&&in_boundary(i+2,k+2)&&in_boundary(i+3,k+3)) {
                        if (board[i][k] == 1 && board[i + 1][k + 1] == 1 && board[i + 2][k + 2] == 1 && board[i + 3][k + 3] == 1) {
                            winner = 1;
                            return winner;
                        }
                        else if (board[i][k] == 2 && board[i + 1][k + 1] == 2 && board[i + 2][k + 2] == 2 && board[i + 3][k + 3] == 2){
                            winner = 2;
                            return winner;
                        }
                    }

                    if(in_boundary(i-1,k-1)&&in_boundary(i-2,k-2)&&in_boundary(i-3,k-3)) {
                        if (board[i][k] == 1 && board[i - 1][k - 1] == 1 && board[i - 2][k - 2] == 1 && board[i - 3][k - 3] == 1) {
                            winner = 1;
                            return winner;
                        }
                        else if (board[i][k] == 2 && board[i - 1][k - 1] == 2 && board[i - 2][k - 2] == 2 && board[i - 3][k - 3] == 2){
                            winner = 2;
                            return winner;
                        }
                    }

                    if(in_boundary(i+1,k-1)&&in_boundary(i+2,k-2)&&in_boundary(i+3,k-3)) {
                        if (board[i][k] == 1 && board[i + 1][k - 1] == 1 && board[i + 2][k - 2] == 1 && board[i + 3][k - 3] == 1) {
                            winner = 1;
                            return winner;
                        }
                        else if (board[i][k] == 2 && board[i + 1][k - 1] == 2 && board[i + 2][k - 2] == 2 && board[i + 3][k - 3] == 2){
                            winner = 2;
                            return winner;
                        }
                    }

                    if(in_boundary(i-1,k+1)&&in_boundary(i-2,k+2)&&in_boundary(i-3,k+3)) {
                        if (board[i][k] == 1 && board[i - 1][k + 1] == 1 && board[i - 2][k + 2] == 1 && board[i - 3][k + 3] == 1) {
                            winner = 1;
                            return winner;
                        }
                        else if (board[i][k] == 2 && board[i - 1][k + 1] == 2 && board[i - 2][k + 2] == 2 && board[i - 3][k + 3] == 2){
                            winner = 2;
                            return winner;
                        }
                    }

                }
            }

            return winner;
        
    }
    
    private boolean in_boundary(int x, int y){
        boolean res = false;
        if(x<xm&&x>=0&&y<ym&&y>=0)
            res = true;
        return res;
    }
    
    
    

    public ArrayList<Action> get_all_actions(State state) {
        ArrayList<Action> list = new ArrayList<Action>();

        for(Actionvar var = new Actionvar(); var.row < VierGewinntFactory.length; ++var.row) {
            var.column = this.move(state.var, var);
            if(var.column != -1) {
                list.add(new VAction(var.clone()));
            }
        }

        return list;
    }

    public State undo_change(Action action, State state) {
        state.var.board[action.act.row][action.act.column] = 0;
        return state;
    }

    public int check_state(State state) {
        return state.gamestate;
    }

    private int move(Statevar sv, Actionvar av) {
        int move = -1;

        for(int i = 0; i < VierGewinntFactory.hight; ++i) {
            if(sv.board[av.row][i] == 0) {
                move = i;
                break;
            }
        }

        return move;
    }
}
