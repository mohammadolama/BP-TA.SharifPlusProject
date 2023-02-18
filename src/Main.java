import view.Cli;
import Managers.LogicalAgent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        LogicalAgent logicalAgent=new LogicalAgent();
        Cli cli=new Cli();
        logicalAgent.setCli(cli);
        cli.setLogicalAgent(logicalAgent);
        cli.start();
    }


}
