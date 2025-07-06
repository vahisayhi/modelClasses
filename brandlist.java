import java.util.*;
import java.io.*;

public class BrandList extends ArrayList<Brand> {
    public boolean loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String id = parts[0];
                String name = parts[1];
                String[] sub = parts[2].split(": ");
                String sound = sub[0];
                double price = Double.parseDouble(sub[1]);
                this.add(new Brand(id, name, sound, price));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Brand b : this) {
                pw.println(b.toString());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int searchID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equals(ID)) return i;
        }
        return -1;
    }

    public Brand getUserChoice() {
        Menu menu = new Menu();
        return menu.ref_getChoice(this);
    }

    public void addBrand() {
        Scanner sc = new Scanner(System.in);
        String id, name, sound;
        double price;
        while (true) {
            System.out.print("Enter brand ID: ");
            id = sc.nextLine().trim();
            if (searchID(id) == -1) break;
            System.out.println("Duplicate ID!");
        }
        do {
            System.out.print("Enter brand name: ");
            name = sc.nextLine().trim();
        } while (name.isEmpty());

        do {
            System.out.print("Enter sound brand: ");
            sound = sc.nextLine().trim();
        } while (sound.isEmpty());

        do {
            System.out.print("Enter price: ");
            price = Double.parseDouble(sc.nextLine());
        } while (price <= 0);

        this.add(new Brand(id, name, sound, price));
    }

    public void updateBrand() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter brand ID to update: ");
        String id = sc.nextLine().trim();
        int pos = searchID(id);
        if (pos < 0) {
            System.out.println("Not found!");
            return;
        }
        String name, sound;
        double price;
        do {
            System.out.print("Enter new brand name: ");
            name = sc.nextLine().trim();
        } while (name.isEmpty());
        do {
            System.out.print("Enter new sound brand: ");
            sound = sc.nextLine().trim();
        } while (sound.isEmpty());
        do {
            System.out.print("Enter new price: ");
            price = Double.parseDouble(sc.nextLine());
        } while (price <= 0);
        Brand b = this.get(pos);
        b.setBrandName(name);
        b.setSoundBrand(sound);
        b.setPrice(price);
    }

    public void listBrands() {
        for (Brand b : this) {
            System.out.println(b);
        }
    }
}
