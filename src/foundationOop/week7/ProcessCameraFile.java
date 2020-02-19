package foundationOop.week7;

import foundationOop.assignment03.sheffield.EasyReader;

import java.util.ArrayList;

public class ProcessCameraFile {

    private static ArrayList<Camera> cameraList = new ArrayList<>();

    // display cameras that have a given name
    private static void searchByName(Camera[] a, int n, String s) {

        // CODE MISSING

    }

    // display cameras with a given stock code
    private static void searchByStockCode(Camera[] a, int n, String stokeCode) {

        // CODE MISSING

    }

    // main method
    public static void main(String[] args) {

        EasyReader easyReader = new EasyReader("U:\\ManW10\\Downloads\\myjava\\src\\foundationOop\\week7\\cameras.txt");
        int i = 0;
        Camera camera = new Camera();
        while (i >= 0) {
            String s = easyReader.readString();
            if (s == null || s.length() == 0) {
                break;
            }
            switch (i % 4) {
                case 0:
                    camera = new Camera();
                    camera.setName(s);
                    break;
                case 1:
                    camera.setStockCode(s);
                    break;
                case 2:
                    camera.setCondition(Integer.parseInt(s));
                    break;
                case 3:
                    camera.setPrice(Double.parseDouble(s));
                    cameraList.add(camera);
                    break;
            }
            i++;
        }
        System.out.println("finish");
    }

}
