import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;

public abstract class AbstractFileWriter {

    protected @NotNull
    FileWriter Writer;

    abstract int getN();

    protected void writeToFile(String str)
    {
        try {
            Writer.write("<a> " + getN() + " " + str + " </a> \n");
            Writer.flush();
        }catch (Exception e){

        }
    }

    protected void initWriter(String nameFile)
    {
        try {
            Writer = new FileWriter(nameFile, false) ;
        }catch (Exception e){
            Writer = null;
        }
    }
}
