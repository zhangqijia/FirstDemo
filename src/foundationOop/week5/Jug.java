package foundationOop.week5;

class Jug {

    // create an empty jug of a given size
    Jug(int v) {
        volume = v;
        contents = 0;
    }


    public void fill() {
        int add = add(volume - contents);
        System.out.println("fill finished and " + add + "L redundant");
    }

    public void empty() {
        int add = add(-contents);
        System.out.println("empty finished");
    }

    public void pourInto(Jug to_jug) {
        int add = to_jug.add(contents);
        this.add(add - contents);
        System.out.println("pourInto finished");
    }

    // display the volume and contents of the jug
    public void display() {
        System.out.println("The " + volume + "-litre jug contains " +
                contents + " litres.");
    }

    // add v litres to the contents of the jug
    private int add(int v) {
        //calculate total contents if add v
        int total = contents + v;

        // the result is in the normal range of Jug volume
        if (volume >= total && total >= 0) {
            contents = total;
            return 0;

            // the contents is not enough to pourOut;
        } else if (total < 0) {
            contents = 0;
            return total;

            // the volume is not enough for contents;
        } else {
            contents = volume;
            return total - volume;
        }
    }

    // private fields
    private int volume;
    private int contents;

}