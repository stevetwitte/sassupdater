package sassupdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Singleton for running sass commands on files
 * TODO: Better way of doing this?
 */
public class SassCompiler {
    private static SassCompiler sassCompiler = new SassCompiler();
    private String sassFileLocation;

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
        String force = null;

        if (Main.getSassLocation() == null) {
            return new String[] {"Sass Gem Location Unset in Config"};
        }

        String[] command = { Main.getSassLocation(), "--no-cache", "--update", sassFile.sassfilename.getAbsoluteFile().toString() + ":" + sassFile.cssfilename.getAbsoluteFile().toString() };
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

        if (result.endsWith("\n")) {
            result = result.substring(0, result.length() - 2);
        }

        String prettyCommand = "Updating " + sassFile.sassfilename.getName();

        if (result.equals("")) {
            result = "No changes detected.";
        }

        return new String[] { prettyCommand, "Output:", result };
    }

    /**
     * Runs the sass --force command with the provided SassFile
     * TODO: set the sass location, and options from config
     * @param sassFile SassFile
     * @throws IOException
     */
    protected String[] forceSassFile(SassFile sassFile) throws IOException {
        if (Main.getSassLocation().equals("")) {
            return new String[] {"Sass Gem Location Unset in Config"};
        }

        String[] command = { Main.getSassLocation(), "--no-cache", "--update", "--force", sassFile.sassfilename.getAbsoluteFile().toString() + ":" + sassFile.cssfilename.getAbsoluteFile().toString() };
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

        if (result.endsWith("\n")) {
            result = result.substring(0, result.length() - 2);
        }

        String prettyCommand = "Force updating " + sassFile.sassfilename.getName();

        return new String[] { prettyCommand, "Output:", result };
    }

}