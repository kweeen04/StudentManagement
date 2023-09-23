import java.util.ArrayList;

import controller.Validation;
import model.Student;
import view.Menu;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> ls = new ArrayList<>();
        Validation validation = new Validation();
        ls.add(new Student("1", "Tran Duy Thai", "Fall23", "java"));
        ls.add(new Student("2", "Linh Kin Park", "Summer23", ".net"));
        ls.add(new Student("3", "Hong Khanh Linh", "Spring23", "c/c++"));
        ls.add(new Student("4", "Ngo Minh Huyen", "Fall22", "java"));
        int count = 4;
        while (true) {
            Menu.menu();
            int choice = Validation.checkInput(1, 5);
            switch (choice) {
                case 1:
                    Menu.createStudent(count, ls);
                    break;
                case 2:
                    Menu.findSort(ls);
                    break;
                case 3:
                    Menu.updateDelete(count, ls);
                    break;
                case 4:
                    Menu.report(ls);
                    break;
                case 5:
                    return;
            }
        }
    }
}
