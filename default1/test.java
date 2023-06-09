package default1;
import java.util.Scanner;
public class test{
	public static void main(String[] args) {
		TreeSet<Integer> tree=new TreeSet<>();
		tree.add(2);
		//добавляем элемент, в данном случае - число
		System.out.println(tree.contains(2));
		//если вывод будет true - элемент в дереве есть, если false - его нету в дереве
		tree.remove(2);
		//удаляем это число
		System.out.println(tree.contains(2));
		//тут должен быть false, т.к. такого элемента нету в дереве
	}
}