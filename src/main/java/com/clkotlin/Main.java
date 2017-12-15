package com.clkotlin;
import source.CustomLog;
import utils.CLUtilsKt;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        CustomLog customLog = new CustomLog();
        customLog.logHelloWorld();

//        CLI cli;

        try
        {
//            cli = new CLI(args);
//            CLUtilsKt.readFileWithPath(cli.getFilePath());
            CLUtilsKt.readFileWithPath("poker.txt");
        }
        catch (IllegalArgumentException e)
        {
            handleException(e, 1);
            return;
        }
    }

    private static void handleException(Exception e, int exitCode)
    {
        e.printStackTrace();
        System.out.println(e.getMessage());
        System.exit(exitCode);
    }
}
