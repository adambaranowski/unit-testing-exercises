package pl.adambaranowski.testing.order;

import pl.adambaranowski.testing.order.Order;

import java.io.*;

public class OrderBackup {

    private Writer fileWriter;

    public void createFile() throws FileNotFoundException {
        File file = new File("orderBackup.txt");
        FileOutputStream os = new FileOutputStream(file);
        OutputStreamWriter ow = new OutputStreamWriter(os);
        fileWriter = new BufferedWriter(ow);
    }

    public void closeFile() throws IOException {
        fileWriter.close();
    }

    public void backupOrder(Order order) throws IOException {
        fileWriter.append(order.toString());
    }
}
