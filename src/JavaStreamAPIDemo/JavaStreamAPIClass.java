package JavaStreamAPIDemo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaStreamAPIClass {

	public static void main(String[] args) {
		System.out.println("Inside java Strema API class");
		List<Person> people = getPerson();
		System.out.println("person list is:" + people);
		// now going to operations on list i.e person without using stream api.

		List<Person> females = new LinkedList<>();

		for (Person person : people) {
			if (person.getGender().equals(Gender.FEMALE)) {
				females.add(person);
			}
		}
		// System.out.println("Females list is "+females);

		// now going to operate on list using Stream API.
		// 1.Filter
		females = new LinkedList<>();
		females = people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
				.collect(Collectors.toList());
		// System.out.println("Females list from filter is "+females);

		// 2. Sort
		females = new LinkedList<>();
		females = people.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender))
				.collect(Collectors.toList());
		// System.out.println("Sorted data on age"+ females);

		// 3. All match
		females = new LinkedList<>();
		boolean allMatch = people.stream().allMatch(person -> person.getAge() >= 10);
		// System.out.println(allMatch);

		// 4. Any Match
		boolean anyMatch = people.stream().anyMatch(person -> person.getAge() < 18);
		// System.out.println(anyMatch);

		// 5. None Match
		boolean noneMatch = people.stream().noneMatch(person -> person.getName().endsWith("ZAXC"));
		System.out.println(noneMatch);

		// 6. Max
		people.stream().max(Comparator.comparing(Person::getAge)).ifPresent(System.out::println);

		// 7. Min
		people.stream().min(Comparator.comparing(Person::getAge)).ifPresent(System.out::println);

		// 8. Group
		Map<Gender, List<Person>> group = people.stream().collect(Collectors.groupingBy(Person::getGender));
		System.out.println(group);
	}

	private static List<Person> getPerson() {
		// List<Person> person=new LinkedList<>();
		return List.of(new Person("Deepak", 24, Gender.MALE), new Person("Shivam", 23, Gender.FEMALE),
				new Person("Pardeep", 21, Gender.MALE), new Person("Sidharth", 23, Gender.MALE),
				new Person("Shubham", 24, Gender.FEMALE), new Person("Ganapati", 10, Gender.FEMALE),
				new Person("Amarnath", 24, Gender.MALE), new Person("Shibu", 26, Gender.FEMALE));
	}

}
