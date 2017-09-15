//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameBot.basicclass;

public class Actionvar {
    public int row;
    public int column;

    public Actionvar() {
        this.row = 0;
        this.column = 0;
    }

    public Actionvar(int i, int j) {
        this.row = i;
        this.column = j;
    }

    public Actionvar clone() {
        return new Actionvar(this.row, this.column);
    }
}
