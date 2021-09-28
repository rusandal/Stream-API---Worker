import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        File file = new File("result.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);

        List<String> surnameList = persons.stream()
                .filter(person -> person.getAge()>=18 && 27 >= person.getAge())
                .filter(person -> person.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                //.map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println(surnameList);

        List<Person> personList = persons.stream()
                .filter(person -> person.getAge()>=18 && person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex().equals(Sex.MAN) && person.getAge()<65) || (person.getSex().equals(Sex.WOMAN) && person.getAge()<60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(personList);
        /*fileOutputStream.write(personList.toString().getBytes(StandardCharsets.UTF_8));
        fileOutputStream.close();*/
    }
}
