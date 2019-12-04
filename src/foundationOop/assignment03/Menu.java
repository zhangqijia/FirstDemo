package foundationOop.assignment03;

import foundationOop.assignment03.food.Pie;
import sheffield.EasyReader;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Format menu
 */
public class Menu {

    private static final Pattern pattern = Pattern.compile("(\\w+)\\s+\\((\\w+)\\)");

    /**
     * format new version menu, nice presentation and beautiful
     *
     * @param menuPath original menu path
     */
    public void formatMenu(String menuPath) {

        // create file reader and writer
        BufferedWriter writer = getBufferedWriter();
        BufferedReader reader = getBufferedReader(menuPath);

        // create three types of pie lists
        ArrayList<Pie> meatPies = new ArrayList<>();
        ArrayList<Pie> vegetarianPies = new ArrayList<>();
        ArrayList<Pie> veganPies = new ArrayList<>();

        try {
            String line;
            // repeat reading every line 
            a:
            while (true) {
                line = reader.readLine();
                // stop at the end of the file
                if (line == null)
                    break;
                // name: means the start of one pie content
                if (line.trim().equals("name:")) {
                    Pie pie = new Pie();
                    ArrayList<Ingredient> ingredients = new ArrayList<>();
                    pie.setIngredients(ingredients);
                    String name = reader.readLine();
                    pie.setName(name);
                    while ((line = reader.readLine()) != null) {
                        // kick off whitespace in line
                        switch (line.trim()) {
                            // find price part
                            case "price:":
                                double price = Double.parseDouble(reader.readLine());
                                pie.setPrice(price);
                                break;
                            //find ingredients part
                            case "ingredients:":
                                // read ingredients until whitespace line or file end
                                String ingredientLine;
                                while (true) {
                                    ingredientLine = reader.readLine();
                                    // a null line means the end of the file
                                    if (ingredientLine == null) {
                                        classifyPie(meatPies, vegetarianPies, veganPies, pie);
                                        continue a;
                                    }
                                    // if the line is not whitespace line
                                    if (!ingredientLine.trim().isEmpty()) {
                                        // extract ingredient's name and type
                                        Matcher matcher = pattern.matcher(ingredientLine);
                                        Ingredient ingredient;
                                        // if ingredient is v or vv
                                        if (matcher.find()) {
                                            String rawName = matcher.group(1);
                                            String type = matcher.group(2);
                                            if ("vv".equals(type)) {
                                                ingredient = new Ingredient(rawName, true, true);
                                            } else {
                                                ingredient = new Ingredient(rawName, true, false);
                                            }
                                            // ingredient is neither v nor vv
                                        } else {
                                            ingredient = new Ingredient(ingredientLine);
                                        }
                                        ingredients.add(ingredient);
                                        // whitespace line after ingredients means the end of one kind of pie
                                    } else {
                                        classifyPie(meatPies, vegetarianPies, veganPies, pie);
                                        continue a;
                                    }
                                }
                        }
                    }
                }
            }

            // print out different types of pies in different parts
            writer.write("# Meat" + System.lineSeparator());
            for (Pie meatPie : meatPies) {
                // format pies content and write into file
                writer.write(meatPie.printf());
            }
            writer.write("# Vegetarian" + System.lineSeparator());
            for (Pie vegetarianPie : vegetarianPies) {
                writer.write(vegetarianPie.printf());
            }
            writer.write("# Vegan" + System.lineSeparator());
            for (Pie veganPie : veganPies) {
                writer.write(veganPie.printf());
            }
            System.out.println("please check your new menu 'newMenu.md' !");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("create new Menu error");
        } finally {
            try {
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * put pie into according list
     *
     * @param meatPies       list1
     * @param vegetarianPies list2
     * @param veganPies      list3
     * @param pie            pie
     */
    private void classifyPie(ArrayList<Pie> meatPies, ArrayList<Pie> vegetarianPies, ArrayList<Pie> veganPies, Pie pie) {
        if (pie.isVegan()) {
            veganPies.add(pie);
        } else if (pie.isVegetarian()) {
            vegetarianPies.add(pie);
        } else {
            meatPies.add(pie);
        }
    }

    /**
     * create file reader
     *
     * @param menuPath file path
     * @return reader stream
     */
    private BufferedReader getBufferedReader(String menuPath) {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(menuPath)));
        } catch (FileNotFoundException e) {
            System.err.println("create reader failed");
            System.exit(0);
        }
        return null;
    }

    /**
     * create file writer
     *
     * @return writer stream
     */
    private BufferedWriter getBufferedWriter() {
        BufferedWriter writer = null;
        try {
            // create write file
            File file = new File("newMenu.md");
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        } catch (IOException e) {
            System.err.println("create writer failed");
            System.exit(0);
        }
        return writer;
    }


    public void formatMenu2(String menuPath) {

        // create file reader and writer
        BufferedWriter writer = getBufferedWriter();
        EasyReader easyReader = new EasyReader(menuPath);

        // create three types of pie lists
        ArrayList<Pie> meatPies = new ArrayList<>();
        ArrayList<Pie> vegetarianPies = new ArrayList<>();
        ArrayList<Pie> veganPies = new ArrayList<>();

        try {
            Pie pie = new Pie();
            while (true) {
                String line = easyReader.readString();
                if (easyReader.eof()) {
                    break;
                }
                if ("name:".equals(line)) {
                    pie.setName(easyReader.readString());
                }
                if ("price:".equals(line)) {
                    pie.setPrice(Double.valueOf(easyReader.readLine()));
                }
                if ("ingredients:".equals(line)) {
                    ArrayList<Ingredient> ingredients = new ArrayList<>();
                    while (true) {
                        line = easyReader.readLine();
                        if ("".equals(line)) {
                            pie.setIngredients(ingredients);
                            meatPies.add(pie);
                            pie = new Pie();
                            break;
                        }
                        Ingredient ingredient = new Ingredient(line);
                        ingredients.add(ingredient);
                    }
                }
            }


            // print out different types of pies in different parts
            writer.write("# Meat" + System.lineSeparator());
            for (Pie meatPie : meatPies) {
                // format pies content and write into file
                writer.write(meatPie.printf());
            }
            writer.write("# Vegetarian" + System.lineSeparator());
            for (Pie vegetarianPie : vegetarianPies) {
                writer.write(vegetarianPie.printf());
            }
            writer.write("# Vegan" + System.lineSeparator());
            for (Pie veganPie : veganPies) {
                writer.write(veganPie.printf());
            }
            System.out.println("please check your new menu 'newMenu.md' !");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("create new Menu error");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
