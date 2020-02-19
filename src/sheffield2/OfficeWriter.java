package sheffield2;

import java.io.*;

public class OfficeWriter
{
    public static void main(String[] args)
    {
        String name = "你好";
        int officeNum = 141;
        try
        {
            // open using append and autoflush modes
            PrintWriter outfile = new PrintWriter(new FileWriter("sheffield2/office.txt",
                    true), true);
            outfile.print(name);
            outfile.print(": ");
            outfile.println(officeNum);
            outfile.close();
        } catch (IOException e)
        {
            System.out.println(e);
            System.exit(1);
        }
    }
}
