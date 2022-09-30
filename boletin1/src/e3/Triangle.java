package e3;
record Triangle(int angle1, int angle2, int angle3) {
    //private static int angle1;
    //private static int angle2;
    //private static int angle3;

    public Triangle {
        if (angle1 + angle2 + angle3 != 180) throw new IllegalArgumentException("Angles not sum 180\n");
    }


}
