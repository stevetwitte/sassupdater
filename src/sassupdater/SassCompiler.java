package sassupdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Singleton for running sass commands on files
 * TODO: Better way of doing this?
 */
public class SassCompiler {
    private static SassCompiler sassCompiler = new SassCompiler();

    private SassCompiler() {}

    public static SassCompiler getInstance() {
        return sassCompiler;
    }

    /**
     * Runs the sass --update command with the provided SassFile
     * TODO: set the sass location, and options from config
     * @param sassFile SassFile
     * @throws IOException
     */
    protected String[] updateSassFile(SassFile sassFile) throws IOException {
        String[] command = { "/home/stephen/.rbenv/shims/sass", "--no-cache", "--update", sassFile.sassfilename.getAbsoluteFile().toString() + ":" + sassFile.cssfilename.getAbsoluteFile().toString() };
        Process process = new ProcessBuilder(command).start();
        InputStream stream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        StringBuilder builder = new StringBuilder();
        String line;
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        String result = builder.toString();

        /**
         * TODO: Update view with result
         */
        System.out.println(Arrays.toString(command));
        System.out.println(result);

        return new String[] { Arrays.toString(command), result };
    }

    /**
     * Runs the sass --force command with the provided SassFile
     * TODO: set the sass location, and options from config
     * @param sassFile SassFile
     * @throws IOException
     */
    protected String[] forceSassFile(SassFile sassFile) throws IOException {
        String[] command = { "/home/stephen/.rbenv/shims/sass", "--no-cache", "--update", "--force", sassFile.sassfilename.getAbsoluteFile().toString() + ":" + sassFile.cssfilename.getAbsoluteFile().toString() };
        Process process = new ProcessBuilder(command).start();
        InputStream stream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        StringBuilder builder = new StringBuilder();
        String line;
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        String result = builder.toString();

        /**
         * TODO: Update view with result
         */
        System.out.println(Arrays.toString(command));
        System.out.println(result);

        return new String[] { Arrays.toString(command), result };
    }

}