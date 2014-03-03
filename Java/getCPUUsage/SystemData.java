import java.io.*;
public class SystemData
{
    static String cmdTop = "top -n 2 -b -d 0.2";
    // returns user cpu usage 
    public static double getCpu()
    {
        double cpu = -1;
        try
        {
            // start up the command in child process
            String cmd = cmdTop;
            Process child = Runtime.getRuntime().exec(cmd);

            // hook up child process output to parent
            InputStream lsOut = child.getInputStream();
            InputStreamReader r = new InputStreamReader(lsOut);
            BufferedReader in = new BufferedReader(r);

            // read the child process' output
            String line;
            int emptyLines = 0;
            while(emptyLines<3)
            {
                line = in.readLine();
                if (line.length()<1) emptyLines++;
            }
            in.readLine();
            in.readLine();
            line = in.readLine();
            System.out.println("Parsing line "+ line);
            String delims = "%";
            String[] parts = line.split(delims);
            System.out.println("Parsing fragment " + parts[0]);
            delims =" ";

            parts = parts[0].split(delims);
            cpu = Double.parseDouble(parts[parts.length-1]);
        }
        catch (Exception e)
        { // exception thrown
            System.out.println("Command failed!");
        }
        return cpu;
    }

    public static void main(String argv[]) {
        for (int i = 0; i < 10; i++) {
            SystemData.getCpu();
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
