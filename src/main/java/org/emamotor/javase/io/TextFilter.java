package org.emamotor.javase.io;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * @author Yoshimasa Tanabe
 */
public class TextFilter extends FileFilter {

    public boolean accept(File file) {
        if (file == null) return false;
        if (file.isDirectory()) return true;

        String ext = getExtension(file);
        if (ext != null && ext.equals("txt")) {
            return true;
        } else {
            return false;
        }
    }

    private String getExtension(File file) {
        if (file == null) return null;

        String name = file.getName();
        int period = name.lastIndexOf('.');
        if (period > 0 && period < name.length() - 1) {
            return name.substring(period + 1).toLowerCase();
        } else {
            return null;
        }
    }

    @Override
    public String getDescription() {
        return "Text File(*.java)";
    }

}
