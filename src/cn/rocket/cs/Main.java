package cn.rocket.cs;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("--help") || args[0].equals("-?")) {
            System.out.println("CreateSequence.jar range:int|start:int,end:int|-?|--help [-r|--reverse]\n" +
                    "range\t\t\tCreate a sequence from 1 to range.\n" +
                    "start,end\t\tCreate sequence from start to end.\n" +
                    "-?|--help|\t\tPrint this help info.\n" +
                    "-r|--reverse\tCreate reverse sequence when generating even one.\n" +
                    "e.g. CreateSequence.jar 7\n" +
                    "-> 1,3,5,7\n" +
                    "2,4,6");
            return;
        }
        int start, end;
        if (args[0].contains(",")) {
            String[] range = args[0].split(",");
            start = Integer.parseInt(range[0]);
            end = Integer.parseInt(range[1]);
        } else {
            start = 1;
            end = Integer.parseInt(args[0]);
        }
        try {
            if (start < 1 || start >= end)
                throw new IllegalArgumentException("Incorrect range or start or end parameter.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = (start & 1) == 0 ? start + 1 : start; i <= end; i += 2)
            sb.append(i).append(",");
        sb.deleteCharAt(sb.length() - 1);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(sb.toString()), null);
        System.out.println("Odd sequence has copied to the clipboard. Input a \\n to continue.");
        new Scanner(System.in).nextLine();
        sb = new StringBuilder();
        if (args.length == 2 && (args[1].equals("-r") || args[1].equals("--reverse")))
            for (int i = (end & 1) == 0 ? end : end - 1; i >= start; i -= 2)
                sb.append(i).append(",");
        else
            for (int i = (start & 1) == 0 ? start : start + 1; i <= end; i += 2)
                sb.append(i).append(",");
        sb.deleteCharAt(sb.length() - 1);
        clipboard.setContents(new StringSelection(sb.toString()), null);
        System.out.println("Even sequence has copied to the clipboard.");
    }
}
